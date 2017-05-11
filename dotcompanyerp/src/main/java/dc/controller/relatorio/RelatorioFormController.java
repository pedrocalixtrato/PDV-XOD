package dc.controller.relatorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Component;

import dc.controller.administrativo.empresa.EmpresaListController;
import dc.controller.administrativo.seguranca.PapelListController;
import dc.controller.administrativo.seguranca.UsuarioListController;
import dc.controller.sistema.SeguimentoListController;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.PapelEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.SeguimentoEntity;
import dc.entidade.relatorio.Relatorio;
import dc.model.dao.administrativo.seguranca.IUsuarioDAO;
import dc.servicos.dao.administrativo.empresa.IEmpresaDAO;
import dc.servicos.dao.framework.geral.IFmMenuDAO;
import dc.servicos.dao.framework.geral.ISeguimentoDAO;
import dc.servicos.dao.relatorio.IRelatorioDAO;
import dc.servicos.dao.sistema.IPapelDAO;
import dc.servicos.util.Util;
import dc.servicos.util.Validator;
import dc.visao.framework.FmMenuListController;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.relatorio.RelatorioFormView;

@Controller
@Scope("prototype")
public class RelatorioFormController extends CRUDFormController<Relatorio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RelatorioFormView subView;

	@Autowired
	private IRelatorioDAO relatorioDAO;

	private Relatorio currentBean;

	@Autowired
	private IFmMenuDAO fmMenuDAO;

	@Autowired
	private ISeguimentoDAO seguimentoDAO;
	@Autowired
	private IPapelDAO papelDAO;
	@Autowired
	private IUsuarioDAO usuarioDAO;
	@Autowired
	private IEmpresaDAO empresaDAO;

	@Override
	protected String getNome() {
		return "Relatório";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	@Transactional
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			// currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			gravarAnexo();
			relatorioDAO.salvar(currentBean, subView.getUsuarioContainer().getItemIds(), subView.getSeguimentoContainer().getItemIds(), subView
					.getPapelContainer().getItemIds(), subView.getEmpresaContainer().getItemIds());

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	protected void carregar(Serializable id) {
		currentBean = relatorioDAO.find(id);

		subView.preencheForm(currentBean);

		if (currentBean.getJasperPath() != null) {
			subView.getBtnDownload().setVisible(true);
			StreamResource myResource = createResource();
			if (myResource != null) {
				FileDownloader fileDownloader = new FileDownloader(myResource);
				fileDownloader.extend(subView.getBtnDownload());
			}
		} else {
			subView.getBtnDownload().setVisible(false);
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new RelatorioFormView();

		DefaultManyToOneComboModel<FmMenu> menusModel = new DefaultManyToOneComboModel<FmMenu>(FmMenuListController.class, this.fmMenuDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "caption";
			}
		};

		DefaultManyToOneComboModel<Relatorio> relatoriosModel = new DefaultManyToOneComboModel<Relatorio>(RelatorioListController.class,
				this.relatorioDAO, super.getMainController(), true);

		DefaultManyToOneComboModel<SeguimentoEntity> seguimentoModel = new DefaultManyToOneComboModel<SeguimentoEntity>(SeguimentoListController.class,
				this.seguimentoDAO, super.getMainController(), true);

		DefaultManyToOneComboModel<UsuarioEntity> usuarioModel = new DefaultManyToOneComboModel<UsuarioEntity>(UsuarioListController.class, this.usuarioDAO,
				super.getMainController(), true) {
			@Override
			public String getCaptionProperty() {
				return "login";
			}
		};
		DefaultManyToOneComboModel<PapelEntity> papelModel = new DefaultManyToOneComboModel<PapelEntity>(PapelListController.class, this.papelDAO,
				super.getMainController(), true);

		DefaultManyToOneComboModel<EmpresaEntity> empresa = new DefaultManyToOneComboModel<EmpresaEntity>(EmpresaListController.class, this.empresaDAO,
				super.getMainController(), true) {
			@Override
			public String getCaptionProperty() {
				return "nomeFantasia";
			}
		};

		subView.getComboMenus().setModel(menusModel);
		subView.getComboSeguimentos().setModel(seguimentoModel);
		subView.getComboEmpresas().setModel(empresa);
		subView.getComboPapeis().setModel(papelModel);
		subView.getComboUsuarios().setModel(usuarioModel);
		subView.getComboRelatorios().setModel(relatoriosModel);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new dc.entidade.relatorio.Relatorio();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		relatorioDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;
		if (!Validator.validateString(subView.getTxNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxNome(), "Não pode ficar em branco");
			valido = false;
		}

		if(!Validator.validateObject(subView.getComboMenus().getValue())){
			adicionarErroDeValidacao(subView.getComboMenus(), "Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "relatorioForm";
	}

	private void gravarAnexo() throws IOException {
		File relatorioForm = (File) subView.getRelatorioUpload().getValue();

		if (relatorioForm != null) {
			String homePath = System.getProperty("user.home");
			String customCompanyBaseFolder = "dc-erp/reports";
			String arqOriginal = subView.getNomeRelatorio();

			String caminho = homePath + "/" + customCompanyBaseFolder + "/" + currentBean.getMenu().getUrlId() + "/" + arqOriginal;

			Util.copyFile(relatorioForm, new File(caminho));
			currentBean.setJasperPath(caminho);

			relatorioForm.delete();
		}
	}

	public StreamResource createResource() {
		return new StreamResource(new StreamSource() {
			@Override
			public InputStream getStream() {
				try {
					return new FileInputStream(currentBean.getJasperPath());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}, getFileName(currentBean.getJasperPath()));
	}

	private String getFileName(String jasperPath) {
		String[] split = jasperPath.split("\\\\|/");
		return split[split.length - 1];
	}

	@Override
	public Relatorio getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
