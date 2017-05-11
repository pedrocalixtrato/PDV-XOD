package dc.control.util.classes.ordemservico;

import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.ordemservico.EquipamentoFormView;


public class EquipamentoUtils {

	public static void validateRequiredFields(EquipamentoFormView subView)
			throws DotErpException {
		
		String filial = subView.getTfFilial().getValue();

		if (StringUtils.isBlank(filial)) {
			throw new DotErpException(subView.getTfFilial(),
					"::DotERP - Não pode ficar em branco");
		}
		
		String equipamento = subView.getTfEquipamento().getValue();

		if (StringUtils.isBlank(equipamento)) {
			throw new DotErpException(subView.getTfEquipamento(),
					"::DotERP - Não pode ficar em branco");
		}

		String descricao = subView.getTfDescricao().getValue();
		
		if (StringUtils.isBlank(descricao)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}

	}

	public static void validateFieldValue(EquipamentoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(EquipamentoFormView subView) {

	}

}