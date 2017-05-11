package dc.br.inf.portalfiscal.nfe;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import dc.br.inf.portalfiscal.nfe.TNFe.InfNFe.Emit;
import dc.br.inf.portalfiscal.nfe.TNFe.InfNFe.Ide;

public class Main {

	public static void main(String[] args) {
		try{
			File f = new File("c://nfe//1.xml");
			JAXBContext context = JAXBContext.newInstance(TNfeProc.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			TNfeProc nfe = (TNfeProc) unmarshaller.unmarshal(f);    

			Ide identificacao = nfe.getNFe().getInfNFe().getIde();
			Emit emitente = nfe.getNFe().getInfNFe().getEmit();
			TLocal entrega = nfe.getNFe().getInfNFe().getEntrega();	
			TLocal retirada = nfe.getNFe().getInfNFe().getRetirada();	
			List<TNFe.InfNFe.Det> produtos = nfe.getNFe().getInfNFe().getDet();	
			
			System.out.println("Código UF::"+identificacao.getCUF());
			System.out.println("Modelo:::"+identificacao.getMod());
			System.out.println("Série:::"+identificacao.getSerie());
			System.out.println("Natureza Op:::"+identificacao.getNatOp());
			System.out.println("Forma Pagto::"+identificacao.getIndPag()); //0 � pagamento � vista;
			//		    1 � pagamento � prazo; 		    2 � outros.
			//System.out.println("Data emissão:"+DataUtil.stringPraData(identificacao.getDEmi()));
			//System.out.println("Data Entrada Saida:"+DataUtil.stringPraData(identificacao.getDSaiEnt()));
			System.out.println("Hora Entrada Saida:"+identificacao.getHSaiEnt()); 
			System.out.println("Código Municipio::"+identificacao.getCMunFG());

			System.out.println("Tipo Impressão DANFE::"+identificacao.getTpImp());//1-Retrato 2-Paisagem
			System.out.println("Forma de emissão da NF-e:"+identificacao.getTpEmis());
			//FORMA DE emissão DE NF-E
			//		    1 - Normal;
			//		    2 - Contingência FS
			//		    3 - Contingência SCAN
			//		    4 - Contingência DPEC
			//		    5 - Contingência FSDA
			//		    6 - Contingência SVC - AN
			//		    7 - Contingência SVC - RS

			//********DADOS DO EMITENTE
			System.out.println("**DADOS DO EMITENTE**");
			System.out.println("CNPJ: "+emitente.getCNPJ());
			System.out.println("Razão Social: "+emitente.getXNome());
			System.out.println("Nome Fantasia: "+emitente.getXFant());
			System.out.println("Endereço: "+emitente.getEnderEmit());
			System.out.println("Inscrição Estadual: "+emitente.getIE());
			System.out.println("CRT: "+emitente.getCRT());
			//CRT
			//		    1 � Simples Nacional;
			//		    2 � Simples Nacional � excesso de sublimite de receita bruta;
			//		    3 � Regime Normal. (v2.0).

			//*****DADOS DA RETIRADA**///
			System.out.println("***DADOS DA RETIRADA******");
			
			
			System.out.println("DADOS DE PRODUTOS");
			for(TNFe.InfNFe.Det p : produtos){
				System.out.println("Código:"+p.getProd().getCProd());
				System.out.println("Descrição:"+p.getProd().getXProd());
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

