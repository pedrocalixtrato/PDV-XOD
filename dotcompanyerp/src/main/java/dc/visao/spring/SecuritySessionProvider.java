package dc.visao.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.istack.logging.Logger;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.PessoaEntity;

@Component
@Scope("singleton")
public class SecuritySessionProvider implements ApplicationContextAware {

	@Value("${security.load}")
	private String loadFromSpring;

	public  Logger logger = Logger.getLogger(SecuritySessionProvider.class);

	@Autowired
	public  ApplicationContext ctx;

	@PostConstruct
	protected void init() {
		if (loadFromSpring != null && Boolean.valueOf(loadFromSpring)) {
			logger.info("will load user from spring session");
		} else {
			logger.warning("Authentication DISABLED");
		}
	}

	public static UsuarioEntity getUsuario() {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(VaadinServlet.getCurrent()
						.getServletContext());
		if (ctx != null) {
			SecuritySessionProvider s = ctx.getBean(SecuritySessionProvider.class);
			if (s.shouldLoadUserFromSpring()) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null) {
					Object principal = auth.getPrincipal();
					if (principal instanceof UsuarioEntity) {
						return (UsuarioEntity) principal;
					} else if (auth.getDetails() instanceof UsuarioEntity) {
						return (UsuarioEntity) auth.getDetails();
					}
				} else {
					return null;
				}
			} else {
				UsuarioEntity u = new UsuarioEntity();
				u.setAdministrador(true);
				ColaboradorEntity c = new ColaboradorEntity();
				PessoaEntity p = new PessoaEntity();
				p.setNome("Nome pessoa");
				c.setPessoa(p);
				u.setConfirmado(true);
				u.setColaborador(c);
				return u;
			}
		} else {
			return null;
		}
		return null;

	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.ctx = arg0;
	}

	public boolean shouldLoadUserFromSpring() {
		return Boolean.valueOf(loadFromSpring);
	}

	public static void putUsuarioInSession(UsuarioEntity u, AuthenticationManager manager) {

		WrappedSession session = VaadinService.getCurrentRequest().getWrappedSession();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(u.getLogin(), u.getSenha());
		token.setDetails(u);
		// token.setAuthenticated(isAuthenticated)
		// RunAsUserToken token = new
		// RunAsUserToken(novaConta.getUsuario().getLogin(),
		// novaConta.getUsuario(), novaConta.getUsuario().getSenha(),
		// novaConta.getUsuario().getAuthorities(), null);
		SecurityContext securityContext = SecurityContextHolder.getContext();

		org.springframework.security.core.Authentication authenticatedUser = manager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

		securityContext.setAuthentication(token);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

	}
}
