package es.caib.carpeta.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Proporciona les opcions d'idioma. Atès que són les mateixes sempre per tots els usuaris
 * ho ficam com un bean a l'scope d'aplicació perquè només es carregui una vegada.
 * <p>
 * Quan indicam que és @Named i no posam nom, per defecte el nom del bean serà el nom de la classe començat en
 * minúscules, en aquest cas "languageController".
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class LanguageController {

    private static final Logger LOG = LoggerFactory.getLogger(LanguageController.class);

    @Inject
    private FacesContext context;

    private List<String> languages;

    /**
     * Obté la llista d'idiomes disponibles en format String. El format és IETF BCP 47
     * que és el retornat per {@link Locale#toLanguageTag()} i és el format que
     * s'empra dins HTML per representar l'atribut lang.
     */
    public List<String> getLanguages() {
        return languages;
    }

    /**
     * Dins l'inialització és quan carregam la llista d'idiomes.
     */
    @PostConstruct
    private void init() {
        LOG.info("Inicialitzat idiomes disponibles");
        languages = new ArrayList<>();

        // Copiam tots els elements d'un iterator, tranformant de Locale a String
        context.getApplication().getSupportedLocales()
                .forEachRemaining(locale -> languages.add(locale.toLanguageTag()));

        /* és equivament al següent codi: */
        /*
        Iterator<Locale> iterator = context.getApplication().getSupportedLocales();
        while (iterator.hasNext()) {
            Locale locale = iterator.next();
            languages.add(locale.toLanguageTag());
        }
        */

        LOG.info("Idiomes disponibles: " + languages);
    }
}
