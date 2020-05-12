package es.caib.carpeta.api.utils;

import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.commons.i18n.I18NTranslator;

import java.util.Locale;

/**
 * Clase d'utilitat per traduir missatges I18N dins el mòdul API REST.
 *
 * @author anadal
 * @author areus
 */
public class I18NTranslatorRest {

    /**
     * Classe en la que es delega la traducció. S'inicialitza amb tots els recursos que s'empraran per cercar les claus.
     */
    public static final I18NTranslator translator = new I18NTranslator(
            new String[]{"ValidationMessages", "persistence.LabelsPersistence", "ejb.LabelsEJB"});

    public static String translate(boolean useCodeIfNotExist, Locale loc, String code, String... args) {
        return translator.translate(useCodeIfNotExist, loc, code, args);
    }

    public static String translate(Locale loc, String code, String... args) {
        return translator.translate(loc, code, args);
    }

    public static String translate(String valueIfNotExist, Locale loc, String code, String... args) {
        return translator.translate(valueIfNotExist, loc, code, args);
    }

    public static String translate(I18NException e, Locale locale) {
        return translator.translate(e, locale);
    }
}
