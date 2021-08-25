package es.caib.carpeta.front.utils;


import es.caib.carpeta.commons.utils.Constants;
import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


import javax.annotation.security.RunAs;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Servlet emprat per inicialitzar el Back
 * 
 * @author anadal
 * 
 */
@RunAs(Constants.CAR_ADMIN)
public class InitServlet extends HttpServlet {

    protected final Logger log = Logger.getLogger(getClass());

    @Override
    public void init(ServletConfig config) throws ServletException {


        // Sistema de Traduccions WEB
        try {
            ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
            String[] basenames = { "missatges", // /WEB-INF/classes/
                   // "logicmissatges", "genapp", "carpeta_genapp" 
                    };
            ms.setDefaultEncoding("UTF-8");
            ms.setBasenames(basenames);
            I18NUtils.setMessageSource(ms);
        } catch (Throwable th) {
            log.error("Error inicialitzant el sistema de traduccions web: " + th.getMessage(), th);
        }


    }

}
