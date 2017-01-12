package br.com.pdv.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.pdv.dao.ProdutoDAO;

import br.com.pdv.domain.Produto;
import br.com.pdv.security.Seguranca;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class PesquisaProdutoBean implements Serializable {
	
	private List<Produto> produtos;
	private Produto produto;
	
	private Seguranca seguranca;
	
	
	
	
	@Inject
	ProdutoDAO produtoDAO;
	
	
			
	
	public void listar(){
		
		
	this.produtos = produtoDAO.listar(produto);
		
		
	}
	
	public void buscar(){
			
//		produtos = produtoDAO.porDesc(produto.getCodUsuario(Long.parseLong(getSeguranca().getIDUsuario())));
//		this.produtos =  produtoDAO.filtrar(produto, "codUsuario");
		
		
	}
	
//	public List<Produto> completar(String descricao){
//		 
//		return this.produtoDAO.porDesc(descricao);
//	}


	public List<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}


	public Produto getProduto() {if(produto == null){
		produto = new Produto();
	}
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Seguranca getSeguranca() {
		return seguranca;
	}
	public void setSeguranca(Seguranca seguranca) {
		this.seguranca = seguranca;
	}

	
	
	

}
