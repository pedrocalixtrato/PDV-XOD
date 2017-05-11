package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.AcessorioFormView;

public class AcessorioUtils {

	public static void validateRequiredFields(AcessorioFormView subView)
			throws DotErpException {
		
		String nome = subView.getTxtNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(AcessorioFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(AcessorioFormView subView) {

	}

}