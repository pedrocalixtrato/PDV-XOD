package dc.controller.contabilidade.lancamento;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.contabilidade.lancamento.LancamentoPadraoEntity;
import dc.servicos.dao.contabilidade.lancamento.ILancamentoPadraoDAO;
import dc.visao.contabilidade.lancamento.LancamentoPadraoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class LancamentoPadraoFormController extends
		CRUDFormController<LancamentoPadraoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoPadraoFormView subView;

	/** DAO'S */

	@Autowired
	private ILancamentoPadraoDAO pDAO;

	/** ENTITIES */

	private LancamentoPadraoEntity pEntity;

	/** CONSTRUTOR */

	public LancamentoPadraoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new LancamentoPadraoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Lançamento padrão";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String descricao = this.subView.getTfDescricao().getValue();
			String historico = this.subView.getTfHistorico().getValue();

			this.pEntity.setDescricao(descricao);
			this.pEntity.setHistorico(historico);

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
		this.subView = new LancamentoPadraoFormView(this);
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
				this.pEntity = new LancamentoPadraoEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfHistorico().setValue(this.pEntity.getHistorico());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LancamentoPadraoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}