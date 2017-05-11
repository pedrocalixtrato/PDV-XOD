package dc.controller.nfe.dto;

import dc.control.converter.ObjectConverter;
import dc.entidade.nfe.NfeDetalheImpostoIiEntity;

public class NfeDetalheImpostoIiDTO {

	public static synchronized NfeDetalheImpostoIiEntity setarValor(
			NfeDetalheImpostoIiEntity ndiIi, String id, Object obj) {
		// TODO ndiIssqnSetarValor

		if (ndiIi == null) {
			return null;
		}

		switch (id) {
		case "tfBaseCalculoBcImpostoImportacao":
			ndiIi.setValorBcIi(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfDespesasAduaneirasImpostoImportacao":
			ndiIi.setValorDespesasAduaneiras(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorImpostoImportacao":
			ndiIi.setValorImpostoImportacao(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfIofImpostoImportacao":
			ndiIi.setValorIof(ObjectConverter.stringToValue((String) obj));

			break;
		}

		return ndiIi;
	}

}