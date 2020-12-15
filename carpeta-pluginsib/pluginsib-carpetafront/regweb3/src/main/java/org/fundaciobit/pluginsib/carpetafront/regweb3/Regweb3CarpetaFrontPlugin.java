package org.fundaciobit.pluginsib.carpetafront.regweb3;

import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWsService;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


/**
 * @author anadal
 * @author mgonzalez
 */
public class Regweb3CarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String REGWEB3_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "regweb3.";


    /**
     *
     */
    public Regweb3CarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public Regweb3CarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public Regweb3CarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public String getTitle(Locale locale) {
        return getTraduccio("title", locale);
    }

    @Override
    public String getSubTitle(Locale locale) {
        return getTraduccio("subtitle", locale);
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontregweb3";
    }


    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
                              HttpServletRequest request, UserData userData, String administrationIDEncriptat) throws Exception {

        registerUserData(userData);
        
        String startURL = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE;

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }


    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                    HttpServletRequest request, HttpServletResponse response, UserData userData,
                                    String administrationEncriptedID, Locale locale, boolean isGet) {


        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => administrationID: " + userData.getAdministrationID());
        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: " + administrationEncriptedID);


        if (query.startsWith(LLISTAT_REGISTRES_PAGE)) {


            llistatDeRegistres(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(DETALL_REGISTRE_PAGE)) {

            detallDeRegistre(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData, administrationEncriptedID, locale, isGet);
        } else {


            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData, administrationEncriptedID, locale, isGet);
        }


    }


    public boolean isDevelopment() {
        return "true".equals(getProperty(REGWEB3_PROPERTY_BASE + "development"));
    }

    public String getEntidad() throws Exception {
        return getPropertyRequired(REGWEB3_PROPERTY_BASE + "entidad");

    }


    public String getDetalleTitle(Locale locale) {
        return getTraduccio("detalletitle", locale);
    }


    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // -------------------     L L I S T A T   DE   REGISTRES                ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------


    protected static final String LLISTAT_REGISTRES_PAGE = "llistatRegistres";
    

    List<AsientoRegistralWs> listRegistros = new ArrayList<>();


    public void llistatDeRegistres(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                   HttpServletRequest request, HttpServletResponse response, UserData userData,
                                   String administrationEncriptedID, Locale locale, boolean isGet) {

        try {


            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");


            String webpage = getLlistatDeRegistresPage(absolutePluginRequestPath, relativePluginRequestPath, userData, getEntidad(), locale, isGet);

            try {
                response.getWriter().println(webpage);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error("Error llistant registres: " + e.getMessage(), e);
        }


    }


    public String getLlistatDeRegistresPage(String absolutePluginRequestPath, String relativePluginRequestPath,UserData userData, String entidad, Locale locale, boolean isGet) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        List<AsientoRegistralWs> registres;


        if (isDevelopment()) {
            registres = getRegistresDebug(userData.getAdministrationID(), entidad);
        } else {
            registres = getRegistres(userData.getAdministrationID(), entidad);
        }


        InputStream input = this.getClass().getResourceAsStream("/webpage/regweb3.html");

        String plantilla = IOUtils.toString(input, "UTF-8");


        // XYZ ZZZ
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCE);

        // XYZ ZZZ
        map.put("form_action", absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE);
        map.put("lang", locale.getLanguage());

        map.put("title", getTitle(new Locale("ca")));




        String[] traduccions = {"registro.listado", "registro.descripcion", "registro.numero",
           "registro.fecha", "registro.extracto", "registro.destinatario", "registro.vacio", "carpeta.acciones", "registro.detalle"};

        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }


        String urlDetalle = absolutePluginRequestPath  + "/" + DETALL_REGISTRE_PAGE +"?numeroRegistroFormateado=";


        map.put("urlDetalle" , urlDetalle);

        map.put("registros", registres);

        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

        return generat;

    }


    private List<AsientoRegistralWs> getRegistresDebug(String administrationID, String entidad) throws Exception {


        List<AsientoRegistralWs> registros = this.getRegistres(administrationID, entidad);


        if (registros == null || registros.isEmpty()) {
            System.out.println(" REGISTRES NULL o EMPTY: " + registros);
        } else {
            int x = 1;
            for (AsientoRegistralWs ar : registros) {

                System.out.println(" -------------  REGISTRE [" + x + " ] -------------------");
                System.out.println("ar.getNumeroRegistroFormateado() => " + (ar.getNumeroRegistroFormateado()));
                System.out.println("ar.getResumen() => " + ar.getResumen());
                System.out.println("ar.getFechaRegistro(); => " + ar.getFechaRegistro());
                System.out.println("ar.getUnidadTramitacionDestinoDenominacion() => " + ar.getUnidadTramitacionDestinoDenominacion());

                x++;

            }

        }

        return registros;
    }


    public List<AsientoRegistralWs> getRegistres(String administrationID, String entidad)
       throws Exception {


        RegWebAsientoRegistralWs service = getRegWebAsientoRegistralWsService();


        ResultadoBusquedaWs result = service.obtenerAsientosCiudadano(entidad, administrationID, 0);


        @SuppressWarnings("unchecked")
        List<AsientoRegistralWs> registros = (List<AsientoRegistralWs>) (List<?>) result.getResults();
        this.listRegistros = registros;
        return registros;
    }


    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // -------------------     DETALL   DE   REGISTRE                        ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String DETALL_REGISTRE_PAGE = "detallRegistre";


    public void detallDeRegistre(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                 HttpServletRequest request, HttpServletResponse response, UserData userData,
                                 String administrationEncriptedID, Locale locale, boolean isGet) {

        try {


            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");


            String numeroRegistroFormateado = request.getParameter("numeroRegistroFormateado");


            String webpage = getDetallDeRegistrePage(absolutePluginRequestPath,numeroRegistroFormateado, userData.getAdministrationID(),getEntidad(),locale);

            try {
                response.getWriter().println(webpage);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error("Error llistant registres: " + e.getMessage(), e);
        }


    }


    public String getDetallDeRegistrePage(String absolutePluginRequestPath, String numeroRegistroFormateado,String administrationID, String entidad, Locale locale) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        AsientoRegistralWs registre;


        if (isDevelopment()) {
            registre = getDetallRegistreDebug(numeroRegistroFormateado,administrationID,entidad);
        } else {
            registre = getDetallRegistre(numeroRegistroFormateado,administrationID,entidad);
        }


        InputStream input = this.getClass().getResourceAsStream("/webpage/detall.html");

        String plantilla = IOUtils.toString(input, "UTF-8");


        // XYZ ZZZ
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCE);

        // XYZ ZZZ
        map.put("form_action", absolutePluginRequestPath + "/" + DETALL_REGISTRE_PAGE);
        map.put("lang", locale.getLanguage());
        map.put("detalletitle", getDetalleTitle(locale));


        //Traduccions tipoDocumentacionFísica
        String [] tipoDocumentacioFisica = {"",getTraduccio("registro.tipoDocumentacionFisica.1",locale),
                getTraduccio("registro.tipoDocumentacionFisica.2",locale),
                getTraduccio("registro.tipoDocumentacionFisica.3",locale)};

        map.put("docFisica", tipoDocumentacioFisica);

        //Traduccions tipoInteresado
        String [] tipoInteresado = {"",getTraduccio("registro.tipoInteresado.1",locale),
                getTraduccio("registro.tipoInteresado.2",locale),
                getTraduccio("registro.tipoInteresado.3",locale)};

        map.put("tipoInteresado", tipoInteresado);


        //Traduccions idioma Registro
        String [] registroIdioma = {"",getTraduccio("registro.idioma.1",locale),
                getTraduccio("registro.idioma.2",locale)};

        map.put("registroIdioma", registroIdioma);



        map.put("tipusMime", Constants.TEXTO_REDUCIDO_BY_TIPO_MIME);

        String[] traduccions = {"registro.titulo.detalle", "registro.entrada", "registro.fecha", "registro.numero",
           "registro.oficina", "registro.destinatario", "registro.tipo.doc", "registro.extracto", "carpeta.idioma",
           "registro.presencial", "registro.justificante", "registre.justifcante.pendiente", "registro.interesados",
           "registro.interesado.nombre", "registro.interesado.documento", "registro.interesado.tipo", "registro.anexos",
           "registro.anexos.vacio", "registro.anexo.titulo", "registro.anexo.tipomime"};


        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }


        map.put("registro", registre);

        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

        return generat;

    }

    private AsientoRegistralWs getDetallRegistreDebug(String numeroRegistroFormateado,String administrationID, String entidad) throws Exception {


        AsientoRegistralWs registro = getDetallRegistre(numeroRegistroFormateado, administrationID, entidad);


        if (registro == null) {
            System.out.println(" REGISTRE NULL o EMPTY: " + registro);
        } else {
            System.out.println(" -------------  REGISTRE  -------------------");
            System.out.println("ar.getNumeroRegistroFormateado() => " + (registro.getNumeroRegistroFormateado()));
            System.out.println("ar.getResumen() => " + registro.getResumen());
            System.out.println("ar.getFechaRegistro(); => " + registro.getFechaRegistro());
            System.out.println("ar.getUnidadTramitacionDestinoDenominacion() => " + registro.getUnidadTramitacionDestinoDenominacion());
            System.out.println("ar.getEntidadRegistralInicioDenominacion() => " + registro.getEntidadRegistralInicioDenominacion());
            System.out.println("ar.getTipoDocumentacionFisicaCodigo() => " + registro.getTipoDocumentacionFisicaCodigo());
            System.out.println("ar.getIdioma() => " + registro.getIdioma());
            System.out.println("ar.isPresencial() => " + registro.isPresencial());
            System.out.println("Anexos retornados => " + registro.getAnexos().size());


        }

        return registro;
    }


    public AsientoRegistralWs getDetallRegistre(String numeroRegistroFormateado,String administrationID, String entidad)
       throws Exception {


        // Llamamos a obtenerAsientoCiudadano para obtener la información completa
        RegWebAsientoRegistralWs service = getRegWebAsientoRegistralWsService();

        AsientoRegistralWs registro= service.obtenerAsientoCiudadano(entidad, administrationID, numeroRegistroFormateado);



        return registro;
    }


    /**
     * @return
     * @throws Exception
     */
    private RegWebAsientoRegistralWs getRegWebAsientoRegistralWsService() throws Exception {

        final String regweb3Url = getPropertyRequired(REGWEB3_PROPERTY_BASE + "url");

        final String username = getPropertyRequired(REGWEB3_PROPERTY_BASE + "user");

        final String password = getPropertyRequired(REGWEB3_PROPERTY_BASE + "pass");

        final URL wsdl = new URL(regweb3Url + "?wsdl");


        RegWebAsientoRegistralWsService service = new RegWebAsientoRegistralWsService(wsdl);

        RegWebAsientoRegistralWs api = service.getRegWebAsientoRegistralWs();


        Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, regweb3Url);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, username);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, password);

        return api;
    }




    /**
     * Mètode que retorna la icona del plugin
     * @param locale
     * @return
     */
    @Override
    public  FileInfo getIcon(Locale locale){

        InputStream input;

        FileInfo fileInfo = null;
        String resource = "/" + LOGORESOURCE ; //resource a on es troba l'icona
        try {

            //Agafa la icona del resource
            input = this.getClass().getResourceAsStream(resource+"/logo-regweb3.png");
            if(input != null) {
                fileInfo =  new  FileInfo("logo-regweb3.png","image/png", IOUtils.toByteArray(input));
            }

        }  catch (Exception e) {
            log.error("Error llegint recurs : "+resource+"/logo-regweb3.png" + e.getMessage(), e);

        }
        return fileInfo;

    }


}
