//package br.com.pdv.bean;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import org.omnifaces.util.Messages;
//
//import br.com.pdv.dao.UsuarioDAO;
//import br.com.pdv.domain.Usuario;
//
//@SuppressWarnings("serial")
//@Named
//@ViewScoped
//public class PesquisaUsuarioBean implements Serializable {
//
//	private List<Usuario> usuarios;
//	private Usuario usuario;
//
//	@Inject
//	private UsuarioDAO usuarioDAO;
//
//	public void listar() {
//		try {
//
//			//this.usuarios = usuarioDAO.filtrar(usuario, "nome");
//			this.usuarios = usuarioDAO.listar(usuario);
//		} catch (RuntimeException e) {
//			Messages.addGlobalError("Erro na listagem");
//			e.printStackTrace();
//		}
//
//	}
//
//	public List<Usuario> getUsuarios() {
//		return usuarios;
//	}
//
//	public void setUsuarios(List<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}
//
//	public Usuario getUsuario() {if(usuario == null){
//		usuario = new Usuario();
//	}
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//
//}
