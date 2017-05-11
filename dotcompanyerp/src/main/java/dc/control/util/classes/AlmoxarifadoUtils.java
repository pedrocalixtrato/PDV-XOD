package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.diverso.AlmoxarifadoFormView;

public class AlmoxarifadoUtils {

	public static void validateRequiredFields(AlmoxarifadoFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(AlmoxarifadoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(AlmoxarifadoFormView subView) {

	}

}