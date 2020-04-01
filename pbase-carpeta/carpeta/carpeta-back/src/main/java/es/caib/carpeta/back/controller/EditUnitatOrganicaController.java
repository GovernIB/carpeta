package es.caib.carpeta.back.controller;

import es.caib.carpeta.back.utils.I18NTranslatorBack;
import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.ejb.UnitatOrganicaService;
import es.caib.carpeta.persistence.UnitatOrganica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Controlador per l'edició d'Unitats Organiques. El definim a l'scope de view perquè a nivell
 * de request es reconstruiria per cada petició AJAX, com ara amb els errors de validació. Amb
 * view es manté mentre no es canvii de vista.
 *
 * @author areus
 */
@Named("editUnitatOrganica")
@ViewScoped
public class EditUnitatOrganicaController implements Serializable {

    private static final long serialVersionUID = -4092311228270716321L;

    private static final Logger LOG = LoggerFactory.getLogger(EditUnitatOrganicaController.class);

    @Inject
    private FacesContext context;

    /**
     * Injecta el bundle definit dins faces-config.xml amb var = labels.
     */
    @Inject
    @ManagedProperty("#{labels}")
    private ResourceBundle labelsBundle;

    @EJB
    UnitatOrganicaService unitatOrganicaService;

    // PROPIETATS + GETTERS/SETTERS

    private UnitatOrganica current;

    /**
     * Obté la unitat orgànica que s'està editant
     */
    public UnitatOrganica getCurrent() {
        return current;
    }

    /**
     * Indica si és una creació o una actualització segons s'hagi fixat o no l'id de la unitat
     * orgànica.
     *
     * @return <code>true</code> si l'id és null, i per tant és una creació, <code>false</code>
     * en cas contrari.
     */
    public boolean isCreate() {
        return current.getId() == null;
    }

    /**
     * Inicialitzam el bean amb les dades inicials.
     */
    @PostConstruct
    public void init() {
        LOG.debug("init");
        current = new UnitatOrganica();
    }

    // ACCIONS

    /**
     * Carrega la unitat orgànica per editar.
     */
    public void load() {
        LOG.debug("load");
        if (current.getId() != null) {
            current = unitatOrganicaService.findById(current.getId());
        }
    }

    /**
     * Crea o actualitza la unitat orgànica que s'està editant. Afegeix un missatge si s'ha fet
     * amb èxit i redirecciona cap a la pàgina de llistat.
     *
     * @return navegació cap al llistat d'unitats orgàniques.
     */
    public String saveOrUpdate() {
        LOG.debug("saveOrUpdate");
        try {
            // Feim una creació o una actualització.
            if (isCreate()) {
                unitatOrganicaService.create(current);
                context.addMessage(null, new FacesMessage(labelsBundle.getString("msg.creaciocorrecta")));
            } else {
                unitatOrganicaService.update(current);
                context.addMessage(null, new FacesMessage(labelsBundle.getString("msg.actualitzaciocorrecta")));
            }

            // Els missatges no aguanten una redirecció ja que no es la mateixa petició
            // amb l'objecte flash podem assegurar que es guardin fins la visualització
            context.getExternalContext().getFlash().setKeepMessages(true);

            // Redireccionam cap al llistat d'unitats orgàniques
            return "/listUnitatOrganica?faces-redirect=true";

        } catch (I18NException i18ne) {
            String msgError = I18NTranslatorBack.translate(i18ne);
            LOG.error("Error saveOrUpdate: " + msgError);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgError, null));

            // si ha donat un error la lògica de negoci, ens mantenim a la pàgina
            return null;
        }
    }
}
