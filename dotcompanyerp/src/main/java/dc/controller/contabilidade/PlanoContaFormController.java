package dc.controller.contabilidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaDAO;
import dc.visao.contabilidade.PlanoContaFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PlanoContaFormController extends CRUDFormController<PlanoContaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanoContaFormView subView;

	@Autowired
	private IPlanoContaDAO planoContaDAO;

	private PlanoContaEntity currentBean;

	@Override
	protected String getNome() {
		return "Plano Conta";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			planoContaDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = planoContaDAO.find(id);
		subView.preencheForm(currentBean);
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
		subView = new PlanoContaFormView();
		subView.getDtDataInclusao().setValue(new Date());

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new PlanoContaEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		planoContaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "planoContaForm";
	}

	@Override
	public PlanoContaEntity getModelBean() {
		return currentBean;
	}
}
