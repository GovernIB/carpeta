package es.caib.carpeta.commons.utils;


/**
 * Constants emprades dins tota l'aplicació.
 *
 * @author anadal
 * @author areus
 */
public interface Constants {

    /**
     * Constant pel rol d'Administrador
     */
    String CAR_ADMIN = "CAR_ADMIN";

    /**
     * Constant pel rol d'Usuari
     */
    String CAR_USER = "CAR_USER";

    String CARPETA_PROPERTY_BASE = "es.caib.carpeta.";

    /* -------------- LOGINIB --------------*/
    String TICKET_USER_CLAVE = "ticket-user-clave";
    String TICKET_PARAM = "ticket";

    /* -------------- REGWEB3 --------------*/
    Long REGISTRO_ENTRADA = 1L;
    Long REGISTRO_SALIDA = 2L;

    // TIPO INTERESADO
    Long TIPO_INTERESADO_ADMINISTRACION = 1L;
    Long TIPO_INTERESADO_PERSONA_FISICA = 2L;
    Long TIPO_INTERESADO_PERSONA_JURIDICA = 3L;


    // TIPO DOCUMENTO IDENTIFICACION
    char TIPODOCUMENTOID_NIF = 'N';
    long TIPODOCUMENTOID_NIF_ID = 1;

    char TIPODOCUMENTOID_CIF = 'C';
    long TIPODOCUMENTOID_CIF_ID = 2;

    char TIPODOCUMENTOID_PASSAPORT = 'P';
    long TIPODOCUMENTOID_PASSAPORT_ID = 3;

    char TIPODOCUMENTOID_NIE = 'E';
    long TIPODOCUMENTOID_NIE_ID = 4;

    char TIPODOCUMENTOID_PERSONA_FISICA = 'X';
    long TIPODOCUMENTOID_PERSONA_FISICA_ID = 5;

    char TIPODOCUMENTOID_CODIGO_ORIGEN = 'O';
    long TIPODOCUMENTOID_CODIGO_ORIGEN_ID = 6;

    /* -------------- SISTRA --------------*/
    Integer SISTRA1 = 1;
    Integer SISTRA2 = 2;
}
