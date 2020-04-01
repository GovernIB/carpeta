package es.caib.carpeta.commons.i18n;

/**
 * Representa un paràmetre per el formateig d'un missatge que és un missatge final.
 *
 * @author anadal
 */
public class I18NArgumentString implements I18NArgument {

    private static final long serialVersionUID = 2475236632433945131L;

    private final String message;

    /**
     * Construeix un nou argument que és un missatge final.
     *
     * @param message
     */
    public I18NArgumentString(String message) {
        super();
        this.message = message;
    }

    public String getValue() {
        return this.message;
    }
}
