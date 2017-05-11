package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.produto.UnidadeProdutoFormView;

public class UnidadeProdutoUtils {

	public static void validateRequiredFields(UnidadeProdutoFormView subView)
			throws DotErpException {
		String nome = subView.getTfSigla().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfSigla(),
					"::DotERP - Não pode ficar em branco");
		}

		String descricao = subView.getTfDescricao().getValue();

		if (StringUtils.isBlank(descricao)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(UnidadeProdutoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(UnidadeProdutoFormView subView) {

	}

}