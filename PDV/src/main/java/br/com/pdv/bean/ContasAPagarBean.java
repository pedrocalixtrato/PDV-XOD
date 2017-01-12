//package br.com.pdv.bean;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import org.omnifaces.util.Messages;
//
//import br.com.pdv.dao.ContasAPagarDAO;
//import br.com.pdv.domain.ContasAPagar;
//import br.com.pdv.domain.StatusConta;
//import br.com.pdv.filter.ContasAPagarFilter;
//
//@SuppressWarnings("serial")
//@Named
//@ViewScoped
//public class ContasAPagarBean implements Serializable {
//	
//	private ContasAPagar contasAPagar;
//	private List<StatusConta> tipos;
//	private List<ContasAPagar> contasAPagarL;
//	private List<ContasAPagar> contasVencidas;
//	private ContasAPagarFilter filtro;
//		
//	
//	private BigDecimal somarTotal;
//	@Inject
//	private ContasAPagarDAO contasAPagarDAO;
//	
//	
//	//@Transactional
//	public void salvar(){
//		try{
//			this.contasAPagarDAO.salvar(contasAPagar);
//			Messages.addGlobalInfo("Salvo com sucesso!");
//		}catch(RuntimeException e){
//			Messages.addGlobalError("Não foi possivel salvar este cadastro");
//			e.printStackTrace();
//		}
//	}
//	
//	@PostConstruct
//	public void init(){
//		
//		this.tipos = Arrays.asList(StatusConta.values());
//		this.contasAPagar = new ContasAPagar();
//		
//	}
//	
//	public void filtrar(){
//		
//		this.contasVencidas = contasAPagarDAO.filtro(filtro);
//		this.somarTotal = contasAPagarDAO.somarTotal(filtro);
//		
//	}
//		
//	
//	
//	public void listar(){
//		
//		this.contasAPagarL = contasAPagarDAO.listar(contasAPagar);
//		
//	}
//	
//
//	public ContasAPagar getContasAPagar() {
//		return contasAPagar;
//	}
//
//	public void setContasAPagar(ContasAPagar contasAPagar) {
//		this.contasAPagar = contasAPagar;
//	}
//
//
//	public List<StatusConta> getTipos() {
//		return tipos;
//	}
//
//
//	public void setTipos(List<StatusConta> tipos) {
//		this.tipos = tipos;
//	}
//	
//	
//	public List<ContasAPagar> getContasAPagarL() {
//		return contasAPagarL;
//	}
//	public void setContasAPagarL(List<ContasAPagar> contasAPagarL) {
//		this.contasAPagarL = contasAPagarL;
//	}
//
//	public void setContasVencidas(List<ContasAPagar> contasVencidas) {
//		this.contasVencidas = contasVencidas;
//	}
//	
//	public List<ContasAPagar> getContasVencidas() {
//		return contasVencidas;
//	}
//
//	public ContasAPagarFilter getFiltro() {if(filtro ==null){
//		filtro = new ContasAPagarFilter();
//		}
//		return filtro;
//	}
//
//	public void setFiltro(ContasAPagarFilter filtro) {
//		this.filtro = filtro;
//	}
//
//	public BigDecimal getSomarTotal() {
//		return somarTotal;
//	}
//
//	public void setSomarTotal(BigDecimal somarTotal) {
//		this.somarTotal = somarTotal;
//	}
//	
//	
//	
//	
//	
//
//}
