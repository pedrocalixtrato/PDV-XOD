package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.MarcaOsEntity;
import dc.servicos.dao.ordemservico.IMarcaOsDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.MarcaFormView;

/** @author Paulo Sérgio */

@Controller("ordemservicoMarcaFormController")
@Scope("prototype")
public class MarcaFormController extends CRUDFormController<MarcaOsEntity> {

	private static final long serialVersionUID = 1L;

	MarcaFormView subView;

	@Autowired
	private IMarcaOsDAO marcaDAO;

	private MarcaOsEntity currentBean;

	@Override
	protected String getNome() {
		return "Marca";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		try {
			marcaDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = marcaDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
	}

	/*
	 * Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada
	 * para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento
	 * padrÃ£o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new MarcaFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new MarcaOsEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		marcaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "marcaForm";
	}

	@Override
	public MarcaOsEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}