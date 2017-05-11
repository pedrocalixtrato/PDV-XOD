package dc.controller.contabilidade.demonstrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.demonstrativo.DfcEntity;
import dc.servicos.dao.contabilidade.demonstrativo.IDfcDAO;
import dc.visao.contabilidade.demonstrativo.DfcFormView;
import dc.visao.framework.geral.CRUDFormController;


@Controller
@Scope("prototype")
public class DfcFormController extends CRUDFormController<DfcEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DfcFormView subView;

	/** DAO'S */

	@Autowired
	private IDfcDAO pDAO;

	/** ENTITIES */

	private DfcEntity pEntity;

	/** CONSTRUTOR */

	public DfcFormController() {
		if (this.pEntity == null) {
			this.pEntity = new DfcEntity();
		}
	}

	@Override
	protected String getNome() {
		return "DFC";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			novoObjeto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new DfcFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	/** COMBOS */

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new DfcEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			// this.subView.getTfValor13().setValue(
			// String.valueOf(this.pEntity.getValor13()));
			// this.subView.getTfValorMensal().setValue(
			// String.valueOf(this.pEntity.getValorMensal()));

			// this.subView.getCbInss().setData(this.inssListarTodos());
			// this.subView.getCbServico().setData(this.servicoListarTodos());

			// this.subView.getCbInss().setValue(this.pEntity.getInss());
			// this.subView.getCbServico().setValue(this.pEntity.getServico());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public DfcEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}