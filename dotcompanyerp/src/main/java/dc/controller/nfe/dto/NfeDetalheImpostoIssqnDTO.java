package dc.controller.nfe.dto;

import dc.control.converter.ObjectConverter;
import dc.entidade.nfe.NfeDetalheImpostoIssqnEntity;

public class NfeDetalheImpostoIssqnDTO {

	public static synchronized NfeDetalheImpostoIssqnEntity setarValor(
			NfeDetalheImpostoIssqnEntity ndiIssqn, String id, Object obj) {
		// TODO ndiIssqnSetarValor

		if (ndiIssqn == null) {
			return null;
		}

		switch (id) {
		case "tfBaseCalculoBcIssqn":
			ndiIssqn.setBaseCalculoIssqn(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaIssqn":
			ndiIssqn.setAliquotaIssqn(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorIssqn":
			ndiIssqn.setValorIssqn(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfMunicipioIssqn":
			ndiIssqn.setMunicipioIssqn(ObjectConverter
					.stringToInteger((String) obj));

			break;
		case "tfItemListaServicosIssqn":
			ndiIssqn.setItemListaServicos(ObjectConverter
					.stringToInteger((String) obj));

			break;
		case "tfTributacaoIssqn":
			//ndiIssqn.setTributacaoIssqn((String) obj);

			break;
		}

		return ndiIssqn;
	}

}