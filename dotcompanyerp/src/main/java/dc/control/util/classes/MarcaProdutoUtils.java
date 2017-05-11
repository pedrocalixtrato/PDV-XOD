package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.produto.MarcaFormView;

public class MarcaProdutoUtils {

	public static void validateRequiredFields(MarcaFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String descricao = subView.getTfDescricao().getValue();

		if (StringUtils.isBlank(descricao)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(MarcaFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(MarcaFormView subView) {

	}

}