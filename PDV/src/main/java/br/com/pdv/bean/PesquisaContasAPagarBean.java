//package br.com.pdv.bean;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import br.com.pdv.dao.ContasAPagarDAO;
//import br.com.pdv.domain.ContasAPagar;
//
//@SuppressWarnings("serial")
//@Named
//@ViewScoped
//public class PesquisaContasAPagarBean implements Serializable{
//	
//	@Inject
//	private ContasAPagarDAO contasAPagarDAO;
//	
//	private List<ContasAPagar> contasVencidas;
//	
//	private ContasAPagar contasAPagar;
//	
//
//	
//	
//	@PostConstruct
//	public void listarVencidas(){
//		if(contasAPagar==null){
//			contasAPagar = new ContasAPagar();
//		}
//		this.contasVencidas = contasAPagarDAO.bucarPorData(contasAPagar.getData(),contasAPagar.getTipo());
//	}
//	
//	
//	public List<ContasAPagar> getContasVencidas() {
//		return contasVencidas;
//	}
//
//	public void setContasVencidas(List<ContasAPagar> contasVencidas) {
//		this.contasVencidas = contasVencidas;
//	}
//
//	public ContasAPagar getContasAPagar() {if(contasAPagar == null){
//		contasAPagar = new ContasAPagar();
//	}
//		return contasAPagar;
//	}
//
//	public void setContasAPagar(ContasAPagar contasAPagar) {
//		this.contasAPagar = contasAPagar;
//	}
//
//		
//	
//	
//
//}
