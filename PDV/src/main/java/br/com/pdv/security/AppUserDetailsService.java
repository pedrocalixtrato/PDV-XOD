package br.com.pdv.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.pdv.dao.UsuarioDAO;
import br.com.pdv.domain.Grupo;
import br.com.pdv.domain.Usuario;
import br.com.pdv.util.jpa.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UsuarioDAO usuarios = CDIServiceLocator.getBean(UsuarioDAO.class);
		Usuario usuario = usuarios.porEmail(email);
		
		UsuarioSistema user = null;
		try{
		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		return user;
		
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		
		return authorities;
	}
}
