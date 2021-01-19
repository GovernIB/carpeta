package es.caib.carpeta.front.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;

/**
 * Controlador que a partir d'una referencia, es conecta a SISTRA1 i retorna la URL que el ciutadà ha d'utilitzar per reprendre el tràmit
 * @author jagarcia
 *
 */

@Controller
public class TramitacioAnonimaController extends CommonFrontController {
	
	private static final Properties fileProperties = new Properties();
	
	@RequestMapping(value = "/tramitacio" , method = RequestMethod.GET)
	public void getURLTramitacioAnonima (HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String codiRequest = request.getParameter("id");
			
			Map<String,String> urlInfo = new HashMap<String,String>();
			urlInfo.put("id", codiRequest);
			
			if(!codiRequest.isEmpty()) {
				
				BackofficeFacade backofficeFacade = getBackofficeFacade();
				String urlAnonimo = backofficeFacade.obtenerUrlAccesoAnonimo(codiRequest);
				
				if (urlAnonimo != null) {
					urlInfo.put("url", urlAnonimo);					
				}else {
					urlInfo.put("error", "true");
				}
								
				Gson gson = new Gson();
				String json = gson.toJson(urlInfo);
				
				response.setCharacterEncoding("UTF8");
				response.setContentType("application/json");
				
				byte[] utf8JsonString = json.getBytes("UTF8");
	            response.getOutputStream().write(utf8JsonString);
				
			}else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private BackofficeFacade getBackofficeFacade() throws Exception {
		
		 
		if (fileProperties.isEmpty()) {
            String propertySystemFile = System.getProperty(Constants.CARPETA_PROPERTY_BASE + "system.properties");
            File systemFile = new File(propertySystemFile);
    
            try {
                fileProperties.load(new FileInputStream(systemFile));
            } catch (IOException e) {
                log.error("No es pot carregar algun dels fitxers de propietats ... ", e);
            }
        }
       
        final String sistraUrl = fileProperties.getProperty(Constants.CARPETA_PROPERTY_BASE + "pluginsib.carpetafront.sistra1.url");
        final String username = fileProperties.getProperty(Constants.CARPETA_PROPERTY_BASE + "pluginsib.carpetafront.sistra1.user");
        final String password = fileProperties.getProperty(Constants.CARPETA_PROPERTY_BASE + "pluginsib.carpetafront.sistra1.pass");
        
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
