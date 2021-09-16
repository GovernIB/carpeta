package es.caib.carpeta.back.utils;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.stereotype.Controller;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.logic.AuthenticationLogicaService;
import es.caib.carpeta.persistence.UsuariJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
public class PluginUserInformationUtils {

    protected static final Logger log = Logger.getLogger(PluginUserInformationUtils.class);

    /**
     * 
     * @param username
     * @param authEJB
     * @return
     * @throws javax.ejb.EJBException
     */
    public static UsuariJPA getUserInfoFromUserInformation(String username,
            AuthenticationLogicaService authEJB) throws javax.ejb.EJBException {

        UserInfo info;
        {

            IUserInformationPlugin plugin = authEJB.getUserInformationPluginInstance();
            log.info("UserInformationPlugin => " + plugin);

            try {
                info = plugin.getUserInfoByUserName(username);
            } catch (Exception e) {
                log.error("Error intentant obtenir informació de l'usari " + username + ": "
                        + e.getMessage(), e);
                info = null;
            }
        }

        UsuariJPA persona = null;
        if (info != null) {

            persona = userInfo2UsuariJPA(info);

        }

        return persona;
    }

    /**
     * 
     * @param info
     * @return
     */
    protected static UsuariJPA userInfo2UsuariJPA(UserInfo info) {
        UsuariJPA persona;

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
                        int pos = full.indexOf(' ');
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

        persona.setUsername(info.getUsername());

        String nif = info.getAdministrationID();
        if (nif != null && nif.trim().length() != 0) {
            persona.setNif(nif.trim().replaceAll("\\s+", "").toUpperCase());
        }

        persona.setIdiomaID(Configuracio.getDefaultLanguage());
        return persona;
    }

    

}
