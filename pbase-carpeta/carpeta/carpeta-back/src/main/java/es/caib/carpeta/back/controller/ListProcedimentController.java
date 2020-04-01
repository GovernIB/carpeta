package es.caib.carpeta.back.controller;

import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.ejb.ProcedimentService;
import es.caib.carpeta.ejb.UnitatOrganicaService;
import es.caib.carpeta.persistence.Procediment;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador per obtenir la vista dels procediments d'una unitat orgànica. El definim a l'scope de view perquè
 * a nivell de request es reconstruiria per cada petició AJAX, com ara amb els errors de validació.
 * Amb view es manté mentre no es canvii de vista.
 *
 * @author areus
 */
@Named("listProcediment")
@ViewScoped
public class ListProcedimentController implements Serializable {

    private static final long serialVersionUID = -7992474170848445700L;

    private static final Logger LOG = LoggerFactory.getLogger(ListProcedimentController.class);

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

    @EJB
    ProcedimentService procedimentService;

    // PROPIETATS + GETTERS/SETTERS

    private UnitatOrganica unitatOrganica;
    private List<Procediment> procediments;

    /**
     * Obté la unitat orgànica de la qual s'estàn llistat els procediments.
     */
    public UnitatOrganica getUnitatOrganica() {
        return unitatOrganica;
    }

    /**
     * Obté la llista de procedmients associats a la unitat orgànica
     */
    public List<Procediment> getProcediments() {
        return procediments;
    }

    /**
     * Inicialitzam el bean.
     */
    @PostConstruct
    public void init() {
        LOG.debug("init");
        unitatOrganica = new UnitatOrganica();
    }

    // ACCIONS

    /**
     * Carrega la unitat orgànica i els procediments.
     */
    public void load() {
        LOG.debug("load");
        unitatOrganica = unitatOrganicaService.findById(unitatOrganica.getId());
        procediments = procedimentService.findAllByUnitatOrganica(unitatOrganica.getId());
    }

    /**
     * Esborra el procediment l'identificador indicat. El mètode retorna void perquè no cal navegació ja que
     * l'eliminació es realitza des de la pàgina de llistat, i quedam en aquesta pàgina.
     *
     * @param id identificador de del procediment.
     */
    public void delete(Long id) {
        LOG.debug("delete");
        try {
            procedimentService.delete(id);
            context.addMessage(null, new FacesMessage(labelsBundle.getString("msg.eliminaciocorrecta")));

            // Actualitza el model de dades perquè desapareixi del llistat.
            procediments = procedimentService.findAllByUnitatOrganica(unitatOrganica.getId());
        } catch (I18NException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }
}
