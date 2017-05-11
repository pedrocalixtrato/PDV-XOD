package dc.controller.nfe.dto;

import dc.control.converter.ObjectConverter;
import dc.control.enums.CsosnEn;
import dc.control.enums.CstIcmsEn;
import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;

public class NfeDetalheImpostoIcmsDTO {

	public static synchronized NfeDetalheImpostoIcmsEntity setarValor(
			NfeDetalheImpostoIcmsEntity ndiIcms, String id, Object obj) {
		// TODO ndiIssqnSetarValor

		if (ndiIcms == null) {
			return null;
		}

		switch (id) {
		case "tfOrigemMercadoriaIcms":
			ndiIcms.setOrigemMercadoria((String) obj);

			break;
		case "cbCstIcms":
			CstIcmsEn cstIcms = (CstIcmsEn) obj;
			String s1 = cstIcms.name().substring(1);

			ndiIcms.setCstIcms(s1);

			break;
		case "tfCsosnIcms":
			CsosnEn csosn = (CsosnEn) obj;
			String s2 = csosn.name().substring(1);

			ndiIcms.setCsosn(s2);

			break;
		case "tfModalidadeBcIcms":
			ndiIcms.setModalidadeBcIcms((String) obj);

			break;
		case "tfTaxaReducaoBcIcms":
			ndiIcms.setTaxaReducaoBcIcms(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfBaseCalculoBcIcms":
			ndiIcms.setBaseCalculoIcms(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaIcms":
			ndiIcms.setAliquotaIcms(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorIcms":
			ndiIcms.setValorIcms(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfMotivoDesoneracaoIcms":
			ndiIcms.setMotivoDesoneracaoIcms((String) obj);

			break;
		case "tfModalidadeBcStIcms":
			ndiIcms.setModalidadeBcIcmsSt((String) obj);

			break;
		case "tfPercentualMvaStIcms":
			ndiIcms.setPercentualMvaIcmsSt(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfTaxaReducaoBcStIcms":
			ndiIcms.setPercentualReducaoBcIcmsSt(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfBaseCalculoStIcms":
			ndiIcms.setValorBaseCalculoIcmsSt(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaStIcms":
			ndiIcms.setAliquotaIcmsSt(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorStIcms":
			ndiIcms.setValorIcmsSt(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfBcStRetidoIcms":
			ndiIcms.setValorBcIcmsStRetido(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorStRetidoIcms":
			ndiIcms.setValorIcmsStRetido(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfBcStDestinoIcms":
			ndiIcms.setValorBcIcmsStDestino(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorStDestinoIcms":
			ndiIcms.setValorIcmsStDestino(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaCreditoSnIcms":
			ndiIcms.setAliquotaCreditoIcmsSn(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorCreditoSnIcms":
			ndiIcms.setValorCreditoIcmsSn(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfPercentualBcOperacaoPropriaIcms":
			ndiIcms.setPercentualBcOperacaoPropria(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfUfStIcms":
			ndiIcms.setUfSt((String) obj);

			break;
		}

		return ndiIcms;
	}

}