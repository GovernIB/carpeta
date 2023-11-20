package org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad;

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
import org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.model.CertificadoDatosDiscapacidad;
import org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.model.DatosEspecificos;
import org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.model.Estado;
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
public class PinbalDiscapacidadCarpetaFrontPlugin extends AbstractPinbalCarpetaFrontPlugin {

    public static final String PINBALDISCAPACIDAD_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "pinbaldiscapacidad.";

    /**
     *
     */
    public PinbalDiscapacidadCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public PinbalDiscapacidadCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public PinbalDiscapacidadCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafront.pinbaldiscapacidad";
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

        log.info("PinbalDiscapacidadCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("PinbalDiscapacidadCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("PinbalDiscapacidadCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
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
        return getImageFromResource(locale, "/logo/logo-pinbaldiscapacidad.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return PINBALDISCAPACIDAD_PROPERTY_BASE;
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

            String resource = "/webpage_pinbaldiscapacidad/index.html";

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

    protected static final String SERVEI_REST_SERVICE = "consultadiscapacidad";

    public void serveiRestService(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        DatosDiscapacidad datos = cridadaRest(userData, absolutePluginRequestPath);
        
        Gson json = new Gson();
        String generat = json.toJson(datos, DatosDiscapacidad.class);
        
        log.info("\nDADES JSON: " + generat + "\n");
        
        try {
            response.getWriter().println(generat);
            response.flushBuffer();
        } catch (IOException e) {
            log.error("Error obtening writer: " + e.getMessage(), e);
        }

    }

	public DatosDiscapacidad cridadaRest(UserData userData, String absolutePluginRequestPath) {
		
		DatosDiscapacidad datos = new DatosDiscapacidad();
        ScspRespuesta resposta;
        
        try {

            // Titular
            final String apellido1; 
            final String apellido2;
            final String documentacion;
            final String nombre;

            // ScspTitular.ScspTipoDocumentacion.DNI, ScspTitular.ScspTipoDocumentacion.NIE
            ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.DNI;
            
            // Dades del Titular del DNI
            {
                String nif = getProperty(PINBALDISCAPACIDAD_PROPERTY_BASE + "testnif");
                String surname = getProperty(PINBALDISCAPACIDAD_PROPERTY_BASE + "testsurname");

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
            
            String codigoComunidadAutonoma = getProperty(PINBALDISCAPACIDAD_PROPERTY_BASE + "codigocomunidadautonoma");
            String codigoProvincia = getProperty(PINBALDISCAPACIDAD_PROPERTY_BASE + "codigoprovincia");
            
            // XYZ ZZZ
            String consentimiento = "S";
            
            datos.setCodigoComunidadAutonoma(codigoComunidadAutonoma);
            datos.setCodigoProvincia(codigoProvincia);
            
            SolicitudDiscapacidad solicitud = new SolicitudDiscapacidad(codigoComunidadAutonoma, codigoProvincia, consentimiento);
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
                 
                Estado estado = dte.getRetorno().get(0).getEstado().get(0);
                
                // TRAMITADA
                if("0003".equals(resposta.getAtributos().getEstado().getCodigoEstado())){
                	
                    datos.setCodigo(estado.getCodigoEstado());
                    datos.setDescripcion(estado.getLiteralError());
                    
                    log.info("Datos discapacidad: " + dte.getRetorno().get(0).getCertificadoDatosDiscapacidad().size());
                    
                    if (dte.getRetorno().get(0).getCertificadoDatosDiscapacidad().size() > 0) {
                    	
                    	CertificadoDatosDiscapacidad certificado = dte.getRetorno().get(0).getCertificadoDatosDiscapacidad().get(0);
                    	
                    	datos.setExpediente(certificado.getExpediente());
                    	datos.setRespuestaDependencia(certificado.getRespuestaDependencia());
                    	datos.setRespuestaAcompananteTPublico(certificado.getRespuestaAcompananteTPublico());
                    	datos.setGradoDiscapacidad(certificado.getGradoDiscapacidad());
                    	datos.setFechaEfectos(certificado.getFechaEfectos());
                    	datos.setFechaRevision(certificado.getFechaRevision());
                    	datos.setValidezPermanente(certificado.getValidezPermanente());
                    	datos.setTiposDiscapacidad(certificado.getTiposDiscapacidad());
                    	
                    	// DEBUG
                    	
                    	log.info("codigoComunidadAutonoma => " + certificado.getCodigoComunidadAutonoma());
                    	log.info("codigoProvincia => " + certificado.getCodigoProvincia());
                    	log.info("expediente => " + certificado.getExpediente());
                    	log.info("respuestaDependencia => " + certificado.getRespuestaDependencia());
                    	log.info("respuestaAcompananteTPublico => " + certificado.getRespuestaAcompananteTPublico());
                    	log.info("gradoDiscapacidad => " + certificado.getGradoDiscapacidad());
                    	log.info("fechaEfectos => " + certificado.getFechaEfectos());
                    	log.info("fechaRevision => " + certificado.getFechaRevision());
                    	log.info("validezPermanente => " + certificado.getValidezPermanente());
                    	log.info("tiposDiscapacidad => " + certificado.getTiposDiscapacidad());
                    	log.info("Núm de RespuestaMovilidad: " + certificado.getRespuestaMovilidad().size());
                    	/*
                    	if (certificado.getRespuestaMovilidad().size() > 0) {
                    		ArrayList<RespuestaMovilidad> respuestaMovilidad = certificado.getRespuestaMovilidad();
                    		int x = 0;
                    		for (RespuestaMovilidad respuesta : respuestaMovilidad) {
                    			log.info("Respuesta[" + x + "][Factor] => " + respuesta.getFactor());
                    			log.info("Respuesta[" + x + "][Puntuacion] => " + respuesta.getPuntuacion());
                    			x++;
                    		}
                    	}*/
                    	
                    	
                    }else {
                    	// No disponible
                    	datos.setCodigo("999");
                    }
                	
                }
                else {
                	// No disponible
                	datos.setCodigo("999");                	
                }
                
            }else {
            	resposta = null;
            	datos.setError("Error servei. No hi ha resposta.");
            }
            

        } catch (Throwable e) {
        	resposta = null;
            String msg = "Error de Pinbal: " + e.getMessage();
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

    protected static final String REACT_JS_PAGE = "reactjs_main_pinbaldiscapacidad.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage_pinbaldiscapacidad/"+REACT_JS_PAGE;

            response.setCharacterEncoding("utf-8");
            
            log.info("Contingut de Resource: " + resource);
            
            InputStream input = this.getClass().getResourceAsStream(resource);
            
            log.info("Contingut de Input: " + input);
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


	public static class SolicitudDiscapacidad extends Solicitud {
		
		protected String codigoComunidadAutonoma;
		
		protected String codigoProvincia; 
		
		protected String consentimiento;
		
		// DD/MM/AAAA
		protected Date fecha = new Date();
		
			
		public SolicitudDiscapacidad(String codigoComunidadAutonoma, String codigoProvincia, String consentimiento) {
			super();
			this.codigoComunidadAutonoma = codigoComunidadAutonoma;
			this.codigoProvincia = codigoProvincia;
			this.consentimiento = consentimiento;
		}

		@Override
		public String getDatosEspecificos() { // xml
			StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			xmlBuilder.append("<DatosEspecificos>");
			xmlBuilder.append("<Consulta>");
			if (!isEmptyString(codigoComunidadAutonoma)) {
				xmlBuilder.append(xmlOptionalStringParameter(this.codigoComunidadAutonoma, "CodigoComunidadAutonoma"));
			}
			
			if (!isEmptyString(codigoProvincia)) {
				xmlBuilder.append(xmlOptionalStringParameter(this.codigoProvincia, "CodigoProvincia"));
			}
			
			xmlBuilder.append(xmlOptionalStringParameter(new SimpleDateFormat("dd/MM/yyyy").format(this.fecha),"FechaConsulta"));
			
			if (!isEmptyString(consentimiento)) {
				xmlBuilder.append(xmlOptionalStringParameter(this.consentimiento, "ConsentimientoTiposDiscapacidad"));
			}
			
			xmlBuilder.append("</Consulta>");
			xmlBuilder.append("</DatosEspecificos>");
			return xmlBuilder.toString();
		}
	}
    
    public class DatosDiscapacidad {

        protected String error = "";
        protected String codigo = "";
        protected String descripcion = "";
        protected String dni = "";
        protected String nombre = "";
        protected String apellido1 = "";
        protected String apellido2 = "";
        protected String fecha = "";
        
        protected String codigoComunidadAutonoma;
        protected String codigoProvincia;
        protected String expediente;
        protected String respuestaDependencia;
        protected String respuestaAcompananteTPublico;
        protected String gradoDiscapacidad;
        protected String fechaEfectos;
        protected String fechaRevision;
        protected String validezPermanente;
        protected String tiposDiscapacidad;

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
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

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getCodigoComunidadAutonoma() {
			return codigoComunidadAutonoma;
		}

		public void setCodigoComunidadAutonoma(String codigoComunidadAutonoma) {
			this.codigoComunidadAutonoma = codigoComunidadAutonoma;
		}

		public String getCodigoProvincia() {
			return codigoProvincia;
		}

		public void setCodigoProvincia(String codigoProvincia) {
			this.codigoProvincia = codigoProvincia;
		}

		public String getExpediente() {
			return expediente;
		}

		public void setExpediente(String expediente) {
			this.expediente = expediente;
		}

		public String getRespuestaDependencia() {
			return respuestaDependencia;
		}

		public void setRespuestaDependencia(String respuestaDependencia) {
			this.respuestaDependencia = respuestaDependencia;
		}

		public String getRespuestaAcompananteTPublico() {
			return respuestaAcompananteTPublico;
		}

		public void setRespuestaAcompananteTPublico(String respuestaAcompananteTPublico) {
			this.respuestaAcompananteTPublico = respuestaAcompananteTPublico;
		}

		public String getGradoDiscapacidad() {
			return gradoDiscapacidad;
		}

		public void setGradoDiscapacidad(String gradoDiscapacidad) {
			this.gradoDiscapacidad = gradoDiscapacidad;
		}

		public String getFechaEfectos() {
			return fechaEfectos;
		}

		public void setFechaEfectos(String fechaEfectos) {
			this.fechaEfectos = fechaEfectos;
		}

		public String getFechaRevision() {
			return fechaRevision;
		}

		public void setFechaRevision(String fechaRevision) {
			this.fechaRevision = fechaRevision;
		}

		public String getValidezPermanente() {
			return validezPermanente;
		}

		public void setValidezPermanente(String validezPermanente) {
			this.validezPermanente = validezPermanente;
		}

		public String getTiposDiscapacidad() {
			return tiposDiscapacidad;
		}

		public void setTiposDiscapacidad(String tiposDiscapacidad) {
			this.tiposDiscapacidad = tiposDiscapacidad;
		}

		public DatosDiscapacidad() {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date fecha = new Date();
			this.fecha = dateFormat.format(fecha);
		}

		public DatosDiscapacidad(String dni, String nombre) {
			super();
			this.dni = dni;
			this.nombre = nombre;
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date fecha = new Date();
			this.fecha = dateFormat.format(fecha);
		}
		
    }
    

}
