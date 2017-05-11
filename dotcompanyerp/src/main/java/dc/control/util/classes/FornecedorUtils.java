package dc.control.util.classes;

import java.util.Date;

import dc.control.util.DateUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.visao.geral.FornecedorFormView;
import dc.visao.geral.pessoal.PessoaFormView;

public class FornecedorUtils {

	public static void validateRequiredFields(FornecedorFormView subView)
			throws DotErpException {

	}

	public static void validateRequiredFields(PessoaFormView subView)
			throws DotErpException {
		System.out.println(":: valida fornecedor");

		AtividadeForCliEntity atividadeForCli = subView
				.getMocFornecedorAtividadeForCli().getValue();

		if (ObjectUtils.isBlank(atividadeForCli)) {
			throw new DotErpException(
					subView.getMocFornecedorAtividadeForCli(),
					"::DotERP - Não pode ficar em branco");
		}

		SituacaoForCliEntity situacaoForCli = subView
				.getMocFornecedorSituacaoForCli().getValue();

		if (ObjectUtils.isBlank(situacaoForCli)) {
			throw new DotErpException(subView.getMocFornecedorSituacaoForCli(),
					"::DotERP - Não pode ficar em branco");
		}

		// ContabilContaEntity contabilConta = subView
		// .getMocFornecedorContabilConta().getValue();

		// if (ObjectUtils.isBlank(contabilConta)) {
		// throw new DotErpException(subView.getMocFornecedorContabilConta(),
		// "::DotERP - Não pode ficar em branco");
		// }

		Date desde = subView.getPdfFornecedorDesde().getValue();

		if (DateUtils.isBlank(desde)) {
			throw new DotErpException(subView.getPdfFornecedorDesde(),
					"::DotERP - Não pode ficar em branco");
		}

		String contaRemetente = subView.getTfFornecedorContaRemetente()
				.getValue();

		if (StringUtils.isBlank(contaRemetente)) {
			throw new DotErpException(subView.getTfFornecedorContaRemetente(),
					"::DotERP - Não pode ficar em branco");
		}

		String chequeNominalA = subView.getTfFornecedorChequeNominalA()
				.getValue();

		if (StringUtils.isBlank(chequeNominalA)) {
			throw new DotErpException(subView.getTfFornecedorChequeNominalA(),
					"::DotERP - Não pode ficar em branco");
		}

		Object geraFaturamento = subView.getCbFornecedorGeraFaturamento()
				.getValue();

		if (ObjectUtils.isBlank(geraFaturamento)) {
			throw new DotErpException(subView.getCbFornecedorGeraFaturamento(),
					"::DotERP - Não pode ficar em branco");
		}

		Object localizacao = subView.getCbFornecedorLocalizacao().getValue();

		if (ObjectUtils.isBlank(localizacao)) {
			throw new DotErpException(subView.getCbFornecedorLocalizacao(),
					"::DotERP - Não pode ficar em branco");
		}

		Object optanteSimples = subView.getCbFornecedorOptanteSimples()
				.getValue();

		if (ObjectUtils.isBlank(optanteSimples)) {
			throw new DotErpException(subView.getCbFornecedorOptanteSimples(),
					"::DotERP - Não pode ficar em branco");
		}

		Object sofreRetencao = subView.getCbFornecedorSofreRetencao()
				.getValue();

		if (ObjectUtils.isBlank(sofreRetencao)) {
			throw new DotErpException(subView.getCbFornecedorSofreRetencao(),
					"::DotERP - Não pode ficar em branco");
		}

		String numDiasIntervalo = subView.getTfFornecedorNumDiasIntervalo()
				.getValue();

		if (StringUtils.isBlank(numDiasIntervalo)) {
			throw new DotErpException(
					subView.getTfFornecedorNumDiasIntervalo(),
					"::DotERP - Não pode ficar em branco");
		}

		String prazoMedioEntrega = subView.getTfFornecedorPrazoMedioEntrega()
				.getValue();

		if (StringUtils.isBlank(prazoMedioEntrega)) {
			throw new DotErpException(
					subView.getTfFornecedorPrazoMedioEntrega(),
					"::DotERP - Não pode ficar em branco");
		}

		String numDiasPrimeiroVenc = subView
				.getTfFornecedorNumDiasPrimeiroVenc().getValue();

		if (StringUtils.isBlank(numDiasPrimeiroVenc)) {
			throw new DotErpException(
					subView.getTfFornecedorNumDiasPrimeiroVenc(),
					"::DotERP - Não pode ficar em branco");
		}

		String quantidadeParcelas = subView.getTfFornecedorQuantidadeParcelas()
				.getValue();

		if (StringUtils.isBlank(quantidadeParcelas)) {
			throw new DotErpException(
					subView.getTfFornecedorQuantidadeParcelas(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	//

	public static void validateFieldValue(FornecedorFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(FornecedorFormView subView) {

	}

}