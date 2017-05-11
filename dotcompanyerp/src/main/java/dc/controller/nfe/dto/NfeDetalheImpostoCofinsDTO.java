package dc.controller.nfe.dto;

import dc.control.converter.ObjectConverter;
import dc.control.enums.CstCofinsEn;
import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;

public class NfeDetalheImpostoCofinsDTO {

	public static synchronized NfeDetalheImpostoCofinsEntity setarValor(
			NfeDetalheImpostoCofinsEntity ndiCofins, String id, Object obj) {
		// TODO ndiIssqnSetarValor

		if (ndiCofins == null) {
			return null;
		}

		switch (id) {
		case "cbCstCofins":
			CstCofinsEn cstCofins = (CstCofinsEn) obj;
			String s1 = cstCofins.name().substring(1);

			ndiCofins.setCstCofins(s1);

			break;
		case "tfQtdVendidaCofins":
			ndiCofins.setQuantidadeVendida(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfBaseCalculoBcCofins":
			ndiCofins.setBaseCalculoCofins(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaPercentualCofins":
			ndiCofins.setAliquotaCofinsPercentual(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaReaisCofins":
			ndiCofins.setAliquotaCofinsReais(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorCofins":
			ndiCofins.setValorCofins(ObjectConverter
					.stringToValue((String) obj));

			break;
		}

		return ndiCofins;
	}

}