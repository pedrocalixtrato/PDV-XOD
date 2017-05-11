package dc.controller.contabilidade.demonstrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.demonstrativo.DreCabecalhoEntity;
import dc.servicos.dao.contabilidade.demonstrativo.IDreCabecalhoDAO;
import dc.visao.contabilidade.demonstrativo.DreCabecalhoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class DreCabecalhoFormController extends
		CRUDFormController<DreCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DreCabecalhoFormView subView;

	/** DAO'S */

	@Autowired
	private IDreCabecalhoDAO pDAO;

	/** ENTITIES */

	private DreCabecalhoEntity pEntity;

	/** CONSTRUTOR */

	public DreCabecalhoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new DreCabecalhoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "DRE cabeçalho";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String descricao = this.subView.getTfDescricao().getValue();
			String padrao = this.subView.getTfPadrao().getValue();
			String periodoInicial = this.subView.getTfPeriodoInicial()
					.getValue();
			String periodoFinal = this.subView.getTfPeriodoFinal().getValue();

			this.pEntity.setDescricao(descricao);
			this.pEntity.setPadrao(padrao);
			this.pEntity.setPeriodoInicial(periodoInicial);
			this.pEntity.setPeriodoFinal(periodoFinal);

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
		this.subView = new DreCabecalhoFormView(this);
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
				this.pEntity = new DreCabecalhoEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfPadrao().setValue(this.pEntity.getPadrao());
			this.subView.getTfPeriodoInicial().setValue(
					this.pEntity.getPeriodoInicial());
			this.subView.getTfPeriodoFinal().setValue(
					this.pEntity.getPeriodoFinal());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public DreCabecalhoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}