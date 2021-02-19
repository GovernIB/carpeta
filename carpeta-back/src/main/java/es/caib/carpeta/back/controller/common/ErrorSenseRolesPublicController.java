package es.caib.carpeta.back.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorSenseRolesPublicController {
	
	@RequestMapping(value = "/public/senseroles")
	public ModelAndView senseRoles (HttpSession session,
		      HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView("errorSenseRolesViewCommon");
		
		mav.addObject("titol", I18NUtils.tradueix("error.403.titol"));
		mav.addObject("desc", I18NUtils.tradueix("error.403.desc"));
		mav.addObject("url", request.getContextPath() + "/logout");
		mav.addObject("urlBtnText", I18NUtils.tradueix("error.403.boto"));
		
	    return mav;
	}
	
}
