package es.caib.carpeta.back.controller.all;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.carpeta.commons.utils.Version;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @autor anadal
 * 
 */
@Controller
public class PublicController {

    protected final Logger log = Logger.getLogger(getClass());
    
    @Autowired
    Version versio;

    @RequestMapping(value = "/public/versio")
    public void versio(HttpServletResponse response) throws Exception {
        response.getWriter().write(versio.getVersion() + "|" + versio.getBuildTime());
        response.getWriter().flush();
        response.getWriter().close();

    }

}
