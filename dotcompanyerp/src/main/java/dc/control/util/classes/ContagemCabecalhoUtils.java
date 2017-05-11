package dc.control.util.classes;

import java.util.Date;

import dc.control.util.DateUtils;
import dc.control.validator.DotErpException;
import dc.visao.suprimento.estoque.ContagemFormView;

public class ContagemCabecalhoUtils {

	public static void validateRequiredFields(ContagemFormView subView)
			throws DotErpException {
		Date dataContagem = subView.getPdfDataContagem().getValue();

		if (DateUtils.isBlank(dataContagem)) {
			throw new DotErpException(subView.getPdfDataContagem(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(ContagemFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(ContagemFormView subView) {

	}

}