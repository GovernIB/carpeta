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

    
    // EJB HIGH LEVEL ROLES
    public static final String ROLE_EJB_FULL_ACCESS  = CAR_SUPER;
    public static final String ROLE_EJB_BASIC_ACCESS = CAR_ADMIN;
    
    
    
    /* -------------- IDIOMES --------------*/
    String IDIOMA_CATALA = "ca";
    String IDIOMA_CASTELLA = "es";
    String IDIOMA_ANGLES = "en";



    /* -------------- LOGINIB --------------*/
    String TICKET_USER_CLAVE = "ticket-user-clave";
    String TICKET_PARAM = "ticket";

    /* -------------- SISTRA --------------*/
    Integer SISTRA1 = 1;
    Integer SISTRA2 = 2;
    
    
    /*   TIPUS DE PLUGINS     */
    public static final int PLUGIN_TIPUS_FRONT_PRIVAT = 1;
    public static final int PLUGIN_TIPUS_FRONT_PUBLIC = 2;
    public static final int PLUGIN_TIPUS_FRONT_PUBLIC_I_PRIVAT = 3;
    
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
    public static final int TIPUS_ENLLAZ_FRONT_PSEUDOPLUGIN = 4;
    public static final int TIPUS_ENLLAZ_FRONT_MENU_BARRA = 5;
    public static final int TIPUS_ENLLAZ_FRONT_MENU_DESLLISANT = 6;
    public static final int TIPUS_ENLLAZ_FRONT_EN_UN_CLIC = 7;

    public static final int[] TIPUS_ENLLAZ_ALL = {
            TIPUS_ENLLAZ_FRONT_XARXA_SOCIAL,
            TIPUS_ENLLAZ_FRONT_LATERAL,
            TIPUS_ENLLAZ_FRONT_PEU_CENTRAL,
            TIPUS_ENLLAZ_FRONT_PSEUDOPLUGIN,
            TIPUS_ENLLAZ_FRONT_MENU_BARRA,
            TIPUS_ENLLAZ_FRONT_MENU_DESLLISANT,
            TIPUS_ENLLAZ_FRONT_EN_UN_CLIC
    };


    /* Tipus LogCarpeta */
    int TIPUS_LOG_PLUGIN_FRONT = 1;
    int TIPUS_LOG_AUTENTICACIO_BACK = 2;
    int TIPUS_LOG_AUTENTICACIO_FRONT = 3;
    int TIPUS_LOG_GESTIO_BACK = 4;


    int[] TIPUS_LOG_ALL = {
       TIPUS_LOG_PLUGIN_FRONT,
       TIPUS_LOG_AUTENTICACIO_BACK,
       TIPUS_LOG_AUTENTICACIO_FRONT,
       TIPUS_LOG_GESTIO_BACK
    };

    /* Estat LogCarpeta */
    int ESTAT_LOG_UNKNOWN = 0;
    int ESTAT_LOG_OK = 1;
    int ESTAT_LOG_ERROR = 2;



     int[] ESTAT_LOG_ALL = {
       ESTAT_LOG_OK,
       ESTAT_LOG_ERROR,
       ESTAT_LOG_UNKNOWN
    };


    /* Tipus Estadística */
    public static final int TIPUS_ESTADISTICA_ENTRADA_BACK = 1;
    public static final int TIPUS_ESTADISTICA_ENTRADA_FRONT_NO_AUTENTICAT = 2;
    public static final int TIPUS_ESTADISTICA_ENTRADA_FRONT_AUTENTICAT = 3;
    public static final int TIPUS_ESTADISTICA_ACCES_PLUGIN = 4;
    public static final int TIPUS_ESTADISTICA_ENVIADA_NOTIFICACIO_MOBIL = 5;


    public static final int[] TIPUS_ESTADISTICA_ALL = {
       TIPUS_ESTADISTICA_ENTRADA_BACK,
       TIPUS_ESTADISTICA_ENTRADA_FRONT_NO_AUTENTICAT,
       TIPUS_ESTADISTICA_ENTRADA_FRONT_AUTENTICAT,
       TIPUS_ESTADISTICA_ACCES_PLUGIN,
       TIPUS_ESTADISTICA_ENVIADA_NOTIFICACIO_MOBIL
    };


    /* Tipus Auditoria */
    public static final int TIPUS_AUDIT_ENTRADA_BACK = 1;
    public static final int TIPUS_AUDIT_ENTRADA_FRONT_NO_AUTENTICAT = 2;
    public static final int TIPUS_AUDIT_ENTRADA_FRONT_AUTENTICAT = 3;
    public static final int TIPUS_AUDIT_ACCES_PLUGIN = 4;
    public static final int TIPUS_AUDIT_AFEGIR_PROPGLOB = 5;
    public static final int TIPUS_AUDIT_AFEGIR_PLUGIN = 6;
    public static final int TIPUS_AUDIT_AFEGIR_PROPENT = 7;
    public static final int TIPUS_AUDIT_ELIMINAT_PLUGIN = 8;
    public static final int TIPUS_AUDIT_ELIMINAT_PROPGLOB = 9;
    public static final int TIPUS_AUDIT_ELIMINAT_PROPENT = 10;




    public static final int[] TIPUS_AUDITORIA_ALL = {
            TIPUS_AUDIT_ENTRADA_BACK,
            TIPUS_AUDIT_ENTRADA_FRONT_NO_AUTENTICAT,
            TIPUS_AUDIT_ENTRADA_FRONT_AUTENTICAT,
            TIPUS_AUDIT_ACCES_PLUGIN,
            TIPUS_AUDIT_AFEGIR_PROPGLOB,
            TIPUS_AUDIT_AFEGIR_PLUGIN,
            TIPUS_AUDIT_AFEGIR_PROPENT,
            TIPUS_AUDIT_ELIMINAT_PLUGIN,
            TIPUS_AUDIT_ELIMINAT_PROPGLOB,
            TIPUS_AUDIT_ELIMINAT_PROPENT

    };


    /* Tipus Dades d'Ajuda */
    public static final int TIPUS_SUPORT_WEB = 1;
    public static final int TIPUS_SUPORT_TELEFON = 2;
    public static final int TIPUS_SUPORT_MAIL = 3;
    public static final int TIPUS_SUPORT_FAQ = 4;
    public static final int TIPUS_SUPORT_CONSULTA_TECNICA = 5;
    public static final int TIPUS_SUPORT_AUTENTICACIO = 6;



    public static final int[] TIPUS_SUPORT_ALL = {
            TIPUS_SUPORT_WEB,
            TIPUS_SUPORT_TELEFON,
            TIPUS_SUPORT_MAIL,
            TIPUS_SUPORT_FAQ,
            TIPUS_SUPORT_CONSULTA_TECNICA,
            TIPUS_SUPORT_AUTENTICACIO
    };



    /* Tipus Accessos */
    public static final int TIPUS_ACCES_LOGIN_AUTENTICAT = 1;
    public static final int TIPUS_ACCES_LOGIN_NO_AUTENTICAT = 2;
    public static final int TIPUS_ACCES_PLUGIN = 3;




    public static final int[] TIPUS_ACCES_ALL = {
            TIPUS_ACCES_LOGIN_AUTENTICAT,
            TIPUS_ACCES_LOGIN_NO_AUTENTICAT,
            TIPUS_ACCES_PLUGIN

    };


}
