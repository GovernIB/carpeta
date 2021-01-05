package es.caib.carpeta.back.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.fundaciobit.pluginsib.userinformation.UserInfo.Gender;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import es.caib.carpeta.back.security.LoginException;
import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.jpa.UsuariJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
public class PluginUserInformationUtils {

    protected static final Logger log = Logger.getLogger(PluginUserInformationUtils.class);

    public static UsuariJPA getUserInfoFromUserInformation(String username) throws javax.ejb.EJBException {

        UserInfo info;
        if (Configuracio.isCAIB()) {

            // PLUGIN DE CAIB !!!!!

            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            if (sra == null || sra.getRequest() == null) {
                String msg = "ServletRequestAttributes o HttpServletRequest val null ";
                log.error(msg);
                throw new LoginException(msg);
            } else {
                HttpServletRequest request = sra.getRequest();

                // https://logindes.caib.es/auth

                RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) request
                        .getAttribute(KeycloakSecurityContext.class.getName());

                if (context == null) {
                    String msg = "RefreshableKeycloakSecurityContext val null ";
                    log.error(msg);
                    throw new LoginException(msg);
                } else {
                    AccessToken token = context.getToken();

                    try {
                        info = userRepresentationToUserInfo(token);
                    } catch (Exception e) {

                        String msg = "Error transformant informació de KeyCloak a UserINFO o de userInfo a UsuariJPA ";
                        log.error(msg);
                        throw new LoginException(msg);
                    }

                }

            }


        } else {

            IUserInformationPlugin plugin = es.caib.carpeta.back.utils.CarpetaPluginsManager
                    .getUserInformationPluginInstance();

            log.info("UserInformationPlugin => " + plugin);

            try {
                info = plugin.getUserInfoByUserName(username);
            } catch (Exception e) {
                log.error("Error intentant obtenir informaciód de l'usari " + username + ": " + e.getMessage(), e);
                info = null;
            }

        }

        UsuariJPA persona = null;
        if (info != null) {

            persona = userInfo2UsuariJPA(info);

        }

        return persona;
    }

    // @Override
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

    protected static UserInfo userRepresentationToUserInfo(AccessToken token) throws Exception {


        final String KEYCLOAKCAIB = Constants.CARPETA_PROPERTY_BASE + "pluginsib.userinformation.keycloakcaib.";
        final String MAPPING_PROPERTY = KEYCLOAKCAIB + "mapping.";
        final String ISDEBUG_PROPERTY = KEYCLOAKCAIB + "debug";


        final boolean debug = "true".equals(Configuracio.getProperty(ISDEBUG_PROPERTY));

        if (debug) {
            log.error("subject = " + token.getSubject());
            log.error("username = " + token.getPreferredUsername());
            log.error("email = " + token.getEmail());
            log.error("familyName = " + token.getFamilyName());
            log.error("givenName = " + token.getGivenName());
            log.error("realmAccess.roles = " + token.getRealmAccess().getRoles());
            log.error("scope = " + token.getScope());

            log.error("--------------------------------");
            log.error("resourceAccessRoles:");

            // MAPPING

            Map<String, AccessToken.Access> resourceAccess = token.getResourceAccess();
            for (String key : resourceAccess.keySet()) {
                log.error(key + " = " + resourceAccess.get(key).getRoles());
            }

            log.error("--------------------------------");
            log.error(" START otherClaims:");
            Map<String, Object> otherClaims = token.getOtherClaims();
            for (String key : otherClaims.keySet()) {
                log.error(key + " = " + otherClaims.get(key));
            }
            log.error(" END otherClaims.");

        }

        UserInfo ui = new UserInfo();
        ui.setAddress(null);
        ui.setCompany(null);
        ui.setEmail(token.getEmail());
        ui.setGender(null);
        ui.setLanguage(null);
        ui.setName(token.getGivenName());
        ui.setSurname1(token.getFamilyName());
        ui.setSurname2(null);
        ui.setPhoneNumber(null);
        ui.setUsername(token.getPreferredUsername());
        ui.setWebsite(null);

        {
            final Set<String> mappingsAvailable = new HashSet<String>(
                    Arrays.asList("username", "administrationID", "name", "surname1", "surname2", "email", "language",
                            "phoneNumber", "password", "gender", "address", "company", "website"));

            Map<String, Object> userAttributes = token.getOtherClaims();
            if (userAttributes != null) {

                if (debug) {
                    for (String key : userAttributes.keySet()) {
                        String value = (String) userAttributes.get(key);
                        log.info(" Attributes[" + key + "] => " + value);
                    }
                }

                for (String userInfoField : mappingsAvailable) {
                    String attributeUser = Configuracio.getProperty(MAPPING_PROPERTY + userInfoField);
                    if (attributeUser == null || attributeUser.trim().length() == 0) {
                        continue;
                    }

                    String attributeUserValue = (String) userAttributes.get(attributeUser);
                    if (attributeUserValue == null) {
                        log.info(" El valor del camp " + userInfoField + " es null. No feim l'assignacio.");
                        continue;
                    }

                    if (debug) {
                        log.info(" Posant al camp " + userInfoField + " el valor " + attributeUserValue);
                    }

                    java.lang.reflect.Field field = ui.getClass().getDeclaredField(userInfoField);
                    field.setAccessible(true);

                    if ("gender".equals(userInfoField)) {
                        try {
                            int genderValue = Integer.parseInt(attributeUserValue);
                            switch (genderValue) {

                            case -1:
                                ui.setGender(Gender.UNKNOWN);
                                break;
                            case 0:
                                ui.setGender(Gender.FEMALE);
                                break;

                            case 1:
                                ui.setGender(Gender.MALE);
                                break;

                            default:
                                throw new Exception();

                            }

                        } catch (Exception e) {
                            log.error(" Error prosessnat mapping de GENDER (-1, 0 o 1): " + attributeUserValue);
                        }

                    } else {
                        field.set(ui, attributeUserValue);
                    }

                }

            }
        }
        return ui;
    }

}
