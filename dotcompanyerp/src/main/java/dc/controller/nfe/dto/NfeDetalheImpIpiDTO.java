package dc.controller.nfe.dto;

import dc.control.converter.ObjectConverter;
import dc.control.enums.CstIpiEn;
import dc.entidade.nfe.NfeDetalheImpIpiEntity;

public class NfeDetalheImpIpiDTO {

	public static synchronized NfeDetalheImpIpiEntity setarValor(
			NfeDetalheImpIpiEntity ndiIpi, String id, Object obj) {
		// TODO ndiIssqnSetarValor

		if (ndiIpi == null) {
			return null;
		}

		switch (id) {
		case "cbCstIpi":
			CstIpiEn cstIpi = (CstIpiEn) obj;
			String s1 = cstIpi.name().substring(1);

			ndiIpi.setCstIpi(s1);

			break;
		case "tfBaseCalculoBcIpi":
			ndiIpi.setValorBaseCalculoIpi(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaIpi":
			ndiIpi.setAliquotaIpi(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfQtdUndTributavelIpi":
			ndiIpi.setQuantidadeUnidadeTributavel(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorUndTributavelIpi":
			ndiIpi.setValorUnidadeTributavel(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorIpi":
			ndiIpi.setValorIpi(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfEnquadramentoIpi":
			ndiIpi.setEnquadramentoIpi((String) obj);

			break;
		case "tfEnquadramentoLegalIpi":
			ndiIpi.setEnquadramentoLegalIpi((String) obj);

			break;
		case "tfCnpjProdutorIpi":
			ndiIpi.setCnpjProdutorIpi((String) obj);

			break;
		case "tfQtdSeloIpi":
			ndiIpi.setQuantidadeSeloIpi(ObjectConverter
					.stringToInteger((String) obj));

			break;
		case "tfCodigoSeloIpi":
			ndiIpi.setCodigoSeloIpi((String) obj);

			break;
		}

		return ndiIpi;
	}

}