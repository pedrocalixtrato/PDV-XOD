package dc.controller.nfe.dto;

import dc.control.converter.ObjectConverter;
import dc.control.enums.CstPisEn;
import dc.entidade.nfe.NfeDetalheImpostoPisEntity;

public class NfeDetalheImpostoPisDTO {

	public static synchronized NfeDetalheImpostoPisEntity setarValor(
			NfeDetalheImpostoPisEntity ndiPis, String id, Object obj) {
		// TODO ndiPisSetarValor

		if (ndiPis == null) {
			return null;
		}

		switch (id) {
		case "cbCstPis":
			CstPisEn cstPis = (CstPisEn) obj;
			String s1 = cstPis.name().substring(1);

			ndiPis.setCstPis(s1);

			break;
		case "tfQtdVendidaPis":
			ndiPis.setQuantidadeVendida(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfBaseCalculoBcPis":
			ndiPis.setValorBaseCalculoPis(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaPercentualPis":
			ndiPis.setAliquotaPisPercentual(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaReaisPis":
			ndiPis.setAliquotaPisReais(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorPis":
			ndiPis.setValorPis(ObjectConverter.stringToValue((String) obj));

			break;
		}

		return ndiPis;
	}

}