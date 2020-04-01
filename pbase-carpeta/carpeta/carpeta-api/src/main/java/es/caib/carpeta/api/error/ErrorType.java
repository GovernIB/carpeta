package es.caib.carpeta.api.error;

/**
 * Tipus possibles d'error. Emprat dins {@link ErrorBean} per enviar la informació d'un
 * error del client.
 *
 * @author areus
 */
public enum ErrorType {
    /**
     * Error de les regles lògiques de l'aplicació
     */
    APLICACIO,

    /**
     * Error a la validació de paràmetres
     */
    VALIDACIO,

    /**
     * Error al format de la petició
     */
    PETICIO
}