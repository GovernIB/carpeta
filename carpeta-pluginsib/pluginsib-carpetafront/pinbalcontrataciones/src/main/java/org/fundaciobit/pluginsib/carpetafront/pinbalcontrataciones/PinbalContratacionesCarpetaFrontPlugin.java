package org.fundaciobit.pluginsib.carpetafront.pinbalcontrataciones;

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
import org.fundaciobit.pluginsib.carpetafront.pinbalcontrataciones.model.DatosEspecificos;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * @author jagarcia
 */
public class PinbalContratacionesCarpetaFrontPlugin extends AbstractPinbalCarpetaFrontPlugin {

    public static final String PINBALCONTRATACIONES_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "pinbalcontrataciones.";

    /**
     *
     */
    public PinbalContratacionesCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public PinbalContratacionesCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public PinbalContratacionesCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafront.pinbalcontrataciones";
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

        log.info("PinbalContratacionesCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("PinbalContratacionesCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("PinbalContratacionesCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                + administrationEncriptedID);

        if (query.startsWith(INDEX_HTML_PAGE)) {

            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {

            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);
            
        } else if (query.startsWith(SERVEI_REST_SERVICE)) {

            serveiRestService(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);
            
        }  else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet, logCarpeta);
        }

    }

    /**
     * Mètode que retorna la icona del plugin
     * 
     * @param locale
     * @return
     */
    @Override
    public FileInfo getResourceIcon(Locale locale) {
        return getImageFromResource(locale, "/logo/logo-pinbalcontrataciones.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return PINBALCONTRATACIONES_PROPERTY_BASE;
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

            String resource = "/webpage_pinbalcontrataciones/index.html";

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

            String pathtoservei = absolutePluginRequestPath + "/" + SERVEI_REST_SERVICE;

            map.put("pathtoservei", pathtoservei);

            map.put("usuariNom", userData.getName());
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
    // ------------------- SERVEI PAGE (Cridades a PInbal)
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String SERVEI_REST_SERVICE = "corrientepagocontrataciones";

    public void serveiRestService(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        DatosContrataciones datos = cridadaRest(userData, absolutePluginRequestPath);
        
        Gson json = new Gson();
        String generat = json.toJson(datos, DatosContrataciones.class);
        
        // log.info("\nDADES JSON: " + generat + "\n");
        
        try {
            response.getWriter().println(generat);
            response.flushBuffer();
        } catch (IOException e) {
            log.error("Error obtening writer: " + e.getMessage(), e);
        }

    }

	public DatosContrataciones cridadaRest(UserData userData, String absolutePluginRequestPath) {
		
		DatosContrataciones datos = new DatosContrataciones();
        ScspRespuesta resposta;
        
        try {

            // Titular
            final String apellido1; 
            final String apellido2;
            final String documentacion;
            final String nombre;

            // TODO ScspTitular.ScspTipoDocumentacion.DNI, ScspTitular.ScspTipoDocumentacion.NIE
            ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.DNI;
            
            // Dades del Titular del DNI
            {
                String nif = getProperty(PINBALCONTRATACIONES_PROPERTY_BASE + "testnif");
                String surname = getProperty(PINBALCONTRATACIONES_PROPERTY_BASE + "testsurname");

                if (nif == null || surname == null) {
                	documentacion = userData.getAdministrationID().toUpperCase();
                	apellido1 = userData.getSurname1() == null? "" : userData.getSurname1().toUpperCase(); 
                	apellido2 = userData.getSurname2() == null? "" : userData.getSurname2().toUpperCase(); 
                	nombre = userData.getName() == null? "" : userData.getName().toUpperCase();
                }else {
                	/* DADES TEST */
                	documentacion = nif.toUpperCase();
                	apellido1 = surname.toUpperCase();
                	apellido2 = "";
                	nombre = "";
                }
                
                // Per defecte, si comença per digit és DNI. 
                // Si comença per [X,Y,Z] és un NIE
                // Sino es un NIF d'empresa 
                if (!Character.isDigit(documentacion.charAt(0))) {
                	if (Character.toUpperCase(documentacion.charAt(0)) == 'X' || 
                			Character.toUpperCase(documentacion.charAt(0)) == 'Y' || 
                			Character.toUpperCase(documentacion.charAt(0)) == 'Z') {
                		tipoDocumentacion = ScspTitular.ScspTipoDocumentacion.NIE;
                	}else {
                		tipoDocumentacion = ScspTitular.ScspTipoDocumentacion.NIF;
                	}                	
                }
            }
            
            ScspTitular titular = new ScspTitular();
            titular.setTipoDocumentacion(tipoDocumentacion);
            titular.setDocumentacion(documentacion);
            titular.setNombre(nombre);
            titular.setApellido1(apellido1);
            titular.setApellido2(apellido2);
            
            datos.setDni(documentacion);
            datos.setNombre(nombre);
            datos.setApellido1(apellido1);
            datos.setApellido2(apellido2);
            
            // Mateix Titular
            final ScspFuncionario funcionario = new ScspFuncionario();
            funcionario.setNifFuncionario(documentacion);
            funcionario.setNombreCompletoFuncionario(nombre + " " + apellido1);
            
            String codigoProvincia = getProperty(PINBALCONTRATACIONES_PROPERTY_BASE + "codigoprovincia");
            String codigoComunidadAutonoma = getProperty(PINBALCONTRATACIONES_PROPERTY_BASE + "codigocomunidadautonoma");
            
            SolicitudContrataciones solicitud = new SolicitudContrataciones(codigoProvincia, codigoComunidadAutonoma);
            omplirDadesSolicitutComunes(solicitud, funcionario, titular);
          
        	/*
        	 * Petició a PINBAL i processament de la resposta XML
        	 */
        	
            resposta = getConnexio(List.of(solicitud)); 
            
            if (resposta != null) {
            	
            	String datosEspecificos = resposta.getTransmisiones().get(0).getDatosEspecificos();
                
                JAXBContext contexto = JAXBContext.newInstance(DatosEspecificos.class);
                
                Unmarshaller datosEspecificosItem = contexto.createUnmarshaller();
                
                DatosEspecificos dte = (DatosEspecificos) datosEspecificosItem.unmarshal(new StringReader(datosEspecificos));
                 
                datos.setCodigo(dte.getRetorno().get(0).getEstado().get(0).getCodigoEstado());
                
                datos.setDescripcion(dte.getRetorno().get(0).getEstado().get(0).getLiteralError());
                
                
            }else {
            	resposta = null;
            	datos.setError("Error servei. No hi ha resposta.");
            }
            
            /* Datos de test para montar la parte de React */
            
            /*
            datos.setCodigo("1");
            datos.setNombre("Nombre");
            datos.setApellido2("Apellido2");
        	datos.setDescripcion("Test descripcion");
        	*/

        } catch (Throwable e) {
        	resposta = null;
            String msg = "Error consulta: " + e.getMessage();
            datos.setError(msg);
            log.error(msg, e);
        }

        return datos;
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

            String resource = "/webpage_pinbalcontrataciones/reactjs_main.js";

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


	public static class SolicitudContrataciones extends Solicitud {
		
		protected  String codigoProvincia;
		protected  String codigoComunidadAutonoma;
		
		public SolicitudContrataciones(String codigoProvincia, String codigoComunidadAutonoma) {
			super();
			this.codigoProvincia = codigoProvincia;
			this.codigoComunidadAutonoma = codigoComunidadAutonoma;
		}

		@Override
		public String getDatosEspecificos() { // xml
			StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			xmlBuilder.append("<DatosEspecificos>");
			xmlBuilder.append("<Consulta>");
			
			if (!isEmptyString(codigoComunidadAutonoma)) {
				xmlBuilder.append(
						xmlOptionalStringParameter(this.codigoComunidadAutonoma, "CodigoComunidadAutonoma")
				);
			}
			
			if (!isEmptyString(codigoProvincia)) {
				xmlBuilder.append(
						xmlOptionalStringParameter(this.codigoProvincia, "CodigoProvincia")
				);
			}
				
			
				
			xmlBuilder.append("</Consulta>");
			xmlBuilder.append("</DatosEspecificos>");
			
			// System.out.println("DATOS ESPECIFICOS CONTRATACIONES: " + xmlBuilder.toString());
			
			return xmlBuilder.toString();
		}
	}
    
    public class DatosContrataciones {

        protected String error = "";       
        protected String dni = "";
        protected String nombre = "";
        protected String apellido1 = "";
        protected String apellido2 = "";
        protected String fecha = "";
        protected String codigo = "";
        protected String descripcion = "";

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

		public String getDni() {
			return dni;
		}

		public void setDni(String dni) {
			this.dni = dni;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public String getApellido1() {
			return apellido1;
		}

		public void setApellido1(String apellido1) {
			this.apellido1 = apellido1;
		}

		public String getApellido2() {
			return apellido2;
		}

		public void setApellido2(String apellido2) {
			this.apellido2 = apellido2;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		
		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public DatosContrataciones() {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date fecha = new Date();
			this.fecha = dateFormat.format(fecha);
		}

		public DatosContrataciones(String dni, String nombre) {
			super();
			this.dni = dni;
			this.nombre = nombre;
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date fecha = new Date();
			this.fecha = dateFormat.format(fecha);
		}
		
    }
    

}
