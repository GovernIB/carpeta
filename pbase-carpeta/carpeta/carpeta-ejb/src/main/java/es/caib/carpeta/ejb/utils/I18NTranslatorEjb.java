package es.caib.carpeta.ejb.utils;

import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.commons.i18n.I18NTranslator;

import java.util.Locale;

/**
 * Clase d'utilitat per traduir missatges I18N dins el mòdul EJB
 *
 * @author anadal
 */
public class I18NTranslatorEjb {

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
