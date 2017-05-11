package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.tabela.EfdTabela4315Entity;
import dc.servicos.dao.geral.tabela.IEfdTabela4315DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.EfdTabela4315FormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class EfdTabela4315FormController extends
		CRUDFormController<EfdTabela4315Entity> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EfdTabela4315FormView subView;

	@Autowired
	private IEfdTabela4315DAO efdTabela4315DAO;

	private EfdTabela4315Entity currentBean;

	@Override
	protected String getNome() {
		return "EFD Tabela 4315";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());
		subView.getDtFimVigencia().setValue(currentBean.getFimVigencia());
		subView.getDtInicioVigencia().setValue(currentBean.getInicioVigencia());
		try {
			efdTabela4315DAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = efdTabela4315DAO.find(id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getTxtObservacao().setValue(currentBean.getObservacao());
		subView.getDtInicioVigencia().setValue(currentBean.getInicioVigencia());
		subView.getDtFimVigencia().setValue(currentBean.getFimVigencia());
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
		subView = new EfdTabela4315FormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new EfdTabela4315Entity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		efdTabela4315DAO.deleteAllByIds(ids);
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
		return "efdTabela4315Form";
	}

	@Override
	public EfdTabela4315Entity getModelBean() {
		return currentBean;
	}

}