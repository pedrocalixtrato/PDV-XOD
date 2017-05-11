package dc.control.util.classes.ordemservico;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.ordemservico.GrupoOsEntity;
import dc.visao.ordemservico.SubGrupoOsFormView;


public class SubGrupoOsUtils {

	public static void validateRequiredFields(SubGrupoOsFormView subView)
			throws DotErpException {
		
		String subGrupo = subView.getTxtNome().getValue();

		if (StringUtils.isBlank(subGrupo)) {
			throw new DotErpException(subView.getTxtNome(),
					"::DotERP - Não pode ficar em branco");
		}

		GrupoOsEntity grupo = subView.getCbGrupo().getValue();
		if (ObjectUtils.isBlank(grupo)) {
			throw new DotErpException(subView.getCbGrupo(),
					"::DotERP - Não pode ficar em branco");
		}

	}

	public static void validateFieldValue(SubGrupoOsFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(SubGrupoOsFormView subView) {

	}

}