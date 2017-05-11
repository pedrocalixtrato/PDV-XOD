package dc.controller.folhapagamento.cadastro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.ObjectValidator;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.folhapagamento.cadastro.ParametroEntity;
import dc.servicos.dao.folhapagamento.cadastro.ParametroDAO;
import dc.visao.folhapagamento.cadastro.ParametroFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class ParametroFormController extends
		CRUDFormController<ParametroEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParametroFormView subView;

	/** DAO'S */

	@Autowired
	private ParametroDAO pDAO;

	/** ENTITIES */

	private ParametroEntity pEntity;

	/** CONSTRUTOR */

	public ParametroFormController() {
		if (this.pEntity == null) {
			this.pEntity = new ParametroEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Parâmetro";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String competencia = this.subView.getTfCompetencia().getValue();
			String contribuiPis = this.subView.getTfContribuiPis().getValue();
			String aliquotaPis = this.subView.getTfAliquotaPis().getValue();
			String discriminarDsr = this.subView.getTfDiscriminarDsr()
					.getValue();
			String diaPagamento = this.subView.getTfDiaPagamento().getValue();
			String calculoProporcionalidade = this.subView
					.getTfCalculoProporcionalidade().getValue();
			String descontarFaltas13 = this.subView.getTfDescontarFaltas13()
					.getValue();
			String pagarAdicionais13 = this.subView.getTfPagarAdicionais13()
					.getValue();
			String pagarEstagiarios13 = this.subView.getTfPagarEstagiarios13()
					.getValue();
			String mesAdiantamento13 = this.subView.getTfMesAdiantamento13()
					.getValue();
			Double percentualAdiantam13 = Double.parseDouble(this.subView
					.getTfPercentualAdiantam13().getValue());
			String feriasDescontarFaltas = this.subView
					.getTfFeriasDescontarFaltas().getValue();
			String feriasPagarAdicionais = this.subView
					.getTfFeriasPagarAdicionais().getValue();
			String feriasAdiantar13 = this.subView.getTfFeriasAdiantar13()
					.getValue();
			String feriasPagarEstagiarios = this.subView
					.getTfFeriasPagarEstagiarios().getValue();
			String feriasCalcJustaCausa = this.subView
					.getTfFeriasCalcJustaCausa().getValue();
			String feriasMovimentoMensal = this.subView
					.getTfFeriasMovimentoMensal().getValue();

			this.pEntity.setCompetencia(competencia);
			this.pEntity.setContribuiPis(contribuiPis);
			this.pEntity.setAliquotaPis(aliquotaPis);
			this.pEntity.setDiscriminarDsr(discriminarDsr);
			this.pEntity.setDiaPagamento(diaPagamento);
			this.pEntity.setCalculoProporcionalidade(calculoProporcionalidade);
			this.pEntity.setDescontarFaltas13(descontarFaltas13);
			this.pEntity.setPagarAdicionais13(pagarAdicionais13);
			this.pEntity.setPagarEstagiarios13(pagarEstagiarios13);
			this.pEntity.setMesAdiantamento13(mesAdiantamento13);
			this.pEntity.setPercentualAdiantam13(percentualAdiantam13);
			this.pEntity.setFeriasDescontarFaltas(feriasDescontarFaltas);
			this.pEntity.setFeriasPagarAdicionais(feriasPagarAdicionais);
			this.pEntity.setFeriasAdiantar13(feriasAdiantar13);
			this.pEntity.setFeriasPagarEstagiarios(feriasPagarEstagiarios);
			this.pEntity.setFeriasCalcJustaCausa(feriasCalcJustaCausa);
			this.pEntity.setFeriasMovimentoMensal(feriasMovimentoMensal);

			/** Empresa vinda da conta do usuário logado */

			EmpresaEntity empresa = SecuritySessionProvider.getUsuario().getConta()
					.getEmpresa();

			this.pEntity.setEmpresa(empresa);

			/** ************************************** */

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new ParametroEntity();

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
			this.subView.getTfContribuiPis().setValue(
					this.pEntity.getContribuiPis());
			this.subView.getTfAliquotaPis().setValue(
					this.pEntity.getAliquotaPis());
			this.subView.getTfDiscriminarDsr().setValue(
					this.pEntity.getDiscriminarDsr());
			this.subView.getTfDiaPagamento().setValue(
					this.pEntity.getDiaPagamento());
			this.subView.getTfCalculoProporcionalidade().setValue(
					this.pEntity.getCalculoProporcionalidade());
			this.subView.getTfDescontarFaltas13().setValue(
					this.pEntity.getDescontarFaltas13());
			this.subView.getTfPagarAdicionais13().setValue(
					this.pEntity.getPagarAdicionais13());
			this.subView.getTfPagarEstagiarios13().setValue(
					this.pEntity.getPagarEstagiarios13());
			this.subView.getTfMesAdiantamento13().setValue(
					this.pEntity.getMesAdiantamento13());
			this.subView.getTfPercentualAdiantam13().setValue(
					String.valueOf(this.pEntity.getPercentualAdiantam13()));
			this.subView.getTfFeriasDescontarFaltas().setValue(
					this.pEntity.getFeriasDescontarFaltas());
			this.subView.getTfFeriasPagarAdicionais().setValue(
					this.pEntity.getFeriasPagarAdicionais());
			this.subView.getTfFeriasAdiantar13().setValue(
					this.pEntity.getFeriasAdiantar13());
			this.subView.getTfFeriasPagarEstagiarios().setValue(
					this.pEntity.getFeriasPagarEstagiarios());
			this.subView.getTfFeriasCalcJustaCausa().setValue(
					this.pEntity.getFeriasCalcJustaCausa());
			this.subView.getTfFeriasMovimentoMensal().setValue(
					this.pEntity.getFeriasMovimentoMensal());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
			this.subView.getTfContribuiPis().setValue(
					this.pEntity.getContribuiPis());
			this.subView.getTfAliquotaPis().setValue(
					this.pEntity.getAliquotaPis());
			this.subView.getTfDiscriminarDsr().setValue(
					this.pEntity.getDiscriminarDsr());
			this.subView.getTfDiaPagamento().setValue(
					this.pEntity.getDiaPagamento());
			this.subView.getTfCalculoProporcionalidade().setValue(
					this.pEntity.getCalculoProporcionalidade());
			this.subView.getTfDescontarFaltas13().setValue(
					this.pEntity.getDescontarFaltas13());
			this.subView.getTfPagarAdicionais13().setValue(
					this.pEntity.getPagarAdicionais13());
			this.subView.getTfPagarEstagiarios13().setValue(
					this.pEntity.getPagarEstagiarios13());
			this.subView.getTfMesAdiantamento13().setValue(
					this.pEntity.getMesAdiantamento13());
			this.subView.getTfPercentualAdiantam13().setValue(
					String.valueOf(this.pEntity.getPercentualAdiantam13()));
			this.subView.getTfFeriasDescontarFaltas().setValue(
					this.pEntity.getFeriasDescontarFaltas());
			this.subView.getTfFeriasPagarAdicionais().setValue(
					this.pEntity.getFeriasPagarAdicionais());
			this.subView.getTfFeriasAdiantar13().setValue(
					this.pEntity.getFeriasAdiantar13());
			this.subView.getTfFeriasPagarEstagiarios().setValue(
					this.pEntity.getFeriasPagarEstagiarios());
			this.subView.getTfFeriasCalcJustaCausa().setValue(
					this.pEntity.getFeriasCalcJustaCausa());
			this.subView.getTfFeriasMovimentoMensal().setValue(
					this.pEntity.getFeriasMovimentoMensal());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			this.pEntity = new ParametroEntity();

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
			this.subView.getTfContribuiPis().setValue(
					this.pEntity.getContribuiPis());
			this.subView.getTfAliquotaPis().setValue(
					this.pEntity.getAliquotaPis());
			this.subView.getTfDiscriminarDsr().setValue(
					this.pEntity.getDiscriminarDsr());
			this.subView.getTfDiaPagamento().setValue(
					this.pEntity.getDiaPagamento());
			this.subView.getTfCalculoProporcionalidade().setValue(
					this.pEntity.getCalculoProporcionalidade());
			this.subView.getTfDescontarFaltas13().setValue(
					this.pEntity.getDescontarFaltas13());
			this.subView.getTfPagarAdicionais13().setValue(
					this.pEntity.getPagarAdicionais13());
			this.subView.getTfPagarEstagiarios13().setValue(
					this.pEntity.getPagarEstagiarios13());
			this.subView.getTfMesAdiantamento13().setValue(
					this.pEntity.getMesAdiantamento13());
			this.subView.getTfPercentualAdiantam13().setValue(
					String.valueOf(this.pEntity.getPercentualAdiantam13()));
			this.subView.getTfFeriasDescontarFaltas().setValue(
					this.pEntity.getFeriasDescontarFaltas());
			this.subView.getTfFeriasPagarAdicionais().setValue(
					this.pEntity.getFeriasPagarAdicionais());
			this.subView.getTfFeriasAdiantar13().setValue(
					this.pEntity.getFeriasAdiantar13());
			this.subView.getTfFeriasPagarEstagiarios().setValue(
					this.pEntity.getFeriasPagarEstagiarios());
			this.subView.getTfFeriasCalcJustaCausa().setValue(
					this.pEntity.getFeriasCalcJustaCausa());
			this.subView.getTfFeriasMovimentoMensal().setValue(
					this.pEntity.getFeriasMovimentoMensal());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new ParametroFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new ParametroEntity();

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
			this.subView.getTfContribuiPis().setValue(
					this.pEntity.getContribuiPis());
			this.subView.getTfAliquotaPis().setValue(
					this.pEntity.getAliquotaPis());
			this.subView.getTfDiscriminarDsr().setValue(
					this.pEntity.getDiscriminarDsr());
			this.subView.getTfDiaPagamento().setValue(
					this.pEntity.getDiaPagamento());
			this.subView.getTfCalculoProporcionalidade().setValue(
					this.pEntity.getCalculoProporcionalidade());
			this.subView.getTfDescontarFaltas13().setValue(
					this.pEntity.getDescontarFaltas13());
			this.subView.getTfPagarAdicionais13().setValue(
					this.pEntity.getPagarAdicionais13());
			this.subView.getTfPagarEstagiarios13().setValue(
					this.pEntity.getPagarEstagiarios13());
			this.subView.getTfMesAdiantamento13().setValue(
					this.pEntity.getMesAdiantamento13());
			this.subView.getTfPercentualAdiantam13().setValue(
					String.valueOf(this.pEntity.getPercentualAdiantam13()));
			this.subView.getTfFeriasDescontarFaltas().setValue(
					this.pEntity.getFeriasDescontarFaltas());
			this.subView.getTfFeriasPagarAdicionais().setValue(
					this.pEntity.getFeriasPagarAdicionais());
			this.subView.getTfFeriasAdiantar13().setValue(
					this.pEntity.getFeriasAdiantar13());
			this.subView.getTfFeriasPagarEstagiarios().setValue(
					this.pEntity.getFeriasPagarEstagiarios());
			this.subView.getTfFeriasCalcJustaCausa().setValue(
					this.pEntity.getFeriasCalcJustaCausa());
			this.subView.getTfFeriasMovimentoMensal().setValue(
					this.pEntity.getFeriasMovimentoMensal());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		String percentualAdiantam13 = this.subView.getTfPercentualAdiantam13()
				.getValue();

		if (!ObjectValidator.validateNotRequiredNumber(percentualAdiantam13)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfPercentualAdiantam13(),
					msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public ParametroEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

	/** COMBOS */

}