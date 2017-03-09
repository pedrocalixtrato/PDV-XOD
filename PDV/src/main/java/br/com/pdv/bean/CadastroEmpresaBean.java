package br.com.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
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
import br.com.pdv.service.EmpresaService;

@Named
@ViewScoped
public class CadastroEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Empresa empresa;	
	private Usuario usuario;
	private List<Grupo> grupos;
	private Grupo grupo;
	
	@Inject
	private GrupoDAO grupoDAO;
	
	@Inject
	private EmpresaService empresaService;
	
	@Inject
	private EmpresaDAO empresaDAO;
	
	
	
	@PostConstruct
	public void init(){
		empresa = new Empresa();
		grupos = grupoDAO.listar(grupo);
		usuario = new Usuario();
		
	}
	
	public void salvar (){
		try {
									
			empresaDAO.salvar(empresa);		
			Messages.addGlobalInfo("Salvo com sucesso!");
		} catch (RuntimeException e) {

			Messages.addGlobalError("Não foi possivel salvar este cadastro");
			e.printStackTrace();

		}	
	}

	public Empresa getEmpresa() {if(empresa == null){
		this.empresa = new Empresa();
	}
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	

	public Usuario getUsuario() {
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
	public EmpresaService getEmpresaService() {
		return empresaService;
	}

	
	
	
	
	

}
