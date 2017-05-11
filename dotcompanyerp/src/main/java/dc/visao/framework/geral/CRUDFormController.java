package dc.visao.framework.geral;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.AbstractModel;
import dc.entidade.relatorio.Relatorio;
import dc.entidade.relatorio.TipoRelatorio;
import dc.framework.DcConstants;
import dc.servicos.dao.framework.geral.IFmMenuDAO;
import dc.servicos.dao.relatorio.IRelatorioDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.spring.SecuritySessionProvider;

/**
 * @author Wesley Jr /* Nessa classe temos a configuração da Tela, todos os
 *         controllers. Essa é a classe principal. Temos a configuração dos
 *         botões SALVAR, CRIAR Tem o Método também do CARREGAR, que pega as
 *         informações contida na Tela, que está salvo no Banco de Dados
 */

public abstract class CRUDFormController<E extends AbstractModel> extends
        ControllerTask implements Controller, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6944317085399570143L;

    CRUDFormView view;


    @Autowired
    private MainController mainController;

    private CRUDListController listController;

    private Map<String, AbstractComponent> validatableComponents;

    private boolean novo;

    private boolean newAttemptOpen;
    private boolean changed = false;
    private ChangeListener changeListener = new ChangeListener();

    @Autowired
    private IRelatorioDAO relatorioDAO;
    @Autowired
    private IFmMenuDAO fmMenuDAO;

    @Autowired(required = true)
    private transient ApplicationContext applicationContext;

    /**
     * FieldGroup associando os campos do formulários às propriedades do modelo de dados.
     */
    public DCFieldGroup<E> fieldGroup = null;

    @PostConstruct
    public void init() {
        validatableComponents = new HashMap<String, AbstractComponent>();

        initSubView();

        // Configura Titulo
        view = new CRUDFormView();
        // view.getLblNome().setContentMode(ContentMode.HTML);
        view.getLblNome().setValue(getNome());

        // Configura Conteudo

        Layout panel = view.getPnlContent();

        if (isFullSized()) {
            panel.setHeight("100%");
        }

        panel.addComponent(getSubView());

		/*
		 * Panel p = view.getPnlContent(); if(isFullSized()){
		 * view.setHeight("100%"); p.setHeight("100%"); }
		 * p.setContent(getSubView());
		 */

        // Configura Botoes;
        view.getBtnSalvar().addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                limpaValidacoes();
                if (validaSalvar()) {
                    if (isNovo()) {
                        if (listController.autoriaCriacao()) {
                            actionSalvar();
                            changed = false;
                        } else {
                            mensagemErro(DcConstants.PERMISSAO_NEGADA);
                        }
                    } else {
                        if (listController.autoriaAlteracao()) {
                            actionSalvar();
                            changed = false;
                        } else {
                            mensagemErro(DcConstants.PERMISSAO_NEGADA);
                        }
                    }
                } else {
                    mensagemErro(DcConstants.OPERATION_SAVE_NOT_OK);
                }
            }
        });

        view.getBtnNovo().addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                init();
                if (!isOnSeparateWindow()) {
                    mainController.showTaskableContent(CRUDFormController.this);
                } else {
                    listController.showOnWindow(view);

                }
                criarNovo();
            }
        });

        view.getBtnCancelar().addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {

                if (changed) {
                    confirmClose();
                } else {
                    closeFormTaskOrWindow();
                }
            }
        });
    }

    private void configuraBotaoRelatorio() {
        List<Relatorio> relatorios = relatorioDAO
                .findRelatoriosByMenuAndUserAndType(
                        fmMenuDAO.getMenu(this.getListController().getClass()
                                .getName()),
                        SecuritySessionProvider.getUsuario(),
                        TipoRelatorio.FORMULARIO);

        if (relatorios != null && relatorios.size() > 0) {
            RelatorioMenuBuilder builder = new RelatorioMenuBuilder();
            MenuBar relatorioMenu = builder.buildRelatorioMenu(relatorios,
                    this.listController, applicationContext);
            view.getHorizontalLayout_3().addComponent(relatorioMenu, 0);
            view.getHorizontalLayout_3().setComponentAlignment(relatorioMenu,
                    new Alignment(34));
        }
    }

    private void closeFormTaskOrWindow() {
        if (!isOnSeparateWindow()) {
            Task parent = listController;
            mainController.removeTask(CRUDFormController.this, false);
            mainController.showTaskableContent(parent);
            listController.doSearch(((CRUDListView) listController.getView())
                    .getTxtPesquisa().getValue());
        } else {
            close();
        }
    }

    public void confirmClose() {
        ConfirmDialog.show(MainUI.getCurrent(), "Tem certeza que deseja sair?",
        		"Você não salvou nenhuma de suas alterações. ", "Sim", "Não",
                new ConfirmDialog.Listener() {
        	
        	      private static final long serialVersionUID = 1L;
        	
                    public void onClose(ConfirmDialog dialog) {
                        if (dialog.isConfirmed()) {
                            newAttemptOpen = false;
                            closeFormTaskOrWindow();
                        }
                    }
                });
    }

    private boolean isOnSeparateWindow() {
        return listController.isOnSeparateWindow();
    }

    private void close() {
        if (hasNewAttemptOpen()) {
            confirmClose();
        } else {
            listController.closeWindow();
        }
    }

    protected boolean isFullSized() {
        return false;
    }

    /** Utilizado para limpar validacoes nos componentes VAADIN */
    protected void limpaValidacoes() {
        Iterator<String> it = validatableComponents.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            AbstractComponent c = validatableComponents.get(key);
            c.setComponentError(null);
        }
    }

    /*
     * Classes que estendem CRUDFormController devem implementar validaãoes para
     * salvar(novo e alterar) nesse metodo
     */
    protected abstract boolean validaSalvar();

    protected abstract void criarNovoBean();

    protected abstract void initSubView();

    public void mostrar(Serializable id) {
        this.novo = false;
        carregar(id);
        addChangeListeners();
        // TODO Exibir só os que o user tiver permissão
        configuraBotaoRelatorio();
    }

    public void criarNovo() {
        this.newAttemptOpen = true;
        this.novo = true;
        novo();
        addChangeListeners();
        // TODO Exibir só os que o user tiver permissão
        configuraBotaoRelatorio();
    }

    protected abstract void carregar(Serializable id);

    public void load(AbstractModel<Serializable> model) {
        if (model != null) {
            carregar(model.getId());
        }
    }

    protected abstract void actionSalvar();

    protected void novo() {
        criarNovoBean();
        quandoNovo();
    }

    public void notifiyFrameworkSaveOK(E obj) {
        newAttemptOpen = false;
        listController.notifySaved(obj);

        new Notification("Gravado!", "Registro gravado com sucesso",
                Notification.TYPE_HUMANIZED_MESSAGE, true).show(Page
                .getCurrent());
    }

    protected void quandoNovo() { };

    protected abstract Component getSubView();

    protected abstract String getNome();

    public Component getContent() {
        return view;
    }

    public void setListController(CRUDListController c) {
        this.listController = c;
    }

    public CRUDListController getListController() {
        return listController;
    }

    protected abstract void remover(List<Serializable> ids);

    protected abstract void removerEmCascata(List<Serializable> objetos);

    public void mensagemRemovidoOK() {
        new Notification(DcConstants.DELETE_TITLE_OK, DcConstants.DELETE_OK,
                Notification.TYPE_HUMANIZED_MESSAGE, true).show(Page
                .getCurrent());
    }

    public void mensagemErro(String message) {
        new Notification(DcConstants.ERROR_TITLE, message,
                Notification.TYPE_ERROR_MESSAGE, true).show(Page.getCurrent());
    }

    public void mensagemAtencao(String message) {
        new Notification(DcConstants.CAUTION_PLEASE, message,
                Notification.TYPE_WARNING_MESSAGE, true)
                .show(Page.getCurrent());
    }

    public View getView() {
        return view;
    }

    protected void adicionarErroDeValidacao(AbstractComponent c,
                                            String errorMessage) {
        validatableComponents.put(c.getId(), c);
        c.setComponentError(new UserError(errorMessage));
    }

    protected boolean estaVazio(TextField txtField) {
        if (txtField.getValue() == null || txtField.getValue().isEmpty()) {
            adicionarErroDeValidacao(txtField, "Não pode ficar em branco");
            return true;
        }

        return false;
    }

    public boolean isNovo() {
        return novo;
    }

    @Override
    public String getControllerTitle() {
        return this.listController.getTitulo();
    }

    @Override
    public Controller getController() {
        return this;
    }

    public void setReadOnly(boolean readonly) {
        view.getBtnNovo().setVisible(!readonly);
    }

    public void setEnabled(boolean enabled) {
        view.getBtnNovo().setEnabled(enabled);
        view.getBtnSalvar().setEnabled(enabled);
    }

    @Override
    public Task getParent() {
        return listController;
    }

    public boolean hasNewAttemptOpen() {
        return this.newAttemptOpen;
    }

    @Override
    public void dispose() {
        // view = null;
        // validatableComponents = null;
        // listController = null;
    }

    protected MainController getMainController() {
        return mainController;
    }

    @Override
    public void setChildModuleID(String id) {
        // nothing for now
    }

    public EmpresaEntity empresaAtual() {
        return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
    }

    private void addChangeListeners() {
    	changed = false;
        percorreComponentes(view);

    }

    private void percorreComponentes(HasComponents parentComponent) {
        Iterator<Component> iterator = parentComponent.iterator();
        while (iterator.hasNext()) {
            Component component = (Component) iterator.next();
            if (component instanceof AbstractField) {
                @SuppressWarnings("rawtypes")
                AbstractField field = (AbstractField) component;
                field.addValueChangeListener(changeListener);
            } else if (component instanceof HasComponents) {
                percorreComponentes((HasComponents) component);
            }

        }

    }

    public abstract E getModelBean();

    class ChangeListener implements ValueChangeListener {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void valueChange(ValueChangeEvent event) {
            changed = true;
        }

    }

}