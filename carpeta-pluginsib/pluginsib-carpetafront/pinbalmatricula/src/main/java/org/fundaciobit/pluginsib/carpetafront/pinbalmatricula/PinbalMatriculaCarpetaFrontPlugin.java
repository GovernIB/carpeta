package org.fundaciobit.pluginsib.carpetafront.pinbalmatricula;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractPinbalCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.pinbal.client.recobriment.model.Solicitud;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.model.DatosAlumno;
import org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.model.DatosEspecificos;
import org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.model.IdTitular;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jpernia
 */
public class PinbalMatriculaCarpetaFrontPlugin extends AbstractPinbalCarpetaFrontPlugin {

    public static final String PINBALMATRICULA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "pinbalmatricula.";

    /**
     *
     */
    public PinbalMatriculaCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public PinbalMatriculaCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public PinbalMatriculaCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafront.pinbalmatricula";
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

        log.info("PinbalMatriculaCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("PinbalMatriculaCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("PinbalMatriculaCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
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
        return getImageFromResource(locale, "/logo/logo-pinbalmatricula.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return PINBALMATRICULA_PROPERTY_BASE;
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

            String resource = "/webpage_pinbalmatricula/index.html";

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
                log.error("Error obtenint writer: " + e.getMessage(), e);
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

    protected static final String SERVEI_REST_SERVICE = "matriculacentre";

    public void serveiRestService(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        DatosMatricula datosMatricula = cridadaRest(userData, absolutePluginRequestPath);

        // S'ha d'esborrar
//        DatosMatricula datosMatricula = new DatosMatricula();
//        datosMatricula.setCursoMatriculaFutura("2021/2022");
//        datosMatricula.setCursoMatriculaVigente("2020/2021");
//        datosMatricula.setFechaProceso("10/08/2021");
//        datosMatricula.alumno.setNombre("JOAN");
//        datosMatricula.alumno.setApellido1("REUS");
//        datosMatricula.alumno.setApellido2("COLL");
//        datosMatricula.alumno.setFechaNacimiento("10/10/2010");
//        IdTitular idTitular = new IdTitular();
//        idTitular.setTipoDocumentacion("NIF");
//        idTitular.setDocumentacion("11111111H");
//        datosMatricula.alumno.setIdTitular(idTitular);
        ///////


        Gson json = new Gson();
        String generat = json.toJson(datosMatricula, DatosMatricula.class);
        
        log.info("\nDADES MATRICULA CENTRE EDUCATIU JSON: " + generat + "\n");
        
        try {
            response.getWriter().println(generat);
            response.flushBuffer();
        } catch (IOException e) {
            log.error("Error obtening writer: " + e.getMessage(), e);
        }

    }

	public DatosMatricula cridadaRest(UserData userData, String absolutePluginRequestPath) {
		
		DatosMatricula datosMatricula = new DatosMatricula();
        ScspRespuesta resposta;
        
        try {

            // Titular
            final String apellido1; 
            final String apellido2 = "";
            final String documentacion;
            final String nombre = "";

            // ScspTitular.ScspTipoDocumentacion.DNI, ScspTitular.ScspTipoDocumentacion.NIE
            ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.NIF;
            
            // Dades del Titular del DNI
            {
                String nif = getProperty(PINBALMATRICULA_PROPERTY_BASE + "testnif");
                String surname = getProperty(PINBALMATRICULA_PROPERTY_BASE + "testsurname");

                if (nif == null || surname == null) {
                    nif = userData.getAdministrationID();
                    surname = userData.getSurname1();
                }

                documentacion = nif.toUpperCase();
                apellido1 = surname.toUpperCase();
                
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

            // Dades per la vista
//            datosMatricula.alumno.setNombre(resposta.);
//            datosMatricula.alumno.setApellido1(apellido1);
//            datosMatricula.alumno.setApellido2(apellido2);
//            datosMatricula.alumno.setApellido2(apellido2);

            // Mateix Titular
            final ScspFuncionario funcionario = new ScspFuncionario();
            funcionario.setNifFuncionario(documentacion);
            funcionario.setNombreCompletoFuncionario(nombre + " " + apellido1);
            
            String fechaNacimientoTitular = getProperty(PINBALMATRICULA_PROPERTY_BASE + "fechaNacimientoTitular");
            String tipoDocumentacionTutor = getProperty(PINBALMATRICULA_PROPERTY_BASE + "tipoDocumentacion");
            String documentacionTutor = getProperty(PINBALMATRICULA_PROPERTY_BASE + "documentacion");

            log.info("11111111111");

            SolicitudMatricula solicitud = new SolicitudMatricula(fechaNacimientoTitular, tipoDocumentacionTutor, documentacionTutor);
            omplirDadesSolicitutComunes(solicitud, funcionario, titular);
            log.info("2222222222");
        	/*
        	 * Petició a PINBAL i processament de la resposta XML
        	 */
            resposta = getConnexio(List.of(solicitud));
            log.info("333333333333333333333333333333333333333");
            
            if (resposta != null) {
                log.info("4444444");
            	
            	String datosEspecificos = resposta.getTransmisiones().get(0).getDatosEspecificos();
                
                JAXBContext contexto = JAXBContext.newInstance(DatosEspecificos.class);
                
                Unmarshaller datosEspecificosItem = contexto.createUnmarshaller();

                // Dades per la vista
                DatosEspecificos dte = (DatosEspecificos) datosEspecificosItem.unmarshal(new StringReader(datosEspecificos));

                datosMatricula.setCodRespuesta(dte.getRespuesta().get(0).getCodRespuesta());
                datosMatricula.setDescRespuesta(dte.getRespuesta().get(0).getDescRespuesta());
                datosMatricula.setFechaProceso(dte.getRespuesta().get(0).getFechaProceso());
                datosMatricula.setCursoMatriculaVigente(dte.getRespuesta().get(0).getCursoMatriculaVigente());
                datosMatricula.setCursoMatriculaFutura(dte.getRespuesta().get(0).getCursoMatriculaFutura());

                log.info("ALUMNE***********: " + dte.getRespuesta().get(0).getDatosAlumno().size());
                if(dte.getRespuesta().get(0).getDatosAlumno().size()>0) {
                    datosMatricula.alumno.setNombre(dte.getRespuesta().get(0).getDatosAlumno().get(0).getNombre());
                    datosMatricula.alumno.setApellido1(dte.getRespuesta().get(0).getDatosAlumno().get(0).getApellido1());
                    datosMatricula.alumno.setApellido2(dte.getRespuesta().get(0).getDatosAlumno().get(0).getApellido2());
                    datosMatricula.alumno.setFechaNacimiento(dte.getRespuesta().get(0).getDatosAlumno().get(0).getFechaNacimiento());
                    datosMatricula.alumno.setIdTitular(dte.getRespuesta().get(0).getDatosAlumno().get(0).getIdTitular());
                }
                
            }else {
                log.info("55555555555");
            	resposta = null;
            	datosMatricula.setError("Error servei. No hi ha resposta.");
            }

        } catch (Throwable e) {
        	resposta = null;
            String msg = "Error consulta: " + e.getMessage();
            datosMatricula.setError(msg);
            log.error(msg, e);
        }

        return datosMatricula;
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

            String resource = "/webpage_pinbalmatricula/reactjs_main.js";

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


	public static class SolicitudMatricula extends Solicitud {

        protected  String fechaNacimientoTitular;
		protected  String tipoDocumentacion;
		protected  String documentacion;

		public SolicitudMatricula(String fechaNacimientoTitular, String tipoDocumentacion, String documentacion) {
			super();
			this.fechaNacimientoTitular = fechaNacimientoTitular;
			this.tipoDocumentacion = tipoDocumentacion;
			this.documentacion = documentacion;
		}

		@Override
		public String getDatosEspecificos() { // xml
            StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			xmlBuilder.append("<DatosEspecificos>");
            xmlBuilder.append("<Solicitud>");

            if (!isEmptyString(String.valueOf(fechaNacimientoTitular))) {
                xmlBuilder.append(xmlOptionalStringParameter(this.fechaNacimientoTitular, "FechaNacimientoTitular"));
            }
            if (!isEmptyString(tipoDocumentacion) || !isEmptyString(documentacion)) {
				xmlBuilder.append("<IdTutor>");
                if (!isEmptyString(tipoDocumentacion)) {
                    xmlBuilder.append(
                            xmlOptionalStringParameter(this.tipoDocumentacion, "TipoDocumentacion")
                    );
                }
                if (!isEmptyString(documentacion)) {
                    xmlBuilder.append(
                            xmlOptionalStringParameter(this.documentacion, "Documentacion")
                    );
                }
                xmlBuilder.append("</IdTutor>");
			}
            xmlBuilder.append("</Solicitud>");
			xmlBuilder.append("</DatosEspecificos>");
			return xmlBuilder.toString();
		}
	}
    
    public class DatosMatricula {

        protected String error = "";       
        protected DatosAlumno alumno = new DatosAlumno();
        protected String codRespuesta = "";
        protected String descRespuesta = "";
        protected String cursoMatriculaVigente = "";
        protected String cursoMatriculaFutura = "";
        protected String fechaProceso = "";

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

		public DatosAlumno getAlumno() { return alumno; }

		public void setAlumno(DatosAlumno alumno) {
			this.alumno = alumno;
		}

		public String getCodRespuesta() {
			return codRespuesta;
		}

		public void setCodRespuesta(String codRespuesta) {
			this.codRespuesta = codRespuesta;
		}
		
		public String getDescRespuesta() {
			return descRespuesta;
		}

		public void setDescRespuesta(String descRespuesta) {
			this.descRespuesta = descRespuesta;
		}

		public String getCursoMatriculaVigente() {
			return cursoMatriculaVigente;
		}

		public void setCursoMatriculaVigente(String cursoMatriculaVigente) {
			this.cursoMatriculaVigente = cursoMatriculaVigente;
		}

		public String getCursoMatriculaFutura() {
			return cursoMatriculaFutura;
		}

		public void setCursoMatriculaFutura(String cursoMatriculaFutura) {
			this.cursoMatriculaFutura = cursoMatriculaFutura;
		}
		
		public String getFechaProceso() {
			return fechaProceso;
		}

		public void setFechaProceso(String fechaProceso) {
			this.fechaProceso = fechaProceso;
		}

		public DatosMatricula() {

		}

		public DatosMatricula(DatosAlumno alumno, String codRespuesta, String descRespuesta, String cursoMatriculaVigente, String cursoMatriculaFutura, String fechaProceso) {
			super();
			this.alumno = alumno;
			this.codRespuesta = codRespuesta;
			this.descRespuesta = descRespuesta;
			this.cursoMatriculaVigente = cursoMatriculaVigente;
			this.cursoMatriculaFutura = cursoMatriculaFutura;
			this.fechaProceso = fechaProceso;
		}
		
    }
    

}
