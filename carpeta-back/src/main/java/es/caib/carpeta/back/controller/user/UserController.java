package es.caib.carpeta.back.controller.user;

import es.caib.carpeta.persistence.Entidad;
import es.caib.carpeta.persistence.Usuario;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController {
  protected final Logger log = Logger.getLogger(getClass());


  @EJB(mappedName = es.caib.carpeta.ejb.UsuarioService.JNDI_NAME)
  protected es.caib.carpeta.ejb.UsuarioService usuarioService;

  @EJB(mappedName = es.caib.carpeta.ejb.EntidadService.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntidadService entidadService;

  
  @RequestMapping(value = "/option1")
  public ModelAndView option1(HttpSession session,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {


    List<Usuario> usuarioList = usuarioService.busquedaUsuario(null, "mgonzalez", "","","", null);

    log.info("Usuario encontrado " +  usuarioList.get(0));
    
    ModelAndView mav = new ModelAndView("option1User");
    mav.addObject("usuario", usuarioList.get(0));
    mav.addObject("optionNumber", "OPCIÓ USER -1-");
    return mav;
    
  }
  
  
  @RequestMapping(value = "/option2")
  public ModelAndView option2(HttpSession session,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    List<Entidad> entidadList = entidadService.findByCodigoDir3("A04003007");

    ModelAndView mav = new ModelAndView("option2User");
    mav.addObject("optionNumber", "OPCIÓ USER -2-");
    mav.addObject("entidades", entidadList);
    return mav;
  }


  @RequestMapping(value = "/option3")
  public ModelAndView option3(HttpSession session,
                              HttpServletRequest request, HttpServletResponse response)
     throws Exception {

    List<Entidad> entidadList = entidadService.findByCodigoDir3("A04003007");

    ModelAndView mav = new ModelAndView("option3User");
    mav.addObject("optionNumber", "OPCIÓ USER -3-");
    mav.addObject("entidades", entidadList);
    return mav;
  }
  
}
