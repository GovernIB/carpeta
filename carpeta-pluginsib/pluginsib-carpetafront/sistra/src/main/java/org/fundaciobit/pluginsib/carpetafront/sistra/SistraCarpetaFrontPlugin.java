package org.fundaciobit.pluginsib.carpetafront.sistra;

import es.caib.carpeta.commons.utils.BasicAuthenticator;
import es.caib.carpeta.commons.utils.DateUtils;
import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.sistramit.rest.api.externa.v1.RFiltroTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitesPersistentes;
import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;
import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.core.utils.XTrustProvider;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * 
 * @author anadal
 */
public class SistraCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String SISTRA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "sistra.";
    public static final String SISTRA1_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "sistra1.";
    public static final String SISTRA2_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "sistra2.";
    
    private final static Boolean development = true;

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
        return "carpetafrontsistra";
    }

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, String administrationID,String administrationIDEncriptat) throws Exception {
        
        String startURL = absolutePluginRequestPath + "/" + LLISTAT_TRAMITS_PAGE; 
        
        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }
    
    
    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                    HttpServletRequest request, HttpServletResponse response, String administrationID,
                                    String administrationEncriptedID, Locale locale, boolean isGet) {



        log.info("SistraCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("SistraCarpetaFrontPlugin::requestCarpetaFront => administrationID: " + administrationID);
        log.info("SistraCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: " + administrationEncriptedID);
        
        if (query.startsWith(LLISTAT_TRAMITS_PAGE)) {
            
            llistatDeTramits(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, administrationID, administrationEncriptedID, locale, isGet);
                        
        } else {
        
        	super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, administrationID, administrationEncriptedID, locale, isGet);
        }
        
        
    }
    
    
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // -------------------     L L I S T A T   DE   T R A M  I T S           ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    
    
    protected static final String LLISTAT_TRAMITS_PAGE = "llistatTramits";
    
    public void llistatDeTramits(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, String administrationID,
            String administrationEncriptedID, Locale locale, boolean isGet)  {
        
        try {
        
	        Date formDataInici;
	        Date formDataFi;
	        
	        if (isGet) {
	        
	        	Calendar cal = Calendar.getInstance();
	            formDataFi = cal.getTime();
	            cal.add(Calendar.MONTH, -6);
	            formDataInici = cal.getTime();
	            
	        } else {
	              
	              String formDataIniciStr = request.getParameter("fechaInicio");
	              String formDataFiStr = request.getParameter("fechaFin");
	        
	              if (isDevelopment()) {
	            	  log.info("formDataIniciStr: " + formDataIniciStr);
	            	  log.info("formDataFiStr: " + formDataFiStr);
	              }
	              
	              SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	              
	              formDataInici = SDF.parse(formDataIniciStr);
	              formDataFi = SDF.parse(formDataFiStr);
	              
	        }

	        response.setCharacterEncoding("utf-8");
	        response.setContentType("text/html");
	                  
	        String webpage =  getLlistatDeTramitsPage(absolutePluginRequestPath, administrationID, formDataInici, formDataFi, locale, isGet);
	              
	        try {
	        	response.getWriter().println(webpage);
	        	response.flushBuffer();
	        } catch (IOException e) {
	        	log.error("Error obtening writer: " + e.getMessage(), e);
	        }
              
        } catch (Exception e) {
            log.error("Error llistant tràmits: " + e.getMessage(), e);
        }  
    }
    
    
    
    public String getLlistatDeTramitsPage(String absolutePluginRequestPath, String administrationID,  Date formDataInici, 
    		Date formDataFi, Locale locale, boolean isGet) throws Exception {
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("form_dataFi", formDataFi);
        map.put("form_dataInici", formDataInici);
        
        List<TramitePersistenteGenerico> tramitesGenericos = new ArrayList<TramitePersistenteGenerico>();
        String missatgeError = "";

        /* SISTRA1 */
        List<TramitePersistenteGenerico> tramits;
        if (isGet) {
            tramits = null;
        } else {
        	try {
	            if (isDevelopment()) {
	              tramits = getTramitsDebug(formDataInici, formDataFi, administrationID);
	            }  else {
	              tramits = getTramits(formDataInici, formDataFi, administrationID);
	            }
        	}catch(SOAPFaultException e) {
      		  tramits = null;
      		  missatgeError = "Sistra1: " + e.getMessage() + "\n";
      	  }
        }
        if (tramits != null)
        	tramitesGenericos.addAll(tramits);
        
        /* SISTRA2 */
        try {
        	        
        List<TramitePersistenteGenerico> tramites = obtenerTramites(administrationID,formDataInici,formDataFi);
        if (tramites != null)
        	tramitesGenericos.addAll(tramites);
        
        }catch(Exception e) {
        	missatgeError += "Sistra2: " + e.getMessage();
        	log.error("Error Sistra2:" + e.getMessage() );
        }
        map.put("missatgeError", missatgeError);
        
        InputStream input = this.getClass().getResourceAsStream("/webpage/sistra.html");
        String plantilla = IOUtils.toString(input, "UTF-8");

        // XYZ ZZZ   
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCE);
  
        // XYZ ZZZ            
        map.put("form_action", absolutePluginRequestPath + "/" + LLISTAT_TRAMITS_PAGE);
        map.put("lang", locale.getLanguage());
        map.put("isget", isGet);
        
        String[] traduccions = { "tramite.listado", "tramite.descripcion", "tramite.tramite",
                "tramite.fecha.inicio", "tramite.acceso", "tramite.vacio", "carpeta.buscar",
                "carpeta.fecha.inicio", "carpeta.fecha.fin", "tramite.continuar", "tramite.genericerror", "tramite.versionsistra" };
        
        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }

        map.put("development", isDevelopment());
        map.put("tramits", tramitesGenericos);
        map.put("web", getWeb() );
        
        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);       
        return generat;

    }
    
    
    

    private List<TramitePersistenteGenerico> getTramitsDebug(Date formDataInici, Date formDataFi, String administrationID) throws Exception {
        
        List<TramitePersistenteGenerico> tramits = this.getTramits(formDataInici, formDataFi, administrationID);

        if (tramits == null || tramits.isEmpty()) {
            log.info(" TRAMITS SISTRA1 NULL o EMPTY: " + tramits);
        } else {
            int x = 1;
            for (TramitePersistenteGenerico tp : tramits) {
                System.out.println(" -------------  TRAMIT SISTRA1 [" + x + " ] -------------------");
                System.out.println("tp.getIdTramite() => " + tp.getIdTramite());
                System.out.println("tp.getDescripcionTramite() => " + tp.getDescripcionTramite());
                System.out.println("tp.getVersionTramite(); => " + tp.getVersionTramite());
                System.out.println("tp.getIdioma() => " + tp.getIdioma());
                System.out.println("tp.getFechaInicio() => " + tp.getFechaInicio());
                System.out.println("tp.getFechaUltimoAcceso() => " + tp.getFechaUltimoAcceso());
                System.out.println(" tp.getIdSesionTramitacion() => " + tp.getIdSesionTramitacion());
                
                //System.out.println(" Tiquet-Acceso => " + plugin.obtenerTiquetAcceso(tp.getIdSesionTramitacion(), usuari));

                x++;

            }
        }
        
        return tramits;
    }

    
    
 
    /* SISTRA1 */
    public List<TramitePersistenteGenerico> getTramits(Date fechaInicio, Date fechaFin, String documento)
            throws Exception {

        BackofficeFacade backofficeFacade = getBackofficeFacade();
               
        GregorianCalendar inicio = new GregorianCalendar();
        inicio.setTime(fechaInicio);

        GregorianCalendar hoy = new GregorianCalendar();
        hoy.setTime(fechaFin);

        TramitesPersistentes tramites = backofficeFacade.obtenerPersistentes(documento,

                DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio),

                DatatypeFactory.newInstance().newXMLGregorianCalendar(hoy));
        
        List<TramitePersistenteGenerico> tramits = new ArrayList<TramitePersistenteGenerico>();
        
        for (TramitePersistente tp : tramites.getTramitePersistente()) {
        	tramits.add(new TramitePersistenteGenerico(tp,1));
        }
        
        return tramits;
    }
    
    /* SISTRA2 */
    /**
    *
    * @param documento
    * @param fechaInicio
    * @param fechaFin
    * @return
    * @throws Exception
    */
   public List<TramitePersistenteGenerico> obtenerTramites(String documento, Date fechaInicio, Date fechaFin) throws Exception {

	       Client client = getClientBasicAuthenticator();
	       
	       final RFiltroTramitePersistencia filtroPer = new RFiltroTramitePersistencia();
	       filtroPer.setFechaDesde(fechaInicio);
	       filtroPer.setFechaHasta(DateUtils.sumarRestarDiasFecha(fechaFin, 1));
	       filtroPer.setNif(documento);
	       
	       if (isDevelopment()) {
	    	   log.info("============= PARAMETROS SISTRA 2 =========================");
	    	   log.info("URL: " + getPropertyRequired(SISTRA2_PROPERTY_BASE + "url") + "/tramite");
	    	   log.info(Entity.entity(filtroPer, MediaType.APPLICATION_JSON).toString());
	       }
	       
	       List<RTramitePersistencia> tramites = client.target( getPropertyRequired(SISTRA2_PROPERTY_BASE + "url") + "/tramite")
	               .request(MediaType.APPLICATION_JSON)
	               .post(Entity.entity(filtroPer, MediaType.APPLICATION_JSON), new GenericType<List<RTramitePersistencia>>() {});
	
	       List<TramitePersistenteGenerico> tramits = new ArrayList<TramitePersistenteGenerico>();
	       
	       if (tramites == null || tramites.isEmpty()) {
	    	   tramits = null;
	           log.info("SISTRA2 TRAMITES NULL o EMPTY: " + tramites);
	       } else {
	           int x = 1;
	           if (isDevelopment()) {
	        	   for (RTramitePersistencia tp : tramites) {
		        	   System.out.println(" -------------  TRAMITE SISTRA 2 [" + x + " ] -------------------");
		               System.out.println("tp.getIdTramite() => " + tp.getIdTramite());
		               System.out.println("tp.getDescripcionTramite() => " + tp.getDescripcionTramite());
		               System.out.println("tp.getVersionTramite(); => " + tp.getVersionTramite());
		               System.out.println("tp.getIdioma() => " + tp.getIdioma());
		               System.out.println("tp.getFechaInicio() => " + tp.getFechaInicio());
		               System.out.println("tp.getFechaUltimoAcceso() => " + tp.getFechaUltimoAcceso());
		               System.out.println(" tp.getIdSesionTramitacion() => " + tp.getIdSesionTramitacion());
		               x++;
		               tramits.add(new TramitePersistenteGenerico(tp,2));
		           }
	           }
	       }
	       return tramits;
      
   }
    
   public boolean isDevelopment() {
        return "true".equals(getProperty(SISTRA_PROPERTY_BASE + "development"));
   }

   public String getWeb() throws Exception {
        return getPropertyRequired(SISTRA1_PROPERTY_BASE + "web");
   }

    /* SISTRA1 */
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
    
    
    /* SISTRA2 */
    /**
    *
    * @return
    */
   private Client getClientBasicAuthenticator() throws Exception{
	   
	   final String sistraUrl = getPropertyRequired(SISTRA2_PROPERTY_BASE + "url");
	   final String username = getPropertyRequired(SISTRA2_PROPERTY_BASE + "user");
       final String password = getPropertyRequired(SISTRA2_PROPERTY_BASE + "pass");

       if(sistraUrl.startsWith("https") && development){
          XTrustProvider.install();
       }

       return ClientBuilder.newClient()
    		   .register(new BasicAuthenticator(username, password))
    		   .register(EntityLoggingFilter.class)
    		   .register(JsonbConfigurator.class);
   }

   /*
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
   */




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

