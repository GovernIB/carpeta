package es.caib.carpeta.back.security;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import es.caib.carpeta.back.preparer.BasePreparer;
import es.caib.carpeta.back.utils.PluginUserInformationUtils;
import es.caib.carpeta.commons.utils.Constants;
import static es.caib.carpeta.commons.utils.Constants.ESTAT_LOG_ERROR;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_AUDIT_ENTRADA_BACK;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_LOG_AUTENTICACIO_BACK;
import es.caib.carpeta.ejb.EntitatLocal;
import es.caib.carpeta.ejb.PropietatGlobalLocal;
import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.jpa.UsuariEntitatJPA;
import es.caib.carpeta.jpa.UsuariJPA;
import es.caib.carpeta.logic.AuditoriaLogicaLocal;
import es.caib.carpeta.logic.LogCarpetaLogicaLocal;
import es.caib.carpeta.logic.UsuariEntitatLogicaLocal;
import es.caib.carpeta.logic.UsuariLogicaLocal;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.model.fields.EntitatFields;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


/**
 *
 * @author anadal(u80067)
 * @author mgonzalez
 *
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    protected final Logger log = Logger.getLogger(getClass());

    protected LogCarpetaLogicaLocal logCarpetaLogicaEjb;
    protected AuditoriaLogicaLocal auditoriaEjb;

    public class LogInfo {

        protected final int tipus;

        protected final long inici;

        protected StringBuilder peticio = new StringBuilder();

        public LogInfo(int tipus) {
            super();

            this.inici = System.currentTimeMillis();
            this.tipus = tipus;
        }

        public void addText(String txt) {
            peticio.append(txt).append("\n");
        }

    }

    @Override
    public synchronized void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

        long temps = System.currentTimeMillis(); // Para calcular el tiempo de logs, estadisticas y auditorias
        StringBuilder peticio = new StringBuilder(); //Guardarem les dades de la petició per als logs
        String entitatCodi ="";

        try {
            logCarpetaLogicaEjb = EjbManager.getLogCarpetaLogicaEJB();
            auditoriaEjb = EjbManager.getAuditoriaLogicaEJB();
        } catch (I18NException e) {
            log.error(e.getMessage());
        }

        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication au = sc.getAuthentication();

        if (au == null) {
            // TODO traduccio
            peticio.append("Usuari: no definit").append("\n");
            peticio.append("classe: ").append(getClass().getName()).append("\n");
            logCarpetaLogicaEjb.crearLog("Autenticació al back", ESTAT_LOG_ERROR, TIPUS_LOG_AUTENTICACIO_BACK,
                        System.currentTimeMillis() - temps, null, "NO PUC ACCEDIR A LA INFORMACIO de AUTENTICACIO",
                        peticio.toString(), "", null);

            throw new LoginException("NO PUC ACCEDIR A LA INFORMACIO de AUTENTICACIO");

        }

        User user = (User) au.getPrincipal();
        String username = user.getUsername();

        //Montam les dades bàsiques de la petició per a la creació dels logs
        peticio = new StringBuilder();
        peticio.append("Usuari: ").append(username).append("\n");
        peticio.append("classe: ").append(getClass().getName()).append("\n");

        log.debug(" =================================================================");
        log.info(" ============ Login Usuari: " + username);

        try {
            LoginInfo loginInfo = LoginInfo.getInstance();

            if (!username.equals(loginInfo.getUsuariPersona().getUsername())) {
                peticio.append("Usuari: ").append(username).append("\n");
                entitatCodi = loginInfo.getEntitat()!=null?loginInfo.getEntitat().getCodi():null;
                logCarpetaLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username, ESTAT_LOG_ERROR,
                        TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps, null,
                        "Amb aquest navegador ja s'ha autenticat amb un altre usuari. Tanqui el navegador completament.",
                        peticio.toString(), entitatCodi, null);
                throw new LoginException("Amb aquest navegador ja s'ha autenticat amb un altre usuari."
                        + " Tanqui el navegador completament.");

            }


        } catch (Throwable e) {
            // OK
            // Si és llança un error significa que ja hi ha una sessió oberta 
        }

        final boolean isDebug = log.isDebugEnabled();

        // Cercam si té el ROLE_ADMIN o ROLE_SUPER
        
        boolean containsRoleAdEn = false;
        boolean containsRoleSuper = false;
        Collection<GrantedAuthority> seyconAuthorities;
        {
        
        
            seyconAuthorities = user.getAuthorities();

    
            if (seyconAuthorities.size() == 0) {
                log.info(" ======= NO TE CAP ROL =========== ");
    
            } else {
    
                for (GrantedAuthority grantedAuthority : seyconAuthorities) {
                    String rol = grantedAuthority.getAuthority();
    
                    log.info("Rol SEYCON : " + rol);
    
                    if (Constants.ROLE_ADMIN.equals(rol)) {
                        containsRoleAdEn = true;
                    }
                    if (Constants.ROLE_SUPER.equals(rol)) {
                        containsRoleSuper = true;
                    }
                }
            }
        }

        UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
        UsuariLogicaLocal usuariPersonaLogicaEjb;
        EntitatLocal entitatLogicaEjb;
        PropietatGlobalLocal propietatGlobalEjb;
        try {
            usuariEntitatLogicaEjb = EjbManager.getUsuariEntitatLogicaEJB();
            usuariPersonaLogicaEjb = EjbManager.getUsuariPersonaLogicaEJB();
            entitatLogicaEjb = EjbManager.getEntitatLogicaEJB();
            propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
        } catch (I18NException e) {
            // TODO traduccio
            throw new LoginException("No puc accedir al gestor d´obtenció de" + " informació d´usuari-entitat per "
                    + username + ": " + e.getMessage(), e);
        }

        UsuariJPA usuariPersona;


        
        
        try {
            usuariPersona = usuariPersonaLogicaEjb.findByUsername(username);
            entitatCodi = usuariPersona.getEntitat()!=null?usuariPersona.getEntitat().getCodi():null;
        } catch (I18NException e1) {
            String msg = I18NUtils.getMessage(e1);
            log.error("Error llegint si l'usuari es troba a la BBDD: " + msg, e1);

            logCarpetaLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username, ESTAT_LOG_ERROR,
                        TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps, e1,
                        "Error llegint si l'usuari es troba a la BBDD: " + msg, peticio.toString(), entitatCodi, null);

            usuariPersona = null;
        }
        
        boolean necesitaConfigurar = false;

       

        if (usuariPersona == null) {
            log.info(" La persona amb username  '" + username + "' no esta registrada !!!!");
            // Revisar si és un Administrador que entra per primera vegada

            {
                try {
                    UsuariJPA persona;
                    // Obtenir informació de l'USUARI del SISTEMA de PLUGIN USER INFORMATION
                    
                {

                        //persona = usuariPersonaLogicaEjb.getUserInfoFromUserInformation(username);
                        
                        persona = PluginUserInformationUtils.getUserInfoFromUserInformation(username);
                    }
                    
                    
                    

                    if (persona == null) {
                     /*  logCarpetaLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username, ESTAT_LOG_ERROR,
                                TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps, null,
                                "No hem trobat informació de la Persona amb username  "+username+" dins del sistema de UserInformation", peticio.toString(), entitatCodi, null);*/
                        log.error("No hem trobat informació de la Persona amb username  '" + username
                                + "' dins del sistema de UserInformation");

                    } else {

                        UsuariEntitatJPA usuariEntitat = null;

                        log.info("Contains role AdminEntitat: " + containsRoleAdEn);
                        if (containsRoleAdEn) {

                            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
                            log.info("Default Entity Code => " + defaultEntityCode);

                            if (defaultEntityCode != null && defaultEntityCode.trim().length() != 0) {

                                Long defaultEntity = entitatLogicaEjb.executeQueryOne(EntitatFields.ENTITATID,
                                        EntitatFields.CODI.equal(defaultEntityCode));

                                log.info("Default Entity ID => " + defaultEntity);

                                if (defaultEntity != null) {
                                    usuariEntitat = new UsuariEntitatJPA();
                                    // usuariEntitat.setActiu(true);
                                    usuariEntitat.setEntitatID(defaultEntity);
                                    usuariEntitat.setActiu(true);
                                    // usuariEntitat.setUsuari(persona);
                                }
                            }

                        }

                        necesitaConfigurar = true;

                        persona = usuariPersonaLogicaEjb.crearUsuari(persona);

                        if (usuariEntitat == null) {
                            usuariPersona = persona;
                        } else {
                            usuariEntitat.setUsuariID(persona.getUsuariID());
                            usuariEntitat = (UsuariEntitatJPA) usuariEntitatLogicaEjb.create(usuariEntitat);
                            usuariPersona = persona;

                        }
                        entitatCodi = usuariPersona.getEntitat()!=null?usuariPersona.getEntitat().getCodi():null;
                        log.info("necesitaConfigurarUsuari = " + necesitaConfigurar);
                    }

                } catch (Throwable e) {
                    usuariPersona = null;
                    String msg;
                    if (e instanceof I18NException) {
                        msg = I18NUtils.getMessage((I18NException) e);
                    } else if (e instanceof I18NValidationException) {
                        msg = I18NUtils.getMessage((I18NValidationException) e);
                    } else {
                        msg = e.getMessage();
                    }

                    logCarpetaLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username, ESTAT_LOG_ERROR,
                                TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps, e,
                                "Error llegint informacio del plugin de User Information o creant l'usuari a la BBDD: "
                                        + msg,
                                peticio.toString(), entitatCodi, null);


                    log.error("Error llegint informacio del plugin de User Information o creant l'usuari a la BBDD: "
                            + msg, e);
                }
            }

        }

        Set<UsuariEntitatJPA> usuariEntitats = null;
        if (usuariPersona != null) {
            // usuariEntitats = usuariPersona.getUsuariEntitatJPAs();

            try {
                usuariEntitats = new HashSet<UsuariEntitatJPA>(
                        usuariEntitatLogicaEjb.findAllByUsuariIdWithEntitat(usuariPersona.getUsuariID()));
                //Obtenim el codi de l'entitat
                if(usuariEntitats.size()>0){
                    entitatCodi = usuariEntitats.iterator().next().getEntitat().getCodi();
                }
            } catch (I18NException e) {
                log.error(I18NUtils.getMessage(e), e);

                logCarpetaLogicaEjb.crearLog("Autenticació al back de l'usuri " + username, ESTAT_LOG_ERROR,
                            TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps, e, "", peticio.toString(),
                            entitatCodi, null);

            }

            log.info("Total UsuariEntitats:" + usuariEntitats.size());
            log.info("isCAR_ADMIN: " + containsRoleAdEn);
        }

        if (usuariEntitats == null) {
            usuariEntitats = new HashSet<UsuariEntitatJPA>();
        }

        if (!containsRoleAdEn && usuariEntitats.size() != 0) {
            // L'usuari " + name + " està assignat a una o varies
            // entitats però no té el rol ROLE_ADEN";
            I18NTranslation translation = new I18NTranslation("error.sensecaradmin", username);
            log.error("");
            log.error(I18NUtils.tradueix(translation));
            log.error("Authentication Info:\n" + au);
            log.error("");


            logCarpetaLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username, ESTAT_LOG_ERROR,
                        TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps, null,
                        "L´usuari "+username+" està assignat a una o varies entitats però no té el rol CAR_ADMIN", peticio.toString(), entitatCodi, null);


            // Com enviar-ho a la PAGINA WEB
            BasePreparer.loginErrorMessage.put(username, translation);

            usuariEntitats = new HashSet<UsuariEntitatJPA>();
        }

        // Seleccionam l'entitat per defecte i verificam que les entitats disponibles
        // siguin correctes
        Map<Long, EntitatJPA> entitats = new HashMap<Long, EntitatJPA>();
        Map<Long, Set<GrantedAuthority>> rolesPerEntitat = new HashMap<Long, Set<GrantedAuthority>>();
        rolesPerEntitat.put((Long) null, new HashSet<GrantedAuthority>(seyconAuthorities));
        Map<Long, UsuariEntitatJPA> usuariEntitatPerEntitatID = new HashMap<Long, UsuariEntitatJPA>();
        EntitatJPA entitatPredeterminada = null;
        for (UsuariEntitatJPA usuariEntitat : usuariEntitats) {

            EntitatJPA entitat = usuariEntitat.getEntitat();

            if (entitat == null) {
               log.warn("Entitat val null");
               continue;
            }

            Long entitatID = entitat.getEntitatID();
            if (isDebug) {
                log.debug("--------------- Entitat " + entitatID);
            }

            // Per si no n'hi ha cap per defecte
            if (entitatPredeterminada == null) {
                entitatPredeterminada = entitat;
            }
            // Cercam Rols Virtuals ROLE_ADMIN de carpeta
            if (containsRoleAdEn) {

                Set<GrantedAuthority> rolescarpeta = new TreeSet<GrantedAuthority>(GRANTEDAUTHORITYCOMPARATOR);
                rolescarpeta.addAll(seyconAuthorities);

                rolesPerEntitat.put(entitatID, rolescarpeta);
            } else {
                log.debug("No te el role seycon CAR_ADMIN");
                rolesPerEntitat.put(entitatID, new HashSet<GrantedAuthority>());
            }

            // Entitats
            entitats.put(entitatID, usuariEntitat.getEntitat());
            // Usuari Entitat
            usuariEntitat.setUsuari(usuariPersona);
            usuariEntitatPerEntitatID.put(entitatID, usuariEntitat);
        }

        log.info(" entitats.size()  => " + entitats.size());
        log.info(" containsRoleSuper  => " + containsRoleSuper);
        log.info(" !containsRoleSuper => " + !containsRoleSuper);

        if (entitats.size() == 0 && containsRoleAdEn) {

            if (usuariEntitats.size() == 0) {
                // L'usuari " + name + " no té cap entitat associada. Consulti amb
                // l'Administrador
                I18NTranslation translation = new I18NTranslation("error.senseentitat", username);
                BasePreparer.loginErrorMessage.put(username, translation);

                logCarpetaLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username, ESTAT_LOG_ERROR,
                            TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps, null,
                            "L´usuari "+username+" no té cap entitat associada. Consulti amb l´Administrador d´Entitat", peticio.toString(), "", null);

            } else {
                // Les entitats a les que pertany estan desactivades
                // "L'usuari " + name + " no té cap entitat vàlida associada");
                I18NTranslation translation = new I18NTranslation("error.senseentitatvalida", username);
                BasePreparer.loginErrorMessage.put(username, translation);
                entitatCodi = usuariEntitats.iterator().next().getEntitat().getCodi();
                logCarpetaLogicaEjb.crearLog("Autenticació al back de l'usuari:" + username, ESTAT_LOG_ERROR,
                            TIPUS_LOG_AUTENTICACIO_BACK, System.currentTimeMillis() - temps, null,
                            "Les entitats a les que pertany estan desactivades. L´usuari "+username+" no té cap entitat vàlida associada", peticio.toString(), entitatCodi, null);

            }
        }

        Long entitatIDActual = null;
        if (entitatPredeterminada != null) {
            entitatIDActual = entitatPredeterminada.getEntitatID();
            if (isDebug) {
                log.debug(">>>>>> Entitat predeterminada " + entitatIDActual);
            }
        }

        LoginInfo loginInfo;
        // create a new authentication token

        log.info("user => " + user);
        log.info("usuariPersona => " + usuariPersona);
        log.info("entitatIDActual => " + entitatIDActual);
        log.info("rolesPerEntitat  => " + rolesPerEntitat);
        log.info("usuariEntitatPerEntitatID => " + usuariEntitatPerEntitatID);
        log.info("necesitaConfigurar => " + necesitaConfigurar);

        loginInfo = new LoginInfo(username, user, usuariPersona, entitatIDActual, entitats, rolesPerEntitat,
                usuariEntitatPerEntitatID, necesitaConfigurar);

        // and set the authentication of the current Session context
        SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());

        try {
            // AUDITORIA
            if (loginInfo.getUsuariPersona() != null) {
              auditoriaEjb.crearAuditoria(TIPUS_AUDIT_ENTRADA_BACK, loginInfo.getEntitatID(), loginInfo.getUsuariPersona().getUsername(), null,
                    null);
            }
        } catch (I18NException ie) {
            log.error("S'ha produit un error creant el log");

        }

        if (isDebug) {
            log.debug(">>>>>> Final del Process d'autenticació.");
        }
        log.debug(" =================================================================");

    }

    public static final Comparator<GrantedAuthority> GRANTEDAUTHORITYCOMPARATOR = new Comparator<GrantedAuthority>() {
        @Override
        public int compare(GrantedAuthority o1, GrantedAuthority o2) {
            return -o1.getAuthority().compareTo(o2.getAuthority());
        }
    };



}
