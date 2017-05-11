package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.TipoEfetivacaoFormView;


public class TipoEfetivacaoOsUtils {

	public static void validateRequiredFields(TipoEfetivacaoFormView subView)
			throws DotErpException {
		
		String efetivacao = subView.getTxtDescricao().getValue();

		if (StringUtils.isBlank(efetivacao)) {
			throw new DotErpException(subView.getTxtDescricao(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(TipoEfetivacaoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(TipoEfetivacaoFormView subView) {

	}

}