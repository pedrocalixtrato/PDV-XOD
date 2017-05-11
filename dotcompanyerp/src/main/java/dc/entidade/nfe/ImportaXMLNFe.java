package dc.entidade.nfe;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import dc.br.inf.portalfiscal.nfe.TNFe;
import dc.br.inf.portalfiscal.nfe.TNfeProc;
import dc.br.inf.portalfiscal.nfe.TProtNFe;

@SuppressWarnings("unchecked")
public class ImportaXMLNFe {

   
	@SuppressWarnings("rawtypes")
	public Map importarXmlNFe(File arquivoXml) {
        try {
            Map map = new HashMap();
            NfeCabecalhoEntity nfeCabecalho = new NfeCabecalhoEntity();
            NfeEmitenteEntity emitente = new NfeEmitenteEntity();
            List<NfeDetalheEntity> listaNfeDetalhe = new ArrayList();

            JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            JAXBElement<TNfeProc> element = (JAXBElement) unmarshaller.unmarshal(arquivoXml);
            TNfeProc nfeProc = element.getValue();

            TNFe nfe = nfeProc.getNFe();
            TNFe.InfNFe infNfe = nfe.getInfNFe();
            TNFe.InfNFe.Ide ide = infNfe.getIde();
            TNFe.InfNFe.Emit emit = infNfe.getEmit();
            TNFe.InfNFe.InfAdic infAdic = infNfe.getInfAdic();
            List<TNFe.InfNFe.Det> listaDet = infNfe.getDet();
            TProtNFe protNfe = nfeProc.getProtNFe();

            SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
            DecimalFormatSymbols simboloDecimal = DecimalFormatSymbols.getInstance();
            simboloDecimal.setDecimalSeparator('.');
            DecimalFormat formatoQuantidade = new DecimalFormat("0.0000", simboloDecimal);
            DecimalFormat formatoValor = new DecimalFormat("0.00", simboloDecimal);
            DecimalFormat formatoNumero = new DecimalFormat("000000000");
            DecimalFormat formatoSerie = new DecimalFormat("000");

            //cabecalho
            nfeCabecalho.setCodigoNumerico(ide.getCNF());
            nfeCabecalho.setNaturezaOperacao(ide.getNatOp());
            nfeCabecalho.setIndicadorFormaPagamento(Integer.valueOf(ide.getIndPag()));
            nfeCabecalho.setCodigoModelo(ide.getMod());
            nfeCabecalho.setSerie(formatoSerie.format(Integer.valueOf(ide.getSerie())));
            nfeCabecalho.setNumero(formatoNumero.format(Double.valueOf(ide.getNNF())));
            nfeCabecalho.setDataHoraEmissao(formatoData.parse(ide.getDEmi()));
            nfeCabecalho.setDataEntradaContingencia(formatoData.parse(ide.getDSaiEnt()));
            nfeCabecalho.setDataHoraEntradaSaida(formatoData.parse(ide.getHSaiEnt()));
            nfeCabecalho.setTipoEmissao(Integer.valueOf(ide.getTpEmis()));
            nfeCabecalho.setVersaoProcessoEmissao(ide.getVerProc());
            nfeCabecalho.setCodigoMunicipio(Integer.valueOf(ide.getCMunFG()));
            nfeCabecalho.setFinalidadeEmissao(Integer.valueOf(ide.getFinNFe()));
            nfeCabecalho.setTipoOperacao(0);
            nfeCabecalho.setDigitoChaveAcesso(ide.getCDV());
            nfeCabecalho.setFormatoImpressaoDanfe(Integer.valueOf(ide.getTpImp()));
            nfeCabecalho.setChaveAcesso(protNfe.getInfProt().getChNFe());
            nfeCabecalho.setStatusNota(protNfe.getInfProt().getCStat());

            //emitente
            emitente.setCpfCnpj(emit.getCNPJ());
            emitente.setIe(emit.getIE());
            emitente.setRazaoSocial(emit.getXNome());
            emitente.setFantasia(emit.getXFant());
            emitente.setLogradouro(emit.getEnderEmit().getXLgr());
            emitente.setNumero(emit.getEnderEmit().getNro());
            emitente.setComplemento(emit.getEnderEmit().getXCpl());
            emitente.setBairro(emit.getEnderEmit().getXBairro());
            emitente.setCodigoMunicipio(Integer.valueOf(emit.getEnderEmit().getCMun()));
            emitente.setNomeMunicipio(emit.getEnderEmit().getXMun());
            emitente.setUf(emit.getEnderEmit().getUF().value());
            emitente.setCep(emit.getEnderEmit().getCEP());
            emitente.setCrt(Integer.valueOf(emit.getCRT()));
            emitente.setCodigoPais(Integer.valueOf(emit.getEnderEmit().getCPais()));
            emitente.setNomePais(emit.getEnderEmit().getXPais());
            emitente.setTelefone(emit.getEnderEmit().getFone());

            //informaÃ§Ãµes adicionais
            nfeCabecalho.setInformacoesAddFisco(infAdic.getInfAdFisco());

            //detalhe
            TNFe.InfNFe.Det det;
            NfeDetalheEntity nfeDetalhe;
            for (int i = 0; i < listaDet.size(); i++) {
                det = listaDet.get(i);
                nfeDetalhe = new NfeDetalheEntity();

                TNFe.InfNFe.Det.Prod prod = det.getProd();
                nfeDetalhe.setNumeroItem(Integer.valueOf(det.getNItem()));

                nfeDetalhe.setGtin(prod.getCProd());
                //nfeDetalhe.setGtin(prod.getCEAN());
                nfeDetalhe.setNomeProduto(prod.getXProd());
                nfeDetalhe.setNcm(prod.getNCM());
                //nfeDetalhe.setExTipi(Integer.valueOf(prod.getEXTIPI()));
                nfeDetalhe.setCfop(Integer.valueOf(prod.getCFOP()));
                nfeDetalhe.setUnidadeComercial(prod.getUCom());
                nfeDetalhe.setQuantidadeComercial(BigDecimal.valueOf(formatoQuantidade.parse(prod.getQCom()).doubleValue()));
                nfeDetalhe.setValorUnitarioComercial(BigDecimal.valueOf(formatoValor.parse(prod.getVUnCom()).doubleValue()));
                nfeDetalhe.setValorTotal(BigDecimal.valueOf(formatoValor.parse(prod.getVProd()).doubleValue()));
                nfeDetalhe.setGtinUnidadeTributavel(prod.getCEANTrib());
                nfeDetalhe.setUnidadeTributavel(prod.getUTrib());
                nfeDetalhe.setQuantidadeTributavel(BigDecimal.valueOf(formatoQuantidade.parse(prod.getQTrib()).doubleValue()));
                nfeDetalhe.setValorUnitarioTributavel(BigDecimal.valueOf(formatoValor.parse(prod.getVUnTrib()).doubleValue()));
                //nfeDetalhe.setValorFrete(BigDecimal.valueOf(formatoValor.parse(prod.getVFrete()).doubleValue()));
                //nfeDetalhe.setValorSeguro(BigDecimal.valueOf(formatoValor.parse(prod.getVSeg()).doubleValue()));
                //nfeDetalhe.setValorDesconto(BigDecimal.valueOf(formatoValor.parse(prod.getVDesc()).doubleValue()));
                nfeDetalhe.setInformacoesAdicionais(det.getInfAdProd());

                //imposto - icms
                TNFe.InfNFe.Det.Imposto.ICMS.ICMS00 icms00 = det.getImposto().getICMS().getICMS00();

                nfeDetalhe.setCstIcmsB(icms00.getCST());
                nfeDetalhe.setOrigemMercadoria(icms00.getOrig());
                nfeDetalhe.setModalidadeBcIcms(icms00.getModBC());
                nfeDetalhe.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icms00.getVBC()).doubleValue()));
                nfeDetalhe.setAliquotaIcms(BigDecimal.valueOf(formatoValor.parse(icms00.getPICMS()).doubleValue()));
                nfeDetalhe.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icms00.getVICMS()).doubleValue()));

                //imposto - pis
                TNFe.InfNFe.Det.Imposto.PIS.PISOutr pisOutr = det.getImposto().getPIS().getPISOutr();

                nfeDetalhe.setCstPis(pisOutr.getCST());
                nfeDetalhe.setValorBaseCalculoPis(BigDecimal.valueOf(formatoValor.parse(pisOutr.getVBC()).doubleValue()));
                nfeDetalhe.setValorPis(BigDecimal.valueOf(formatoValor.parse(pisOutr.getPPIS()).doubleValue()));
                nfeDetalhe.setValorPis(BigDecimal.valueOf(formatoValor.parse(pisOutr.getVPIS()).doubleValue()));

                //imposto - cofins
                TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr cofinsOutr = det.getImposto().getCOFINS().getCOFINSOutr();

                nfeDetalhe.setCstCofins(cofinsOutr.getCST());
                nfeDetalhe.setBaseCalculoCofins(BigDecimal.valueOf(formatoValor.parse(cofinsOutr.getVBC()).doubleValue()));
                nfeDetalhe.setValorCofins(BigDecimal.valueOf(formatoValor.parse(cofinsOutr.getPCOFINS()).doubleValue()));
                nfeDetalhe.setValorCofins(BigDecimal.valueOf(formatoValor.parse(cofinsOutr.getVCOFINS()).doubleValue()));

                listaNfeDetalhe.add(nfeDetalhe);
            }

            //totais icms
            TNFe.InfNFe.Total.ICMSTot icmsTot = nfe.getInfNFe().getTotal().getICMSTot();

            nfeCabecalho.setBaseCalculoIcms(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVBC()).doubleValue()));
            nfeCabecalho.setValorIcms(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVICMS()).doubleValue()));
            nfeCabecalho.setBaseCalculoIcmsSt(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVBCST()).doubleValue()));
            nfeCabecalho.setValorIcmsSt(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVST()).doubleValue()));
            nfeCabecalho.setValorTotalProdutos(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVProd()).doubleValue()));
            nfeCabecalho.setValorFrete(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVFrete()).doubleValue()));
            nfeCabecalho.setValorSeguro(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVSeg()).doubleValue()));
            nfeCabecalho.setValorDesconto(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVDesc()).doubleValue()));
            nfeCabecalho.setValorIpi(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVIPI()).doubleValue()));
            nfeCabecalho.setValorPis(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVPIS()).doubleValue()));
            nfeCabecalho.setValorCofins(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVCOFINS()).doubleValue()));
            nfeCabecalho.setValorDespesasAcessorias(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVOutro()).doubleValue()));
            nfeCabecalho.setValorTotal(BigDecimal.valueOf(formatoValor.parse(icmsTot.getVNF()).doubleValue()));

            // TODO: Quais outros dados necessitam serem importados?

            map.put("cabecalho", nfeCabecalho);
            map.put("detalhe", listaNfeDetalhe);
            map.put("emitente", emitente);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

