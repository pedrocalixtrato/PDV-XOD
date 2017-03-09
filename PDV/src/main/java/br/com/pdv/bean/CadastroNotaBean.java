package br.com.pdv.bean;


import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.pdv.Enum.FormaPagamento;
import br.com.pdv.Enum.NotaFinalidade;
import br.com.pdv.dao.ClienteDAO;
import br.com.pdv.dao.NotaFiscalDAO;
import br.com.pdv.dao.ProdutoDAO;
import br.com.pdv.domain.Nota;


@Named
@ViewScoped
public class CadastroNotaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Nota nota;
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@Inject
	private NotaFiscalDAO notaDAO;
	
	@Inject
	private ProdutoDAO produtoDAO;	
	
	
	public void init(){
		nota = new Nota();
		
	}
	
	public void salvar (){
		try{
			
			this.notaDAO.salvar(nota);
			
			Messages.addGlobalInfo("Salvo com sucesso!");
		}catch(Exception e){
			e.printStackTrace();
			Messages.addGlobalError("Erro ao salvar este cadastro.");
		}
		
	}
	
	public NotaFinalidade[] getNotaFinalidade() {
		return NotaFinalidade.values();
	}

	public Nota getNota() {if (nota == null){
		nota = new Nota();
	}
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}
	
	
	
	
	

}
	 
	
	
