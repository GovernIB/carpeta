package es.caib.carpeta.commons.utils;


import java.util.HashMap;
import java.util.Map;

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
    public static final int TIPUS_ENLLAZ_FRONT_PSEUDOPLUGIN = 4;
    public static final int TIPUS_ENLLAZ_FRONT_MENU_BARRA = 5;
    public static final int TIPUS_ENLLAZ_FRONT_MENU_DESLLISANT = 6;

    public static final int[] TIPUS_ENLLAZ_ALL = {
            TIPUS_ENLLAZ_FRONT_XARXA_SOCIAL,
            TIPUS_ENLLAZ_FRONT_LATERAL,
            TIPUS_ENLLAZ_FRONT_PEU_CENTRAL,
            TIPUS_ENLLAZ_FRONT_PSEUDOPLUGIN,
            TIPUS_ENLLAZ_FRONT_MENU_BARRA,
            TIPUS_ENLLAZ_FRONT_MENU_DESLLISANT
    };


    /* Tipus LogCarpeta */
    int TIPUS_LOG_PLUGIN_FRONT = 1;
    int TIPUS_LOG_AUTENTICACIO_BACK = 2;
    int TIPUS_LOG_AUTENTICACIO_FRONT = 3;


    int[] TIPUS_LOG_ALL = {
       TIPUS_LOG_PLUGIN_FRONT,
       TIPUS_LOG_AUTENTICACIO_BACK,
       TIPUS_LOG_AUTENTICACIO_FRONT
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
    public static final int TIPUS_ESTAD_ENTRADA_BACK = 1;
    public static final int TIPUS_ESTAD_ENTRADA_FRONT_NO_AUTENTICAT = 2;
    public static final int TIPUS_ESTAD_ENTRADA_FRONT_AUTENTICAT = 3;
    public static final int TIPUS_ESTAD_ACCES_PLUGIN = 4;


    public static final int[] TIPUS_ESTADISTICA_ALL = {
       TIPUS_ESTAD_ENTRADA_BACK,
       TIPUS_ESTAD_ENTRADA_FRONT_NO_AUTENTICAT,
       TIPUS_ESTAD_ENTRADA_FRONT_AUTENTICAT,
       TIPUS_ESTAD_ACCES_PLUGIN
    };


    /* Tipus Auditoria */
    public static final int TIPUS_AUDIT_ENTRADA_BACK = 1;
    public static final int TIPUS_AUDIT_ENTRADA_FRONT_NO_AUTENTICAT = 2;
    public static final int TIPUS_AUDIT_ENTRADA_FRONT_AUTENTICAT = 3;
    public static final int TIPUS_AUDIT_ACCES_PLUGIN = 4;
    public static final int TIPUS_AUDIT_AFEGIR_PROPGLOB = 5;
    public static final int TIPUS_AUDIT_AFEGIR_PLUGIN = 6;



    public static final int[] TIPUS_AUDITORIA_ALL = {
            TIPUS_AUDIT_ENTRADA_BACK,
            TIPUS_AUDIT_ENTRADA_FRONT_NO_AUTENTICAT,
            TIPUS_AUDIT_ENTRADA_FRONT_AUTENTICAT,
            TIPUS_AUDIT_ACCES_PLUGIN,
            TIPUS_AUDIT_AFEGIR_PROPGLOB,
            TIPUS_AUDIT_AFEGIR_PLUGIN
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


    /* -------- TIPO DOCUMENTO ---------- */



    String MIME_JPG  = "image/jpeg";
    String MIME_XML1  = "text/xml";
    String MIME_XML2  = "application/xml";
    String MIME_PDF  = "application/pdf";
    String MIME_PNG  = "image/png";
    String MIME_RTF  = "text/rtf";
    String MIME_RTF2  = "application/rtf";
    String MIME_SVG  = "image/svg+xml";
    String MIME_TIFF = "image/tiff";
    String MIME_TXT  = "text/plain";
    String MIME_ODT  = "application/vnd.oasis.opendocument.text";
    String MIME_XLSX  = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    String MIME_PPTX  = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
    String MIME_ODP  = "application/vnd.oasis.opendocument.presentation";
    String MIME_ODS  = "application/vnd.oasis.opendocument.spreadsheet";
    String MIME_ODG  = "application/vnd.oasis.opendocument.graphics";
    String MIME_DOCX  = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    String MIME_HTML  = "text/html";
    String MIME_DEFAULT  = "application/octet-stream";





    Map<String, String> TEXTO_REDUCIDO_BY_TIPO_MIME = new HashMap<String, String>() {{
        put(MIME_JPG, "JPG");
        put(MIME_XML1, "XML");
        put(MIME_XML2, "XML");
        put(MIME_PDF, "PDF");
        put(MIME_PNG, "PNG");
        put(MIME_RTF, "RTF");
        put(MIME_RTF2, "RTF2");
        put(MIME_SVG, "SVG");
        put(MIME_TIFF, "TIFF");
        put(MIME_TXT, "TXT");
        put(MIME_ODT, "ODT");
        put(MIME_XLSX, "XLSX(EXCEL)");
        put(MIME_PPTX, "PPTX");
        put(MIME_ODP, "ODP");
        put(MIME_ODS, "ODS");
        put(MIME_ODG, "ODG");
        put(MIME_DOCX, "DOCX");
        put(MIME_HTML, "HTML");
        put(MIME_DEFAULT, "OCTET-STREAM");
    }};


}
