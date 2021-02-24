package es.caib.carpeta.commons.rest.error;

/**
 * Bean per modelar un error de client a l'API REST
 *
 * @author areus
 */
public class ErrorBean {

    private final String message;
    private final ErrorType type;

    private ErrorBean(String message, ErrorType type) {
        this.message = message;
        this.type = type;
    }

    public static ErrorBean errorPeticio(String message) {
        return new ErrorBean(message, ErrorType.PETICIO);
    }

    public static ErrorBean errorValidacio(String message) {
        return new ErrorBean(message, ErrorType.VALIDACIO);
    }

    public static ErrorBean errorAplicacio(String message) {
        return new ErrorBean(message, ErrorType.APLICACIO);
    }

    public String getMessage() {
        return message;
    }

    public ErrorType getType() {
        return type;
    }
}
