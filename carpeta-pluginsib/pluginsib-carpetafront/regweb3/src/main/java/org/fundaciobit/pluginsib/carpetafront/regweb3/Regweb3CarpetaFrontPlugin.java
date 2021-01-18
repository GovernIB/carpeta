package org.fundaciobit.pluginsib.carpetafront.regweb3;

import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

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
import java.sql.Timestamp;
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

    
    
    public static final String MIME_JPG  = "image/jpeg";
    public static final String MIME_XML1  = "text/xml";
    public static final String MIME_XML2  = "application/xml";
    public static final String MIME_PDF  = "application/pdf";
    public static final String MIME_PNG  = "image/png";
    public static final String MIME_RTF  = "text/rtf";
    public static final String MIME_RTF2  = "application/rtf";
    public static final String MIME_SVG  = "image/svg+xml";
    public static final String MIME_TIFF = "image/tiff";
    public static final String MIME_TXT  = "text/plain";
    public static final String MIME_ODT  = "application/vnd.oasis.opendocument.text";
    public static final String MIME_XLSX  = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String MIME_PPTX  = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
    public static final String MIME_ODP  = "application/vnd.oasis.opendocument.presentation";
    public static final String MIME_ODS  = "application/vnd.oasis.opendocument.spreadsheet";
    public static final String MIME_ODG  = "application/vnd.oasis.opendocument.graphics";
    public static final String MIME_DOCX  = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String MIME_HTML  = "text/html";
    public static final String MIME_DEFAULT  = "application/octet-stream";
    
    public static final Map<String, String> TEXTO_REDUCIDO_BY_TIPO_MIME = new HashMap<String, String>() {{
        put(MIME_JPG, "JPG");
        put(MIME_XML1, "XML");
        put(MIME_XML2, "XML");
        put(MIME_PDF, "PDF");
        put(MIME_PNG, "PNG");
        put(MIME_RTF, "RTF");
        put(MIME_RTF2, "RTF2");
        put(MIME_SVG, "SVG");
        put(MIME_TIFF, "TIFF");
        put(MIME_TXT, "TXT");
        put(MIME_ODT, "ODT");
        put(MIME_XLSX, "XLSX(EXCEL)");
        put(MIME_PPTX, "PPTX");
        put(MIME_ODP, "ODP");
        put(MIME_ODS, "ODS");
        put(MIME_ODG, "ODG");
        put(MIME_DOCX, "DOCX");
        put(MIME_HTML, "HTML");
        put(MIME_DEFAULT, "OCTET-STREAM");
    }};

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
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

        // XYZ ZZZ
        map.put("form_action", absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE);
        map.put("lang", locale.getLanguage());

        map.put("title", getTitle(locale));




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
            log.info(" REGISTRES NULL o EMPTY: " + registros);
            
            /*            
            AsientoRegistralWs as1 = new AsientoRegistralWs();
            as1.setNumeroRegistroFormateado("XXX1");
            as1.setFechaRegistro(new Timestamp(2018 - 1900, 1, 3, 0, 0, 0, 0));
            as1.setResumen("Un resumen 1");
            as1.setUnidadTramitacionDestinoDenominacion("LDER7863254");
            registros.add(as1);
            
            
            AsientoRegistralWs as2 = new AsientoRegistralWs();
            as2.setNumeroRegistroFormateado("XXX2");
            as2.setFechaRegistro(new Timestamp(2019 - 1900, 1, 2, 0, 0, 0, 0));
            as2.setResumen("Un resumen 2");
            as2.setUnidadTramitacionDestinoDenominacion("LDER7863254");
            registros.add(as2);
            
            AsientoRegistralWs as3 = new AsientoRegistralWs();
            as3.setNumeroRegistroFormateado("XXX3");
            as3.setFechaRegistro(new Timestamp(2020 - 1900, 1, 1, 0, 0, 0, 0));
            as3.setResumen("Un resumen 3");
            as3.setUnidadTramitacionDestinoDenominacion("LDER7863254");
            registros.add(as3);
            */
            
        } else {
            int x = 1;
            for (AsientoRegistralWs ar : registros) {

                log.info(" -------------  REGISTRE [" + x + " ] -------------------");
                log.info("ar.getNumeroRegistroFormateado() => " + (ar.getNumeroRegistroFormateado()));
                log.info("ar.getResumen() => " + ar.getResumen());
                log.info("ar.getFechaRegistro(); => " + ar.getFechaRegistro());
                log.info("ar.getUnidadTramitacionDestinoDenominacion() => " + ar.getUnidadTramitacionDestinoDenominacion());

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
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

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



        map.put("tipusMime", TEXTO_REDUCIDO_BY_TIPO_MIME);

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
    public  FileInfo getResourceIcon(Locale locale)  {

        return getImageFromResource(locale, "/logo/logo-regweb3.png", "image/png");

    }
    
    @Override
    public String getPropertyBase() {
        return REGWEB3_PROPERTY_BASE;
    }


}
