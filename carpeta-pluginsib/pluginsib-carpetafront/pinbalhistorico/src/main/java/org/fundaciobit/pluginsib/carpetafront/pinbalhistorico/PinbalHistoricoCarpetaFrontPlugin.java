package org.fundaciobit.pluginsib.carpetafront.pinbalhistorico;

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
import org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model.DatosEspecificos;
import org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model.Documentacion;
import org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model.Domicilio;
import org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model.Resultado;
import org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.resposta.DatosHistorico;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * @author jagarcia
 */
public class PinbalHistoricoCarpetaFrontPlugin extends AbstractPinbalCarpetaFrontPlugin {

    public static final String PINBALHISTORICO_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "pinbalhistorico.";

    /**
     *
     */
    public PinbalHistoricoCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public PinbalHistoricoCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public PinbalHistoricoCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafront.pinbalhistorico";
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

        log.info("PinbalHistoricoCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("PinbalHistoricoCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("PinbalHistoricoCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
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
            
        } else {

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
        return getImageFromResource(locale, "/logo/logo-pinbalhistorico.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return PINBALHISTORICO_PROPERTY_BASE;
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

            String resource = "/webpage_pinbalhistorico/index.html";

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
            
            String localidades = getProperty(PINBALHISTORICO_PROPERTY_BASE + "localidades");
            
            map.put("localitats", "");
            if (localidades != null) {
            	map.put("localitats", Base64.getEncoder().encodeToString(localidades.getBytes()));
            }

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

    protected static final String SERVEI_REST_SERVICE = "dadeshistoric";

    public void serveiRestService(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        String codMunicipio = request.getParameter("municipio");
               
        DatosHistorico datosHistorico = cridadaRest(userData, codMunicipio, absolutePluginRequestPath);
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String generat = gson.toJson(datosHistorico);
        
        log.info("\nDADES HISTORIC JSON: " + generat + "\n");
        
        try {
            response.getWriter().println(generat);
            response.flushBuffer();
        } catch (IOException e) {
            log.error("Error obtening writer: " + e.getMessage(), e);
        }

    }

	public DatosHistorico cridadaRest(UserData userData, String codMunicipio, String absolutePluginRequestPath) {
		
		DatosHistorico datosHistorico = new DatosHistorico();
        ScspRespuesta resposta;
        
        try {

            // Titular
            final String apellido1; 
            final String apellido2;
            final String documentacion;
            final String nombre;
            String tipoDoc = "";

            // ScspTitular.ScspTipoDocumentacion.DNI, ScspTitular.ScspTipoDocumentacion.NIE
            ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.DNI;
            tipoDoc = "DNI";
            
            // Dades del Titular del DNI
            {
                String nif = getProperty(PINBALHISTORICO_PROPERTY_BASE + "testnif");
                String surname = getProperty(PINBALHISTORICO_PROPERTY_BASE + "testsurname");

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
                		tipoDoc = "NIE";
                	}else {
                		tipoDocumentacion = ScspTitular.ScspTipoDocumentacion.NIF;
                		tipoDoc = "NIF";
                	}                	
                }
            }
            
            ScspTitular titular = new ScspTitular();
            titular.setTipoDocumentacion(tipoDocumentacion);
            titular.setDocumentacion(documentacion);
            titular.setNombre(nombre);
            titular.setApellido1(apellido1);
            titular.setApellido2(apellido2);
            
            datosHistorico.setDni(documentacion);
            datosHistorico.setNombre(nombre);
            datosHistorico.setApellido1(apellido1);
            datosHistorico.setApellido2(apellido2);
            
            // Mateix Titular
            final ScspFuncionario funcionario = new ScspFuncionario();
            funcionario.setNifFuncionario(documentacion);
            funcionario.setNombreCompletoFuncionario(nombre + " " + apellido1);
            
            String codigoProvincia = getProperty(PINBALHISTORICO_PROPERTY_BASE + "codigoprovincia");
            
            SolicitudHistorico solicitud = new SolicitudHistorico(codigoProvincia, codMunicipio, tipoDoc, documentacion);
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
                
                String codigoEstado = dte.getEstado().get(0).getCodigoEstado();
                if(codigoEstado.startsWith("00")) {
                	// TRAMITADA
                	
                	datosHistorico.setCodigo(codigoEstado);
                	datosHistorico.setDescripcion(dte.getEstado().get(0).getLiteralError());
                	
                	Resultado elemento = dte.getResultado().get(0);
                	
                	Documentacion doc = elemento.getDocumentacion().get(0);
                	datosHistorico.setTipoDocumentacion(doc.getTipo());
                	datosHistorico.setDocumentacion(doc.getValor());
                	datosHistorico.setNumSoporte(doc.getNumSoporte());
                	datosHistorico.setFechaNacimiento(elemento.getFechaNacimiento());
                	
                	ArrayList<Domicilio> domicilios = new ArrayList<Domicilio>();
                	for (Domicilio domicili : elemento.getHistoricoDomicilios()) {
                		domicilios.add(domicili);
                	}
                 	
                	datosHistorico.setHistorico(domicilios);
                	datosHistorico.setFechaExpedicion(elemento.getFechaExpedicion());
                	
                }else {
                	// 0233 - Titular no identificat
                	// 0232 - Més d'un registre per a les dades aportades.
                	datosHistorico.setError(dte.getEstado().get(0).getLiteralError());
                }
            	
            }else {
            	resposta = null;
            	datosHistorico.setError("Error servei. No hi ha resposta.");
            }

        } catch (Throwable e) {
        	resposta = null;
            String msg = "Error de Pinbal: " + e.getMessage();
            datosHistorico.setError(msg);
            log.error(msg, e);
        }
        
        
        /* 
    	 * datos de test
    	 * 
    	 */
        /*
        datosHistorico.setError("");
        datosHistorico.setDni("12345678A");
        datosHistorico.setNombre("JUAN ANTONIO");
        datosHistorico.setApellido1("GARCIA");
        datosHistorico.setApellido2("");
    	datosHistorico.setCodigo("0003");
    	datosHistorico.setDescripcion("Tramitada");
    	datosHistorico.setFechaNacimiento("1972-08-27");
    	datosHistorico.setFechaExpedicion("2020-01-26");
    	
    	datosHistorico.setTipoDocumentacion("DNI");
    	datosHistorico.setDocumentacion("12345678A");
    	datosHistorico.setNumSoporte("10");
    	
    	Domicilio domicilio1 = new Domicilio();
    	domicilio1.setClaveHojaPadronal(new ArrayList<>(Arrays.asList(new ClaveHojaPadronal("distrito", "seccion", "hoja"))));
    	domicilio1.setProvinciaRespuesta(new ArrayList<>(Arrays.asList(new ProvinciaRespuesta("07", "Baleares"))));
    	domicilio1.setMunicipioRespuesta(new ArrayList<>(Arrays.asList(new MunicipioRespuesta("40", "Palma"))));
       	domicilio1.setEntColectiva(new ArrayList<>(Arrays.asList(new EntColectiva("41", "EntColectiva"))));
    	domicilio1.setEntSingular(new ArrayList<>(Arrays.asList(new EntSingular("42", "EntSingular"))));
    	domicilio1.setNucleo(new ArrayList<>(Arrays.asList(new Nucleo("43", "Nucleo"))));
    	domicilio1.setCodUnidadPoblacional("codUnidad");
    	domicilio1.setDesde("01/01/1999");
    	domicilio1.setHasta("30/12/2005");
    	domicilio1.setMotivoInscripcion(new ArrayList<>(Arrays.asList(new MotivoInscripcion("cod","mot","causa"))));
    	domicilio1.setMotivoBaja(new ArrayList<>(Arrays.asList(new MotivoBaja("1","2","3"))));
    	
    	Direccion direccion = new Direccion();
    	direccion.setVia(new ArrayList<>(Arrays.asList(new Via("cod", "tipo", "via"))));
    	direccion.setKmt("");
    	direccion.setBloque("");
    	direccion.setPortal("B");
    	direccion.setEscalera("");
    	direccion.setPlanta("6");
    	direccion.setPuerta("A");
    	direccion.setCodigoPostal("07003");
    	domicilio1.setDireccion(new ArrayList<>(Arrays.asList(direccion)));
    	datosHistorico.setHistorico(new ArrayList<>(Arrays.asList(domicilio1, domicilio1)));
    	*/
        return datosHistorico;
	}

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "pinbalhistorico_reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage_pinbalhistorico/pinbalhistorico_reactjs_main.js";

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


	public static class SolicitudHistorico extends Solicitud {
		
		protected  String provinciaSolicitud;
		protected  String municipioSolicitud;
		protected  String tipoDocumentacion;
		protected  String numeroDocumento;
		//protected  String numeroAnyos; 
		
		public SolicitudHistorico(String provinciaSolicitud, String municipioSolicitud, String tipoDocumentacion, String numeroDocumento) {
			super();
			this.provinciaSolicitud = provinciaSolicitud;
			this.municipioSolicitud = municipioSolicitud;
			this.tipoDocumentacion  = tipoDocumentacion;
			this.numeroDocumento    = numeroDocumento;
			//this.numeroAnyos        = numeroAnyos;
		}

		@Override
		public String getDatosEspecificos() { // xml
			StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			xmlBuilder.append("<DatosEspecificos>");
			xmlBuilder.append("<Solicitud>");
			
			if (!isEmptyString(municipioSolicitud)) {
				xmlBuilder.append(xmlOptionalStringParameter(this.municipioSolicitud, "MunicipioSolicitud"));
			}
			
			if (!isEmptyString(provinciaSolicitud)) {
				xmlBuilder.append(xmlOptionalStringParameter(this.provinciaSolicitud, "ProvinciaSolicitud"));
			}
			
			if (!isEmptyString(numeroDocumento)) {
				xmlBuilder.append("<Titular>");
					xmlBuilder.append("<Documentacion>");
						xmlBuilder.append(xmlOptionalStringParameter(this.tipoDocumentacion, "Tipo"));
						xmlBuilder.append(xmlOptionalStringParameter(this.numeroDocumento, "Valor"));
						xmlBuilder.append("</Documentacion>");
				xmlBuilder.append("</Titular>");
			}
			
			/*if (!isEmptyString(numeroAnyos)) {
				xmlBuilder.append(xmlOptionalStringParameter(this.numeroAnyos, "NumeroAnyos"));
			}*/
			
			xmlBuilder.append("</Solicitud>");
			xmlBuilder.append("</DatosEspecificos>");
						
			return xmlBuilder.toString();
		}
	}
		
}
