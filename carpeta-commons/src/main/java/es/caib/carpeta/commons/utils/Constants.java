package es.caib.carpeta.commons.utils;


/**
 * Constants emprades dins tota l'aplicació.
 *
 * @author anadal
 * @author areus
 */
public interface Constants {

    String CARPETA_PROPERTY_BASE = "es.caib.carpeta.";
    String CARPETA_PROPERTY_LOGINIB = CARPETA_PROPERTY_BASE + "loginib.";

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
    
    /* Tipus Avis */
    public static final int TIPUS_AVIS_FRONT_ABANS_LOGIN = 1;
    public static final int TIPUS_AVIS_FRONT_DESPRES_LOGIN = 2;
    public static final int TIPUS_AVIS_FRONT_PLUGIN = 3;
    public static final int TIPUS_AVIS_BACK_ENTITAT = 4;
    public static final int TIPUS_AVIS_BACK = 5;
    
    public static final int[] TIPUS_AVIS_ALL = {
            TIPUS_AVIS_FRONT_ABANS_LOGIN,
            TIPUS_AVIS_FRONT_DESPRES_LOGIN,
            TIPUS_AVIS_FRONT_PLUGIN,
            TIPUS_AVIS_BACK_ENTITAT,
            TIPUS_AVIS_BACK
    };
    
    /* Gravetat Avis */
    public static final int GRAVETAT_AVIS_INFO = 1;
    public static final int GRAVETAT_AVIS_WARNING = 2;
    public static final int GRAVETAT_AVIS_ERROR = 3;
    
    public static final int[] GRAVETAT_AVIS_ALL = {
            GRAVETAT_AVIS_INFO,
            GRAVETAT_AVIS_WARNING,
            GRAVETAT_AVIS_ERROR
    };
    
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


    /* Tipus LogCarpeta */
    public static final int TIPUS_LOG_PLUGIN_FRONT = 1;
    public static final int TIPUS_LOG_AUTENTICACIO_BACK = 2;


    public static final int[] TIPUS_LOG_ALL = {
       TIPUS_LOG_PLUGIN_FRONT,
       TIPUS_LOG_AUTENTICACIO_BACK
    };

    /* Estat LogCarpeta */
    public static final int ESTAT_LOG_OK = 1;
    public static final int ESTAT_LOG_ERROR = 2;


    public static final int[] ESTAT_LOG_ALL = {
       ESTAT_LOG_OK,
       ESTAT_LOG_ERROR
    };

}
