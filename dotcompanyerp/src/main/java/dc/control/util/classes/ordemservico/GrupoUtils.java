package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.GrupoOsFormView;


public class GrupoUtils {

	public static void validateRequiredFields(GrupoOsFormView subView)
			throws DotErpException {
		
		String grupo = subView.getTxtNome().getValue();

		if (StringUtils.isBlank(grupo)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(GrupoOsFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(GrupoOsFormView subView) {

	}

}