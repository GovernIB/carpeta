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
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementosExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.FiltroElementosExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ObjectFactory;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;
import org.apache.commons.io.IOUtils;
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
 
        } else if (query.startsWith(OBTENER_TIQUET)) {
        	
        	obtenerTramiteSistra2(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, administrationID, administrationEncriptedID, locale, isGet);
            
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
	        String formEstat;
	        
	        if (isGet) {
	        
	        	Calendar cal = Calendar.getInstance();
	            formDataFi = cal.getTime();
	            cal.add(Calendar.MONTH, -6);
	            formDataInici = cal.getTime();
	            formEstat = "A";
	            
	        } else {
	              
	              String formDataIniciStr = request.getParameter("fechaInicio");
	              String formDataFiStr = request.getParameter("fechaFin");
	              formEstat = request.getParameter("tramiteFinalizado");
	        
	              if (isDevelopment()) {
	            	  log.info("formDataIniciStr: " + formDataIniciStr);
	            	  log.info("formDataFiStr: " + formDataFiStr);
	            	  log.info("formStatus: " + formEstat);
	              }
	              
	              SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	              
	              formDataInici = SDF.parse(formDataIniciStr);
	              formDataFi = SDF.parse(formDataFiStr);
	              
	              
	        }

	        response.setCharacterEncoding("utf-8");
	        response.setContentType("text/html");
	        
	                  
	        String webpage =  getLlistatDeTramitsPage(absolutePluginRequestPath, administrationID, formDataInici, formDataFi, formEstat, locale, isGet);
	              
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
    		Date formDataFi, String formEstat, Locale locale, boolean isGet) throws Exception {
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("form_dataFi", formDataFi);
        map.put("form_dataInici", formDataInici);
        map.put("form_estat", formEstat);
        
        List<TramitePersistenteGenerico> tramitesGenericos = new ArrayList<TramitePersistenteGenerico>();
        String missatgeError = "";

        List<TramitePersistenteGenerico> tramits;
        if (isGet) {
            tramits = null;
        } else {
        	
        	formDataFi = DateUtils.sumarRestarDiasFecha(formDataFi, 1);

        	/* SISTRA1 */
        	try {
	            if (isDevelopment()) {
	              tramits = getTramitsDebug(formDataInici, formDataFi, administrationID, formEstat, locale);
	            }  else {
	              tramits = getTramits(formDataInici, formDataFi, administrationID, formEstat, locale);
	            }
        	}catch(SOAPFaultException e) {
      		  tramits = null;
      		  missatgeError = "Sistra1: " + e.getMessage() + "\n";
      	  	}
        
	        if (tramits != null) {
	          	tramitesGenericos.addAll(tramits);        	  
	        }

	       /* SISTRA2 */
	       try {
	    	   if (isDevelopment()) {
	              tramits = obtenerTramitesDebug(administrationID, formDataInici, formDataFi, formEstat, absolutePluginRequestPath);
	            }  else {
	              tramits = obtenerTramites(administrationID,formDataInici,formDataFi, formEstat, absolutePluginRequestPath);
	            }
	       }catch(javax.ws.rs.client.ResponseProcessingException e) {
	    	   tramits = null;
	    	   e.printStackTrace();
	    	   log.error("Sistra2 - No hi ha tramits:" + e.getMessage());
	       }catch(Exception e) {
	    	    tramits = null;
              	missatgeError += "Sistra2: " + e.getMessage();
              	log.error("Error Sistra2:" + e.getMessage());
           }
	       
	       if (tramits != null) {
	    	   tramitesGenericos.addAll(tramits);
           }
        
        }
        
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
                "carpeta.fecha.inicio", "carpeta.fecha.fin", "tramite.continuar", "tramite.genericerror", 
                "tramite.versionsistra", "tramite.estado", "tramite.finalizado", "tramite.nofinalizado",
                "tramite.todos", "tramite.detalle", "tramite.ver", "tramite.continuar", "tramite.modal.titulo", 
                "tramite.modal.texte", "tramite.modal.continuarBtn", "tramite.modal.cancelarBtn"};
        
        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }

        map.put("development", isDevelopment());
        map.put("missatgeError", missatgeError);
        map.put("tramits", tramitesGenericos);
        
        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);       
        return generat;
   }
   
   public boolean isDevelopment() {
        return "true".equals(getProperty(SISTRA_PROPERTY_BASE + "development"));
   }

   public String getWeb() throws Exception {
        return getPropertyRequired(SISTRA1_PROPERTY_BASE + "web");
   }
   
   
   
    /* SISTRA 1 */
   /**
    * Mètode que retorna els tràmits sense acabar de Sistra1
    * @param fechaInicio
    * @param fechaFin
    * @param documento
    * @return List<TramitePersistenteGenerico>
    * @throws Exception
    */
    public List<TramitePersistenteGenerico> getTramits(Date fechaInicio, Date fechaFin, String documento, String finalizado, Locale locale)
            throws Exception {

        BackofficeFacade backofficeFacade = getBackofficeFacade();
               
        GregorianCalendar inicio = new GregorianCalendar();
        inicio.setTime(fechaInicio);

        GregorianCalendar hoy = new GregorianCalendar();
        hoy.setTime(fechaFin);
        
        List<TramitePersistenteGenerico> tramits = new ArrayList<TramitePersistenteGenerico>();
        
        // Tramits acabats o inacabats si son del tipus PREREGISTRO o PREENVIO
        {   
	        final ObjectFactory objectFactoryElement = new ObjectFactory();    	
	        final FiltroElementosExpediente filtroElementosExpediente = objectFactoryElement.createFiltroElementosExpediente();
	        
	        filtroElementosExpediente.setNif(documento);
	        filtroElementosExpediente.setFechaInicio(objectFactoryElement.createFiltroElementosExpedienteFechaInicio(DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio)));
	        filtroElementosExpediente.setFechaFin(objectFactoryElement.createFiltroElementosExpedienteFechaFin(DatatypeFactory.newInstance().newXMLGregorianCalendar(hoy)));
	        filtroElementosExpediente.setIdioma(locale.getLanguage());
	        
	        // XYZ ZZZ  Paginació
	        ElementosExpediente tramitesAcabados = backofficeFacade.obtenerElementosExpediente(filtroElementosExpediente, 0, 99);
	        
	        for(ElementoExpediente item : tramitesAcabados.getElemento() ) {
	        	TipoElementoExpediente tipus = item.getTipo();
	        	if (tipus != TipoElementoExpediente.REGISTRO && tipus != TipoElementoExpediente.COMUNICACION  && tipus != TipoElementoExpediente.NOTIFICACION ) {
		        	// Els tràmits de tipos Registre es mostren a través del plugin de REGWEB #231
		        	// Els tràmits de tipus Comunicacion o notificacion es mostren a través del plugin de NOTIB #231
	        		
	        		TramitePersistenteGenerico tpg = new TramitePersistenteGenerico(item,1);
	        		 
	        		// Es marquen com a pendents perquè els falta entregar documentació presencialment
	        		Boolean estaPendent = (tipus == TipoElementoExpediente.PREREGISTRO || tipus == TipoElementoExpediente.PREENVIO) ? true : false;
	        		tpg.setPendiente(estaPendent);
	        		
	        		if ((estaPendent && !finalizado.equals("S")) || (!estaPendent && !finalizado.equals("N"))) {
	        			tramits.add(tpg);
	        		}
	        	}
	        	
	        }
        }

        // Tràmits no acabats
        if (!finalizado.equals("S")) {
	        TramitesPersistentes tramites = backofficeFacade.obtenerPersistentes(documento,
	
	                DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio),
	
	                DatatypeFactory.newInstance().newXMLGregorianCalendar(hoy));
	        
	        TramitePersistenteGenerico tpg; 
	        
	        for (TramitePersistente tp : tramites.getTramitePersistente()) {
	        	tpg = new TramitePersistenteGenerico(tp,1);
	        	tramits.add(tpg);
	        }
        }
        
        return tramits;
    }
    
    private List<TramitePersistenteGenerico> getTramitsDebug(Date formDataInici, Date formDataFi, String administrationID, String finalizado, Locale locale) throws Exception {
        
    	BackofficeFacade backofficeFacade = getBackofficeFacade();
    	
        List<TramitePersistenteGenerico> tramits = this.getTramits(formDataInici, formDataFi, administrationID, finalizado, locale);

        if (tramits == null || tramits.isEmpty()) {
            log.info(" TRAMITS SISTRA1 NULL o EMPTY: " + tramits);
        } else {
            int x = 1;
            for (TramitePersistenteGenerico tp : tramits) {
            	log.info(" -------------  TRAMIT [" + x + " ] SISTRA1 -------------------");
                log.info("tp.getIdSesionTramitacion() => " + tp.getIdSesionTramitacion());
            	log.info("tp.getIdTramite() => " + tp.getIdTramite());
            	log.info("tp.getUrl() => " + tp.getUrl());
            	log.info("tp.getDescripcionTramite() => " + tp.getDescripcionTramite());
            	log.info("tp.getFechaInicio() => " + tp.getFechaInicio());
            	log.info("tp.isPendiente() => " + tp.isPendiente());
            	log.info("tp.getVersionSistra() => " +tp.getVersionSistra());
            	log.info("tp.getVersionTramite(); => " + tp.getVersionTramite());
            	log.info("tp.getIdioma() => " + tp.getIdioma());
            	log.info("tp.getTipo() => " + tp.getTipo());
            	log.info("tp.getFechaUltimoAcceso() => " + tp.getFechaUltimoAcceso());
                x++;
            }
        }
        return tramits;
    }
    

    private BackofficeFacade getBackofficeFacade() throws Exception {

        final String sistraUrl = getPropertyRequired(SISTRA1_PROPERTY_BASE + "url");
        final String username = getPropertyRequired(SISTRA1_PROPERTY_BASE + "user");
        final String password = getPropertyRequired(SISTRA1_PROPERTY_BASE + "pass");

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
    * Mètode per obtenir els tràmits pendents de Sistra2
    * @param documento
    * @param fechaInicio
    * @param fechaFin
    * @return List<TramitePersistenteGenerico>
    * @throws Exception
    */
   public List<TramitePersistenteGenerico> obtenerTramites(String documento, Date fechaInicio, Date fechaFin, String finalizado, String absolutePluginRequestPath) throws Exception {

	       Client client = getClientBasicAuthenticator();
	       
	       final RFiltroTramitePersistencia filtroPer = new RFiltroTramitePersistencia();
	       filtroPer.setFechaDesde(fechaInicio);
	       filtroPer.setFechaHasta(DateUtils.sumarRestarDiasFecha(fechaFin, 1));
	       filtroPer.setNif(documento);
	      
	       
	       List<RTramitePersistencia> tramites = client.target( getPropertyRequired(SISTRA2_PROPERTY_BASE + "url") + "/tramite")
	               .request(MediaType.APPLICATION_JSON)
	               .post(Entity.entity(filtroPer, MediaType.APPLICATION_JSON), new GenericType<List<RTramitePersistencia>>() {});
	       
	       List<TramitePersistenteGenerico> tramits = new ArrayList<TramitePersistenteGenerico>();
	       
	       if (tramites == null || tramites.isEmpty()) {
	    	   tramits = null;
	       } else {
	    	   for (RTramitePersistencia tp : tramites) {
	    		   if(finalizado.equals("A") || finalizado.equals("N")) {
	    			   TramitePersistenteGenerico tpg = new TramitePersistenteGenerico(tp,2);
	    			   tpg.setUrl(absolutePluginRequestPath + "/" + OBTENER_TIQUET + "?tramite=" + tpg.getIdSesionTramitacion());
	    			   tramits.add(tpg);
	    		   }
	    	   }
	       }
	       return tramits;
   }
   
   
   private List<TramitePersistenteGenerico> obtenerTramitesDebug(String documento, Date fechaInicio, Date fechaFin, String finalizado, String absolutePluginRequestPath) throws Exception {
            
       List<TramitePersistenteGenerico> tramits = this.obtenerTramites(documento,fechaInicio,fechaFin,finalizado,absolutePluginRequestPath);
       
       if (tramits == null || tramits.isEmpty()) {
    	   tramits = null;
           log.info("SISTRA2 TRAMITES NULL o EMPTY: " + tramits);
       } else {
           int x = 1;
    	   for (TramitePersistenteGenerico tp : tramits) {
    		   log.info(" -------------  TRAMITE [" + x + " ] SISTRA 2-------------------");
    		   log.info("tp.getIdTramite() => " + tp.getIdTramite());
    		   log.info("tp.getDescripcionTramite() => " + tp.getDescripcionTramite());
    		   log.info("tp.getVersionTramite(); => " + tp.getVersionTramite());
    		   log.info("tp.getIdioma() => " + tp.getIdioma());
    		   log.info("tp.getFechaInicio() => " + tp.getFechaInicio());
    		   log.info("tp.getFechaUltimoAcceso() => " + tp.getFechaUltimoAcceso());
    		   log.info("tp.getIdSesionTramitacion() => " + tp.getIdSesionTramitacion());
    		   log.info("tp.getTipo() => " +tp.getTipo());
    		   log.info("tp.getVersionSistra() => " +tp.getVersionSistra());
    		   log.info("tp.getUrl() => " +tp.getUrl());
    		   log.info("tp.isPendiente() => " + tp.isPendiente());
               x++;
           }
       }
       return tramits;
  
}
    

   private Client getClientBasicAuthenticator() throws Exception{
	   
	   final String sistraUrl = getPropertyRequired(SISTRA2_PROPERTY_BASE + "url");
	   final String username = getPropertyRequired(SISTRA2_PROPERTY_BASE + "user");
       final String password = getPropertyRequired(SISTRA2_PROPERTY_BASE + "pass");

       return ClientBuilder.newClient()
    		   .register(new BasicAuthenticator(username, password))
    		   .register(EntityLoggingFilter.class)
    		   .register(JsonbConfigurator.class);
   }


   
   // --------------------------------------------------------------------------------------
   // --------------------------------------------------------------------------------------
   // -------------------     A C C E S O  T I Q U E T   S I S T R A 2      ----------------
   // --------------------------------------------------------------------------------------
   // --------------------------------------------------------------------------------------
   
   
   protected static final String OBTENER_TIQUET = "tiquetAcceso";    
   
   public void obtenerTramiteSistra2(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
           HttpServletRequest request, HttpServletResponse response, String administrationID,
           String administrationEncriptedID, Locale locale, boolean isGet)  {
       
       try {
       
	        // XYZ ZZZ Issue #197 Pendent obtenir dades autenticació usuari per cridar obtenerTiquetAccesoSistra2
    	   
	        /*
	        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();
	        UsuarioClave usuClave = usuarioAutenticado.getUsuarioClave();
	        
	        if (isDevelopment()) {
    		   log.info("REQUEST TRAMITE: " + request.getParameter("tramite"));
    		   log.info("Nombre: " + usuClave.getNombre());
	   	        log.info("Apellido1: " + usuClave.getApellido1());
	   	        log.info("Apellido2: " + usuClave.getApellido2());
	   	        log.info("Nif: " + usuClave.getNif());
	   	        log.info("MetodoAutentication: " + usuClave.getMetodoAutentificacion());
	   	        log.info("Qaa: " + usuClave.getQaa());
    	   }
	        
	       String url = obtenerTiquetAccesoSistra2(request.getParameter("tramite"), usuClave);
           response.sendRedirect(url);
           */
           
           
       } catch (Exception e) {
           log.error("Error obtenint tiquet accès Sistra 2: " + e.getMessage(), e);
       }  
   }
   
   /*
   public String obtenerTiquetAccesoSistra2(String idSesionTramitacion, UsuarioClave usuario) throws Exception {

       Client client = getClientBasicAuthenticator();
       
       RInfoTicketAcceso infoTicket = new RInfoTicketAcceso();
       infoTicket.setIdSesionTramitacion(idSesionTramitacion);
       
       RUsuarioAutenticadoInfo usuarioInfo = new RUsuarioAutenticadoInfo();
       usuarioInfo.setNombre(usuario.getNombre());
       usuarioInfo.setApellido1(usuario.getApellido1());
       usuarioInfo.setApellido2(usuario.getApellido2());
       usuarioInfo.setEmail("");
       usuarioInfo.setUsername(usuario.getNif());
       usuarioInfo.setNif(usuario.getNif());
       usuarioInfo.setAutenticacion("c");
       usuarioInfo.setMetodoAutenticacion(usuario.getMetodoAutentificacion());
       usuarioInfo.setQaa(usuario.getQaa());
       
       infoTicket.setUsuarioAutenticadoInfo(usuarioInfo);
       
       String url = client.target( getPropertyRequired(SISTRA2_PROPERTY_BASE + "url") + "/ticketAcceso" )
    		   .request(MediaType.APPLICATION_JSON)
    		   .post(Entity.entity(infoTicket, MediaType.APPLICATION_JSON), String.class);
    		   
	    if (isDevelopment()) {
      		log.info("URL TIQUET: " + url);
       }
       
       return url;
   }
   */
   
   /*
   // SISTRA 1
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
      String resource = "/" + LOGORESOURCE; //resource a on es troba l'icona
      try {

         //Agafa la icona del resource
         input = this.getClass().getResourceAsStream(resource+"/logo-sistra.png");
         if(input != null) {
            fileInfo =  new  FileInfo("logo-sistra.png","image/png", IOUtils.toByteArray(input));
         }

      }  catch (Exception e) {
         log.error("Error llegint recurs : "+resource+"/logo-sistra.png" + e.getMessage(), e);

      }
      return fileInfo;

   }
   
}

