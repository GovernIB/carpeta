package es.caib.carpeta.back.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class CarpetaLogoutSuccessHandler implements LogoutSuccessHandler {
	
	protected final Logger log = Logger.getLogger(getClass());

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		try {
			
			HttpSession session = request.getSession(); 
			session.invalidate();
			
			request.logout();
			
			response.sendRedirect(request.getContextPath() + "/");
			
		} catch ( Exception e) {
			log.error("Error alhora de fer logout:" + e.getMessage());
		}
	}
	
}
