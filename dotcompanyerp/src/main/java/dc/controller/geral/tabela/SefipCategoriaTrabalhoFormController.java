package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.tabela.SefipCategoriaTrabalhoEntity;
import dc.servicos.dao.geral.tabela.ISefipCategoriaTrabalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.SefipCategoriaTrabalhoFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class SefipCategoriaTrabalhoFormController extends
		CRUDFormController<SefipCategoriaTrabalhoEntity> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SefipCategoriaTrabalhoFormView subView;

	@Autowired
	private ISefipCategoriaTrabalhoDAO sefipDAO;

	private SefipCategoriaTrabalhoEntity currentBean;

	@Override
	protected String getNome() {
		return "Categoria de Trabalho - SEFIP";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		currentBean.setNome(subView.getTxtNome().getValue());
		try {
			sefipDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = sefipDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
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
		subView = new SefipCategoriaTrabalhoFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new SefipCategoriaTrabalhoEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		sefipDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (subView.getTxtNome().getValue() == null
				|| subView.getTxtNome().getValue().isEmpty()) {
			// Utilizar adicionarErroDeValidacao() para adicionar mensagem de
			// erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtNome(),
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
		return "sefipForm";
	}

	@Override
	public SefipCategoriaTrabalhoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}