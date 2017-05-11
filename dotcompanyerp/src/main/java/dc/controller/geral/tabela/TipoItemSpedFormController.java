package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.tabela.TipoItemSpedEntity;
import dc.servicos.dao.geral.tabela.ITipoItemSpedDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.TipoItemSpedFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class TipoItemSpedFormController extends
		CRUDFormController<TipoItemSpedEntity> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TipoItemSpedFormView subView;

	@Autowired
	private ITipoItemSpedDAO tipoItemSpedDAO;

	private TipoItemSpedEntity currentBean;

	@Override
	protected String getNome() {
		return "Tipo Item Sped";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		currentBean.setCodigo(subView.getTxtCodigo().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		try {
			tipoItemSpedDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoItemSpedDAO.find(id);
		subView.getTxtCodigo().setValue(currentBean.getCodigo());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new TipoItemSpedFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new TipoItemSpedEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoItemSpedDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (subView.getTxtDescricao().getValue() == null
				|| subView.getTxtDescricao().getValue().isEmpty()) {
			// Utilizar adicionarErroDeValidacao() para adicionar mensagem de
			// erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em Branco!");
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "tipoItemSpedForm";
	}

	@Override
	public TipoItemSpedEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}