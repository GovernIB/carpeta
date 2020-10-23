package es.caib.carpeta.front.controller;

import com.google.gson.Gson;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaLocal;
import es.caib.carpeta.logic.utils.PluginInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class InicioController {

    @EJB(mappedName = UtilitiesForFrontLogicaLocal.JNDI_NAME)
    UtilitiesForFrontLogicaLocal utilsEjb;

    protected final Log log = LogFactory.getLog(getClass());


    @RequestMapping(value={"/", "/inici"})
    public ModelAndView inicio(HttpServletRequest request) throws I18NException {

        String lang = LocaleContextHolder.getLocale().getLanguage();
        // TODO XYZ ZZZ falta entitat
        String codiEntitat = "caib";

        List<PluginInfo> pluginsEntitat = utilsEjb.getFrontPlugins(codiEntitat, lang);

        ModelAndView mav = new ModelAndView("inici");

        // Passar JSON de pluginsEntitat
        Gson gson = new Gson();
        String plugins = gson.toJson(pluginsEntitat);
        mav.addObject("plugins", plugins);

        return mav;

    }

}
