package es.caib.carpeta.back.controller.common;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.form.UsuarioForm;
import es.caib.carpeta.persistence.Usuario;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/common/usuario/")
public class UsuarioController {

	@EJB(mappedName = es.caib.carpeta.ejb.UsuarioService.JNDI_NAME)
	protected es.caib.carpeta.ejb.UsuarioService usuarioService;

	protected final Logger log = Logger.getLogger(getClass());

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {

		Usuario usuario = usuarioService.findById(id);

		UsuarioForm form = new UsuarioForm();

		form.setUsuario(usuario);

		ModelAndView mav = new ModelAndView("usuarioform");

		mav.addObject("form", form);

		return mav;

	}

}
