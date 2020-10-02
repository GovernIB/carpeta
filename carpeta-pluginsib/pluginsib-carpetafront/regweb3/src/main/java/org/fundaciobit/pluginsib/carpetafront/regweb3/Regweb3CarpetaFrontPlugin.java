package org.fundaciobit.pluginsib.carpetafront.regweb3;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWsService;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;
import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.core.utils.XTrustProvider;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

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
                              HttpServletRequest request, String administrationID, String administrationIDEncriptat) throws Exception {

        String startURL = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE;

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }


    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                    HttpServletRequest request, HttpServletResponse response, String administrationID,
                                    String administrationEncriptedID, Locale locale, boolean isGet) {


        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => administrationID: " + administrationID);
        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: " + administrationEncriptedID);


        if (query.startsWith(LLISTAT_REGISTRES_PAGE)) {


            llistatDeRegistres(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, administrationID, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(DETALL_REGISTRE_PAGE)) {

            detallDeRegistre(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, administrationID, administrationEncriptedID, locale, isGet);
        } else {


            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, administrationID, administrationEncriptedID, locale, isGet);
        }


    }


    public boolean isDevelopment() {
        return "true".equals(getProperty(REGWEB3_PROPERTY_BASE + "development"));
    }

    public String getWeb() throws Exception {
        return getPropertyRequired(REGWEB3_PROPERTY_BASE + "web");

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


    public static AsientoRegistralWs findByNumeroRegistroFormateado(Collection<AsientoRegistralWs> listRegistros, String numeroRegistroFormateado) {
        return listRegistros.stream().filter(registro -> numeroRegistroFormateado.equals(registro.getNumeroRegistroFormateado())).findFirst().orElse(null);
    }

    public void llistatDeRegistres(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                   HttpServletRequest request, HttpServletResponse response, String administrationID,
                                   String administrationEncriptedID, Locale locale, boolean isGet) {

        try {


            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");


            String webpage = getLlistatDeRegistresPage(absolutePluginRequestPath, relativePluginRequestPath,administrationID, getEntidad(), locale, isGet);

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


    public String getLlistatDeRegistresPage(String absolutePluginRequestPath, String relativePluginRequestPath,String administrationID, String entidad, Locale locale, boolean isGet) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        List<AsientoRegistralWs> registres;


        if (isDevelopment()) {
            registres = getRegistresDebug(administrationID, entidad);
        } else {
            registres = getRegistres(administrationID, entidad);
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
        map.put("web", getWeb());

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
                                 HttpServletRequest request, HttpServletResponse response, String administrationID,
                                 String administrationEncriptedID, Locale locale, boolean isGet) {

        try {


            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");


            String numeroRegistroFormateado = request.getParameter("numeroRegistroFormateado");


            String webpage = getDetallDeRegistrePage(absolutePluginRequestPath,numeroRegistroFormateado, locale);

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


    public String getDetallDeRegistrePage(String absolutePluginRequestPath, String numeroRegistroFormateado, Locale locale) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        AsientoRegistralWs registre;


        if (isDevelopment()) {
            registre = getDetallRegistreDebug(numeroRegistroFormateado);
        } else {
            registre = getDetallRegistre(numeroRegistroFormateado);
        }


        InputStream input = this.getClass().getResourceAsStream("/webpage/detall.html");

        String plantilla = IOUtils.toString(input, "UTF-8");


        // XYZ ZZZ
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCE);

        // XYZ ZZZ
        map.put("form_action", absolutePluginRequestPath + "/" + DETALL_REGISTRE_PAGE);
        map.put("lang", locale.getLanguage());
        map.put("detalletitle", getDetalleTitle(new Locale("ca")));


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

        String[] traduccions = {"registro.titulo.detalle", "registro.entrada", "registro.fecha", "registro.numero",
           "registro.oficina", "registro.destinatario", "registro.tipo.doc", "registro.extracto", "carpeta.idioma",
           "registro.justificante", "registre.justifcante.pendiente", "registro.interesados", "registro.interesado.nombre",
           "registro.interesado.documento", "registro.interesado.tipo", "registro.anexos", "registro.anexos.vacio",
           "registro.anexo.titulo"};


        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }


        map.put("registro", registre);
        map.put("web", getWeb());

        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

        return generat;

    }

    private AsientoRegistralWs getDetallRegistreDebug(String numeroRegistroFormateado) throws Exception {


        AsientoRegistralWs registro = findByNumeroRegistroFormateado(listRegistros, numeroRegistroFormateado);


        if (registro == null) {
            System.out.println(" REGISTRE NULL o EMPTY: " + registro);
        } else {
            System.out.println(" -------------  REGISTRE  -------------------");
            System.out.println("ar.getNumeroRegistroFormateado() => " + (registro.getNumeroRegistroFormateado()));
            System.out.println("ar.getResumen() => " + registro.getResumen());
            System.out.println("ar.getFechaRegistro(); => " + registro.getFechaRegistro());
            System.out.println("ar.getUnidadTramitacionDestinoDenominacion() => " + registro.getUnidadTramitacionDestinoDenominacion());
            System.out.println("ar.getEntidadRegistralOrigenDenominacion() => " + registro.getEntidadRegistralOrigenDenominacion());
            System.out.println("ar.getTipoDocumentacionFisicaCodigo() => " + registro.getTipoDocumentacionFisicaCodigo());
            System.out.println("ar.getIdioma() => " + registro.getIdioma());


        }

        return registro;
    }


    public AsientoRegistralWs getDetallRegistre(String numeroRegistroFormateado)
       throws Exception {

        AsientoRegistralWs registro = findByNumeroRegistroFormateado(listRegistros, numeroRegistroFormateado);
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

        if (regweb3Url.startsWith("https") && isDevelopment()) {
            XTrustProvider.install();
        }

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
        String resource = "/" + WEBRESOURCE + "/images"; //resource a on es troba l'icona
        try {

            //Agafa la icona del resource
            input = this.getClass().getResourceAsStream(resource+"/icon.jpg");
            if(input != null) {
                fileInfo =  new  FileInfo("icon.jpg","image/jpeg", IOUtils.toByteArray(input));
            }

        }  catch (Exception e) {
            log.error("Error llegint recurs : "+resource+"/icon.jpg" + e.getMessage(), e);

        }
        return fileInfo;

    }


}
