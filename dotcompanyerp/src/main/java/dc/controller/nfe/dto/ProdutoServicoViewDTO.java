package dc.controller.nfe.dto;

import java.io.Serializable;

import dc.control.converter.ObjectConverter;
import dc.control.enums.CsosnEn;
import dc.control.enums.CstIcmsEn;
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
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.visao.nfe.ProdutoServicoFormView;

public class ProdutoServicoViewDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static synchronized void setNfeCabecalhoSubView(
			NfeCabecalhoEntity nfeCabecalho, ProdutoServicoFormView subView) {
		OperacaoFiscalEntity operacaoFiscal = nfeCabecalho.getTributOperacaoFiscal();

		if (operacaoFiscal != null) {
			subView.getMtoOperacaoFiscal().setValue(operacaoFiscal);
			// this.subView.getTfOperacaoFiscalId().setValue(
			// operacaoFiscal.getDescricaoNaNF());
		}

		// this.subView.getTfVenda().setValue(nfeCabecalho.getVendaCabecalho().toString());
		subView.getTfModeloNotaFiscal().setValue(
				nfeCabecalho.getCodigoModelo().trim());
		subView.getTfNaturezaOperacao().setValue(
				nfeCabecalho.getNaturezaOperacao().trim());
		subView.getTfChaveAcesso().setValue(
				nfeCabecalho.getChaveAcesso().trim());
		
		//subView.getTfDigitoChaveAcesso().setValue(nfeCabecalho.getDigitoChaveAcesso().trim());
		
		//subView.getTfCodigoNumerico().setValue(nfeCabecalho.getCodigoNumerico());		
		//subView.getTfSerie().setValue(nfeCabecalho.getSerie());
		
		//subView.getTfNumero().setValue(nfeCabecalho.getNumero().trim());
		subView.getPdfDataEmissao().setValue(nfeCabecalho.getDataHoraEmissao());
		subView.getPdfDataEntradaSaida().setValue(
				nfeCabecalho.getDataHoraEntradaSaida());
		
		//subView.getTfTipoOperacao().setValue(nfeCabecalho.getTipoOperacao().trim());
		// this.subView.getTfTipoEmissao().setValue(
		// this.nfeCabecalho.getTipoEmissao().trim());
		// this.subView.getTfFinalidadeEmissao().setValue(
		// this.nfeCabecalho.getFinalidadeEmissao().trim());
		// this.subView.getTfFormatoImpressaoDanfe().setValue(
		// this.nfeCabecalho.getFormatoImpressaoDanfe().trim());
		// this.subView.getTfFormaPagamento().setValue(
		// this.nfeCabecalho.getIndicadorFormaPagamento().trim());

		//
		subView.getPlNfeDetalheSubForm().setCaption(
				"NFE CABEÇALHO " + nfeCabecalho.getNumero());
	}

	public static synchronized void setNfeDestinatarioSubView(
			NfeCabecalhoEntity nfeCabecalho, ProdutoServicoFormView subView)
			throws Exception {
		/*
		 * subView.getTfEmailDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getEmail());
		 * subView.getTfSuframaDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getSuframa());
		 * subView.getTfTelefoneDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getTelefone());
		 * subView.getTfInscricaoEstadualDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getInscricaoEstadual());
		 * subView.getTfUfDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getUf()); //
		 * this.subView.getTfCidadeDestinatario
		 * ().setValue(this.nfeCabecalho.getNfeDestinatario().get); //
		 * this.subView
		 * .getTfCodigoIbgeDestinatario().setValue(this.nfeCabecalho.
		 * getNfeDestinatario().getco);
		 * subView.getTfBairroLogradouroDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getBairro());
		 * subView.getTfLogradouroComplementoDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getComplemento());
		 * subView.getTfLogradouroNumeroDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getNumero());
		 * subView.getTfLogradouroDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getLogradouro());
		 * subView.getTfCepDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getCep());
		 * subView.getTfRazaoSocialDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getRazaoSocial());
		 * subView.getTfCpfCnpjDestinatario().setValue(
		 * nfeCabecalho.getNfeDestinatario().getCpfCnpj()); //
		 * this.subView.getTfIdDestinatario().setValue();
		 * 
		 * Cliente cliente = nfeCabecalho.getCliente();
		 * 
		 * if (cliente != null) {
		 * subView.getMtoCliente().setValue(nfeCabecalho.getCliente()); }
		 */
	}

	public static synchronized void setNfeDetalheListSubView(
			NfeCabecalhoEntity nfeCabecalho, ProdutoServicoFormView subView)
			throws Exception {
		subView.carregarSfNfeDetalhe(nfeCabecalho.getNfeDetalheList());
	}

	public static synchronized void setNfeDetalheSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		subView.getTfNumeroItem().setValue(
				nfeDetalhe.getNumeroItem().toString());
		subView.getTfGtin().setValue(nfeDetalhe.getGtin());

		if (nfeDetalhe.getProduto() != null) {
			subView.getMtoProduto().setValue(nfeDetalhe.getProduto());
			subView.getTfCodigoProduto().setValue(
					nfeDetalhe.getProduto().getCodigoInterno());
		} else {
			subView.getTfCodigoProduto().setValue("");
		}

		subView.getTfNcm().setValue(nfeDetalhe.getNcm());
		subView.getTfExTipi().setValue(nfeDetalhe.getExTipi().toString());
		subView.getTfCfop().setValue(nfeDetalhe.getCfop().toString());
		subView.getTfUnidadeComercial().setValue(
				nfeDetalhe.getUnidadeComercial());
		subView.getTfQuantidadeComercial().setValue(
				ObjectConverter.valueToString(nfeDetalhe
						.getQuantidadeComercial()));
		subView.getTfValorUnitarioComercial().setValue(
				nfeDetalhe.getValorUnitarioComercial().toString());
		subView.getTfValorBrutoProduto().setValue(
				nfeDetalhe.getValorBrutoProduto().toString());
		subView.getTfGtinUnidadeTributavel().setValue(
				nfeDetalhe.getGtinUnidadeTributavel());
		subView.getTfUnidadeTributavel().setValue(
				nfeDetalhe.getUnidadeTributavel());
		subView.getTfQuantidadeTributavel().setValue(
				nfeDetalhe.getQuantidadeTributavel().toString());
		subView.getTfValorUnitarioTributavel().setValue(
				nfeDetalhe.getValorUnitarioTributavel().toString());
		subView.getTfValorFrete().setValue(
				nfeDetalhe.getValorFrete().toString());
		subView.getTfValorSeguro().setValue(
				nfeDetalhe.getValorSeguro().toString());
		subView.getTfValorDesconto().setValue(
				nfeDetalhe.getValorDesconto().toString());
		subView.getTfValorOutrasDespesas().setValue(
				nfeDetalhe.getValorOutrasDespesas().toString());
		//subView.getTfEntraTotal().setValue(nfeDetalhe.getEntraTotal());
		subView.getTfValorSubtotal().setValue(
				nfeDetalhe.getValorSubtotal().toString());
		subView.getTfValorTotal().setValue(
				nfeDetalhe.getValorTotal().toString());
		subView.getTfNumeroPedidoCompra().setValue(
				nfeDetalhe.getNumeroPedidoCompra());
		subView.getTfItemPedidoCompra().setValue(
				nfeDetalhe.getItemPedidoCompra().toString());
		subView.getTfInformacoesAdicionais().setValue(
				nfeDetalhe.getInformacoesAdicionais());
	}

	public static synchronized void setNdiCofinsSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetalheImpostoCofinsEntity ndiCofins = nfeDetalhe
				.getNfeDetalheImpostoCofins();

		// if (entCofins == null) {
		// entCofins = new NfeDetalheImpostoCofinsEntity();
		// entCofins.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoCofins(
		// entCofins);
		// }

		// subView.getNdiCofinsFormView().getPlNdiCofins()
		// .setCaption("NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		// this.subView.getTfCstCofins().setValue(
		// entCofins.getCstCofins().trim());
		subView.getNdiCofinsFormView().getTfQtdVendidaCofins()
				.setValue(ndiCofins.getQuantidadeVendida().toString().trim());
		subView.getNdiCofinsFormView().getTfBaseCalculoBcCofins()
				.setValue(ndiCofins.getBaseCalculoCofins().toString().trim());
		subView.getNdiCofinsFormView()
				.getTfAliquotaPercentualCofins()
				.setValue(
						ndiCofins.getAliquotaCofinsPercentual().toString()
								.trim());
		subView.getNdiCofinsFormView().getTfAliquotaReaisCofins()
				.setValue(ndiCofins.getAliquotaCofinsReais().toString().trim());
		subView.getNdiCofinsFormView().getTfValorCofins()
				.setValue(ndiCofins.getValorCofins().toString().trim());
	}

	public static synchronized void setNdiIcmsSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetalheImpostoIcmsEntity entIcms = nfeDetalhe
				.getNfeDetalheImpostoIcms();

		// if (entIcms == null) {
		// entIcms = new NfeDetalheImpostoIcmsEntity();
		// entIcms.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIcms(entIcms);
		// }

		// subView.getPlNdiIcms().setCaption(
		// "NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getNdiIcmsFormView().getTfOrigemMercadoriaIcms()
				.setValue(entIcms.getOrigemMercadoria().trim());

		// this.subView.getCbCstIcms().setValue(entIcms.getCstIcms() == null
		// ? null : CstIcmsEn.valueOf("_"
		// + entIcms.getCstIcms()));

		if (!entIcms.getCstIcms().isEmpty()) {
			CstIcmsEn cstIcms = CstIcmsEn.valueOf("_" + entIcms.getCstIcms());

			subView.getNdiIcmsFormView().getCbCstIcms().setValue(cstIcms);
		} else {
			subView.getNdiIcmsFormView().getCbCstIcms().setValue(null);
		}

		if (!entIcms.getCsosn().isEmpty()) {
			CsosnEn csosn = CsosnEn.valueOf("_" + entIcms.getCsosn());

			subView.getNdiIcmsFormView().getCbCsosnIcms().setValue(csosn);
		} else {
			subView.getNdiIcmsFormView().getCbCsosnIcms().setValue(null);
		}

		subView.getNdiIcmsFormView().getTfModalidadeBcIcms()
				.setValue(entIcms.getModalidadeBcIcms().trim());
		subView.getNdiIcmsFormView().getTfTaxaReducaoBcIcms()
				.setValue(entIcms.getTaxaReducaoBcIcms().toString().trim());
		subView.getNdiIcmsFormView().getTfBaseCalculoBcIcms()
				.setValue(entIcms.getBaseCalculoIcms().toString().trim());
		subView.getNdiIcmsFormView().getTfAliquotaIcms()
				.setValue(entIcms.getAliquotaIcms().toString().trim());
		subView.getNdiIcmsFormView().getTfValorIcms()
				.setValue(entIcms.getValorIcms().toString().trim());
		subView.getNdiIcmsFormView().getTfMotivoDesoneracaoIcms()
				.setValue(entIcms.getMotivoDesoneracaoIcms().trim());
		subView.getNdiIcmsFormView().getTfModalidadeBcStIcms()
				.setValue(entIcms.getModalidadeBcIcmsSt().trim());
		subView.getNdiIcmsFormView().getTfPercentualMvaStIcms()
				.setValue(entIcms.getPercentualMvaIcmsSt().toString().trim());
		// this.subView.getTfTaxaReducaoBcStIcms().setValue(entIcms.get);
		subView.getNdiIcmsFormView()
				.getTfBaseCalculoStIcms()
				.setValue(entIcms.getValorBaseCalculoIcmsSt().toString().trim());
		subView.getNdiIcmsFormView().getTfAliquotaStIcms()
				.setValue(entIcms.getAliquotaIcmsSt().toString().trim());
		subView.getNdiIcmsFormView().getTfValorStIcms()
				.setValue(entIcms.getValorIcmsSt().toString().trim());
		subView.getNdiIcmsFormView().getTfBcStRetidoIcms()
				.setValue(entIcms.getValorBcIcmsStRetido().toString().trim());
		subView.getNdiIcmsFormView().getTfValorStRetidoIcms()
				.setValue(entIcms.getValorIcmsStRetido().toString().trim());
		subView.getNdiIcmsFormView().getTfBcStDestinoIcms()
				.setValue(entIcms.getValorBcIcmsStDestino().toString().trim());
		subView.getNdiIcmsFormView().getTfValorStDestinoIcms()
				.setValue(entIcms.getValorIcmsStDestino().toString().trim());
		subView.getNdiIcmsFormView().getTfAliquotaCreditoSnIcms()
				.setValue(entIcms.getAliquotaCreditoIcmsSn().toString().trim());
		subView.getNdiIcmsFormView().getTfValorCreditoSnIcms()
				.setValue(entIcms.getValorCreditoIcmsSn().toString().trim());
		subView.getNdiIcmsFormView()
				.getTfPercentualBcOperacaoPropriaIcms()
				.setValue(
						entIcms.getPercentualBcOperacaoPropria().toString()
								.trim());
		subView.getNdiIcmsFormView().getTfUfStIcms()
				.setValue(entIcms.getUfSt().trim());
	}

	public static synchronized void setNdiIiSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetalheImpostoIiEntity entIi = nfeDetalhe.getNfeDetalheImpostoIi();

		// if (entIi == null) {
		// entIi = new NfeDetalheImpostoIiEntity();
		// entIi.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIi(entIi);
		// }

		// subView.getPlNdiIi().setCaption(
		// "NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getNdiIiFormView().getTfBaseCalculoBcImpostoImportacao()
				.setValue(entIi.getValorBcIi().toString().trim());
		subView.getNdiIiFormView().getTfDespesasAduaneirasImpostoImportacao()
				.setValue(entIi.getValorDespesasAduaneiras().toString().trim());
		subView.getNdiIiFormView().getTfValorImpostoImportacao()
				.setValue(entIi.getValorImpostoImportacao().toString().trim());
		subView.getNdiIiFormView().getTfIofImpostoImportacao()
				.setValue(entIi.getValorIof().toString().trim());
	}

	public static synchronized void setNdiIpiSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		// NfeDetalheImpostoIpiEntity ndiIpi = new
		// NfeDetalheImpostoIpiEntity();

		NfeDetalheImpIpiEntity ndiIpi = nfeDetalhe.getNfeDetalheImpIpi();

		// if (ndiIpi == null) {
		// ndiIpi = new NfeDetalheImpIpiEntity();
		// ndiIpi.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpIpi(ndiIpi);
		// }

		// subView.getNdiIpiFormView().getPlNdiIpi()
		// .setCaption("NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		// this.subView.getTfCstIpi().setValue(ndiIpi.getCstIpi());
		subView.getNdiIpiFormView().getTfBaseCalculoBcIpi()
				.setValue(ndiIpi.getValorBaseCalculoIpi().toString());
		subView.getNdiIpiFormView().getTfAliquotaIpi()
				.setValue(ndiIpi.getAliquotaIpi().toString());
		subView.getNdiIpiFormView().getTfQtdUndTributavelIpi()
				.setValue(ndiIpi.getQuantidadeUnidadeTributavel().toString());
		subView.getNdiIpiFormView().getTfValorUndTributavelIpi()
				.setValue(ndiIpi.getValorUnidadeTributavel().toString());
		subView.getNdiIpiFormView().getTfValorIpi()
				.setValue(ndiIpi.getValorIpi().toString());
		subView.getNdiIpiFormView().getTfEnquadramentoIpi()
				.setValue(ndiIpi.getEnquadramentoIpi());
		subView.getNdiIpiFormView().getTfEnquadramentoLegalIpi()
				.setValue(ndiIpi.getEnquadramentoLegalIpi());
		subView.getNdiIpiFormView().getTfCnpjProdutorIpi()
				.setValue(ndiIpi.getCnpjProdutorIpi());
		subView.getNdiIpiFormView().getTfQtdSeloIpi()
				.setValue(ndiIpi.getQuantidadeSeloIpi().toString());
		subView.getNdiIpiFormView().getTfCodigoSeloIpi()
				.setValue(ndiIpi.getCodigoSeloIpi());
	}

	public static synchronized void setNdiIssqnSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetalheImpostoIssqnEntity entIssqn = nfeDetalhe
				.getNfeDetalheImpostoIssqn();

		// if (entIssqn == null) {
		// entIssqn = new NfeDetalheImpostoIssqnEntity();
		// entIssqn.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIssqn(
		// entIssqn);
		// }

		// subView.getPlNdiIssqn().setCaption(
		// "NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getNdiIssqnFormView().getTfBaseCalculoBcIssqn()
				.setValue(entIssqn.getBaseCalculoIssqn().toString().trim());
		subView.getNdiIssqnFormView().getTfAliquotaIssqn()
				.setValue(entIssqn.getAliquotaIssqn().toString().trim());
		subView.getNdiIssqnFormView().getTfValorIssqn()
				.setValue(entIssqn.getValorIssqn().toString().trim());
		subView.getNdiIssqnFormView().getTfMunicipioIssqn()
				.setValue(entIssqn.getMunicipioIssqn().toString().trim());
		subView.getNdiIssqnFormView().getTfItemListaServicosIssqn()
				.setValue(entIssqn.getItemListaServicos().toString().trim());
		//subView.getNdiIssqnFormView().getTfTributacaoIssqn().setValue(entIssqn.getTributacaoIssqn().trim());
	}

	public static synchronized void setNdiPisSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetalheImpostoPisEntity entPis = nfeDetalhe
				.getNfeDetalheImpostoPis();

		// if (entPis == null) {
		// entPis = new NfeDetalheImpostoPisEntity();
		// entPis.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoPis(entPis);
		// }

		// this.subView.getTfCstPis().setValue(entPis.getCstPis().trim());

		// subView.getNdiPisFormView().getPlNdiPis().setCaption(
		// "NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getNdiPisFormView().getTfQtdVendidaPis()
				.setValue(entPis.getQuantidadeVendida().toString().trim());
		subView.getNdiPisFormView().getTfBaseCalculoBcPis()
				.setValue(entPis.getValorBaseCalculoPis().toString().trim());
		subView.getNdiPisFormView().getTfAliquotaPercentualPis()
				.setValue(entPis.getAliquotaPisPercentual().toString().trim());
		subView.getNdiPisFormView().getTfAliquotaReaisPis()
				.setValue(entPis.getAliquotaPisReais().toString().trim());
		subView.getNdiPisFormView().getTfValorPis()
				.setValue(entPis.getValorPis().toString().trim());

	}

	public static synchronized void setNdeCombustivelSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetEspecificoCombustivelEntity entCombustivel = nfeDetalhe
				.getNfeDetEspecificoCombustivel();

		// if (entCombustivel == null) {
		// entCombustivel = new NfeDetEspecificoCombustivelEntity();
		// entCombustivel.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe()
		// .setNfeDetEspecificoCombustivel(entCombustivel);
		// }

		// subView.getPlNdeCombustivel().setCaption(
		// "NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getNdeCombustivelFormView().getTfCodigoAnpCombustivel()
				.setValue(entCombustivel.getCodigoAnp().toString().trim());
		subView.getNdeCombustivelFormView().getTfCodifCombustivel()
				.setValue(entCombustivel.getCodif().trim());
		subView.getNdeCombustivelFormView()
				.getTfQtdeTempAmbienteCombustivel()
				.setValue(
						entCombustivel.getQuantidadeTempAmbiente().toString()
								.trim());
		subView.getNdeCombustivelFormView().getTfUfConsumoCombustivel()
				.setValue(entCombustivel.getUfConsumo().trim());
		subView.getNdeCombustivelFormView()
				.getTfBcCideCombustivel()
				.setValue(entCombustivel.getBaseCalculoCide().toString().trim());
		subView.getNdeCombustivelFormView().getTfAliquotaCideCombustivel()
				.setValue(entCombustivel.getAliquotaCide().toString().trim());
		subView.getNdeCombustivelFormView().getTfValorCideCombustivel()
				.setValue(entCombustivel.getValorCide().toString().trim());
	}

	public static synchronized void setNdeVeiculoSubView(
			NfeDetalheEntity nfeDetalhe, ProdutoServicoFormView subView)
			throws Exception {
		NfeDetEspecificoVeiculoEntity entVeiculo = nfeDetalhe
				.getNfeDetEspecificoVeiculo();

		// if (entVeiculo == null) {
		// entVeiculo = new NfeDetEspecificoVeiculoEntity();
		// entVeiculo.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

		// this.nfeCabecalho.getNfeDetalhe().setNfeDetEspecificoVeiculo(
		// entVeiculo);
		// }

		// subView.getPlNdeVeiculo().setCaption(
		// "NFE DETALHE: " + nfeDetalhe.getNumeroItem());

		subView.getNdeVeiculoFormView().getTfTipoOperacaoVeiculo()
				.setValue(entVeiculo.getTipoOperacao());
		subView.getNdeVeiculoFormView().getTfChassiVeiculo()
				.setValue(entVeiculo.getChassi());
		subView.getNdeVeiculoFormView().getTfCodigoCorVeiculo()
				.setValue(entVeiculo.getCodigoCor());
		subView.getNdeVeiculoFormView().getTfDescricaoCorVeiculo()
				.setValue(entVeiculo.getDescricaoCor());
		subView.getNdeVeiculoFormView().getTfPotenciaMotorVeiculo()
				.setValue(entVeiculo.getPotenciaMotor());
		subView.getNdeVeiculoFormView().getTfCilindradasVeiculo()
				.setValue(entVeiculo.getCilindradas());
		subView.getNdeVeiculoFormView().getTfPesoLiquidoVeiculo()
				.setValue(entVeiculo.getPesoLiquido());
		subView.getNdeVeiculoFormView().getTfPesoBrutoVeiculo()
				.setValue(entVeiculo.getPesoBruto());
		subView.getNdeVeiculoFormView().getTfNumeroSerieVeiculo()
				.setValue(entVeiculo.getNumeroSerie());
		subView.getNdeVeiculoFormView().getTfCombustivelVeiculo()
				.setValue(entVeiculo.getTipoCombustivel());
		subView.getNdeVeiculoFormView().getTfNumeroMotorVeiculo()
				.setValue(entVeiculo.getNumeroMotor());
		subView.getNdeVeiculoFormView().getTfCapacidadeTracaoVeiculo()
				.setValue(entVeiculo.getCapacidadeMaximaTracao());
		subView.getNdeVeiculoFormView().getTfDistanciaEixosVeiculo()
				.setValue(entVeiculo.getDistanciaEixos());
		subView.getNdeVeiculoFormView().getTfAnoModeloVeiculo()
				.setValue(entVeiculo.getAnoModelo());
		subView.getNdeVeiculoFormView().getTfAnoFabricacaoVeiculo()
				.setValue(entVeiculo.getAnoFabricacao());
		subView.getNdeVeiculoFormView().getTfTipoPinturaVeiculo()
				.setValue(entVeiculo.getTipoPintura());
		subView.getNdeVeiculoFormView().getTfTipoVeiculo()
				.setValue(entVeiculo.getTipoVeiculo());
		subView.getNdeVeiculoFormView().getTfEspecieVeiculo()
				.setValue(entVeiculo.getEspecieVeiculo());
		subView.getNdeVeiculoFormView().getTfCondicaoVinVeiculo()
				.setValue(entVeiculo.getCondicaoVin());
		subView.getNdeVeiculoFormView().getTfCondicaoVeiculo()
				.setValue(entVeiculo.getCondicaoVeiculo());
		subView.getNdeVeiculoFormView().getTfCodigoMarcaModeloVeiculo()
				.setValue(entVeiculo.getCodigoMarcaModelo());
		subView.getNdeVeiculoFormView().getTfCodigoCorDenatranVeiculo()
				.setValue(entVeiculo.getCor());
		subView.getNdeVeiculoFormView().getTfLotacaoVeiculo()
				.setValue(entVeiculo.getLotacao().toString());
		subView.getNdeVeiculoFormView().getTfRestricaoVeiculo()
				.setValue(entVeiculo.getRestricao());
	}

	/**
	 * LIMPAR
	 * 
	 * @param item
	 */

	public static synchronized void subViewClean(ProdutoServicoFormView subView) {
		NfeDetalheEntity nfeDetalhe = new NfeDetalheEntity();

		subView.getTfNumeroItem().setValue(
				nfeDetalhe.getNumeroItem().toString());
		subView.getTfCodigoProduto().setValue(nfeDetalhe.getCodigoProduto());
		subView.getTfGtin().setValue(nfeDetalhe.getGtin());
		// this.subView.getTfNomeProduto().setValue(nfeDetalhe.getNomeProduto());
		// this.subView.getCbLivro().setValue(this.pEntity.getLivro());
		// this.subView.getMtoProduto().setValue(new Produto());

		// this.subView.getMtoProduto().setValue(nfeDetalhe.getProduto());
		subView.getTfNcm().setValue(nfeDetalhe.getNcm());
		subView.getTfExTipi().setValue(nfeDetalhe.getExTipi().toString());
		subView.getTfCfop().setValue(nfeDetalhe.getCfop().toString());
		subView.getTfUnidadeComercial().setValue(
				nfeDetalhe.getUnidadeComercial());
		subView.getTfQuantidadeComercial().setValue(
				nfeDetalhe.getQuantidadeComercial().toString());
		subView.getTfValorUnitarioComercial().setValue(
				nfeDetalhe.getValorUnitarioComercial().toString());
		subView.getTfValorBrutoProduto().setValue(
				nfeDetalhe.getValorBrutoProduto().toString());
		subView.getTfGtinUnidadeTributavel().setValue(
				nfeDetalhe.getGtinUnidadeTributavel());
		subView.getTfUnidadeTributavel().setValue(
				nfeDetalhe.getUnidadeTributavel());
		subView.getTfQuantidadeTributavel().setValue(
				nfeDetalhe.getQuantidadeTributavel().toString());
		subView.getTfValorUnitarioTributavel().setValue(
				nfeDetalhe.getValorUnitarioTributavel().toString());
		subView.getTfValorFrete().setValue(
				nfeDetalhe.getValorFrete().toString());
		subView.getTfValorSeguro().setValue(
				nfeDetalhe.getValorSeguro().toString());
		subView.getTfValorDesconto().setValue(
				nfeDetalhe.getValorDesconto().toString());
		subView.getTfValorOutrasDespesas().setValue(
				nfeDetalhe.getValorOutrasDespesas().toString());
		//subView.getTfEntraTotal().setValue(nfeDetalhe.getEntraTotal());
		subView.getTfValorSubtotal().setValue(nfeDetalhe.getValorSubtotal().toString());
		subView.getTfValorTotal().setValue(
				nfeDetalhe.getValorTotal().toString());
		subView.getTfNumeroPedidoCompra().setValue(
				nfeDetalhe.getNumeroPedidoCompra());
		subView.getTfItemPedidoCompra().setValue(
				nfeDetalhe.getItemPedidoCompra().toString());
		subView.getTfInformacoesAdicionais().setValue(
				nfeDetalhe.getInformacoesAdicionais());

		/**
		 * 
		 */

		NfeDetalheImpostoCofinsEntity ndiCofins = new NfeDetalheImpostoCofinsEntity();

		// this.subView.getTfCstCofins().setValue(entCofins.getCstCofins());
		subView.getNdiCofinsFormView().getTfQtdVendidaCofins()
				.setValue(ndiCofins.getQuantidadeVendida().toString());
		subView.getNdiCofinsFormView().getTfBaseCalculoBcCofins()
				.setValue(ndiCofins.getBaseCalculoCofins().toString());
		subView.getNdiCofinsFormView().getTfAliquotaPercentualCofins()
				.setValue(ndiCofins.getAliquotaCofinsPercentual().toString());
		subView.getNdiCofinsFormView().getTfAliquotaReaisCofins()
				.setValue(ndiCofins.getAliquotaCofinsReais().toString());
		subView.getNdiCofinsFormView().getTfValorCofins()
				.setValue(ndiCofins.getValorCofins().toString());

		/**
		 * 
		 */

		NfeDetalheImpostoIcmsEntity entIcms = new NfeDetalheImpostoIcmsEntity();

		subView.getNdiIcmsFormView().getTfOrigemMercadoriaIcms()
				.setValue(entIcms.getOrigemMercadoria());
		// this.subView.getTfCstIcms().setValue(entIcms.getCstIcms());
		// this.subView.getTfCsosnIcms().setValue(entIcms.getCsosn());
		subView.getNdiIcmsFormView().getTfModalidadeBcIcms()
				.setValue(entIcms.getModalidadeBcIcms());
		subView.getNdiIcmsFormView().getTfTaxaReducaoBcIcms()
				.setValue(entIcms.getTaxaReducaoBcIcms().toString());
		subView.getNdiIcmsFormView().getTfBaseCalculoBcIcms()
				.setValue(entIcms.getBaseCalculoIcms().toString());
		subView.getNdiIcmsFormView().getTfAliquotaIcms()
				.setValue(entIcms.getAliquotaIcms().toString());
		subView.getNdiIcmsFormView().getTfValorIcms()
				.setValue(entIcms.getValorIcms().toString());
		subView.getNdiIcmsFormView().getTfMotivoDesoneracaoIcms()
				.setValue(entIcms.getMotivoDesoneracaoIcms());
		subView.getNdiIcmsFormView().getTfModalidadeBcStIcms()
				.setValue(entIcms.getModalidadeBcIcmsSt());
		subView.getNdiIcmsFormView().getTfPercentualMvaStIcms()
				.setValue(entIcms.getPercentualMvaIcmsSt().toString());
		// this.subView.getTfTaxaReducaoBcStIcms().setValue(entIcms.get);
		subView.getNdiIcmsFormView().getTfBaseCalculoStIcms()
				.setValue(entIcms.getValorBaseCalculoIcmsSt().toString());
		subView.getNdiIcmsFormView().getTfAliquotaStIcms()
				.setValue(entIcms.getAliquotaIcmsSt().toString());
		subView.getNdiIcmsFormView().getTfValorStIcms()
				.setValue(entIcms.getValorIcmsSt().toString());
		subView.getNdiIcmsFormView().getTfBcStRetidoIcms()
				.setValue(entIcms.getValorBcIcmsStRetido().toString());
		subView.getNdiIcmsFormView().getTfValorStRetidoIcms()
				.setValue(entIcms.getValorIcmsStRetido().toString());
		subView.getNdiIcmsFormView().getTfBcStDestinoIcms()
				.setValue(entIcms.getValorBcIcmsStDestino().toString());
		subView.getNdiIcmsFormView().getTfValorStDestinoIcms()
				.setValue(entIcms.getValorIcmsStDestino().toString());
		subView.getNdiIcmsFormView().getTfAliquotaCreditoSnIcms()
				.setValue(entIcms.getAliquotaCreditoIcmsSn().toString());
		subView.getNdiIcmsFormView().getTfValorCreditoSnIcms()
				.setValue(entIcms.getValorCreditoIcmsSn().toString());
		subView.getNdiIcmsFormView().getTfPercentualBcOperacaoPropriaIcms()
				.setValue(entIcms.getPercentualBcOperacaoPropria().toString());
		subView.getNdiIcmsFormView().getTfUfStIcms()
				.setValue(entIcms.getUfSt());

		/**
		 * 
		 */

		NfeDetalheImpostoIiEntity ndiIi = new NfeDetalheImpostoIiEntity();

		subView.getNdiIiFormView().getTfBaseCalculoBcImpostoImportacao()
				.setValue(ndiIi.getValorBcIi().toString());
		subView.getNdiIiFormView().getTfDespesasAduaneirasImpostoImportacao()
				.setValue(ndiIi.getValorDespesasAduaneiras().toString());
		subView.getNdiIiFormView().getTfValorImpostoImportacao()
				.setValue(ndiIi.getValorImpostoImportacao().toString());
		subView.getNdiIiFormView().getTfIofImpostoImportacao()
				.setValue(ndiIi.getValorIof().toString());

		/**
		 * 
		 */

		NfeDetalheImpIpiEntity ndiIpi = new NfeDetalheImpIpiEntity();

		// this.subView.getTfCstIpi().setValue(ndiIpi.getCstIpi());
		subView.getNdiIpiFormView().getTfBaseCalculoBcIpi()
				.setValue(ndiIpi.getValorBaseCalculoIpi().toString());
		subView.getNdiIpiFormView().getTfAliquotaIpi()
				.setValue(ndiIpi.getAliquotaIpi().toString());
		subView.getNdiIpiFormView().getTfQtdUndTributavelIpi()
				.setValue(ndiIpi.getQuantidadeUnidadeTributavel().toString());
		subView.getNdiIpiFormView().getTfValorUndTributavelIpi()
				.setValue(ndiIpi.getValorUnidadeTributavel().toString());
		subView.getNdiIpiFormView().getTfValorIpi()
				.setValue(ndiIpi.getValorIpi().toString());
		subView.getNdiIpiFormView().getTfEnquadramentoIpi()
				.setValue(ndiIpi.getEnquadramentoIpi());
		subView.getNdiIpiFormView().getTfEnquadramentoLegalIpi()
				.setValue(ndiIpi.getEnquadramentoLegalIpi());
		subView.getNdiIpiFormView().getTfCnpjProdutorIpi()
				.setValue(ndiIpi.getCnpjProdutorIpi());
		subView.getNdiIpiFormView().getTfQtdSeloIpi()
				.setValue(ndiIpi.getQuantidadeSeloIpi().toString());
		subView.getNdiIpiFormView().getTfCodigoSeloIpi()
				.setValue(ndiIpi.getCodigoSeloIpi());

		/**
		 * 
		 */

		NfeDetalheImpostoIssqnEntity ndiIssqn = new NfeDetalheImpostoIssqnEntity();

		subView.getNdiIssqnFormView().getTfBaseCalculoBcIssqn()
				.setValue(ndiIssqn.getBaseCalculoIssqn().toString());
		subView.getNdiIssqnFormView().getTfAliquotaIssqn()
				.setValue(ndiIssqn.getAliquotaIssqn().toString());
		subView.getNdiIssqnFormView().getTfValorIssqn()
				.setValue(ndiIssqn.getValorIssqn().toString());
		subView.getNdiIssqnFormView().getTfMunicipioIssqn()
				.setValue(ndiIssqn.getMunicipioIssqn().toString());
		subView.getNdiIssqnFormView().getTfItemListaServicosIssqn()
				.setValue(ndiIssqn.getItemListaServicos().toString());
		//subView.getNdiIssqnFormView().getTfTributacaoIssqn().setValue(ndiIssqn.getTributacaoIssqn());

		/**
		 * 
		 */

		NfeDetalheImpostoPisEntity ndiPis = new NfeDetalheImpostoPisEntity();

		// this.subView.getTfCstPis().setValue(entPis.getCstPis());
		subView.getNdiPisFormView().getTfQtdVendidaPis()
				.setValue(ndiPis.getQuantidadeVendida().toString());
		subView.getNdiPisFormView().getTfBaseCalculoBcPis()
				.setValue(ndiPis.getValorBaseCalculoPis().toString());
		subView.getNdiPisFormView().getTfAliquotaPercentualPis()
				.setValue(ndiPis.getAliquotaPisPercentual().toString());
		subView.getNdiPisFormView().getTfAliquotaReaisPis()
				.setValue(ndiPis.getAliquotaPisReais().toString());
		subView.getNdiPisFormView().getTfValorPis()
				.setValue(ndiPis.getValorPis().toString());

		/**
		 * 
		 */

		NfeDetEspecificoCombustivelEntity ndeCombustivel = new NfeDetEspecificoCombustivelEntity();

		subView.getNdeCombustivelFormView().getTfCodigoAnpCombustivel()
				.setValue(ndeCombustivel.getCodigoAnp().toString());
		subView.getNdeCombustivelFormView().getTfCodifCombustivel()
				.setValue(ndeCombustivel.getCodif());
		subView.getNdeCombustivelFormView()
				.getTfQtdeTempAmbienteCombustivel()
				.setValue(ndeCombustivel.getQuantidadeTempAmbiente().toString());
		subView.getNdeCombustivelFormView().getTfUfConsumoCombustivel()
				.setValue(ndeCombustivel.getUfConsumo());
		subView.getNdeCombustivelFormView().getTfBcCideCombustivel()
				.setValue(ndeCombustivel.getBaseCalculoCide().toString());
		subView.getNdeCombustivelFormView().getTfAliquotaCideCombustivel()
				.setValue(ndeCombustivel.getAliquotaCide().toString());
		subView.getNdeCombustivelFormView().getTfValorCideCombustivel()
				.setValue(ndeCombustivel.getValorCide().toString());

		/**
		 * 
		 */

		NfeDetEspecificoVeiculoEntity entVeiculo = new NfeDetEspecificoVeiculoEntity();

		subView.getNdeVeiculoFormView().getTfTipoOperacaoVeiculo()
				.setValue(entVeiculo.getTipoOperacao());
		subView.getNdeVeiculoFormView().getTfChassiVeiculo()
				.setValue(entVeiculo.getChassi());
		subView.getNdeVeiculoFormView().getTfCodigoCorVeiculo()
				.setValue(entVeiculo.getCodigoCor());
		subView.getNdeVeiculoFormView().getTfDescricaoCorVeiculo()
				.setValue(entVeiculo.getDescricaoCor());
		subView.getNdeVeiculoFormView().getTfPotenciaMotorVeiculo()
				.setValue(entVeiculo.getPotenciaMotor());
		subView.getNdeVeiculoFormView().getTfCilindradasVeiculo()
				.setValue(entVeiculo.getCilindradas());
		subView.getNdeVeiculoFormView().getTfPesoLiquidoVeiculo()
				.setValue(entVeiculo.getPesoLiquido());
		subView.getNdeVeiculoFormView().getTfPesoBrutoVeiculo()
				.setValue(entVeiculo.getPesoBruto());
		subView.getNdeVeiculoFormView().getTfNumeroSerieVeiculo()
				.setValue(entVeiculo.getNumeroSerie());
		subView.getNdeVeiculoFormView().getTfCombustivelVeiculo()
				.setValue(entVeiculo.getTipoCombustivel());
		subView.getNdeVeiculoFormView().getTfNumeroMotorVeiculo()
				.setValue(entVeiculo.getNumeroMotor());
		subView.getNdeVeiculoFormView().getTfCapacidadeTracaoVeiculo()
				.setValue(entVeiculo.getCapacidadeMaximaTracao());
		subView.getNdeVeiculoFormView().getTfDistanciaEixosVeiculo()
				.setValue(entVeiculo.getDistanciaEixos());
		subView.getNdeVeiculoFormView().getTfAnoModeloVeiculo()
				.setValue(entVeiculo.getAnoModelo());
		subView.getNdeVeiculoFormView().getTfAnoFabricacaoVeiculo()
				.setValue(entVeiculo.getAnoFabricacao());
		subView.getNdeVeiculoFormView().getTfTipoPinturaVeiculo()
				.setValue(entVeiculo.getTipoPintura());
		subView.getNdeVeiculoFormView().getTfTipoVeiculo()
				.setValue(entVeiculo.getTipoVeiculo());
		subView.getNdeVeiculoFormView().getTfEspecieVeiculo()
				.setValue(entVeiculo.getEspecieVeiculo());
		subView.getNdeVeiculoFormView().getTfCondicaoVinVeiculo()
				.setValue(entVeiculo.getCondicaoVin());
		subView.getNdeVeiculoFormView().getTfCondicaoVeiculo()
				.setValue(entVeiculo.getCondicaoVeiculo());
		subView.getNdeVeiculoFormView().getTfCodigoMarcaModeloVeiculo()
				.setValue(entVeiculo.getCodigoMarcaModelo());
		subView.getNdeVeiculoFormView().getTfCodigoCorDenatranVeiculo()
				.setValue(entVeiculo.getCor());
		subView.getNdeVeiculoFormView().getTfLotacaoVeiculo()
				.setValue(entVeiculo.getLotacao().toString());
		subView.getNdeVeiculoFormView().getTfRestricaoVeiculo()
				.setValue(entVeiculo.getRestricao());

		// this.subView.carregarSfNdeMedicamento(this.nfeCabecalho.getNfeDetalhe()
		// .getNdeMedicamentoList());

		// this.ndeMedicamentoSelecionado = new
		// NfeDetEspecificoMedicamentoEntity();

		// this.subView.getTfNumeroLoteMedicamento().setValue(
		// this.ndeMedicamentoSelecionado.getNumeroLote());
		// this.subView.getTfQuantidadeLoteMedicamento().setValue(
		// this.ndeMedicamentoSelecionado.getQuantidadeLote().toString());
		// this.subView.getPdfDataFabricacaoMedicamento().setValue(
		// this.ndeMedicamentoSelecionado.getDataFabricacao());
		// this.subView.getPdfDataValidadeMedicamento().setValue(
		// this.ndeMedicamentoSelecionado.getDataValidade());
		// this.subView.getTfPrecoMaximoConsumidorMedicamento().setValue(
		// this.ndeMedicamentoSelecionado.getPrecoMaximoConsumidor()
		// .toString());

		// this.subView.getGlNdeMedicamento().setEnabled(false);
	}

	/**
	 * 
	 */
	public static synchronized void tabEnabled(NfeDetalheEntity nfeDetalhe,
			ProdutoServicoFormView subView) {
		if (nfeDetalhe == null) {
			subView.getGlNfeDetalhe().setEnabled(false);
			subView.getNdiIcmsFormView().getGlNdiIcms().setEnabled(false);
			subView.getNdiPisFormView().getGlNdiPis().setEnabled(false);
			subView.getNdiCofinsFormView().getGlNdiCofins().setEnabled(false);
			subView.getNdiIpiFormView().getGlNdiIpi().setEnabled(false);
			subView.getNdiIiFormView().getGlNdiIi().setEnabled(false);
			subView.getNdiIssqnFormView().getGlNdiIssqn().setEnabled(false);
			// subView.getNdeGlCombustivel().setEnabled(false);
			// subView.getNdeGlVeiculo().setEnabled(false);
			// subView.getPlNdeMedicamentoSubForm().setEnabled(false);
			// subView.getPlNdeArmamentoSubForm().setEnabled(false);
		} else {
			subView.getGlNfeDetalhe().setEnabled(true);
			subView.getNdiIcmsFormView().getGlNdiIcms().setEnabled(true);
			subView.getNdiPisFormView().getGlNdiPis().setEnabled(true);
			subView.getNdiCofinsFormView().getGlNdiCofins().setEnabled(true);
			subView.getNdiIpiFormView().getGlNdiIpi().setEnabled(true);
			subView.getNdiIiFormView().getGlNdiIi().setEnabled(true);
			subView.getNdiIssqnFormView().getGlNdiIssqn().setEnabled(true);
			// subView.getNdeGlCombustivel().setEnabled(true);
			// subView.getNdeGlVeiculo().setEnabled(true);
			// subView.getPlNdeMedicamentoSubForm().setEnabled(true);
			// subView.getPlNdeArmamentoSubForm().setEnabled(true);
		}
	}

}