package es.caib.carpeta.commons.utils;


/**
 * Constants emprades dins tota l'aplicació.
 *
 * @author anadal
 * @author areus
 */
public interface Constants {

    String CARPETA_PROPERTY_BASE = "es.caib.carpeta.";

    /* -------------- ROLS REALS--------------*/
    String CAR_SUPER = "CAR_SUPER";
    String CAR_ADMIN = "CAR_ADMIN";
    
    /* -------------- ROLS VIRTUALS --------------*/
    String ROLE_SUPER = "ROLE_SUPER";
    String ROLE_ADMIN = "ROLE_ADMIN";


    /* -------------- IDIOMAS --------------*/
    String DEFAULT_LANGUAGE = "ca";


    /* -------------- LOGINIB --------------*/
    String TICKET_USER_CLAVE = "ticket-user-clave";
    String TICKET_PARAM = "ticket";

    /* -------------- SISTRA --------------*/
    Integer SISTRA1 = 1;
    Integer SISTRA2 = 2;
    
    
    /*   TIPUS DE PLUGINS     */
    public static final int PLUGIN_TIPUS_FRONT = 1;
    
    
    /* Tipus Enllaç */
    public static final int TIPUS_ENLLAZ_FRONT_XARXA_SOCIAL = 1;
    public static final int TIPUS_ENLLAZ_FRONT_LATERAL = 2;
    public static final int TIPUS_ENLLAZ_FRONT_PEU_CENTRAL = 3;
    public static final int TIPUS_ENLLAZ_FRONT_PLUGIN = 4;
    
    public static final int[] TIPUS_ENLLAZ_ALL = {
            TIPUS_ENLLAZ_FRONT_XARXA_SOCIAL,
            TIPUS_ENLLAZ_FRONT_LATERAL,
            TIPUS_ENLLAZ_FRONT_PEU_CENTRAL,
            TIPUS_ENLLAZ_FRONT_PLUGIN
    };

}
