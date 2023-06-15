package org.fundaciobit.pluginsib.carpetafront.certificats;

import es.caib.carpeta.apiexterna.client.api.CertificatsApi;
import es.caib.carpeta.apiexterna.client.model.CertificatBean;
import es.caib.carpeta.apiexterna.client.model.CertificatFileInfo;
import es.caib.carpeta.apiexterna.client.model.CertificatInfo;
import es.caib.carpeta.apiexterna.client.services.ApiClient;
import es.caib.carpeta.apiexterna.client.services.auth.HttpBasicAuth;
import es.caib.carpeta.pluginsib.carpetafront.api.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @author anadal
 */
public class CertificatsCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String CERTIFICATS_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "certificats.";
    protected static final String ESPERA_CERTIFICATS_PAGE = "esperacertificats";

    /**
     *
     */
    public CertificatsCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     */
    public CertificatsCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public CertificatsCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontcertificats";
    }

    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------
    // ------------------- CACHE DE TITOLS i SUBTITOLS ----------------------------
    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------

    private TitlesInfo titlesInfo = null;;

    @Override
    public void setTitlesInfo(TitlesInfo titlesInfo) {
        this.titlesInfo = titlesInfo;
    }

    @Override
    public TitlesInfo getTitlesInfo() {
        return titlesInfo;
    }

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userdata, String administrationEncriptedID, String parameter,
            IListenerLogCarpeta listenerError) throws Exception {

        registerUserData(userdata);

        String startURL = (isReactComponent()) ? absolutePluginRequestPath + "/" + INDEX_HTML_PAGE
                : absolutePluginRequestPath + "/" + ESPERA_CERTIFICATS_PAGE;

        log.info(" CERTIFICATS getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public String getPropertyBase() {
        return CERTIFICATS_PROPERTY_BASE;
    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        log.info("CertificatsCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("CertificatsCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("CertificatsCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                + administrationEncriptedID);

        if (query.startsWith(INDEX_HTML_PAGE)) {

            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {

            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_TE_CERTIFICATS)) {

            teCertificatRest(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_DESCARREGA_CERTIFICATS)) {

            descarregaCertificatsRest(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);

        } else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet, logCarpeta);
        }

    }

    @Override
    public boolean isReactComponent() {
        return true;
    }

    @Override
    protected FileInfo getResourceIcon(Locale locale) {
        return getImageFromResource(locale, "/logo/logo-certificats.png", "image/png");
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- INDEX ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String INDEX_HTML_PAGE = "index.html";

    public void index(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("text/html");

            String resource = "/webpage_certificat/certificats_index.html";

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(INDEX_HTML_PAGE, "UTF-8") + "\"");

            response.setCharacterEncoding("utf-8");

            InputStream input = this.getClass().getResourceAsStream(resource);

            String plantilla = IOUtils.toString(input, "UTF-8");

            Map<String, Object> map = new HashMap<String, Object>();

            Gson json = new Gson();

            TitlesInfo titles = getTitlesInfo();
            
            map.put("titles", json.toJson(titles.getTitlesByLang()));

            map.put("subtitles", json.toJson(titles.getSubtitlesByLang()));
            
            String activeStr = getPropertyRequired(CERTIFICATS_PROPERTY_BASE+"active");

            String[] active = activeStr.split(",");
            
            PluginInfo[] plugins = new PluginInfo[active.length];
            String lang = locale.getLanguage();
            
            for (int i = 0; i < active.length; i++) {
                PluginInfo p = new PluginInfo();
                String num = active[i];
                p.setPluginNumber(Integer.parseInt(num));
                p.setTitle(getPropertyRequired(CERTIFICATS_PROPERTY_BASE+num+".label."+lang));
                p.setSubtitle("XYZ");
                plugins[i] = p;
            }
            
            map.put("pluginsToLoad", json.toJson(plugins));

            log.info("absolutePluginRequestPath ==> " + absolutePluginRequestPath);

            String pathtojs = absolutePluginRequestPath + "/" + REACT_JS_PAGE;

            map.put("pathtojs", pathtojs);

            /*
             * map.put("usuariNom", userData.getName());
             * map.put("usuariLlinatge1", userData.getSurname1()==
             * null?"":userData.getSurname1());
             * map.put("usuariLlinatge2", userData.getSurname2()==
             * null?"":userData.getSurname2());
             * map.put("usuariDNI", userData.getAdministrationID());
             * map.put("usuariMetode", userData.getAuthenticationMethod());
             * 
             * map.put("userData", json.toJson(userData));
             */

            String pathtoserveiTe = absolutePluginRequestPath + "/" + URL_REST_SERVICE_TE_CERTIFICATS;

            map.put("pathtoserveiTe", pathtoserveiTe);

            String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            try {
                response.getWriter().println(generat);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            // XYZ ZZZ
            log.error("Error retornant pàgina principal del Plguin " + this.getTitle(locale) + ": " + e.getMessage(),
                    e);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------- JAVASCRIPT REACT
    // -----------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "certificats_reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage_certificat/certificats_reactjs_main.js";

            response.setCharacterEncoding("utf-8");

            InputStream input = this.getClass().getResourceAsStream(resource);

            String plantilla = IOUtils.toString(input, "UTF-8");

            try {
                response.getWriter().println(plantilla);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error("Error llistant registres XYZ ZZZ: " + e.getMessage(), e);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- CONSULTA REST --------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_TE_CERTIFICATS = "teCertificatRest";

    public void teCertificatRest(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, Boolean isGet) {
        try {
            
            int pos = query.lastIndexOf('/');
            String numeroStr = query.substring(pos+1);
            CertificatsApi api = getApi(Integer.parseInt(numeroStr));
            


            /*
             * String dni = "99999999X";
             * if(api.teCertificat(dni)) {
             * String idioma = "ca";
             * CertificatBean cert = api.descarregarCertificat(dni, idioma);
             * //XYZ ZZZ
             * System.out.println("Tipus = " + cert.getTipus());
             * System.out.println("Nom Fitxer = " + file.getNom());
             * System.out.println("Mime Fitxer = " + file.getMime());
             * System.out.println("Length Fitxer = " + file.getLength());
             * System.out.println("Contingut Fitxer Codificat= " +
             * file.getBytes());
             * }else {
             * System.out.println("L'usuari amb DNI: "+dni +
             * " no te certificats per aquest plugin.");
             * 
             * }
             */
            
            log.info("XYZ ZZZ userData AdministrationID = " + userData.getAdministrationID());
            CertificatInfo teCertInfo = api.teCertificat(userData.getAdministrationID());
            Boolean teCertificat = teCertInfo.isTeCertificat();
            String url = teCertificat ? absolutePluginRequestPath + "/" + URL_REST_SERVICE_DESCARREGA_CERTIFICATS +"/"+numeroStr 
                    : null;

            Gson gson = new Gson();
            String json = gson.toJson(url);

            // log.info(json);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            
            Thread.sleep(1000);

            response.getWriter().write(json);

        } catch (Exception e) {
            log.error("Error comprovant possesió de certificats: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    protected static final String URL_REST_SERVICE_DESCARREGA_CERTIFICATS = "descarregaCertificatsRest";

    public void descarregaCertificatsRest(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, Boolean isGet) {
        try {

            // XYZ ZZZ Centralitzar en un mètode i agafar dades de propietats
            log.info("XYZ ZZZ query = "+query);
            int pos = query.lastIndexOf('/');
            log.info("XYZ ZZZ pos = "+pos);
            String numeroStr = query.substring(pos+1);
            log.info("XYZ ZZZ numeroStr = |"+numeroStr+"|");
            CertificatsApi api = getApi(Integer.parseInt(numeroStr));

            log.info("XYZ ZZZ AdministrationID = "+userData.getAdministrationID());
            CertificatBean cert = api.descarregarCertificat(userData.getAdministrationID(), locale.getLanguage());

            CertificatFileInfo file = cert.getFitxer();

            // XYZ ZZZ
            log.info("Tipus = " + cert.getTipus());
            log.info("Nom Fitxer = " + file.getNom());
            log.info("Mime Fitxer = " + file.getMime());
            log.info("Length Fitxer = " + file.getLength());
            log.info("Contingut Fitxer Codificat= " + file.getBytes());

            // modifies response
            response.setContentType(file.getMime());
            response.setContentLength(file.getLength());

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", file.getNom());
            response.setHeader(headerKey, headerValue);

            OutputStream outStream = response.getOutputStream();
            FileInputStream inStream = new FileInputStream(file.getBytes());
            
            byte[] buffer = new byte[1024*1024];
            int bytesRead = -1;
             
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
             

            inStream.close();
            outStream.close();

        } catch (Exception e) {
            log.error("Error comprovant possesió de certificats: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);
        }

    }

    private CertificatsApi getApi(int pluginNumber) throws Exception {
        ApiClient client = new ApiClient();
        client.setBasePath(getPropertyRequired(CERTIFICATS_PROPERTY_BASE+pluginNumber+".url"));
        HttpBasicAuth auth = (HttpBasicAuth) client.getAuthentication("BasicAuth");
        auth.setUsername(getPropertyRequired(CERTIFICATS_PROPERTY_BASE+pluginNumber+".username"));
        auth.setPassword(getPropertyRequired(CERTIFICATS_PROPERTY_BASE+pluginNumber+".password"));

        CertificatsApi api = new CertificatsApi(client);
        return api;
    }

}
