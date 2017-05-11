package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.CorFormView;


public class CorUtils {

	public static void validateRequiredFields(CorFormView subView)
			throws DotErpException {
		
		String cor = subView.getTxtNome().getValue();

		if (StringUtils.isBlank(cor)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(CorFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(CorFormView subView) {

	}

}