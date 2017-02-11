package br.com.pdv.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.pdv.dao.ProdutoDAO;
import br.com.pdv.domain.Produto;
import br.com.pdv.domain.Unidades;
import br.com.pdv.security.Seguranca;
import br.com.pdv.util.jsf.FacesUtil;
import br.com.pdv.util.jsf.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private Produto produto;
	private List<Unidades> unidades;
	@SuppressWarnings("unused")
	private List<Produto> produtos;

	private Long codigoProduto;
	
	private Seguranca seguranca;

	@Inject
	private ProdutoDAO produtoDAO;
	@SuppressWarnings("unused")
	@Inject
	private EntityManager em;

	// @Transactional
	public void salvar() {
		try {
			produto.setCodUsuario(getSeguranca().getIDUsuario());
			produtoDAO.salvar(produto);
			Messages.addGlobalInfo("Salvo com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Não foi possivel salvar este cadastro");
			e.printStackTrace();
		}

	}

	// @Transactional
	public void excluir(ActionEvent evento) {
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		try {
			produtoDAO.excluir(produto.getId());
			this.produtos = produtoDAO.listar(produto);
			Messages.addGlobalInfo("Removido com sucesso!");

		} catch (RuntimeException e) {
			Messages.addGlobalError("Não foi possivel remover este cadastro");
			e.printStackTrace();

		}

	}
	
	public void imprimir(){
		
		try{
			
		
		String caminho = Faces.getRealPath("/reports/produtos.jasper");
		
		Map< String, Object> parametros = new HashMap<>();
		
		Connection conexao = HibernateUtil.getConexao();
		
		JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
		
		JasperPrintManager.printReport(relatorio, true);
		
		}catch(JRException erro){
			Messages.addGlobalError("Erro ao gerar o relatorio");
			erro.printStackTrace();
		
		}
	}

	@PostConstruct
	public void inti() {

		this.produto = new Produto();
		this.unidades = Arrays.asList(Unidades.values());

	}

	public void carregarCadastro() {

		try {
			String valor = FacesUtil.getParam("produtoCod");
			if (valor != null) {
				Long codigo = Long.parseLong(valor);

				this.produto = produtoDAO.buscarPeloCodigo(codigo);
			}

			// Busca com cache Inicializaçao Eager em subclasses
			/*
			  produto = this.em.createQuery("from Produto p inner join fetch
			  p.categorias where p.codigo = :codigo" , Produto.class)
			  .setParameter("codigo", codigo) .getSingleResult();
			 */

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao obter os dados do Produto");
			e.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Unidades> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidades> unidades) {
		this.unidades = unidades;
	}

	public ProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

	public void setProdutoDAO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}

	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public Seguranca getSeguranca() {if(seguranca == null){
		seguranca = new Seguranca();
	}
		return seguranca;
	}

	public void setSeguranca(Seguranca seguranca) {
		this.seguranca = seguranca;
	}
	

}
