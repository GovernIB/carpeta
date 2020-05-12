package es.caib.carpeta.commons.i18n;

/**
 * Representa un paràmetre per el formateig d'un missatge que és una etiqueta que es traduirà.
 *
 * @author anadal
 */
public class I18NArgumentCode implements I18NArgument {

    private static final long serialVersionUID = -6566772986461062754L;

    private final String code;

    /**
     * Construeix un nou argument que és una etiqueta.
     *
     * @param code etiqueta.
     */
    public I18NArgumentCode(String code) {
        super();
        this.code = code;
    }

    public String getValue() {
        return this.code;
    }

}
