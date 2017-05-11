package dc.control.util.classes;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.diverso.UfEntity;
import dc.visao.geral.diverso.MunicipioFormView;

public class MunicipioUtils {

	public static void validateRequiredFields(MunicipioFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		UfEntity uf = subView.getMocUf().getValue();

		if (ObjectUtils.isBlank(uf)) {
			throw new DotErpException(subView.getMocUf(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(MunicipioFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(MunicipioFormView subView) {

	}

}