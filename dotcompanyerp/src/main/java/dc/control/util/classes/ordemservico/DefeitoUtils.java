package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.DefeitoFormView;

public class DefeitoUtils {

	public static void validateRequiredFields(DefeitoFormView subView)
			throws DotErpException {
		
		String defeito = subView.getTxtNome().getValue();

		if (StringUtils.isBlank(defeito)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(DefeitoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(DefeitoFormView subView) {

	}

}