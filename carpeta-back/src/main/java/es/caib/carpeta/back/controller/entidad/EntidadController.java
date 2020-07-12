package es.caib.carpeta.back.controller.entidad;

import es.caib.carpeta.back.controller.common.CommonController;
import es.caib.carpeta.back.form.EntidadForm;
import es.caib.carpeta.commons.utils.Idioma;
import es.caib.carpeta.persistence.Entidad;
import es.caib.carpeta.persistence.TraduccionBase;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/entidad/")
public class EntidadController extends CommonController {

	@EJB(mappedName = es.caib.carpeta.ejb.EntidadService.JNDI_NAME)
	protected es.caib.carpeta.ejb.EntidadService entidadService;


	protected final Logger log = Logger.getLogger(getClass());

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {

		Entidad entidad = entidadService.findById(id);

		EntidadForm form = new EntidadForm();

		form.setEntidad(entidad);

		ModelAndView mav = new ModelAndView("entidadform");

		mav.addObject("form", form);

		return mav;

	}


	/**
	 * Carga el formulario para una nueva {@link es.caib.carpeta.persistence.Entidad}
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView nuevaEntidad( HttpServletRequest request) throws Exception {

		Entidad entidad = new Entidad();
		EntidadForm form = new EntidadForm();

      //Inicializamos las traducciones
		Idioma.stream()
			.forEach(element -> entidad.setTraduccion(element.toString(), new TraduccionBase()));


		ModelAndView mav = new ModelAndView("entidadform");
		mav.addObject("entidadForm", form);

		return mav;
	}

	/**
	 * Guardar un nuevo {@link es.caib.carpeta.persistence.Entidad}
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String nuevaEntidad(@ModelAttribute EntidadForm entidadForm, BindingResult result, Model model, SessionStatus status, HttpServletRequest request) throws Exception {

		//entidadValidator.validate(entidadForm, result);

		if (result.hasErrors()) { // Si hay errores volvemos a la vista del formulario

			return "entidad/entidadForm";

		} else { // Si no hay errores guardamos el registro

			try {
				Entidad entidad = entidadForm.getEntidad();

				//Guardamos la nueva Entidad y sus propiedades por defecto
				entidadService.crearEntidad(entidad);


				log.info("Entidad creada " + entidad.getId());

				//Mensaje.saveMessageInfo(request, getMessage("regweb.guardar.registro"));
			} catch (I18NException e) {
				//Mensaje.saveMessageError(request, getMessage("regweb.error.registro"));
				e.printStackTrace();
			}

			status.setComplete();
			return "redirect:/entidad/list";
		}


	}


	/**
	 * Listado de entidades
	 *
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() throws Exception {

		ModelAndView mav = new ModelAndView("entidadlist");
		try {
			List<Entidad> listado = entidadService.findAllList();
			mav.addObject("listado", listado);
		} catch (I18NException e) {

			e.printStackTrace();
		}


		return mav;
	}





}
