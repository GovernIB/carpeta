package es.caib.carpeta.logic;


import java.util.List;

import javax.annotation.security.PermitAll;


import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.ejb.UsuariEJB;
import es.caib.carpeta.jpa.UsuariJPA;
import es.caib.carpeta.logic.utils.CarpetaPluginsManager;
import es.caib.carpeta.model.entity.Usuari;
import es.caib.carpeta.model.fields.UsuariFields;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class UsuariLogicaEJB extends UsuariEJB implements UsuariLogicaLocal {

	
	@Override
	@PermitAll
	public UsuariJPA findByUsername(String username) throws I18NException {
		
		
		List<Usuari> usuaris = select(UsuariFields.USERNAME.equal(username));
		
		if (usuaris == null || usuaris.size() == 0) {
			return null;
		} else {
			return (UsuariJPA)usuaris.get(0);
		}
		
		
		
	}
	
	
	
	@Override
	@PermitAll
	public UsuariJPA crearUsuari(UsuariJPA usuario) throws I18NException, javax.ejb.EJBException {

		// TODO comprobar que existe en keycloack y obtener la info para crear el
		// usuario.
		try {
			create(usuario);	
		} catch(Throwable th) {
			
			log.error(" ==============================================" );
			log.error(" TIPUS EXCEPCIO: " + th.getClass());
			log.error(th.getMessage(), th);
			
			if (th instanceof I18NException) {
				throw (I18NException)th;
			} else {
				throw new I18NException("comodi", th.getMessage());
			}
			
		}
		
		return usuario;

	}
	

	@Override
	@PermitAll
	public UsuariJPA getUserInfoFromUserInformation(String username) throws  javax.ejb.EJBException {

		UsuariJPA persona = null;
		
		log.info(" XYZ ZZZ  PRE CarpetaPluginsManager.getUserInformationPluginInstance()");
		
		IUserInformationPlugin plugin = CarpetaPluginsManager.getUserInformationPluginInstance();
		
		log.info("XYZ ZZZ ZZZ UserInformationPlugin => " + plugin);
		
		
		UserInfo info;
        try {
            info = plugin.getUserInfoByUserName(username);
        } catch (Exception e) {
            log.error("Error intentant obtenir informació ");
            info = null;
        }
		if (info != null) {
			
			// XYZ ZZZ
			// persona.setIdiomaID(Configuracio.getDefaultLanguage());
			String nom, llinatge1, llinatge2;
			{
				nom = info.getName();

				llinatge1 = info.getSurname1();
				llinatge2 = info.getSurname2();
				
				if (nom == null || llinatge1 == null) {
				    String full = info.getFullName();
				    
				    if (full == null) {
				        nom = "Unknown";
				        llinatge1 = "Unknown";
				    } else {
				    
    				    if (nom == null) {
    				        int pos =full.indexOf(' ');
    				        if (pos == -1) {
    				            nom = full;
    				        } else { 
    				            nom = full.substring(0, pos);
    				        }
    				    }

    				    if (llinatge1 == null) {
    				        int pos = full.indexOf(' ') + 1;
    				        if (pos == -1) {
    				            llinatge1 = full;
    				        } else {
    				            llinatge1 = full.substring(pos + 1);
    				        }
    				    }
				    }
				    
				}
				
			}
			
			persona = new UsuariJPA();
            persona.setEmail(info.getEmail() == null ? "" : info.getEmail());

			persona.setNom(nom);
			persona.setLlinatge1(llinatge1);
			persona.setLlinatge2(llinatge2);

			persona.setUsername(username);
	
			String nif = info.getAdministrationID();
			if (nif != null && nif.trim().length() != 0) {
			  persona.setNif(nif.trim().replaceAll("\\s+", "").toUpperCase());
			}

			persona.setIdiomaID(Configuracio.getDefaultLanguage());
			
		}

		return persona;
	}

}

