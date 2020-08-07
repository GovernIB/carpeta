package org.fundaciobit.pluginsib.carpetafront.sistra;

import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitesPersistentes;
import es.caib.zonaper.ws.v2.model.usuarioautenticadoinfo.UsuarioAutenticadoInfo;
import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;

import org.fundaciobit.pluginsib.core.utils.XTrustProvider;


import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * 
 * @author anadal
 */
public class SistraCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String SISTRA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "sistra.";
    
    public static final String SISTRA1_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "sistra1.";
    
    public static final String SISTRA2_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "sistra2.";

    /**
     * 
     */
    public SistraCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public SistraCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public SistraCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    /*
     * @Override public String generarCSV(String data, long currentTime, long
     * randomNumber) throws Exception {
     * 
     * String ignoreServerCertificates = getProperty(ARXIUCAIB_PROPERTY_BASE +
     * "connection.ignoreservercertificates");
     * 
     * if ("true".equals(ignoreServerCertificates)) {
     * org.fundaciobit.pluginsib.core.utils.XTrustProvider.install(); }
     * 
     * CabeceraPeticion cabecera = new CabeceraPeticion(); // intern api
     * cabecera.setServiceVersion(ApiArchivoDigital.VERSION_SERVICIO); // aplicacio
     * cabecera.setCodiAplicacion(getPropertyRequired(ARXIUCAIB_PROPERTY_BASE +
     * "aplicacio_client")); // TEST
     * cabecera.setUsuarioSeguridad(getPropertyRequired(ARXIUCAIB_PROPERTY_BASE +
     * "connection.username")); // app1
     * cabecera.setPasswordSeguridad(getPropertyRequired(ARXIUCAIB_PROPERTY_BASE +
     * "connection.password")); // app1
     * cabecera.setOrganizacion(getPropertyRequired(ARXIUCAIB_PROPERTY_BASE +
     * "organitzacio")); // CAIB // info login
     * cabecera.setNombreSolicitante(getPropertyRequired(ARXIUCAIB_PROPERTY_BASE +
     * "nom_solicitant")); // Víctor // Herrera
     * cabecera.setDocumentoSolicitante(getPropertyRequired(ARXIUCAIB_PROPERTY_BASE
     * + "document_solicitant")); // 123456789C
     * cabecera.setNombreUsuario(getPropertyRequired(ARXIUCAIB_PROPERTY_BASE +
     * "nom_usuari")); // u104848 // info peticio
     * cabecera.setNombreProcedimiento(getPropertyRequired(ARXIUCAIB_PROPERTY_BASE +
     * "nom_procedimient")); // subvenciones // empleo
     * 
     * String urlBase = getPropertyRequired(ARXIUCAIB_PROPERTY_BASE +
     * "connection.endPoint"); // https://afirmades.caib.es:4430/esb
     * 
     * ApiArchivoDigital apiArxiu = new ApiArchivoDigital(urlBase, cabecera);
     * 
     * apiArxiu.setTrazas(false);
     * 
     * Resultado<String> csv = apiArxiu.generarCSV();
     * 
     * if (log.isDebugEnabled()) { log.debug(" CSV: " + csv.getElementoDevuelto());
     * }
     * 
     * return csv.getElementoDevuelto();
     * 
     * }
     */
    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath, String encriptedUserID,
            String query, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath, String encriptedUserID,
            String query, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub

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
        return "carpetafrontsistra";
    }

    public List<TramitePersistente> obtenerTramites(String documento, Date fechaInicio, Date fechaFin)
            throws Exception {

        BackofficeFacade backofficeFacade = getBackofficeFacade();

        GregorianCalendar inicio = new GregorianCalendar();
        inicio.setTime(fechaInicio);

        GregorianCalendar hoy = new GregorianCalendar();
        hoy.setTime(fechaFin);

        TramitesPersistentes tramites = backofficeFacade.obtenerPersistentes(documento,
                
                DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio),
                
                
                DatatypeFactory.newInstance().newXMLGregorianCalendar(hoy));

        return tramites.getTramitePersistente();
    }

    public String obtenerTiquetAcceso(String idSesionTramitacion, UserData usuario) throws Exception {

        BackofficeFacade backofficeFacade = getBackofficeFacade();

        UsuarioAutenticadoInfo usuarioAutenticadoInfo = new UsuarioAutenticadoInfo();
        usuarioAutenticadoInfo.setNombre(usuario.getName());
        usuarioAutenticadoInfo.setApellido1(usuario.getSurname1());
        usuarioAutenticadoInfo.setApellido2(usuario.getSurname2());
        usuarioAutenticadoInfo.setMetodoAutenticacion(getPropertyRequired(SISTRA1_PROPERTY_BASE + "level"));
        usuarioAutenticadoInfo.setNif(usuario.getAdministrationID());

        String url = backofficeFacade.obtenerTiquetAcceso(idSesionTramitacion, usuarioAutenticadoInfo);

        return url;
    }

    public boolean isDevelopment() {
        return "true".equals(getProperty(SISTRA_PROPERTY_BASE + "development"));
    }
    
    public String getWeb() throws Exception {
        return  getPropertyRequired(SISTRA1_PROPERTY_BASE + "web");
    }
    

    /**
     *
     * @return
     * @throws Exception
     */
    private BackofficeFacade getBackofficeFacade() throws Exception {

        final String sistraUrl = getPropertyRequired(SISTRA1_PROPERTY_BASE + "url");

        final String username = getPropertyRequired(SISTRA1_PROPERTY_BASE + "user");
        
        final String password = getPropertyRequired(SISTRA1_PROPERTY_BASE + "pass");

        if (sistraUrl.startsWith("https") && isDevelopment()) {
            XTrustProvider.install();
        }

        final URL wsdl = new URL(sistraUrl + "?wsdl");
        BackofficeFacadeService service = new BackofficeFacadeService(wsdl);
        BackofficeFacade backofficeFacade = service.getBackofficeFacade();

        BindingProvider bindingProvider = (BindingProvider) backofficeFacade;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, sistraUrl);
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        return backofficeFacade;
    }

}
