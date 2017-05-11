package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.diverso.PaisFormView;

public class PaisUtils {

	public static void validateRequiredFields(PaisFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String nomeIngles = subView.getTfNomeIngles().getValue();

		if (StringUtils.isBlank(nomeIngles)) {
			throw new DotErpException(subView.getTfNomeIngles(),
					"::DotERP - Não pode ficar em branco");
		}

		String sigla2 = subView.getTfSigla2().getValue();

		if (StringUtils.isBlank(sigla2)) {
			throw new DotErpException(subView.getTfSigla2(),
					"::DotERP - Não pode ficar em branco");
		}

		String sigla3 = subView.getTfSigla3().getValue();

		if (StringUtils.isBlank(sigla3)) {
			throw new DotErpException(subView.getTfSigla3(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(PaisFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(PaisFormView subView) {

	}

}