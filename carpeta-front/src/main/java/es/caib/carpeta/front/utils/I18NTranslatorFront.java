package es.caib.carpeta.front.utils;

import es.caib.carpeta.commons.i18n.I18NArgument;
import es.caib.carpeta.commons.i18n.I18NArgumentCode;
import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.commons.i18n.I18NTranslation;
import es.caib.carpeta.commons.i18n.MultipleResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clase d'utilitat per traduir missatges I18N dins el mòdul Front
 *
 * @author anadal
 * @author areus
 */
public final class I18NTranslatorFront {

    private static final Logger LOG = LoggerFactory.getLogger(I18NTranslatorFront.class);

    private static final Map<Locale, ResourceBundle> resourceMapping = new HashMap<>();

    public static ResourceBundle getResourceBundle(Locale locale) {
        ResourceBundle rb = resourceMapping.get(locale);
        if (rb == null) {
            FacesContext context = FacesContext.getCurrentInstance();

            // Identificadors de ResourceBundles que es troben a faces-config.xml
            List<ResourceBundle> bundles = Stream.of("ValidationMessages", "labelsPersistence", "labelsEJB", "labels")
                    .map(name -> context.getApplication().getResourceBundle(context, name))
                    .collect(Collectors.toList());

            rb = new MultipleResourceBundle(bundles);
            resourceMapping.put(locale, rb);
        }

        return rb;
    }

    public static String translate(boolean useCodeIfNotExist, String code, String... args) {
        return translate(useCodeIfNotExist ? code : null, code, args);
    }

    public static String translate(I18NTranslation translation) {
        String code = translation.getCode();
        String[] args = translateArguments(translation.getArgs());
        return translate(null, code, args);
    }

    public static String translateArguments(String code, I18NArgument... args) {
        String[] argsT = translateArguments(args);
        return translate(null, code, argsT);
    }

    public static String translate(String code) {
        return translate(null, code, (String[]) null);
    }

    public static String translate(String code, String... args) {
        return translate(null, code, args);
    }

    public static String translate(String valueIfNotExist, String code, String... args) {
        if (code == null) {
            return null;
        }

        Locale loc = getCurrentLocale();
        ResourceBundle resource = getResourceBundle(loc);

        try {
            String trans = resource.getString(code);
            if (args != null && args.length != 0) {
                trans = MessageFormat.format(trans, (Object[]) args);
            }
            return trans;

        } catch (MissingResourceException nsme) {
            if (valueIfNotExist == null) {
                LOG.error("No es pot obtenir la clau de traducció [" + code + "] per l'idioma " + loc.getLanguage()
                        + ": " + nsme.getMessage(), nsme);
                return "{" + loc.getLanguage() + "_" + code + "}";
            } else {
                return valueIfNotExist;
            }
        }
    }

    public static String translate(I18NException e) {
        I18NTranslation traduccio = e.getTraduccio();
        return translate(traduccio.getCode(), translateArguments(traduccio.getArgs()));
    }

    public static String[] translateArguments(I18NArgument[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        String[] traduits = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (args[i] instanceof I18NArgumentCode) {
                    traduits[i] = translate(args[i].getValue(), (String[]) null);
                } else {
                    traduits[i] = args[i].getValue();
                }
            }
        }
        return traduits;
    }

    public static Locale getCurrentLocale() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot viewRoot = facesContext.getViewRoot();
        return viewRoot.getLocale();
    }
}
