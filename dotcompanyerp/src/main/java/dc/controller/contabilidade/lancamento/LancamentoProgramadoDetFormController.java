package dc.controller.contabilidade.lancamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.controller.contabilidade.cadastro.HistoricoListController;
import dc.controller.contabilidade.planoconta.ContaListController;
import dc.entidade.contabilidade.cadastro.HistoricoEntity;
import dc.entidade.contabilidade.lancamento.LancamentoProgramadoCabEntity;
import dc.entidade.contabilidade.lancamento.LancamentoProgramadoDetEntity;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.servicos.dao.contabilidade.cadastro.IHistoricoDAO;
import dc.servicos.dao.contabilidade.lancamento.ILancamentoProgramadoCabDAO;
import dc.servicos.dao.contabilidade.lancamento.ILancamentoProgramadoDetDAO;
import dc.servicos.dao.contabilidade.planoconta.IContaDAO;
import dc.visao.contabilidade.lancamento.LancamentoProgramadoDetFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class LancamentoProgramadoDetFormController extends
		CRUDFormController<LancamentoProgramadoDetEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoProgramadoDetFormView subView;

	/** DAO'S */

	@Autowired
	private ILancamentoProgramadoDetDAO pDAO;

	@Autowired
	private ILancamentoProgramadoCabDAO lpcDAO;

	@Autowired
	private IContaDAO cDAO;

	@Autowired
	private IHistoricoDAO hDAO;

	/** ENTITIES */

	private LancamentoProgramadoDetEntity pEntity;

	/** CONSTRUTOR */

	public LancamentoProgramadoDetFormController() {
		if (this.pEntity == null) {
			this.pEntity = new LancamentoProgramadoDetEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Lançamento programado det";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String descricaoHistorico = this.subView.getTfDescricaoHistorico()
					.getValue();
			BigDecimal valor = new BigDecimal(this.subView.getTfValor()
					.getValue());
			String tipo = this.subView.getTfTipo().getValue();

			LancamentoProgramadoCabEntity lancamentoProgramadoCab = (LancamentoProgramadoCabEntity) this.subView
					.getCbLancamentoProgramadoCab().getValue();
			ContaEntity conta = (ContaEntity) this.subView.getCbConta()
					.getValue();
			HistoricoEntity historico = (HistoricoEntity) this.subView
					.getCbHistorico().getValue();

			this.pEntity.setDescricaoHistorico(descricaoHistorico);
			this.pEntity.setValor(valor);
			this.pEntity.setTipo(tipo);

			this.pEntity.setLancamentoProgramadoCab(lancamentoProgramadoCab);
			this.pEntity.setConta(conta);
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
		this.subView = new LancamentoProgramadoDetFormView(this);

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
		String valor = this.subView.getTfValor().getValue();

		if (!ObjectValidator.validateNotRequiredBigDecimal(valor)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValor(), msg);

			return false;
		}

		/** REQUIRED */

		LancamentoProgramadoCabEntity lancamentoProgramadoCab = this.subView
				.getCbLancamentoProgramadoCab().getValue();

		if (!ObjectValidator.validateObject(lancamentoProgramadoCab)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(
					this.subView.getCbLancamentoProgramadoCab(), msg);

			return false;
		}

		ContaEntity conta = this.subView.getCbConta().getValue();

		if (!ObjectValidator.validateObject(conta)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbConta(), msg);

			return false;
		}

		HistoricoEntity historico = this.subView.getCbHistorico().getValue();

		if (!ObjectValidator.validateObject(historico)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbHistorico(), msg);

			return false;
		}

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
			DefaultManyToOneComboModel<LancamentoProgramadoCabEntity> model1 = new DefaultManyToOneComboModel<LancamentoProgramadoCabEntity>(
					LancamentoProgramadoCabListController.class, this.lpcDAO,
					super.getMainController());

			this.subView.getCbLancamentoProgramadoCab().setModel(model1);

			DefaultManyToOneComboModel<ContaEntity> model2 = new DefaultManyToOneComboModel<ContaEntity>(
					ContaListController.class, this.cDAO,
					super.getMainController());

			this.subView.getCbConta().setModel(model2);

			DefaultManyToOneComboModel<HistoricoEntity> model3 = new DefaultManyToOneComboModel<HistoricoEntity>(
					HistoricoListController.class, this.hDAO,
					super.getMainController());

			this.subView.getCbHistorico().setModel(model3);
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
				this.pEntity = new LancamentoProgramadoDetEntity();

				// this.subView.getCbLancamentoProgramadoCab().setValue(
				// this.pEntity.getLancamentoProgramadoCab());
				// this.subView.getCbConta().setValue(this.pEntity.getConta());
				// this.subView.getCbHistorico().setValue(
				// this.pEntity.getHistorico());
			} else {
				this.pEntity = this.pDAO.find(id);

				this.subView.getCbLancamentoProgramadoCab().setValue(
						this.pEntity.getLancamentoProgramadoCab());
				this.subView.getCbConta().setValue(this.pEntity.getConta());
				this.subView.getCbHistorico().setValue(
						this.pEntity.getHistorico());
			}

			this.subView.getTfDescricaoHistorico().setValue(
					this.pEntity.getDescricaoHistorico());
			this.subView.getTfValor().setValue(
					this.pEntity.getValor().toString());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LancamentoProgramadoDetEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}