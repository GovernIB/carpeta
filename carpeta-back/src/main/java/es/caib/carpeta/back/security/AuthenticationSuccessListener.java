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
import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.persistence.UsuariJPA;
import es.caib.carpeta.logic.AuthenticationLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
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

    protected AuthenticationLogicaService authenticationLogicaEjb;

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
            authenticationLogicaEjb = EjbManager.getAuthenticationLogicaEJB();
        } catch (I18NException e) {
            log.error(e.getMessage());
        }

        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication au = sc.getAuthentication();

        if (au == null) {
            // TODO traduccio
            peticio.append("Usuari: no definit").append("\n");
            peticio.append("classe: ").append(getClass().getName()).append("\n");
            authenticationLogicaEjb.crearLog("Autenticació al back",temps,peticio,null,"NO PUC ACCEDIR A LA INFORMACIO de AUTENTICACIO",null);

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
                authenticationLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username,temps,peticio,null,"Amb aquest navegador ja s'ha autenticat amb un altre usuari. Tanqui el navegador completament.",entitatCodi);
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

        UsuariJPA usuariPersona;

        try {
            usuariPersona = authenticationLogicaEjb.findByUsername(username);
            entitatCodi = usuariPersona.getEntitat()!=null?usuariPersona.getEntitat().getCodi():null;
        } catch (I18NException e1) {
            String msg = I18NUtils.getMessage(e1);
            log.error("Error llegint si l'usuari es troba a la BBDD: " + msg, e1);
            authenticationLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username,temps,peticio,e1,"Error llegint si l'usuari es troba a la BBDD: " + msg,entitatCodi);
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
                        persona = PluginUserInformationUtils.getUserInfoFromUserInformation(username);
                    }
                    
                    
                    

                    if (persona == null) {
                        authenticationLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username,temps,peticio,null,"No hem trobat informació de la Persona amb username  "+username+" dins del sistema de UserInformation",entitatCodi);
                        log.error("No hem trobat informació de la Persona amb username  '" + username
                                + "' dins del sistema de UserInformation");

                    } else {

                        UsuariEntitatJPA usuariEntitat = null;

                        log.info("Contains role AdminEntitat: " + containsRoleAdEn);
                        if (containsRoleAdEn) {


                            PropietatGlobalService propietatGlobalEjb;
                            try {
                                propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
                            } catch (I18NException e) {
                                // TODO traduccio
                                throw new LoginException("No puc obtenir PropietatLogicaEJB  " + e.getMessage(), e);
                            }


                            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
                            log.info("Default Entity Code => " + defaultEntityCode);

                            if (defaultEntityCode != null && defaultEntityCode.trim().length() != 0) {

                                Long defaultEntity = authenticationLogicaEjb.executeQueryOne(
                                        defaultEntityCode);

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

                        persona = authenticationLogicaEjb.crearUsuari(persona);

                        if (usuariEntitat == null) {
                            usuariPersona = persona;
                        } else {
                            usuariEntitat.setUsuariID(persona.getUsuariID());
                            usuariEntitat = authenticationLogicaEjb.create(usuariEntitat);
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
                    authenticationLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username,temps,peticio,e,"Error llegint informacio del plugin de User Information o creant l'usuari a la BBDD: "
                            + msg,entitatCodi);

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
                        authenticationLogicaEjb.findAllByUsuariIdWithEntitat(usuariPersona.getUsuariID()));
                //Obtenim el codi de l'entitat
                if(usuariEntitats.size()>0){
                    entitatCodi = usuariEntitats.iterator().next().getEntitat().getCodi();
                }
            } catch (I18NException e) {
                log.error(I18NUtils.getMessage(e), e);
                authenticationLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username,temps,peticio,e, "",entitatCodi);
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

            authenticationLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username,temps,peticio,null,
                    "L´usuari "+username+" està assignat a una o varies entitats però no té el rol CAR_ADMIN",entitatCodi);

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
                authenticationLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username,temps,peticio,null,
                        "L´usuari "+username+" no té cap entitat associada. Consulti amb l´Administrador d´Entitat",null);

            } else {
                // Les entitats a les que pertany estan desactivades
                // "L'usuari " + name + " no té cap entitat vàlida associada");
                I18NTranslation translation = new I18NTranslation("error.senseentitatvalida", username);
                BasePreparer.loginErrorMessage.put(username, translation);
                entitatCodi = usuariEntitats.iterator().next().getEntitat().getCodi();
                authenticationLogicaEjb.crearLog("Autenticació al back de l'usuari: " + username,temps,peticio,null,
                        "Les entitats a les que pertany estan desactivades. L´usuari "+username+" no té cap entitat vàlida associada",entitatCodi);
            }
        }

        Long entitatIDActual = null;
        if (entitatPredeterminada != null) {
            entitatIDActual = entitatPredeterminada.getEntitatID();
            if (isDebug) {
                log.debug(">>>>>> Entitat predeterminada " + entitatIDActual);
            }
        }
        
        // 
        Long darreraEntitatUsuari = usuariPersona.getDarreraEntitat(); 
        if (darreraEntitatUsuari != null) {
        	Set <GrantedAuthority> listaAutorizaciones = rolesPerEntitat.get(darreraEntitatUsuari);
            for (GrantedAuthority autorizacion : listaAutorizaciones) {
            	if (Constants.ROLE_ADMIN.equals(autorizacion.getAuthority())) {
            		entitatIDActual = darreraEntitatUsuari;
            		log.info("Canvi EntitatIDActual = " + darreraEntitatUsuari);
            	}
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
              authenticationLogicaEjb.crearAuditoria(loginInfo.getEntitatID(),loginInfo.getUsuariPersona().getUsername());
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
