package br.com.pdv.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.pdv.dao.UsuarioDAO;
import br.com.pdv.domain.Usuario;


@Named
@SuppressWarnings("serial")
@ViewScoped
public class UsuarioBean implements Serializable {
	
	private Usuario usuario;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	
	public void init(){
		usuario = new Usuario();
	}
	
	public void salvar(){
		
		try{
			
			usuarioDAO.salvar(usuario);
			Messages.addGlobalInfo("Salvo com sucesso!");
		}catch(RuntimeException e){
			Messages.addGlobalError("Nao foi possivel salvar este cadastro!");
			e.printStackTrace();
			
		}
		
	}


	public Usuario getUsuario() {if(usuario==null){
		usuario = new Usuario();
	}
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
