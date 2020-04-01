package es.caib.carpeta.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Bean per controlar la sessió d'usuari. S'ha de mantenir dins l'scope de sessió, i per tant cal definir-ho
 * com a serialitzable. Anar en compte a mantenir referències a objectes no serialitzables.
 * <p>
 * Quan indicam que és @Named i no posam nom, per defecte el nom del bean serà el nom de la classe començat en
 * minúscules, en aquest cas "sessionController".
 *
 * @author areus
 */
@Named
@SessionScoped
public class SessionController implements Serializable {

    private static final long serialVersionUID = -3709390221710580769L;

    private static final Logger LOG = LoggerFactory.getLogger(SessionController.class);

    @Inject
    private FacesContext context;

    // Dades de sessió de l'usuari

    private String language;

    /**
     * Obté l'idioma de l'usuari
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Fixa l'idioma de l'usuari
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    // Mètodes

    /**
     * Per defecte inialitzam el locale de l'usuari amb el locale que haurà autodectat el view d'acord amb
     * punt 2.5.2.1 de l'especificació
     */
    @PostConstruct
    private void init() {
        LOG.debug("Inicialitzant sessió");
        language = context.getViewRoot().getLocale().toLanguageTag();
    }

    /**
     * Realitza un logout i redirecciona a la pàgina principal.
     *
     * @return navegació cap a la pàgina principal
     */
    public String logout() {
        LOG.debug("logout");
        try {
            // Opcional: invalidam la sessió d'usuari
            // Aquesta passa és opcional, podriem fer logout sense invalidar la sessió d'usuari.
            // p.e. si una aplicació web combina funcionalitats per les que requereix estar autenticat i altres que
            // no, si evitam invalidar la sessió, podriem fer logout sense perdre la informacío de la sessió com
            // ara l'idioma seleccionat en el que estam navegant.
            context.getExternalContext().invalidateSession();

            // Per fer un logout cal emprar el mètode logout de HttpServletRequest. És l'única manera de evitar
            // que recarregant la pàgina es torni autenticar.
            HttpServletRequest httpServletRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            httpServletRequest.logout();

        } catch (ServletException e) {
            LOG.error("Error durant el logout", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            // Atès que farem una redirecció fixam l'objecte flash perquè guardi el missatge fins la visualització
            context.getExternalContext().getFlash().setKeepMessages(true);
        }
        return "/index?faces-redirect=true";
    }
}
