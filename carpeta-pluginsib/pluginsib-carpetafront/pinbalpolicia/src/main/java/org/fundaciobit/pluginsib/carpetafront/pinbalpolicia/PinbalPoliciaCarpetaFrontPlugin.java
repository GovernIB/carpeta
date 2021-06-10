package org.fundaciobit.pluginsib.carpetafront.pinbalpolicia;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

import es.caib.pinbal.client.recobriment.model.ScspSolicitante;
import es.caib.pinbal.client.recobriment.ClientBase;
import es.caib.pinbal.client.recobriment.ClientGeneric;
import es.caib.pinbal.client.recobriment.model.ScspConfirmacionPeticion;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.pinbal.client.recobriment.model.Solicitud;
import es.caib.pinbal.client.recobriment.model.SolicitudBase;
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
 */
public class PinbalPoliciaCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

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
    // ------------------- DOCUMENT IDENTITAT PAGE (Cridades a PInbal)
    // ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String DOCUMENT_IDENTITAT_REST_SERVICE = "documentidentitat";

    public void documentIdentitatRestService(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        DadesPolicia dadesPolicia = new DadesPolicia();

        try {

            if (userData.isBusiness() ) {
                // "No es pot consultar la informació de DNI d'una empresa.
                throw new Exception(getTraduccio("error.empresa", locale)); 
            }

            final String PINBAL_PROPERTY_BASE = PINBALPOLICIA_PROPERTY_BASE + "pinbal.";

            // Atributos 
            final String codigoCertificado = getPropertyRequired(PINBAL_PROPERTY_BASE + "codicertificat"); // "SVDDGPCIWS02";

            // Procedimiento
            final String codProcedimiento = getPropertyRequired(PINBAL_PROPERTY_BASE + "codiprocediment"); // "CODSVDR_GBA_20121107";

            // Solicitante
            final String finalidad = getPropertyRequired(PINBAL_PROPERTY_BASE + "finalitat"); // "Baremacions per el proces d'escolaritzacio";
            final String identificadorSolicitante = getPropertyRequired(PINBAL_PROPERTY_BASE + "identificadorsolicitant"); // "S0711001H";
            final String unidadTramitadora = getPropertyRequired(PINBAL_PROPERTY_BASE + "unitattramitadora"); // "Servei d'escolarització";

            // Titular
            final String apellido1; // = "JAUME";
            final String apellido2 = "";
            final String documentacion; // = "41107605G";
            final String nombre = "";
            
            // ScspTitular.ScspTipoDocumentacion.DNI, ScspTitular.ScspTipoDocumentacion.NIE
            final ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.DNI;

            // Dades del Titular del DNI
            {
                String nif = getProperty(PINBALPOLICIA_PROPERTY_BASE + "pinbal.testnif");
                String surname = getProperty(PINBALPOLICIA_PROPERTY_BASE + "pinbal.testsurname");

                if (nif == null || surname == null) {
                    nif = userData.getAdministrationID();
                    surname = userData.getSurname1();
                }
                
                // XYZ ZZZ veure com diferenciar un document de l'altre.

                documentacion = nif.toUpperCase();
                apellido1 = surname.toUpperCase();
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
            

            // Dades de connexio
            String baseurl = getProperty(PINBALPOLICIA_PROPERTY_BASE + "pinbal.baseurl");
            String username = getProperty(PINBALPOLICIA_PROPERTY_BASE + "pinbal.username");
            String password = getProperty(PINBALPOLICIA_PROPERTY_BASE + "pinbal.password");
            
            
            Solicitud solicitud = new SolicitudServeiWeb();
            solicitud.setIdentificadorSolicitante(identificadorSolicitante);
            solicitud.setUnidadTramitadora(unidadTramitadora);
            solicitud.setCodigoProcedimiento(codProcedimiento);
            solicitud.setFinalidad(finalidad);
            solicitud.setConsentimiento(ScspSolicitante.ScspConsentimiento.Si);
            solicitud.setFuncionario(funcionario);
            solicitud.setTitular(titular);
            
            ScspRespuesta resposta;
            
            try {
            	
            	ClientGeneric clientRest = new ClientGeneric(baseurl,username,password);

                resposta = clientRest.peticionSincrona(codigoCertificado, List.of(solicitud));
                
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

                    String error = "Error amb Estado " + dte.getRetorno().get(0).getEstado().get(0).getCodigoEstado() + " | Estado Secundario: "
                            + " | Literal Error: " + dte.getRetorno().get(0).getEstado().get(0).getLiteralError();
                           
                    dadesPolicia.setError(error);
                }
                
            } catch (Exception e) {
            	resposta = null;
                log.error("Error petición PinbalDadesPolicia: " + e.getMessage());
            } 

        } catch (Throwable e) {

            String msg = "Error consultant Dades d'Identitat a la Policia: " + e.getMessage();

            dadesPolicia.setError(msg);

            log.error(msg, e);
        }

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


	public static class SolicitudServeiWeb extends Solicitud {
		protected  String numeroSoporte;

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
