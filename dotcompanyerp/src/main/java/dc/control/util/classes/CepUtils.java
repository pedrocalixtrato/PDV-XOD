package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.diverso.CepFormView;

public class CepUtils {

	public static void validateRequiredFields(CepFormView subView)
			throws DotErpException {
		String cep = subView.getTfCep().getValue();

		if (StringUtils.isBlank(cep)) {
			throw new DotErpException(subView.getTfCep(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(CepFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(CepFormView subView) {
		// subView.getTfBairro().setValue("");
		subView.getTfCodigoIbgeMunicipio().setValue(null);
		subView.getTfMunicipio().setValue(null);
		subView.getTfBairro().setValue(null);
		subView.getTfComplemento().setValue(null);
		subView.getTfLogradouro().setValue(null);
		subView.getTfCep().setValue(null);
		subView.getMocUf().setValue(null);
	}

}