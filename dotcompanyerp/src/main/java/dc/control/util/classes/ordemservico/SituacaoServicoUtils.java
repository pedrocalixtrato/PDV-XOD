package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.SituacaoServicoFormView;


public class SituacaoServicoUtils {

	public static void validateRequiredFields(SituacaoServicoFormView subView)
			throws DotErpException {
		
		String situacao = subView.getTfDescricao().getValue();

		if (StringUtils.isBlank(situacao)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(SituacaoServicoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(SituacaoServicoFormView subView) {

	}

}