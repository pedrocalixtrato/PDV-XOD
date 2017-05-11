package dc.framework.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

public class CustomFilterLoginCaptcha extends
		UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		AuthenticationException exception = (AuthenticationException) request
				.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (exception instanceof BadCredentialsException
				|| exception instanceof InsufficientAuthenticationException) {
			String jcaptcha = request.getParameter("jcaptcha");
			boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(
					request, jcaptcha);
			if (!captchaPassed) {
				throw new InsufficientAuthenticationException(
						"Captcha informado inválido");
			}
		}
		return super.attemptAuthentication(request, response);
	}
}
