package dc.controller.contabilidade;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.contabilidade.PlanoContaRefSped;
import dc.servicos.dao.contabilidade.planoconta.IPlanoContaRefSpedDAO;
import dc.visao.contabilidade.PlanoContaRefSpedFormView;
import dc.visao.contabilidade.PlanoContaRefSpedFormView.Tipo;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PlanoContaRefSpedFormController extends CRUDFormController<PlanoContaRefSped> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanoContaRefSpedFormView subView;

	@Autowired
	private IPlanoContaRefSpedDAO planoContaRefSpedDAO;

	private PlanoContaRefSped currentBean;

	@Override
	protected String getNome() {
		return "Plano Conta Ref Sped";
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
			planoContaRefSpedDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = planoContaRefSpedDAO.find(id);
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
		subView = new PlanoContaRefSpedFormView();

		for (Tipo value : Tipo.values()) {
			subView.getCbTipo().addItem(value);
		}

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new PlanoContaRefSped();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		planoContaRefSpedDAO.deleteAllByIds(ids);
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
		// TODO Auto-generated method stub
		return "planoContaRefSpedForm";
	}

	@Override
	public PlanoContaRefSped getModelBean() {
		return currentBean;
	}
}
