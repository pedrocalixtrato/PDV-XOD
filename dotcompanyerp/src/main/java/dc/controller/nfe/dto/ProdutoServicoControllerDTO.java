package dc.controller.nfe.dto;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.nfe.NfeDetEspecificoCombustivelEntity;
import dc.entidade.nfe.NfeDetEspecificoVeiculoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpIpiEntity;
import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIiEntity;
import dc.entidade.nfe.NfeDetalheImpostoIssqnEntity;
import dc.entidade.nfe.NfeDetalheImpostoPisEntity;
import dc.visao.nfe.ProdutoServicoFormView;

public class ProdutoServicoControllerDTO {

	public static synchronized void setNfeCabecalhoSubView(
			NfeCabecalhoEntity nfeCabecalho, ProdutoServicoFormView subView) {

	}

	public static synchronized void ndiCofinsCarregar(NfeDetalheEntity ent)
			throws Exception {
		/**
		 * COFINS
		 */

		NfeDetalheImpostoCofinsEntity ndiCofins = new NfeDetalheImpostoCofinsEntity();
		ndiCofins.setNfeDetalhe(ent);

		ent.setNfeDetalheImpostoCofins(ndiCofins);
	}

	public static synchronized void ndiIcmsCarregar(NfeDetalheEntity ent)
			throws Exception {
		/**
		 * ICMS
		 */

		NfeDetalheImpostoIcmsEntity ndiIcms = new NfeDetalheImpostoIcmsEntity();
		ndiIcms.setNfeDetalhe(ent);

		ent.setNfeDetalheImpostoIcms(ndiIcms);
	}

	public static synchronized void ndiIiCarregar(NfeDetalheEntity ent)
			throws Exception {
		/**
		 * IMPOSTO IMPORTAÇÃO
		 */

		NfeDetalheImpostoIiEntity ndiIi = new NfeDetalheImpostoIiEntity();
		ndiIi.setNfeDetalhe(ent);

		ent.setNfeDetalheImpostoIi(ndiIi);
	}

	public static synchronized void ndiIpiCarregar(NfeDetalheEntity ent)
			throws Exception {
		/**
		 * IPI
		 */

		NfeDetalheImpIpiEntity ndiIpi = new NfeDetalheImpIpiEntity();
		ndiIpi.setNfeDetalhe(ent);

		ent.setNfeDetalheImpIpi(ndiIpi);
	}

	public static synchronized void ndiIssqnCarregar(NfeDetalheEntity ent)
			throws Exception {
		/**
		 * ISSQN
		 */

		NfeDetalheImpostoIssqnEntity ndiIssqn = new NfeDetalheImpostoIssqnEntity();
		ndiIssqn.setNfeDetalhe(ent);

		ent.setNfeDetalheImpostoIssqn(ndiIssqn);
	}

	public static synchronized void ndiPisCarregar(NfeDetalheEntity ent)
			throws Exception {
		/**
		 * PIS
		 */

		NfeDetalheImpostoPisEntity ndiPis = new NfeDetalheImpostoPisEntity();
		ndiPis.setNfeDetalhe(ent);

		ent.setNfeDetalheImpostoPis(ndiPis);
	}

	public static synchronized void ndeCombustivelCarregar(NfeDetalheEntity ent)
			throws Exception {
		/**
		 * COMBUSTÍVEL
		 */

		NfeDetEspecificoCombustivelEntity ndeCombustivel = new NfeDetEspecificoCombustivelEntity();
		ndeCombustivel.setNfeDetalhe(ent);

		ent.setNfeDetEspecificoCombustivel(ndeCombustivel);
	}

	public static synchronized void ndeVeiculoCarregar(NfeDetalheEntity ent)
			throws Exception {
		/**
		 * VEÍCULO
		 */

		NfeDetEspecificoVeiculoEntity ndeVeiculo = new NfeDetEspecificoVeiculoEntity();
		ndeVeiculo.setNfeDetalhe(ent);

		ent.setNfeDetEspecificoVeiculo(ndeVeiculo);
	}

}