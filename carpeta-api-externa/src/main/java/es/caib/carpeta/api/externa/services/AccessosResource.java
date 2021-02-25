package es.caib.carpeta.api.externa.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fundaciobit.genapp.common.i18n.I18NException;
import com.google.gson.Gson;

import es.caib.carpeta.api.externa.model.AccesDTO;
import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.logic.AccesLogicaService;
import es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaService;
import es.caib.carpeta.logic.PluginEntitatLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.model.entity.Acces;
import es.caib.carpeta.model.entity.Plugin;
import es.caib.carpeta.model.fields.PluginFields;
import es.caib.carpeta.persistence.PluginJPA;

@Path("/accessos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccessosResource {
	
	protected static Logger log = Logger.getLogger(AccessosResource.class);
	
	@EJB(mappedName = PluginEntitatLogicaService.JNDI_NAME)
    protected PluginEntitatLogicaService pluginEntitatLogicaEjb;	
	
	@EJB(mappedName = PluginDeCarpetaFrontLogicaService.JNDI_NAME)
	protected PluginDeCarpetaFrontLogicaService pluginCarpetaFrontEjb;
	
	@EJB(mappedName = AccesLogicaService.JNDI_NAME)
	protected AccesLogicaService accesEjb;

	@GET
	@Operation(operationId = "hello", summary = "Retorna la llista d`accessos a CARPETA")
	@APIResponse(
            responseCode = "200",
            description = "Llista d'accessos a l'aplicació",
            content = @Content(mediaType = "application/json"))
	public Response estadistiques(		
			
			@Parameter(description = "Data d'inici, en format YYYY-MM-DD, a partir de la qual volem obtenir estadistiques") 
            @QueryParam("inici") String dataIniciRequest, 
            
            @Parameter(description = "Data fi, en format YYYY-MM-DD, fins la qual volem tenir estadistiques") 
            @QueryParam("fi") String dataFiRequest,
            
            @Parameter(description = "Codi de l'entitat de la qual obtenim les estadistiques")
            @QueryParam("entitat") String entitatRequest,
            
            @Parameter(description = "Idioma")
            @QueryParam("idioma") String idiomaRequest
			) {
		
		try {
			
			if (Configuracio.isDesenvolupament()) {
				log.info("REQUEST API EXTERNA ACCESSOS: {inici=" + dataIniciRequest + "&fi=" + dataFiRequest + "&entitat=" + entitatRequest + "&idioma=" + idiomaRequest +"}");
			}
			
			// Si no hi ha parametres de dates, es retorna per defecte el darrer mes
			// Li suman 1 dia per fer les cerques inclusives
			if(dataFiRequest == null) {
				dataFiRequest = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}else {
				dataFiRequest = LocalDate.parse(dataFiRequest,DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			
			if (dataIniciRequest == null) {
				dataIniciRequest = LocalDate.parse(dataFiRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd")).minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			
			// si no hi ha entitatId, es retorna la propietatGlobal defaultEntity 
			if (entitatRequest == null) {
				PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
			    entitatRequest = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
			}
			
			// Si no es defineix idioma, agafa el de la aplicació i en cas de no estar definit, català per defecte.
			idiomaRequest = (idiomaRequest != null) ? 
					idiomaRequest : 
						(Configuracio.getDefaultLanguage() != null) ? 
								Configuracio.getDefaultLanguage() : "ca"; 
			
			// si falta entitat, retorna error 400
			if(entitatRequest != null) {

				List<Long> pluginsEntitat = pluginEntitatLogicaEjb.getAllPluginsByEntitat(entitatRequest);
			    List<Plugin> plugins = pluginCarpetaFrontEjb.getAllPlugins(PluginFields.PLUGINID.in(pluginsEntitat));
			    Map<Long,String> llistaPlugins = new HashMap<Long,String>(plugins.size());
				for (Plugin plugin : plugins) {
					PluginJPA p = (PluginJPA) plugin;
					llistaPlugins.put(p.getPluginID(), p.getNom().getTraduccio(idiomaRequest).getValor());
				}
			
				List <Acces> accesos = accesEjb.findBetweenDates(
						java.sql.Date.valueOf(LocalDate.parse(dataIniciRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
						java.sql.Date.valueOf(LocalDate.parse(dataFiRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
						entitatRequest );
				
				List <AccesDTO> accesosInfo = new ArrayList<AccesDTO>();
				
				for(Acces item : accesos) {
					
					String tipusAcces = "";
					String nomPlugin = "";
					Long itemPluginId = item.getPluginID();
					
					switch(item.getTipus()) {
						case Constants.TIPUS_ACCES_LOGIN_AUTENTICAT:
							tipusAcces = "Login Autenticat";
							nomPlugin = (Configuracio.isCAIB()) ? "LoginIB" : "Login";
							break;
						case Constants.TIPUS_ACCES_LOGIN_NO_AUTENTICAT:
							tipusAcces = "Login No Autenticat";
							nomPlugin = (Configuracio.isCAIB()) ? "LoginIB" : "Login";
							break;
						case Constants.TIPUS_ACCES_PLUGIN:
							tipusAcces = "Accés a Plugin"; 
							nomPlugin = (llistaPlugins.containsKey(itemPluginId)) ? llistaPlugins.get(itemPluginId) : String.valueOf(itemPluginId);
							break;
					}
					
					accesosInfo.add(new AccesDTO(
							item.getProveidorIdentitat(), 
							item.getMetodeAutenticacio(),
							item.getQaa(),
							item.getDataAcces(),
							item.getIdioma(),
							entitatRequest,
							tipusAcces,
							nomPlugin
							));
				}
				
				Gson gson = new Gson();
				String json = gson.toJson(accesosInfo);
				
				return Response.ok(json, MediaType.APPLICATION_JSON).build();
				
			}
		} catch (I18NException e) {
			log.error("Error cridada api rest estadistiques accesos: " + e.getMessage());
		}
		
		return Response.status(Status.BAD_REQUEST).build();
		
	}
	
}
