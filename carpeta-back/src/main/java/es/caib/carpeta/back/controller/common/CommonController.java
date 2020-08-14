package es.caib.carpeta.back.controller.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/common/")
public class CommonController {

	
	 @RequestMapping(value = "/systemproperties")
	  public ModelAndView systemProperties(HttpSession session,
	      HttpServletRequest request, HttpServletResponse response)
	      throws Exception {
	    
	    ModelAndView mav = new ModelAndView("systemproperties");
	    return mav;
	    
	  }
  
  
  @RequestMapping(value = "/option1")
  public ModelAndView option1(HttpSession session,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    ModelAndView mav = new ModelAndView("option1Common");
    mav.addObject("optionNumber", "OPCIÓ -1-");
    return mav;
    
  }
  
  
  @RequestMapping(value = "/option2")
  public ModelAndView option2(HttpSession session,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    ModelAndView mav = new ModelAndView("option2Common");
    mav.addObject("optionNumber", "OPCIÓ -2-");
    return mav;
  }


  
}
