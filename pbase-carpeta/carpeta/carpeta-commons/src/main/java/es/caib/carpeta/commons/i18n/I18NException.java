package es.caib.carpeta.commons.i18n;

import javax.ejb.ApplicationException;

/**
 * Reprsenta una excepció d'aplicació amb un missatge traduible.
 * Es marca amb amb l'annotació d'excepció d'aplicació i fixant el rollback perquè quan l'excepció s'empri
 * dins la capa d'EJBs volem que si es llança es produeix un rollback de la transacció.
 *
 * @author anadal
 */
@ApplicationException(rollback = true)
public class I18NException extends Exception {

    private static final long serialVersionUID = 125634754654675478L;

    private final I18NTranslation traduccio;

    public I18NTranslation getTraduccio() {
        return this.traduccio;
    }

    /**
     * Crea una nova excepció amb una traducció.
     *
     * @param traduccio Traducció amb la que es generarà el missatge de l'excepció.
     */
    public I18NException(I18NTranslation traduccio) {
        super(traduccio.getCode());
        this.traduccio = traduccio;
    }

    /**
     * Crea una nova traducció amb una etiqueta i uns arguments per generar una traducció, així com l'excepció origen.
     *
     * @param cause excepció origen
     * @param code  etiqueta del missatge de l'excepció
     * @param args  arguments a emprar amb l'etiqueta
     */
    public I18NException(Throwable cause, String code, I18NArgument... args) {
        super(code, cause);
        this.traduccio = new I18NTranslation(code, args);
    }

    /**
     * Crea una nova traducció amb una etiqueta i uns arguments per generar una traducció.
     *
     * @param code etiqueta del missatge de l'excepció
     * @param args arguments a emprar amb l'etiqueta
     */
    public I18NException(String code, I18NArgument... args) {
        super(code);
        this.traduccio = new I18NTranslation(code, args);
    }

    /**
     * Crea una nova traducció amb una etiqueta i uns arguments per generar una traducció.
     *
     * @param code etiqueta del missatge de l'excepció
     * @param args arguments a emprar amb l'etiqueta
     */
    public I18NException(String code, String... args) {
        super(code);
        this.traduccio = new I18NTranslation(code, args);
    }

    /**
     * Crea una nova traducció amb una etiqueta.
     *
     * @param code etiqueta del missatge de l'excepció
     */
    public I18NException(String code) {
        super(code);
        this.traduccio = new I18NTranslation(code);
    }
}
