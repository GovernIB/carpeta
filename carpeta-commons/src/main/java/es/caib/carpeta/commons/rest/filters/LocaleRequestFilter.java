package es.caib.carpeta.commons.rest.filters;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static es.caib.carpeta.commons.rest.RestConstants.REQUEST_LOCALE;
import static es.caib.carpeta.commons.rest.RestConstants.SUPPORTED_LOCALES;

/**
 * Filtre per garantir que la petició és retorna amb un dels idiomes soportats, segons les preferències
 * indicades a la petició.
 *
 * @author areus
 */
@Provider
@PreMatching
@Priority(200)
public class LocaleRequestFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Context
    private HttpServletRequest servletRequest;

    @Context
    private ServletContext servletContext;

    private List<Locale> supportedLocales;

    /**
     * Inicialitza la llista de locales soportats.
     */
    @PostConstruct
    private void init() {
        String supportedLocalesParam = servletContext.getInitParameter(SUPPORTED_LOCALES);
        supportedLocales = Stream.of(supportedLocalesParam.split(","))
                .map(String::trim)
                .map(Locale::forLanguageTag)
                .collect(Collectors.toList());
    }

    /**
     * Determina el language de la petició, en primer lloc agafant el paràmetre lang, i si no, de la capçalera
     * Accept-language de la petició.
     *
     * @param request informació de context de la petició
     */
    @Override
    public void filter(ContainerRequestContext request) {

        Locale locale = supportedLocales.isEmpty() ? Locale.getDefault() : supportedLocales.get(0);

        String lang = servletRequest.getParameter("lang");
        if (lang != null && !lang.isEmpty()) {
            Locale langLocale = Locale.forLanguageTag(lang);
            if (supportedLocales.contains(langLocale)) {
                locale = langLocale;
            }
        } else {
            List<Locale> acceptableLanguages = request.getAcceptableLanguages();
            for (Locale acceptableLocale : acceptableLanguages) {
                if (supportedLocales.contains(acceptableLocale)) {
                    locale = acceptableLocale;
                    break;
                }
            }
        }
        request.getHeaders().putSingle(HttpHeaders.ACCEPT_LANGUAGE, locale.toLanguageTag());
        request.setProperty(REQUEST_LOCALE, locale);
    }

    /**
     * Comprova si la resposta té el language fixat i si no el té afegeix la capçalera Content-Language amb
     * el valor determinat al filtre d'entrada.
     *
     * @param request  informació de context de la petició
     * @param response informació de context de la resposta
     */
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) {
        if (response.getLanguage() == null) {
            Locale locale = (Locale) request.getProperty(REQUEST_LOCALE);
            response.getHeaders().putSingle(HttpHeaders.CONTENT_LANGUAGE, locale.toLanguageTag());
        }
    }
}