package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.StatusOsFormView;


public class StatusOsUtils {

	public static void validateRequiredFields(StatusOsFormView subView)
			throws DotErpException {
		
		String situacao = subView.getTfDescricao().getValue();

		if (StringUtils.isBlank(situacao)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(StatusOsFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(StatusOsFormView subView) {

	}

}