package dc.controller.geral;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.Teste;
import dc.servicos.dao.geral.testeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.TesteFormView;

@Controller
@Scope("prototype")
public class TesteFormController extends CRUDFormController<Teste> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TesteFormView subView;

	@Autowired
	testeDAO testeDAO;

	private Teste currentBean;

	@Override
	protected String getNome() {
		return "Teste";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		String nome = subView.getTxtNome().getValue();
		String descricao = subView.getTxtDescricao().getValue();
		currentBean.setNome(nome);
		currentBean.setDescricao(descricao);

		try {
			testeDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = testeDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
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
		subView = new TesteFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new Teste();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		testeDAO.deleteAllByIds(ids);

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
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Teste getModelBean() {
		return currentBean;
	}

}