package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.CombustivelFormView;


public class CombustivelUtils {

	public static void validateRequiredFields(CombustivelFormView subView)
			throws DotErpException {
		
		String combustivel = subView.getTxtNome().getValue();

		if (StringUtils.isBlank(combustivel)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(CombustivelFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(CombustivelFormView subView) {

	}

}