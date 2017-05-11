package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.MarcaFormView;


public class MarcaOsUtils {

	public static void validateRequiredFields(MarcaFormView subView)
			throws DotErpException {
		
		String marca = subView.getTxtNome().getValue();

		if (StringUtils.isBlank(marca)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(MarcaFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(MarcaFormView subView) {

	}

}