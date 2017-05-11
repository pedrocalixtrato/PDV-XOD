package dc.control.util.classes.ordemservico;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.ordemservico.GrupoOsEntity;
import dc.entidade.ordemservico.SubGrupoOsEntity;
import dc.visao.ordemservico.ServicoOsFormView;


public class ServicoOsUtils {

	public static void validateRequiredFields(ServicoOsFormView subView)
			throws DotErpException {
		
		String modelo = subView.getTfNome().getValue();
		if (StringUtils.isBlank(modelo)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}
		
		GrupoOsEntity grupo = subView.getCbGrupo().getValue();
		if (ObjectUtils.isBlank(grupo)) {
			throw new DotErpException(subView.getCbGrupo(),
					"::DotERP - Não pode ficar em branco");
		}

		SubGrupoOsEntity subGrupo = subView.getCbSubGrupo().getValue();
		if (ObjectUtils.isBlank(subGrupo)) {
			throw new DotErpException(subView.getCbSubGrupo(),
					"::DotERP - Não pode ficar em branco");
		}

		String tipoComissaoVendedor = subView.getTfValorComissaoVendedor().getValue();
		if (StringUtils.isBlank(tipoComissaoVendedor)) {
			throw new DotErpException(subView.getTfValorComissaoVendedor(),
					"::DotERP - Não pode ficar em branco");
		}

		String tipoComissaoTecnico = subView.getTfValorComissaoTecnico().getValue();
		if (StringUtils.isBlank(tipoComissaoTecnico)) {
			throw new DotErpException(subView.getTfValorComissaoTecnico(),
					"::DotERP - Não pode ficar em branco");
		}

	}

	public static void validateFieldValue(ServicoOsFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(ServicoOsFormView subView) {

	}

}