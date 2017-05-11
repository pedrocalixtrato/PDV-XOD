package dc.visao.framework;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.FmModulo;
import dc.servicos.dao.framework.geral.IFmModuloDAO;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class FmModuloFormController extends CRUDFormController<FmModulo> {

	FmModuloFormView subView;

	@Autowired
	private IFmModuloDAO fmDAO;

	private FmModulo currentBean;

	@Override
	protected String getNome() {
		return "Módulo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setCaption(subView.getTxtCaption().getValue());
			currentBean.setUrlID(subView.getTxtURL().getValue());
			String vViewName = subView.getTxtViewName().getValue();
			currentBean.setViewName(vViewName);
			fmDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = fmDAO.find(id);
		subView.getTxtViewName().setValue(currentBean.getViewName());
		subView.getTxtCaption().setValue(currentBean.getCaption());
		subView.getTxtURL().setValue(currentBean.getUrlID());

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
		subView = new FmModuloFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new FmModulo();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		fmDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (estaVazio(subView.getTxtCaption()) && estaVazio(subView.getTxtURL())) {
			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		fmDAO.deleteAll(objetos);
		mensagemRemovidoOK();

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "fmModuloForm";
	}

	@Override
	public FmModulo getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
