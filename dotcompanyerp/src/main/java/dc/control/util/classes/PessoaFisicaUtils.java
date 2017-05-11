package dc.control.util.classes;

import dc.control.util.ObjectUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.visao.geral.pessoal.PessoaFormView;

public class PessoaFisicaUtils {

	public static void validateRequiredFields(PessoaFormView subView)
			throws DotErpException {
		EstadoCivilEntity estadoCivil = subView.getMocEstadoCivil().getValue();

		if (ObjectUtils.isBlank(estadoCivil)) {
			throw new DotErpException(subView.getCbTipoPessoa(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	//

	public static void validateFieldValue(PessoaFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(PessoaFormView subView) {

	}

}