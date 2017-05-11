package dc.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dc.model.dao.administrativo.seguranca.IUsuarioDAO;

@Service("dcUserDetailsService")
public class DcUserDetailsService implements UserDetailsService{

	
	@Autowired
	private IUsuarioDAO usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		System.out.println("load by username");
		UserDetails usuarioByLogin = usuarioDao.getUsuarioByLogin(login);
		return usuarioByLogin;
	}

}
