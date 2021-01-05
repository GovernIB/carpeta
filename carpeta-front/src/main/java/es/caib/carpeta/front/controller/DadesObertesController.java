package es.caib.carpeta.front.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.PropietatGlobalLocal;
import es.caib.carpeta.jpa.AccesJPA;
import es.caib.carpeta.jpa.PluginJPA;
import es.caib.carpeta.logic.AccesLogicaLocal;
import es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaLocal;
import es.caib.carpeta.logic.PluginEntitatLogicaLocal;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.model.entity.Plugin;
import es.caib.carpeta.model.fields.PluginFields;

/**
 * Conjunt de cridades REST per obtenir informació per a la plataforma de dades obertes
 * @author jagarcia
 *
 */

@Controller
@RequestMapping(value = DadesObertesController.DADES_OBERTES_PATH)
public class DadesObertesController extends CommonFrontController {
	
	public static final String DADES_OBERTES_PATH = "/dadesobertes";
	
	@EJB(mappedName = AccesLogicaLocal.JNDI_NAME)
	AccesLogicaLocal accesEjb;
	
	@EJB(mappedName = PluginDeCarpetaFrontLogicaLocal.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaLocal pluginCarpetaFrontEjb;
	
	@EJB(mappedName = PluginEntitatLogicaLocal.JNDI_NAME)
    protected PluginEntitatLogicaLocal pluginEntitatLogicaEjb;
	
	public static class AccesInfo {
		
		protected String proveidor;
		protected String nivellSeguretat;
		protected int autenticacio;
		protected Date data;
		protected String idioma;
		protected String entitat;
		protected String tipus;
		protected String plugin;
		
		public AccesInfo() {
			super();
		}
		
		public AccesInfo( String proveidor, String nivell, int autenticacio, Date data, String idioma, String entitat, String tipus, String plugin) {
			super();
			this.proveidor = proveidor;
			this.nivellSeguretat = nivell;
			this.autenticacio = autenticacio;
			this.data = data;
			this.idioma = idioma;
			this.entitat = entitat;
			this.tipus = tipus;
			this.plugin = plugin;
		}
		
		public String getProveidor() {
			return this.proveidor;
		}
		
		public void setProveidor(String proveidor) {
			this.proveidor = proveidor;
		}
		
		public String getNivellSeguretat() {
			return this.nivellSeguretat;
		}
		
		public void setNivellSegureta(String nivell) {
			this.nivellSeguretat = nivell;
		}
		
		public int getAutenticacio() {
			return this.autenticacio;
		}
		
		public void setAutenticacio(int autenticacio) {
			this.autenticacio = autenticacio;
		}
		
		public Date getData() {
			return this.data;
		}
		
		public void setData(Date data) {
			this.data = data;
		}
		
		public String getIdioma() {
			return this.idioma;
		}
		
		public void setIdioma(String idioma) {
			this.idioma = idioma;
		}
		
		public String getEntitat() {
			return this.entitat;
		}
		
		public void setEntitat(String entitat) {
			this.entitat = entitat;
		}
		
		public String getTipus() {
			 return this.tipus;
		}
		
		public void setTipus(String tipus) {
			this.tipus = tipus;
		}
		
		public String getPlugin() {
			return this.plugin;
		}
		
		public void setPlugin(String plugin) {
			this.plugin = plugin;
		}
	}
	
	@RequestMapping(value = "/accesos" , method = RequestMethod.GET)
	public void getAccesInfo(HttpServletRequest request, HttpServletResponse response) {
		
		try { 
			
			String dataIniciRequest = request.getParameter("inici");
			String dataFiRequest = request.getParameter("fi");
			String entitatRequest = request.getParameter("entitat"); 
			String idiomaRequest = request.getParameter("idioma");
			
			// Si no hi ha parametres de dates, es retorna per defecte el darrer mes
			// Li suman 1 dia per fer les cerques inclusives
			if(dataFiRequest == null) {
				dataFiRequest = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}else {
				dataFiRequest = LocalDate.parse(dataFiRequest,DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			
			if (dataIniciRequest == null) {
				dataIniciRequest = LocalDate.parse(dataFiRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
						.minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			
			// si no hi ha entitatId, es retorna la propietatGlobal defaultEntity 
			if (entitatRequest == null) {
				PropietatGlobalLocal propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
	            entitatRequest = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
			}
			
			idiomaRequest = (idiomaRequest != null) ? 
					idiomaRequest : 
						(Configuracio.getDefaultLanguage() != null) ? 
								Configuracio.getDefaultLanguage() : "ca"; 
			
			// si falta entitat, retorna error 400
			if(entitatRequest != null) {
				
				/* Recuperam tots els plugins de la entitat*/
				List<Long> pluginsEntitat = pluginEntitatLogicaEjb.getAllPluginsByEntitat(entitatRequest);
		        List<Plugin> plugins = pluginCarpetaFrontEjb.getAllPlugins(PluginFields.PLUGINID.in(pluginsEntitat));
		        Map<Long,String> llistaPlugins = new HashMap<Long,String>(plugins.size());
				for (Plugin plugin : plugins) {
					PluginJPA p = (PluginJPA) plugin;
					llistaPlugins.put(p.getPluginID(), p.getNom().getTraduccio(idiomaRequest).getValor());
				}
			
				List <AccesJPA> accesos = accesEjb.findBetweenDates(
						java.sql.Date.valueOf(LocalDate.parse(dataIniciRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
						java.sql.Date.valueOf(LocalDate.parse(dataFiRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
						entitatRequest );
				
				List <AccesInfo> accesosInfo = new ArrayList<AccesInfo>();
				
				for(AccesJPA item : accesos) {
					
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
					
					accesosInfo.add(new AccesInfo(
							item.getProveidorIdentitat(), 
							item.getNivellSeguretat(),
							item.getResultatAutenticacio(),
							item.getDataDarrerAcces(),
							item.getIdioma(),
							entitatRequest,
							tipusAcces,
							nomPlugin
							));
				}
				
				Gson gson = new Gson();
				String json = gson.toJson(accesosInfo);
			
				response.setContentType("application/json");
	            response.setCharacterEncoding("UTF8");
				
	            byte[] utf8JsonString = json.getBytes("UTF8");
	            response.getOutputStream().write(utf8JsonString);
				
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
			}
			
        } catch (Exception e) {
			log.error("DadesObertesController -> getAccesInfo : " + e.getMessage());
		}
	}

}
