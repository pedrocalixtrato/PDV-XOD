package dc.controller.contabilidade.demonstrativo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.controller.contabilidade.planoconta.ContaListController;
import dc.entidade.contabilidade.demonstrativo.EncerramentoExeCabEntity;
import dc.entidade.contabilidade.demonstrativo.EncerramentoExeDetEntity;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.servicos.dao.contabilidade.demonstrativo.IEncerramentoExeCabDAO;
import dc.servicos.dao.contabilidade.demonstrativo.IEncerramentoExeDetDAO;
import dc.servicos.dao.contabilidade.planoconta.ContaDAO;
import dc.visao.contabilidade.demonstrativo.EncerramentoExeDetFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class EncerramentoExeDetFormController extends
		CRUDFormController<EncerramentoExeDetEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EncerramentoExeDetFormView subView;

	/** DAO'S */

	@Autowired
	private IEncerramentoExeDetDAO pDAO;

	@Autowired
	private IEncerramentoExeCabDAO eecDAO;

	@Autowired
	private ContaDAO cDAO;

	/** ENTITIES */

	private EncerramentoExeDetEntity pEntity;

	/** CONSTRUTOR */

	public EncerramentoExeDetFormController() {
		if (this.pEntity == null) {
			this.pEntity = new EncerramentoExeDetEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Encerramento de exercício detalhe";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			/*Double saldoAnterior = Double.parseDouble(this.subView
					.getTfSaldoAnterior().getValue());
			Double valorDebito = Double.parseDouble(this.subView
					.getTfValorDebito().getValue());
			Double valorCredito = Double.parseDouble(this.subView
					.getTfValorCredito().getValue());
			Double saldo = Double.parseDouble(this.subView.getTfSaldo()
					.getValue());*/

			EncerramentoExeCabEntity encerramentoExeCab = this.subView
					.getCbEncerramentoExeCab().getValue();
			ContaEntity conta = this.subView.getCbConta().getValue();
			
			pEntity.setSaldoAnterior((BigDecimal) this.subView.getTfSaldoAnterior().getConvertedValue());
			pEntity.setValorDebito((BigDecimal) this.subView.getTfValorDebito().getConvertedValue());
			pEntity.setValorCredito((BigDecimal) this.subView.getTfValorCredito().getConvertedValue());
			pEntity.setSaldo((BigDecimal) this.subView.getTfSaldo().getConvertedValue());
			

			//this.pEntity.setSaldoAnterior(saldoAnterior);
			//this.pEntity.setValorDebito(valorDebito);
			//this.pEntity.setValorCredito(valorCredito);
			//this.pEntity.setSaldo(saldo);

			this.pEntity.setEncerramentoExeCab(encerramentoExeCab);
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
		this.subView = new EncerramentoExeDetFormView(this);

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
		String saldoAnterior = this.subView.getTfSaldoAnterior().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(saldoAnterior)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfSaldoAnterior(), msg);

			return false;
		}

		String valorDebito = this.subView.getTfValorDebito().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(valorDebito)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValorDebito(), msg);

			return false;
		}

		String valorCredito = this.subView.getTfValorCredito().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(valorCredito)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValorCredito(), msg);

			return false;
		}

		String saldo = this.subView.getTfSaldo().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(saldo)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfSaldo(), msg);

			return false;
		}

		/** REQUIRED */

		EncerramentoExeCabEntity encerramentoExeCab = this.subView
				.getCbEncerramentoExeCab().getValue();

		if (!ObjectValidator.validateObject(encerramentoExeCab)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbEncerramentoExeCab(),
					msg);

			return false;
		}

		ContaEntity conta = this.subView.getCbConta().getValue();

		if (!ObjectValidator.validateObject(conta)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbConta(), msg);

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
			DefaultManyToOneComboModel<EncerramentoExeCabEntity> model1 = new DefaultManyToOneComboModel<EncerramentoExeCabEntity>(
					EncerramentoExeCabListController.class, this.eecDAO,
					super.getMainController());

			this.subView.getCbEncerramentoExeCab().setModel(model1);

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
				this.pEntity = new EncerramentoExeDetEntity();

				// this.subView.getCbEncerramentoExeCab().setValue(
				// this.pEntity.getEncerramentoExeCab());
				// this.subView.getCbConta().setValue(this.pEntity.getConta());
			} else {
				this.pEntity = this.pDAO.find(id);

				this.subView.getCbEncerramentoExeCab().setValue(
						this.pEntity.getEncerramentoExeCab());
				this.subView.getCbConta().setValue(this.pEntity.getConta());
			}

			this.subView.getTfSaldoAnterior().setValue(
					this.pEntity.getSaldoAnterior().toString());
			this.subView.getTfValorDebito().setValue(
					this.pEntity.getValorDebito().toString());
			this.subView.getTfValorCredito().setValue(
					this.pEntity.getValorCredito().toString());
			this.subView.getTfSaldo().setValue(
					this.pEntity.getSaldo().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public EncerramentoExeDetEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}