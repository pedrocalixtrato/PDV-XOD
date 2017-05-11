package dc.control.util.classes;

import dc.control.enums.SimNaoEn;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.diverso.SetorEntity;
import dc.entidade.geral.pessoal.NivelFormacaoEntity;
import dc.entidade.geral.pessoal.SituacaoColaboradorEntity;
import dc.entidade.geral.pessoal.TipoColaboradorEntity;
import dc.visao.geral.pessoal.ColaboradorFormView;
import dc.visao.geral.pessoal.PessoaFormView;

public class ColaboradorUtils {

	public static void validateRequiredFields(ColaboradorFormView subView)
			throws DotErpException {
		String matricula = subView.getTfMatricula().getValue();

		if (StringUtils.isBlank(matricula)) {
			throw new DotErpException(subView.getTfMatricula(),
					"::DotERP - Não pode ficar em branco");
		}

		String categoria = subView.getTfCategoria().getValue();

		if (StringUtils.isBlank(categoria)) {
			throw new DotErpException(subView.getTfCategoria(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateRequiredFields(PessoaFormView subView)
			throws DotErpException {
		System.out.println(":: valida colaborador");

		SituacaoColaboradorEntity situacaoColaborador = subView
				.getMocColaboradorSituacaoColaborador().getValue();

		if (ObjectUtils.isBlank(situacaoColaborador)) {
			throw new DotErpException(
					subView.getMocColaboradorSituacaoColaborador(),
					"::DotERP - Não pode ficar em branco");
		}

		TipoColaboradorEntity tipoColaborador = subView
				.getMocColaboradorTipoColaborador().getValue();

		if (ObjectUtils.isBlank(tipoColaborador)) {
			throw new DotErpException(
					subView.getMocColaboradorTipoColaborador(),
					"::DotERP - Não pode ficar em branco");
		}

		NivelFormacaoEntity nivelFormacao = subView
				.getMocColaboradorNivelFormacao().getValue();

		if (ObjectUtils.isBlank(nivelFormacao)) {
			throw new DotErpException(subView.getMocColaboradorNivelFormacao(),
					"::DotERP - Não pode ficar em branco");
		}

		SetorEntity setor = subView.getMocColaboradorSetor().getValue();

		if (ObjectUtils.isBlank(setor)) {
			throw new DotErpException(subView.getMocColaboradorSetor(),
					"::DotERP - Não pode ficar em branco");
		}

		SimNaoEn priorizarComissao = (SimNaoEn) subView
				.getCbColaboradorPriorizarComissao().getValue();

		if (ObjectUtils.isBlank(priorizarComissao)) {
			throw new DotErpException(
					subView.getCbColaboradorPriorizarComissao(),
					"::DotERP - Não pode ficar em branco");
		}

		SimNaoEn comissaoOver = (SimNaoEn) subView
				.getCbColaboradorComissaoOver().getValue();

		if (ObjectUtils.isBlank(comissaoOver)) {
			throw new DotErpException(subView.getCbColaboradorComissaoOver(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(ColaboradorFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(ColaboradorFormView subView) {

	}

}