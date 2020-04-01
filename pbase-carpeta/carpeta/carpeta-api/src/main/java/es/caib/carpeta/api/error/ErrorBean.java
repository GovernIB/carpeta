package es.caib.carpeta.api.error;

/**
 * Bean per modelar un error de client a l'API REST
 *
 * @author areus
 */
public class ErrorBean {

    /**
     * Missatge d'error
     */
    private String message;

    /**
     * Tipus d'error
     */
    private ErrorType type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorType getType() {
        return type;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }
}
