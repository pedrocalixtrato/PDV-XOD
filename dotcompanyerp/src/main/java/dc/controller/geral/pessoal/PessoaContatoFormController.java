package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.PessoaContatoEntity;
import dc.servicos.dao.geral.PessoaContatoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.PessoaContatoFormView;

@Controller
@Scope("prototype")
public class PessoaContatoFormController extends
		CRUDFormController<PessoaContatoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PessoaContatoFormView subView;

	@Autowired
	PessoaContatoDAO pessoaContatoDAO;

	private PessoaContatoEntity currentBean;

	@Override
	protected String getNome() {
		return "Pessoa Contato";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		String nome = subView.getTxtNome().getValue();
		String email = subView.getTxtEmail().getValue();
		String foneComercial = subView.getTxtFoneComercial().getValue();
		String foneResidencial = subView.getTxtFoneResidencial().getValue();
		String foneCelular = subView.getTxtFoneCelular().getValue();
		currentBean.setNome(nome);
		currentBean.setEmail(email);
		currentBean.setFoneComercial(foneComercial);
		currentBean.setFoneResidencial(foneResidencial);
		currentBean.setFoneCelular(foneCelular);

		try {
			pessoaContatoDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = pessoaContatoDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtEmail().setValue(currentBean.getEmail());
		subView.getTxtFoneComercial().setValue(currentBean.getFoneComercial());
		subView.getTxtFoneResidencial().setValue(
				currentBean.getFoneResidencial());
		subView.getTxtFoneCelular().setValue(currentBean.getFoneCelular());
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
		subView = new PessoaContatoFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new PessoaContatoEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		pessoaContatoDAO.deleteAllByIds(ids);

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
		// TODO Auto-generated method stub

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public PessoaContatoEntity getModelBean() {
		return currentBean;
	}

}