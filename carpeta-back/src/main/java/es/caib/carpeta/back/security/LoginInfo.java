package es.caib.carpeta.back.security;

import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.persistence.UsuariJPA;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Map;
import java.util.Set;

/**
 * Informació disponible durant el cicle de vida de l'aplicació en la Sessio
 * HTTP. Veure BasePreparer
 * 
 * @author anadal
 * 
 */
public class LoginInfo {

    protected final Logger log = Logger.getLogger(getClass());

    final User springSecurityUser;

    final String username;

    // final UsuariAplicacioJPA usuariAplicacio;

    UsuariJPA usuariPersona;

    final Map<Long, EntitatJPA> entitats;

    final Map<Long, Set<GrantedAuthority>> rolesPerEntitat;

    final Map<Long, UsuariEntitatJPA> usuariEntitatPerEntitatID;

    Long entitatIDActual;

    boolean needConfigUser;

    /**
     * @param usuari
     * @param entitatActual
     * @param roles
     */
    public LoginInfo(String username, User springSecurityUser, UsuariJPA usuariPersona, Long entitatIDActual,
            Map<Long, EntitatJPA> entitats, Map<Long, Set<GrantedAuthority>> rolesPerEntitat,
            Map<Long, UsuariEntitatJPA> usuariEntitatPerEntitatID, boolean needConfigUser) {
        this.username = username;
        log.info(" ---------- LoginInfo -------------- ");
        this.springSecurityUser = springSecurityUser;
        this.usuariPersona = usuariPersona;
        this.entitats = entitats;
        this.rolesPerEntitat = rolesPerEntitat;
        this.usuariEntitatPerEntitatID = usuariEntitatPerEntitatID;
        this.needConfigUser = needConfigUser;
//        log.info(" ---------- PRE 1 -------------- ");
        setEntitatID(entitatIDActual);
//        log.info(" ---------- POST -------------- ");

    }

    /**
     * Login per usuari aplicacio
     * 
     * @param springSecurityUser
     * @param usuariAplicacio
     * @param entitat
     * @param roles
     */
    /*
     * public LoginInfo(User springSecurityUser, Entitat entitat,
     * Collection<GrantedAuthority> roles) { this.springSecurityUser =
     * springSecurityUser;
     * 
     * Map<Long, Entitat> entitats = new HashMap<Long, Entitat>(); if (entitat !=
     * null) { entitats.put(entitat.getId(), entitat); }
     * 
     * Map<Long, Set<GrantedAuthority>> rolesPerEntitat = new HashMap<Long,
     * Set<GrantedAuthority>>(); rolesPerEntitat.put((Long) null, new
     * HashSet<GrantedAuthority>(roles)); if (entitat != null) {
     * rolesPerEntitat.put(entitat.getId(), new HashSet<GrantedAuthority>(roles)); }
     * 
     * Map<Long, UsuarioEntitat> usuariEntitatPerEntitatID = new HashMap<Long,
     * UsuarioEntitat>(); if (entitat != null) {
     * usuariEntitatPerEntitatID.put(entitat.getId(), null); }
     * 
     * this.entitats = entitats; this.rolesPerEntitat = rolesPerEntitat;
     * 
     * if (entitat != null) { setEntitatID(entitat.getId()); } else {
     * setEntitatID(null); } // Només per usuari-entitat this.usuariPersona = null;
     * this.usuariEntitatPerEntitatID = usuariEntitatPerEntitatID;
     * this.needConfigUser = false;
     * 
     * }
     */

    public UsuariJPA getUsuariPersona() {
        return usuariPersona;
    }

    public void setUsuariPersona(UsuariJPA usr) {
        this.usuariPersona = usr;
    }

    public Long getEntitatID() {
        return entitatIDActual;
    }

    /**
     * Aquest és l'únic mètode necessari per canviar d'entitat a part d'actualitzar
     * el token
     * 
     * @param novaEntitatID
     */
    public void setEntitatID(Long novaEntitatID) {
        if (entitats != null) {
            EntitatJPA novaEntitat = this.entitats.get(novaEntitatID);
            if (novaEntitat != null) {
                this.entitatIDActual = novaEntitatID;
            }
        }
    }

    public EntitatJPA getEntitat() {
        return this.entitats.get(this.entitatIDActual);
    }

    public Set<GrantedAuthority> getRoles() {
        return this.rolesPerEntitat.get(this.entitatIDActual);
    }

    public static boolean hasRole(String role) {
        return LoginInfo.getInstance().hasRoleInstance(role);
    }

    public boolean hasRoleInstance(String role) {

        Set<GrantedAuthority> rols = getRoles();
        if (rols != null) {
            for (GrantedAuthority grantedAuthority : rols) {
                if (grantedAuthority.getAuthority().equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Long getUsuariEntitatID() {
        UsuariEntitatJPA ue = getUsuariEntitat();
        if (ue == null) {
            return null;
        } else {
            return ue.getUsuariEntitatID();
        }
    }

    public UsuariEntitatJPA getUsuariEntitat() {
        return this.usuariEntitatPerEntitatID.get(this.entitatIDActual);
    }

    public Map<Long, EntitatJPA> getEntitats() {
        return entitats;
    }

    public Map<Long, Set<GrantedAuthority>> getRolesPerEntitat() {
        return rolesPerEntitat;
    }

    public Map<Long, UsuariEntitatJPA> getUsuariEntitatPerEntitatID() {
        return usuariEntitatPerEntitatID;
    }

    public boolean isNeedConfigUser() {
        return needConfigUser;
    }

    public boolean getNeedConfigUser() {
        return needConfigUser;
    }

    public void setNeedConfigUser(boolean needConfigUser) {
        this.needConfigUser = needConfigUser;
    }

    public String getLanguage() {
        return getUsuariPersona().getIdiomaID();
    }

    public String getUsername() {
        return username;
    }

    public UsernamePasswordAuthenticationToken generateToken() {
        UsernamePasswordAuthenticationToken authToken;
        Set<GrantedAuthority> roles = getRoles();
        authToken = new UsernamePasswordAuthenticationToken(this.springSecurityUser, "", roles);
        authToken.setDetails(this);
        return authToken;
    }

    public static LoginInfo getInstance() throws LoginException {
        Object obj;
        try {
            obj = SecurityContextHolder.getContext().getAuthentication().getDetails();
        } catch (Exception e) {
            // TODO traduccio
            throw new LoginException("Error intentant obtenir informació de Login.", e);
        }

        if (obj == null) {
            // TODO traduccio
            throw new LoginException("La informació de Login és buida");
        }

        if (obj instanceof LoginInfo) {
            return (LoginInfo) obj;
        } else {
            // TODO traduccio
            throw new LoginException("La informació de Login no és del tipus esperat." + " Hauria de ser de tipus "
                    + LoginInfo.class.getName() + " i és del tipus " + obj.getClass().getName());
        }
    }

}
