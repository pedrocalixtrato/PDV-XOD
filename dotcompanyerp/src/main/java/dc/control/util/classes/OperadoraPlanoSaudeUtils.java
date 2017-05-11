package dc.control.util.classes;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.diverso.OperadoraPlanoSaudeFormView;

public class OperadoraPlanoSaudeUtils {

	public static void validateRequiredFields(
			OperadoraPlanoSaudeFormView subView) throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String registroAns = subView.getTfRegistroAns().getValue();

		if (StringUtils.isBlank(registroAns)) {
			throw new DotErpException(subView.getTfRegistroAns(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(OperadoraPlanoSaudeFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(OperadoraPlanoSaudeFormView subView) {

	}

}