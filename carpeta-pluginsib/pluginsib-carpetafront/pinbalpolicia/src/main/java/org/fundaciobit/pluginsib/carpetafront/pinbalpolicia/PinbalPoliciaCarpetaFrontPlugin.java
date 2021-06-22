package org.fundaciobit.pluginsib.carpetafront.pinbalpolicia;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractPinbalCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.pinbal.client.recobriment.model.Solicitud;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.model.DatosEspecificos;
import org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.model.DatosTitular;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * @author anadal
 * @author jagarcia
 */
public class PinbalPoliciaCarpetaFrontPlugin extends AbstractPinbalCarpetaFrontPlugin {

    public static final String PINBALPOLICIA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "pinbalpolicia.";

    /**
     *
     */
    public PinbalPoliciaCarpetaFrontPlugin() {
        super(); 
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public PinbalPoliciaCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public PinbalPoliciaCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }
    

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontpinbalpolicia";
    }

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userData, String administrationIDEncriptat, 
            String parameter, IListenerLogCarpeta logCarpeta)
            throws Exception {

        registerUserData(userData);

        String startURL = absolutePluginRequestPath + "/" + INDEX_HTML_PAGE;

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        log.info("PinbalPoliciaCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("PinbalPoliciaCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("PinbalPoliciaCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                + administrationEncriptedID);

        if (query.startsWith(INDEX_HTML_PAGE)) {

            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {

            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);
            
        } else if (query.startsWith(DOCUMENT_IDENTITAT_REST_SERVICE)) {

            documentIdentitatRestService(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
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

    /**
     * Mètode que retorna la icona del plugin
     * 
     * @param locale
     * @return
     */
    @Override
    public FileInfo getResourceIcon(Locale locale) {
        return getImageFromResource(locale, "/logo/logo-pinbalpolicia.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return PINBALPOLICIA_PROPERTY_BASE;
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

            String resource = "/webpage_pinbalpolicia/index.html";

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

            log.info("absolutePluginRequestPath ==> " + absolutePluginRequestPath);

            String pathtojs = absolutePluginRequestPath + "/" + REACT_JS_PAGE;

            map.put("pathtojs", pathtojs);

            String pathtodocumentidentitat = absolutePluginRequestPath + "/" + DOCUMENT_IDENTITAT_REST_SERVICE;

            map.put("pathtodocumentidentitat", pathtodocumentidentitat);

            map.put("usuariNom", userData.getName());
            map.put("usuariLlinatge1", userData.getSurname1() == null? "" : userData.getSurname1());
            map.put("usuariLlinatge2", userData.getSurname2() == null? "" : userData.getSurname2());
            map.put("usuariDNI", userData.getAdministrationID());
            map.put("usuariMetode", userData.getAuthenticationMethod());

            String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            try {
                response.getWriter().println(generat);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            // XYZ ZZZ
            log.error("Error generant pàgina bàsica: " + e.getMessage(), e);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- DOCUMENT IDENTITAT PAGE (Cridades a Pinbal)
    // ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String DOCUMENT_IDENTITAT_REST_SERVICE = "documentidentitat";

    public void documentIdentitatRestService(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        DadesPolicia dadesPolicia = cridadaRest(userData, locale);

        Gson json = new Gson();
        String generat = json.toJson(dadesPolicia, DadesPolicia.class);
        
        log.info("\nDADES POLICIA JSON: " + generat + "\n");

        try {
            response.getWriter().println(generat);
            response.flushBuffer();
        } catch (IOException e) {
            log.error("Error obtening writer: " + e.getMessage(), e);
        }

    }

	public DadesPolicia cridadaRest(UserData userData, Locale locale) {
		DadesPolicia dadesPolicia = new DadesPolicia();

        try {

            if (userData.isBusiness() ) {
                // "No es pot consultar la informació de DNI d'una empresa.
                throw new Exception(getTraduccio("error.empresa", locale)); 
            }

            // Titular
            final String apellido1; // = "JAUME";
            final String apellido2 = "";
            final String documentacion; // = "41107605G";
            final String nombre = "";

            // ScspTitular.ScspTipoDocumentacion.DNI, ScspTitular.ScspTipoDocumentacion.NIE
            ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.DNI;
            
            // Dades del Titular del DNI
            {
                String nif = getProperty(PINBALPOLICIA_PROPERTY_BASE + "testnif");
                String surname = getProperty(PINBALPOLICIA_PROPERTY_BASE + "testsurname");

                if (nif == null || surname == null) {
                    nif = userData.getAdministrationID();
                    surname = userData.getSurname1();
                }

                documentacion = nif.toUpperCase();
                apellido1 = surname.toUpperCase();
                
                // Si no es un digit el primer caracter, es tracta d'un NIE 
                if (!Character.isDigit(documentacion.charAt(0))) {
                	tipoDocumentacion = ScspTitular.ScspTipoDocumentacion.NIE;
                }
            }
            
            ScspTitular titular = new ScspTitular();
            titular.setTipoDocumentacion(tipoDocumentacion);
            titular.setDocumentacion(documentacion);
            titular.setNombre(nombre);
            titular.setApellido1(apellido1);
            titular.setApellido2(apellido2);
            
            // Mateix Titular
            final ScspFuncionario funcionario = new ScspFuncionario();
            funcionario.setNifFuncionario(documentacion);
            funcionario.setNombreCompletoFuncionario(nombre + " " + apellido1);
            
            
            SolicitudPolicia solicitud = new SolicitudPolicia(null);
            omplirDadesSolicitutComunes(solicitud, funcionario, titular);
            
            ScspRespuesta resposta;
            	
        	/*
        	 * Petició a PINBAL i processament de la resposta XML
        	 */
        	
            resposta = getConnexio(List.of(solicitud)); 
            
            String datosEspecificos = resposta.getTransmisiones().get(0).getDatosEspecificos();
            
            JAXBContext contexto = JAXBContext.newInstance(DatosEspecificos.class);
            
            Unmarshaller datosEspecificosItem = contexto.createUnmarshaller();
            
            DatosEspecificos dte = (DatosEspecificos) datosEspecificosItem.unmarshal(new StringReader(datosEspecificos));
            
            if("00".equals(dte.getRetorno().get(0).getEstado().get(0).getCodigoEstado())) {
            	DatosTitular dt = dte.getRetorno().get(0).getDatosTitular().get(0);
            	SimpleDateFormat sdf_in = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf_out = new SimpleDateFormat("dd/MM/yyyy");
                
                dt.setFechaCaducidad(sdf_out.format(sdf_in.parse(dt.getFechaCaducidad())));
                
                dt.getDatosNacimiento().get(0).setFecha(sdf_out.format(sdf_in.parse(dt.getDatosNacimiento().get(0).getFecha())));
                
                dadesPolicia.setDatosTitular(dt);
            }else {

                String error = "Error amb Estado " + dte.getRetorno().get(0).getEstado().get(0).getCodigoEstado() 
                        + " | " + dte.getRetorno().get(0).getEstado().get(0).getLiteralError();
                       
                dadesPolicia.setError(error);
            }

        } catch (Throwable e) {

            String msg = "Error consultant Dades d'Identitat a la Policia: " + e.getMessage();

            dadesPolicia.setError(msg);

            log.error(msg, e);
        }
		return dadesPolicia;
	}

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage_pinbalpolicia/reactjs_main.js";

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

    /*
     * Solicitud datos especificos
     */
	public static class SolicitudPolicia extends Solicitud {
		protected  String numeroSoporte;

		public SolicitudPolicia(String numeroSoporte) {
			super();
			this.numeroSoporte = numeroSoporte;
		}

		@Override
		public String getDatosEspecificos() { // xml
			StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			xmlBuilder.append("<DatosEspecificos>");
			if (numeroSoporte != null && !numeroSoporte.isEmpty()) {
				xmlBuilder.append("<Consulta>");
				xmlBuilder.append(
						xmlOptionalStringParameter(this.numeroSoporte, "NumeroSoporte")
				);
				xmlBuilder.append("</Consulta>");
			}
			xmlBuilder.append("</DatosEspecificos>");
			return xmlBuilder.toString();
		}
	}
    
	
	/*
	 * Informació que passam a la vista i pintam al plugin
	 */
    public class DadesPolicia {

        protected String error = "";

        protected DatosTitular datosTitular = null;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public DatosTitular getDatosTitular() {
            return datosTitular;
        }

        public void setDatosTitular(DatosTitular datosTitular) {
            this.datosTitular = datosTitular;
        }

    }
    

}
