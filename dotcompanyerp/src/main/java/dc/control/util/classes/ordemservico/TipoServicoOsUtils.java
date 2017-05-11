package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.TipoServicoFormView;


public class TipoServicoOsUtils {

	public static void validateRequiredFields(TipoServicoFormView subView)
			throws DotErpException {
		
		String servico = subView.getTfDescricao().getValue();

		if (StringUtils.isBlank(servico)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(TipoServicoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(TipoServicoFormView subView) {

	}

}