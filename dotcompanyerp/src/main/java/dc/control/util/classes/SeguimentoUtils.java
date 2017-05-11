package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.sistema.SeguimentoFormView;

public class SeguimentoUtils {

	public static void validateRequiredFields(SeguimentoFormView subView)
			throws DotErpException {
		String nome = subView.getTxtNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}

	}

	public static void validateFieldValue(SeguimentoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(SeguimentoFormView subView) {

	}

}