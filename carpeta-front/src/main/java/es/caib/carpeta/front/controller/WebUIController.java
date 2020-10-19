package es.caib.carpeta.front.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import es.caib.carpeta.hibernate.HibernateFileUtil;
import es.caib.carpeta.jpa.EnllazJPA;
import es.caib.carpeta.model.entity.Enllaz;

/**
 * Conjunt de cridades REST per obtenir informació per a la presentació de la
 * pàgina WEB.
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = WebUIController.WEBUI_PATH)
public class WebUIController extends CommonFrontController {

    public static final String WEBUI_PATH = "/webui";

    public static final String WEBUI_FAVICON_PATH = "/icona";

    public static final String WEBUI_LOGOLATERAL_PATH = "/logolateral";

    /**
     * 
     * @author anadal
     *
     */
    public static class EnllazInfo {
        protected String label;
        protected String url;
        protected String urllogo;

        public EnllazInfo() {
            super();
            // TODO Auto-generated constructor stub
        }

        public EnllazInfo(String label, String url, String urllogo) {
            super();
            this.label = label;
            this.url = url;
            this.urllogo = urllogo;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrllogo() {
            return urllogo;
        }

        public void setUrllogo(String urllogo) {
            this.urllogo = urllogo;
        }

    }

    @RequestMapping(value = WEBUI_LOGOLATERAL_PATH, method = RequestMethod.GET)
    public void getEntitatLogoLateral(HttpServletRequest request, HttpServletResponse response) {

        try {
            // XYZ ZZZ Pendent codiEntitat
            String codiEntitat = "caib";

            Long iconaEntitatId = utilsEjb.getLogolateralEntitat(codiEntitat);

            descarregaFitxer(response, iconaEntitatId);

        } catch (Throwable e) {
            processException(e, response);
        }

    }

    @RequestMapping(value = WEBUI_FAVICON_PATH, method = RequestMethod.GET)
    public void getEntitatFavicon(HttpServletRequest request, HttpServletResponse response) {

        try {
            // XYZ ZZZ Pendent codiEntitat
            String codiEntitat = "caib";

            Long iconaEntitatId = utilsEjb.getIconaEntitat(codiEntitat);

            descarregaFitxer(response, iconaEntitatId);

        } catch (Throwable e) {
            processException(e, response);
        }

    }

    public static final String ENLLAZ_LOGO_PATH = "/enllazlogo";

    @RequestMapping(value = ENLLAZ_LOGO_PATH + "/{encodedenllazlogoid}", method = RequestMethod.GET)
    public void getEnllazLogo(@PathVariable("encodedenllazlogoid") String encodedenllazlogoid,
            HttpServletRequest request, HttpServletResponse response) {

        try {
            String fileIDStr = HibernateFileUtil.decryptString(encodedenllazlogoid);

            Long fileID = Long.parseLong(fileIDStr);

            descarregaFitxer(response, fileID);

        } catch (Throwable e) {
            processException(e, response);
        }

    }

    @RequestMapping(value = "/socialnetworks", method = RequestMethod.GET)
    public void getSocialNetworks(HttpServletRequest request, HttpServletResponse response) {

        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();
            // TODO XYZ ZZZ falta entitat
            String codiEntitat = "caib";

            List<Enllaz> enllazos = utilsEjb.getSocialNetworks(codiEntitat, lang);

            List<EnllazInfo> enllazosInfo = new ArrayList<WebUIController.EnllazInfo>();

            for (Enllaz enllaz : enllazos) {

                EnllazJPA enllazJPA = (EnllazJPA) enllaz;

                String label = enllazJPA.getNomTraduccions().get(lang).getValor();
                String url = enllazJPA.getUrlTraduccions().get(lang).getValor();

                String urllogo = request.getContextPath() + WEBUI_PATH + ENLLAZ_LOGO_PATH + "/"
                        + HibernateFileUtil.encryptFileID(enllazJPA.getLogoID());

                enllazosInfo.add(new EnllazInfo(label, url, urllogo));

            }

            // Passar JSON de pluginsEntitat
            Gson gson = new Gson();
            String json = gson.toJson(enllazosInfo);

            response.setContentType("application/json");

            response.getWriter().write(json);

        } catch (Throwable e) {
            processException(e, response);
        }
    }

}
