package es.caib.carpeta.front.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import es.caib.carpeta.ejb.PropietatGlobalLocal;
import es.caib.carpeta.jpa.AccesJPA;
import es.caib.carpeta.logic.AccesLogicaLocal;
import es.caib.carpeta.logic.utils.EjbManager;

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
	
	public static class AccesInfo {
		
		protected String proveidor;
		protected String nivellSeguretat;
		protected int autenticacio;
		protected Date data;
		protected String idioma;
		protected long entitatid;
		protected int tipus;
		protected Long pluginid;
		
		public AccesInfo() {
			super();
		}
		
		public AccesInfo( String proveidor, String nivell, int autenticacio, Date data, String idioma, long entitat, int tipus, Long plugin) {
			super();
			this.proveidor = proveidor;
			this.nivellSeguretat = nivell;
			this.autenticacio = autenticacio;
			this.data = data;
			this.idioma = idioma;
			this.entitatid = entitat;
			this.tipus = tipus;
			this.pluginid = plugin;
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
		
		public long getEntitatId() {
			return this.entitatid;
		}
		
		public void setEntitatId(long entitat) {
			this.entitatid = entitat;
		}
		
		public int getTipus() {
			 return this.tipus;
		}
		
		public void setTipus(int tipus) {
			this.tipus = tipus;
		}
		
		public Long getPluginId() {
			return this.pluginid;
		}
		
		public void setPluginId(Long plugin) {
			this.pluginid = plugin;
		}
	}
	
	@RequestMapping(value = "/accesos" , method = RequestMethod.GET)
	public void getAccesInfo(HttpServletRequest request, HttpServletResponse response) {
		
		try { 
			
			String dataIniciRequest = request.getParameter("inici");
			String dataFiRequest = request.getParameter("fi");
			String entitatRequest = request.getParameter("entitat"); 
			
			// Si no hi ha parametres de dates, es retorna per defecte el darrer mes
			if(dataFiRequest == null) {
				dataFiRequest = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
			
			// si falta entitat, retorna error 400
			if(entitatRequest != null) {
			
				List <AccesJPA> accesos = accesEjb.findBetweenDates(
						java.sql.Date.valueOf(LocalDate.parse(dataIniciRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
						java.sql.Date.valueOf(LocalDate.parse(dataFiRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
						entitatRequest );
				
				List <AccesInfo> accesosInfo = new ArrayList<AccesInfo>();
				
				for(AccesJPA item : accesos) {
					accesosInfo.add(new AccesInfo(
							item.getProveidorIdentitat(), 
							item.getNivellSeguretat(),
							item.getResultatAutenticacio(),
							item.getDataDarrerAcces(),
							item.getIdioma(),
							item.getEntitatID(),
							item.getTipus(),
							item.getPluginID()
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
