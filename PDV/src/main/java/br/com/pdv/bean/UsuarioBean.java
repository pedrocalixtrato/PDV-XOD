package br.com.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.pdv.dao.EmpresaDAO;
import br.com.pdv.dao.GrupoDAO;
import br.com.pdv.dao.UsuarioDAO;
import br.com.pdv.domain.Empresa;
import br.com.pdv.domain.Grupo;
import br.com.pdv.domain.Usuario;


@Named
@SuppressWarnings("serial")
@ViewScoped
public class UsuarioBean implements Serializable {
	
	private Usuario usuario;
	private List<Grupo> grupos;
	private Grupo grupo;	
	
	private Empresa empresa;
	private List<Empresa> empresas;

	
	
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private GrupoDAO grupoDAO;
	@Inject
	private EmpresaDAO empresaDAO;
	
	
	public void init(){
		usuario = new Usuario();
		empresas = empresaDAO.listar(empresa);
		grupos = grupoDAO.listar(grupo);
	}
	
	
	
	public void salvar(){
		
		try{
			//usuario.setEmpresa(empresa);
			usuarioDAO.salvar(usuario);
			Messages.addGlobalInfo("Salvo com sucesso!");
		}catch(RuntimeException e){
			Messages.addGlobalError("Nao foi possivel salvar este cadastro!");
			e.printStackTrace();
			
		}
		
	}
	
	public void salvarNovo(){
		
		try{			
			usuario.setEmpresa(empresa);
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



	public List<Grupo> getGrupos() {
		return grupos;
	}



	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}



	public Grupo getGrupo() {
		return grupo;
	}



	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}



	public Empresa getEmpresa() {if(empresa == null){
		empresa = new Empresa();
	}
		return empresa;
	}



	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}



	public List<Empresa> getEmpresas() {
		return empresas;
	}



	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	
	
	
	
}
