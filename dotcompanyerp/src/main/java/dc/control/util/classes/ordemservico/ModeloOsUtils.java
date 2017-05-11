package dc.control.util.classes.ordemservico;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.ordemservico.MarcaOsEntity;
import dc.visao.ordemservico.ModeloFormView;


public class ModeloOsUtils {

	public static void validateRequiredFields(ModeloFormView subView)
			throws DotErpException {
		
		String modelo = subView.getTxtNome().getValue();
		if (StringUtils.isBlank(modelo)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}

		MarcaOsEntity marca = subView.getCbMarca().getValue();
		if (ObjectUtils.isBlank(marca)) {
			throw new DotErpException(subView.getCbMarca(),
					"::DotERP - Não pode ficar em branco");
		}

	}

	public static void validateFieldValue(ModeloFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(ModeloFormView subView) {

	}

}