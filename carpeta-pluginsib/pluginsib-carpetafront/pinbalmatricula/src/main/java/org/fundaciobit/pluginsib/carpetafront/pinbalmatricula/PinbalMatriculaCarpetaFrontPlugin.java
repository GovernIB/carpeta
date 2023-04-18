package org.fundaciobit.pluginsib.carpetafront.pinbalmatricula;

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
import org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.model.DatosAlumno;
import org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.model.DatosEspecificos;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

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
            HttpServletRequest request, UserData userData, String administrationIDEncriptat, String parameter,
            IListenerLogCarpeta logCarpeta) throws Exception {

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

            serveiRestService(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

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

    public void serveiRestService(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        DatosMatricula datosMatricula = cridadaRest(userData, absolutePluginRequestPath, request);

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

    public DatosMatricula cridadaRest(UserData userData, String absolutePluginRequestPath, HttpServletRequest request) {

        DatosMatricula datosMatricula = new DatosMatricula();
        ScspRespuesta resposta;

        try {

            // Tutor
            final String apellido1Tutor;
            final String apellido2Tutor;
            final String documentacionTutor;
            final String nombreTutor;

            ScspTipoDocumentacion tipoDocumentacionTutor = ScspTipoDocumentacion.NIF;

            // Dades del Tutor
            {
                String nif = getProperty(PINBALMATRICULA_PROPERTY_BASE + "testnif");
                String surname = getProperty(PINBALMATRICULA_PROPERTY_BASE + "testsurname");

                if (nif == null || surname == null) {
                    documentacionTutor = userData.getAdministrationID().toUpperCase();
                    apellido1Tutor = userData.getSurname1() == null ? "" : userData.getSurname1().toUpperCase();
                    apellido2Tutor = userData.getSurname2() == null ? "" : userData.getSurname2().toUpperCase();
                    nombreTutor = userData.getName() == null ? "" : userData.getName().toUpperCase();
                } else {
                    /* DADES TEST */
                    documentacionTutor = nif.toUpperCase();
                    apellido1Tutor = surname.toUpperCase();
                    apellido2Tutor = "";
                    nombreTutor = "";
                }

                // Per defecte, si comença per digit és DNI. 
                // Si comença per [X,Y,Z] és un NIE
                // Sino es un NIF d'empresa 
                if (!Character.isDigit(documentacionTutor.charAt(0))) {
                    if (Character.toUpperCase(documentacionTutor.charAt(0)) == 'X'
                            || Character.toUpperCase(documentacionTutor.charAt(0)) == 'Y'
                            || Character.toUpperCase(documentacionTutor.charAt(0)) == 'Z') {
                        tipoDocumentacionTutor = ScspTitular.ScspTipoDocumentacion.NIE;
                    } else {
                        tipoDocumentacionTutor = ScspTitular.ScspTipoDocumentacion.NIF;
                    }
                }
            }

            // Dades del formulari del front
            String formDataNaixementTitular = request.getParameter("dataNaixementTitular");
            String formDocumentTitular = request.getParameter("documentTitular");
            String formTipusDocumentTitular = request.getParameter("tipusDocumentTitular");
            String formRadioOpcio = request.getParameter("radioSelectedOption");
            String formNomTitular = request.getParameter("nomTitular");
            String formPrimerLlinatgeTitular = request.getParameter("primerLlinatgeTitular");
            String formSegonLlinatgeTitular = request.getParameter("segonLlinatgeTitular");

            log.info("formDataNaixementStr: " + formDataNaixementTitular);
            log.info("formDocumentTitular: " + formDocumentTitular);
            log.info("formTipusDocumentTitular: " + formTipusDocumentTitular);
            log.info("formRadioOpcio: " + formRadioOpcio);
            log.info("formNomTitular: " + formNomTitular);
            log.info("formPrimerLlinatgeTitular: " + formPrimerLlinatgeTitular);
            log.info("formSegonLlinatgeTitular: " + formSegonLlinatgeTitular);

            String data;

            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
            SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy");

            if (formDataNaixementTitular != null && !formDataNaixementTitular.equals("")) {
                Date date = sdfInput.parse(formDataNaixementTitular);
                sdfOutput.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
                data = sdfOutput.format(new Date(date.getTime() + 86400000));
            } else {
                data = "";
            }

            log.info("tipus document: " + formTipusDocumentTitular);

            ScspTipoDocumentacion tipoDocumentacionTitular = null;
            String documentTitular = null;

            switch (formRadioOpcio) {

                case "option1":

                    tipoDocumentacionTitular = tipoDocumentacionTutor;
                    documentTitular = documentacionTutor;

                break;

                case "option2":

                    switch (formTipusDocumentTitular) {

                        case "1":
                            tipoDocumentacionTitular = ScspTipoDocumentacion.NIF;
                        break;

                        case "2":
                            tipoDocumentacionTitular = ScspTipoDocumentacion.NIE;
                        break;

                        case "3":
                            tipoDocumentacionTitular = ScspTipoDocumentacion.Pasaporte;
                        break;

                        case "4":
                            tipoDocumentacionTitular = ScspTipoDocumentacion.Otros;
                        break;
                    }
                    documentTitular = formDocumentTitular;
                break;

                case "option3":

                    tipoDocumentacionTitular = ScspTitular.ScspTipoDocumentacion.NIF;
                break;

            }

            // Titular
            ScspTitular titular = new ScspTitular();

            if (documentTitular == null) {
                titular.setTipoDocumentacion(null);
            } else {
                titular.setTipoDocumentacion(tipoDocumentacionTitular);
            }
            titular.setDocumentacion(documentTitular);
            titular.setNombre(formNomTitular);
            titular.setApellido1(formPrimerLlinatgeTitular);
            titular.setApellido2(formSegonLlinatgeTitular);

            // Mateix Titular
            final ScspFuncionario funcionario = new ScspFuncionario();
            funcionario.setNifFuncionario(documentacionTutor);
            funcionario.setNombreCompletoFuncionario(
                    nombreTutor + " " + apellido1Tutor + ((apellido2Tutor != null) ? (" " + apellido2Tutor) : ""));

            SolicitudMatricula solicitud = new SolicitudMatricula(data, tipoDocumentacionTitular.toString(),
                    titular.getDocumentacion(), titular.getNombre(), titular.getApellido1(), titular.getApellido2(),
                    tipoDocumentacionTutor.toString(), documentacionTutor);
            log.info("solicitud: " + solicitud.getDatosEspecificos());
            omplirDadesSolicitutComunes(solicitud, funcionario, titular);

            /*
             * Petició a PINBAL i processament de la resposta XML
             */
            resposta = getConnexio(List.of(solicitud));

            if (resposta != null) {

                String datosEspecificos = resposta.getTransmisiones().get(0).getDatosEspecificos();

                JAXBContext contexto = JAXBContext.newInstance(DatosEspecificos.class);

                Unmarshaller datosEspecificosItem = contexto.createUnmarshaller();

                // Dades per la vista
                DatosEspecificos dte = (DatosEspecificos) datosEspecificosItem
                        .unmarshal(new StringReader(datosEspecificos));

                datosMatricula.setCodRespuesta(dte.getRespuesta().get(0).getCodRespuesta());
                datosMatricula.setDescRespuesta(dte.getRespuesta().get(0).getDescRespuesta());
                datosMatricula.setFechaProceso(dte.getRespuesta().get(0).getFechaProceso());
                datosMatricula.setCursoMatriculaVigente(dte.getRespuesta().get(0).getCursoMatriculaVigente());
                datosMatricula.setCursoMatriculaFutura(dte.getRespuesta().get(0).getCursoMatriculaFutura());

                if (dte.getRespuesta().get(0).getDatosAlumno().size() > 0) {
                    datosMatricula.alumno.setNombre(dte.getRespuesta().get(0).getDatosAlumno().get(0).getNombre());
                    datosMatricula.alumno
                            .setApellido1(dte.getRespuesta().get(0).getDatosAlumno().get(0).getApellido1());
                    datosMatricula.alumno
                            .setApellido2(dte.getRespuesta().get(0).getDatosAlumno().get(0).getApellido2());
                    datosMatricula.alumno
                            .setFechaNacimiento(dte.getRespuesta().get(0).getDatosAlumno().get(0).getFechaNacimiento());
                    datosMatricula.alumno
                            .setIdTitular(dte.getRespuesta().get(0).getDatosAlumno().get(0).getIdTitular());
                }

            } else {
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

    protected static final String REACT_JS_PAGE = "reactjs_main_pinbalmatricula.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage_pinbalmatricula/" + REACT_JS_PAGE;

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

        protected String fechaNacimientoTitular;
        protected String tipoDocumentacionTitular;
        protected String documentacionTitular;
        protected String nombreTitular;
        protected String primerApellidoTitular;
        protected String segundoApellidoTitular;
        protected String tipoDocumentacionTutor;
        protected String documentacionTutor;

        public SolicitudMatricula(String fechaNacimientoTitular, String tipoDocumentacionTitular,
                String documentacionTitular, String nombreTitular, String primerApellidoTitular,
                String segundoApellidoTitular, String tipoDocumentacionTutor, String documentacionTutor) {
            super();
            this.fechaNacimientoTitular = fechaNacimientoTitular;
            this.tipoDocumentacionTitular = tipoDocumentacionTitular;
            this.documentacionTitular = documentacionTitular;
            this.nombreTitular = nombreTitular;
            this.primerApellidoTitular = primerApellidoTitular;
            this.segundoApellidoTitular = segundoApellidoTitular;
            this.tipoDocumentacionTutor = tipoDocumentacionTutor;
            this.documentacionTutor = documentacionTutor;
        }

        @Override
        public String getDatosEspecificos() { // xml
            StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlBuilder.append("<DatosEspecificos>");
            xmlBuilder.append("<Solicitud>");

            // TITULAR
            if (!isEmptyString(String.valueOf(this.fechaNacimientoTitular))) {
                xmlBuilder.append(xmlOptionalStringParameter(this.fechaNacimientoTitular, "FechaNacimientoTitular"));
            }
            //            if (!isEmptyString(String.valueOf(nombreTitular))) {
            //                xmlBuilder.append(xmlOptionalStringParameter(this.nombreTitular, "NombreTitular"));
            //            }
            //            if (!isEmptyString(String.valueOf(primerApellidoTitular))) {
            //                xmlBuilder.append(xmlOptionalStringParameter(this.primerApellidoTitular, "Apellido1Titular"));
            //            }
            //            if (!isEmptyString(String.valueOf(segundoApellidoTitular))) {
            //                xmlBuilder.append(xmlOptionalStringParameter(this.segundoApellidoTitular, "Apellido2Titular"));
            //            }

            // TUTOR
            if (!isEmptyString(this.documentacionTutor)) {
                xmlBuilder.append("<IdTutor>");
                if (!isEmptyString(this.tipoDocumentacionTutor)) {
                    xmlBuilder.append(xmlOptionalStringParameter(this.tipoDocumentacionTutor, "TipoDocumentacion"));
                }
                xmlBuilder.append(xmlOptionalStringParameter(this.documentacionTutor, "Documentacion"));

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

        public DatosAlumno getAlumno() {
            return alumno;
        }

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

        public DatosMatricula(DatosAlumno alumno, String codRespuesta, String descRespuesta,
                String cursoMatriculaVigente, String cursoMatriculaFutura, String fechaProceso) {
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
