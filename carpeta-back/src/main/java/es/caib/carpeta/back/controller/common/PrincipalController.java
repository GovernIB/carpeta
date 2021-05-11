package es.caib.carpeta.back.controller.common;

import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.logic.UsuariLogicaService;
import es.caib.carpeta.model.entity.Usuari;
import es.caib.carpeta.persistence.UsuariJPA;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * @autor anadal
 * 
 */
@Controller
public class PrincipalController {

	protected final Logger log = Logger.getLogger(getClass());
	

	@EJB(mappedName = UsuariLogicaService.JNDI_NAME)
	protected UsuariLogicaService usuariLogicaEjb;

	@RequestMapping(value = "/common/principal.html")
	public ModelAndView principal(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Boolean initialized = (Boolean) session.getAttribute("inicialitzat");

		if (initialized == null) {
			//HtmlUtils.saveMessageInfo(request, "MessageInfo : Benvingut a Carpeta");

//			log.info(" request.isUserInRole(ROLE_ADMIN) =>    "  + request.isUserInRole("ROLE_ADMIN") );
//			log.info(" request.isUserInRole(ROLE_SUPER) =>    "  + request.isUserInRole("ROLE_SUPER") );

            if (request.isUserInRole("ROLE_SUPER")) {
                return new ModelAndView(new RedirectView("/superadmin/buit", true));
            }

            if (request.isUserInRole("ROLE_ADMIN") && LoginInfo.getInstance().getEntitatID() != null ) {
                return new ModelAndView(new RedirectView("/adminentitat/buit", true));
            }
		}
		return new ModelAndView("principal");
		
	}

	@RequestMapping(value = "/canviarIdioma/{idioma}/{pipella}", method = RequestMethod.GET)
	public ModelAndView canviarIdioma(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(name = "idioma") String idioma, @PathVariable String pipella) throws Exception {
		es.caib.carpeta.back.utils.CarpetaSessionLocaleResolver.setLocaleManually(request, idioma);

		if ("superadmin".equals(pipella)) {
			return new ModelAndView(new RedirectView("/superadmin/buit", true));
		}
		if ("adminentitat".equals(pipella)) {
			return new ModelAndView(new RedirectView("/adminentitat/buit", true));
		}

		return new ModelAndView("principal");
	}

	@RequestMapping(value = "/canviarPipella", method = RequestMethod.GET)
	public ModelAndView canviarPipella(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return canviarPipella(request, response, null);
	}

	@RequestMapping(value = "/canviarPipella/{pipella}", method = RequestMethod.GET)
	public ModelAndView canviarPipella(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String pipella) throws Exception {

		ModelAndView mav = new ModelAndView();
		
		if (pipella != null && pipella.trim().length() != 0) {

			// TODO GENAPP Afegir altres pipelles !!!!!
			/*
			 * if ("ROLE_ADEN".equals(pipella)) { //return new ModelAndView("role_aden");
			 * return new ModelAndView(new RedirectView("/aden/peticionscaducades/list/1",
			 * true)); }
			 */
			
			if ("superadmin".equals(pipella)) {
				mav.setView(new RedirectView("/superadmin/buit", true));
				return mav; 
			}

			if ("adminentitat".equals(pipella)) {
				mav.setView(new RedirectView("/adminentitat/buit", true));
				return mav;
			}
			
			if ("sobre".equals(pipella)) {
				mav.setViewName("principal");
				return mav;
			}

			if ("webdb".equals(pipella)) {
				mav.setViewName("webdb");
				return mav;
			}

			if (Configuracio.isDesenvolupament() && "desenvolupament".equals(pipella)) {
				mav.setViewName("desenvolupament");
				return mav;
			}

			log.error("S'ha accedit a canviarPipella amb un paràmetre desconegut: " + pipella);
		}

		mav.setViewName("principal");
		return mav;
	}
	
	@RequestMapping(value = "/canviarEntitat/{idEntitat}/{pipella}", method = RequestMethod.GET)
	public ModelAndView canviarEntitat(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@PathVariable String idEntitat, @PathVariable String pipella) throws Exception {

		LoginInfo loginInfo = LoginInfo.getInstance();

		// Guardam la darrera entitat a base de dades
    	Usuari usuariAutenticat = usuariLogicaEjb.findByPrimaryKey(loginInfo.getUsuariPersona().getUsuariID());
    	usuariAutenticat.setDarreraEntitat(Long.parseLong(idEntitat));

    	usuariLogicaEjb.update((UsuariJPA)usuariAutenticat);

		// Guardam la darrera entitat a logininfo
    	loginInfo.setEntitatID(Long.parseLong(idEntitat));
		
		HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();	
		httpRequest.getSession().setAttribute("loginInfo", loginInfo);

		if ("superadmin".equals(pipella)) {
			return new ModelAndView(new RedirectView("/superadmin/buit", true));
		}
		if ("adminentitat".equals(pipella)) {
			return new ModelAndView(new RedirectView("/adminentitat/buit", true));
		}
		
		return new ModelAndView("principal");
	
	}

	@RequestMapping(value = "/avislegal", method = RequestMethod.GET)
	public ModelAndView avislegal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("avislegal");
	}

	@RequestMapping(value = "/accessibilitat", method = RequestMethod.GET)
	public ModelAndView accessibilitat(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("accessibilitat");
	}

	@RequestMapping(value = "/mapaweb", method = RequestMethod.GET)
	public ModelAndView mapaweb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("mapaweb");
	}

	@RequestMapping(value = "/protecciodades", method = RequestMethod.GET)
	public ModelAndView protecciodades(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("protecciodades");
	}

}
