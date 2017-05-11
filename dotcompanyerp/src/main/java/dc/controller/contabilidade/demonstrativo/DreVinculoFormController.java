package dc.controller.contabilidade.demonstrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.contabilidade.planoconta.ContaListController;
import dc.entidade.contabilidade.demonstrativo.DreDetalheEntity;
import dc.entidade.contabilidade.demonstrativo.DreVinculoEntity;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.servicos.dao.contabilidade.demonstrativo.IDreDetalheDAO;
import dc.servicos.dao.contabilidade.demonstrativo.IDreVinculoDAO;
import dc.servicos.dao.contabilidade.planoconta.IContaDAO;
import dc.visao.contabilidade.demonstrativo.DreVinculoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class DreVinculoFormController extends
		CRUDFormController<DreVinculoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DreVinculoFormView subView;

	/** DAO'S */

	@Autowired
	private IDreVinculoDAO pDAO;

	@Autowired
	private IDreDetalheDAO ddDAO;

	@Autowired
	private IContaDAO cDAO;

	/** ENTITIES */

	private DreVinculoEntity pEntity;

	/** CONSTRUTOR */

	public DreVinculoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new DreVinculoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "DRE vínculo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			DreDetalheEntity dreDetalhe = (DreDetalheEntity) this.subView
					.getCbDreDetalhe().getValue();
			ContaEntity conta = (ContaEntity) this.subView.getCbConta()
					.getValue();

			this.pEntity.setDreDetalhe(dreDetalhe);
			this.pEntity.setConta(conta);

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
		this.subView = new DreVinculoFormView(this);

		popularCombo();
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

	private void popularCombo() {
		try {
			DefaultManyToOneComboModel<DreDetalheEntity> model1 = new DefaultManyToOneComboModel<DreDetalheEntity>(
					DreDetalheListController.class, this.ddDAO,
					super.getMainController());

			this.subView.getCbDreDetalhe().setModel(model1);

			DefaultManyToOneComboModel<ContaEntity> model2 = new DefaultManyToOneComboModel<ContaEntity>(
					ContaListController.class, this.cDAO,
					super.getMainController());

			this.subView.getCbConta().setModel(model2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new DreVinculoEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getCbDreDetalhe().setValue(
					this.pEntity.getDreDetalhe());
			this.subView.getCbConta().setValue(this.pEntity.getConta());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public DreVinculoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}