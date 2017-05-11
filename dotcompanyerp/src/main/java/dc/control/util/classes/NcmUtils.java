package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.produto.NcmFormView;

public class NcmUtils {

	public static void validateRequiredFields(NcmFormView subView)
			throws DotErpException {
		String codigo = subView.getTfCodigo().getValue();

		if (StringUtils.isBlank(codigo)) {
			throw new DotErpException(subView.getTfCodigo(),
					"::DotERP - Não pode ficar em branco");
		}

		String descricao = subView.getTfDescricao().getValue();

		if (StringUtils.isBlank(descricao)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}

		String observacao = subView.getTfObservacao().getValue();

		if (StringUtils.isBlank(observacao)) {
			throw new DotErpException(subView.getTfObservacao(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(NcmFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(NcmFormView subView) {

	}

}