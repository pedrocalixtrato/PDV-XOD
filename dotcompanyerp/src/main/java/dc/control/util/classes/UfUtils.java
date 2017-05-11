package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.diverso.PaisEntity;
import dc.servicos.util.Validator;
import dc.visao.geral.diverso.UfFormView;

public class UfUtils {

	public static void validateRequiredFields(UfFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String sigla = subView.getTfSigla().getValue();

		if (StringUtils.isBlank(sigla)) {
			throw new DotErpException(subView.getTfSigla(),
					"::DotERP - Não pode ficar em branco");
		}

		PaisEntity pais = subView.getMocPais().getValue();

		if (!Validator.validateObject(pais)) {
			throw new DotErpException(subView.getMocPais(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(UfFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(UfFormView subView) {

	}

}