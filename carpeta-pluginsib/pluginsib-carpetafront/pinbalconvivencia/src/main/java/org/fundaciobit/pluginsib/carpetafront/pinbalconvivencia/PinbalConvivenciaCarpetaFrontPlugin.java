package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia;

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
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model.ClaveHojaPadronal;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model.DatosEspecificos;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model.Documentacion;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model.Domicilio;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model.MotivoInscripcion;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model.Resultado;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta.DatosConvivencia;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta.Persona;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta.PeriodoInscripcion;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta.PeriodoInscripcionAdapter;
import org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta.PersonaAdapter;
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
public class PinbalConvivenciaCarpetaFrontPlugin extends AbstractPinbalCarpetaFrontPlugin {

    public static final String PINBALCONVIVENCIA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "pinbalconvivencia.";

    /**
     *
     */
    public PinbalConvivenciaCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public PinbalConvivenciaCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public PinbalConvivenciaCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafront.pinbalconvivencia";
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

        log.info("PinbalConvivenciaCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("PinbalConvivenciaCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("PinbalConvivenciaCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
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
        return getImageFromResource(locale, "/logo/logo-pinbalconvivencia.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return PINBALCONVIVENCIA_PROPERTY_BASE;
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

            String resource = "/webpage_pinbalconvivencia/index.html";

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
            
            String localidades = getProperty(PINBALCONVIVENCIA_PROPERTY_BASE + "localidades");
            
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

    protected static final String SERVEI_REST_SERVICE = "dadesconvivencia";

    public void serveiRestService(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        String codMunicipio = request.getParameter("municipio");
               
        DatosConvivencia datosConvivencia = cridadaRest(userData, codMunicipio, absolutePluginRequestPath);
        
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Persona.class, new PersonaAdapter().nullSafe());
        builder.registerTypeAdapter(PeriodoInscripcion.class, new PeriodoInscripcionAdapter().nullSafe());
        Gson gson = builder.create();
        String generat = gson.toJson(datosConvivencia);
        
        log.info("\nDADES CONVIVENCIA JSON: " + generat + "\n");
        
        try {
            response.getWriter().println(generat);
            response.flushBuffer();
        } catch (IOException e) {
            log.error("Error obtening writer: " + e.getMessage(), e);
        }

    }

	public DatosConvivencia cridadaRest(UserData userData, String codMunicipio, String absolutePluginRequestPath) {
		
		DatosConvivencia datosConvivencia = new DatosConvivencia();
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
                String nif = getProperty(PINBALCONVIVENCIA_PROPERTY_BASE + "testnif");
                String surname = getProperty(PINBALCONVIVENCIA_PROPERTY_BASE + "testsurname");

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
            
            datosConvivencia.setDni(documentacion);
            datosConvivencia.setNombre(nombre);
            datosConvivencia.setApellido1(apellido1);
            datosConvivencia.setApellido2(apellido2);
            
            // Mateix Titular
            final ScspFuncionario funcionario = new ScspFuncionario();
            funcionario.setNifFuncionario(documentacion);
            funcionario.setNombreCompletoFuncionario(nombre + " " + apellido1);
            
            String codigoProvincia = getProperty(PINBALCONVIVENCIA_PROPERTY_BASE + "codigoprovincia");
            
            SolicitudConvivencia solicitud = new SolicitudConvivencia(codigoProvincia, codMunicipio, tipoDoc, documentacion);
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
                	
                	datosConvivencia.setCodigo(codigoEstado);
                	datosConvivencia.setDescripcion(dte.getEstado().get(0).getLiteralError());
                	
                	Resultado elemento = dte.getResultado().get(0);
                	ClaveHojaPadronal hojaPadronal = elemento.getClaveHojaPadronal().get(0); 
                	
                	datosConvivencia.setDistrito(hojaPadronal.getDistrito());
                	datosConvivencia.setSeccion(hojaPadronal.getSeccion());
                	datosConvivencia.setHoja(hojaPadronal.getHoja());
                	
                	Domicilio dm = elemento.getDomicilio().get(0);
               
                	datosConvivencia.setVia(dm.getVia().get(0).getNombre());
                	datosConvivencia.setTipoVia(dm.getVia().get(0).getTipo());
                	datosConvivencia.setNumero(dm.getNumero().get(0).getValor());
                	datosConvivencia.setKmt(dm.getKmt());
                	datosConvivencia.setBloque(dm.getBloque());
                	datosConvivencia.setPortal(dm.getPortal());
                	datosConvivencia.setEscalera(dm.getEscalera());
                	datosConvivencia.setPlanta(dm.getPlanta());
                	datosConvivencia.setPuerta(dm.getPuerta());
                	datosConvivencia.setCodPostal(dm.getCodigoPostal());
               
                	int numPersonas = elemento.getPersonas().size();
                	ArrayList<Persona> personas = new ArrayList<Persona>();
                	
                	for (int i = 0; i < numPersonas; i++) {
                		
                		org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model.Persona item = elemento.getPersonas().get(i);
                		
                		ArrayList<PeriodoInscripcion> periodos = new ArrayList<PeriodoInscripcion>();
                		int numPeriodosInscripcion = item.getPeriodoInscripcion().size();
                		for (int j = 0; j < numPeriodosInscripcion; j++) {
            				org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model.PeriodoInscripcion periodoInscripcionItem = item.getPeriodoInscripcion().get(j);
            				MotivoInscripcion motivo = periodoInscripcionItem.getMotivoInscripcion().get(0);
            				periodos.add(new PeriodoInscripcion( periodoInscripcionItem.getDesde(), motivo.getCodigoVariacion(), motivo.getCausaVariacion(), motivo.getDescripcion()));
            			}
                		
                		Documentacion d = item.getDocumentacion().get(0);
                		
                		Persona per = new Persona(item.getNombre(), item.getApellido1(), item.getApellido2(), 
                				item.getFechaNacimiento().toString(), d.getTipo(), d.getValor(), item.getNia(), periodos);
                		
                		personas.add(per);
                	}
                	
                	datosConvivencia.setPersonas(personas);
                	datosConvivencia.setFechaExpedicion(elemento.getFechaExpedicion());
                	
                }else {
                	// 0233 - Titular no identificat
                	// 0232 - Més d'un registre per a les dades aportades.
                	datosConvivencia.setError(dte.getEstado().get(0).getLiteralError());
                }
            	
            }else {
            	resposta = null;
            	datosConvivencia.setError("Error servei. No hi ha resposta.");
            }

        } catch (Throwable e) {
        	resposta = null;
            String msg = "Error consulta: " + e.getMessage();
            datosConvivencia.setError(msg);
            log.error(msg, e);
        }
        
        
        /* 
    	 * datos de test
    	 * 
    	 */
        /*
        datosConvivencia.setDni("12345678A");
        datosConvivencia.setNombre("JUAN ANTONIO");
        datosConvivencia.setApellido1("GARCIA");
        datosConvivencia.setApellido2("");
    	datosConvivencia.setCodigo("0003");
    	datosConvivencia.setDescripcion("Tramitada");
    	datosConvivencia.setDistrito("Distrito Palma");
    	datosConvivencia.setSeccion("Seccion 1");
    	datosConvivencia.setHoja("Hoja 1");
    	datosConvivencia.setVia("DIRECCION");
    	datosConvivencia.setTipoVia("PG");
    	datosConvivencia.setNumero("4");
    	datosConvivencia.setKmt("");
    	datosConvivencia.setBloque("");
    	datosConvivencia.setPortal("B");
    	datosConvivencia.setEscalera("");
    	datosConvivencia.setPlanta("6");
    	datosConvivencia.setPuerta("A");
    	datosConvivencia.setCodPostal("07003");
    	datosConvivencia.setError("");
    	
    	ArrayList<Persona> personas = new ArrayList<Persona>();
    	
    	org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta.Persona per1 = new Persona();
    	per1.setNombre("Miguel");
    	per1.setApellido1("Apellido1");
    	per1.setApellido2("Apellido2");
    	per1.setFechaNacimiento("1972-08-27");
    	per1.setTipoDocumentacion("NIF");
    	per1.setDocumentacion("01234568J");
    	per1.setNia("00045271F");
    	ArrayList<PeriodoInscripcion> periodosInscripcion1 = new ArrayList<PeriodoInscripcion>();
    	periodosInscripcion1.add(new PeriodoInscripcion("2015-12-17", "M", "CD", "Cambio Domicilio"));
    	periodosInscripcion1.add(new PeriodoInscripcion("2016-12-15", "M", "CD", "Cambio Domicilio"));
    	periodosInscripcion1.add(new PeriodoInscripcion("2020-06-08", "M", "CD", "Cambio Domicilio"));
    	per1.setPeriodosInscripcion(periodosInscripcion1);
    	personas.add(per1);
    	
    	Persona per2 = new Persona();
    	per2.setNombre("Rosa");
    	per2.setApellido1("Apellido1");
    	per2.setApellido2("Apellido2");
    	per2.setFechaNacimiento("1975-04-05");
    	per2.setTipoDocumentacion("NIF");
    	per2.setDocumentacion("43000111J");
    	per2.setNia("00045272P");
    	ArrayList<PeriodoInscripcion> periodosInscripcion2 = new ArrayList<PeriodoInscripcion>();
    	periodosInscripcion2.add(new PeriodoInscripcion("2015-12-17", "M", "CD", "Cambio Domicilio"));
    	periodosInscripcion2.add(new PeriodoInscripcion("2018-02-12", "M", "CD", "Cambio Domicilio"));
    	periodosInscripcion2.add(new PeriodoInscripcion("2019-09-06", "M", "CD", "Cambio Domicilio"));
    	periodosInscripcion2.add(new PeriodoInscripcion("2021-10-25", "M", "CD", "Cambio Domicilio"));
    	per2.setPeriodosInscripcion(periodosInscripcion2);
    	personas.add(per2);
    	
    	datosConvivencia.setPersonas(personas);
        */
        return datosConvivencia;
	}

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "pinbalconvivencia_reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage_pinbalconvivencia/pinbalconvivencia_reactjs_main.js";

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


	public static class SolicitudConvivencia extends Solicitud {
		
		protected  String provinciaSolicitud;
		protected  String municipioSolicitud;
		protected  String tipoDocumentacion;
		protected  String numeroDocumento;
		
		public SolicitudConvivencia(String provinciaSolicitud, String municipioSolicitud, String tipoDocumentacion, String numeroDocumento) {
			super();
			this.provinciaSolicitud = provinciaSolicitud;
			this.municipioSolicitud = municipioSolicitud;
			this.tipoDocumentacion  = tipoDocumentacion;
			this.numeroDocumento    = numeroDocumento;
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
			
			xmlBuilder.append("</Solicitud>");
			xmlBuilder.append("</DatosEspecificos>");
						
			return xmlBuilder.toString();
		}
	}
		
}
