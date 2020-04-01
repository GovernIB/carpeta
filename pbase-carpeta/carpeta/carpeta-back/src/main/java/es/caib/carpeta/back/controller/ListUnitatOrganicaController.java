package es.caib.carpeta.back.controller;

import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.commons.query.OrderBy;
import es.caib.carpeta.commons.query.OrderType;
import es.caib.carpeta.ejb.UnitatOrganicaService;
import es.caib.carpeta.persistence.UnitatOrganica;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controlador pels llistats d'Unitats Organiques. El definim a l'scope de view perquè a nivell de request es
 * reconstruiria per cada petició AJAX, com ara amb la paginació. Amb view es manté mentre no es canvii de vista.
 *
 * @author areus
 */
@Named("listUnitatOrganica")
@ViewScoped
public class ListUnitatOrganicaController implements Serializable {

    private static final long serialVersionUID = -6015369276336087696L;

    private static final Logger LOG = LoggerFactory.getLogger(ListUnitatOrganicaController.class);

    @Inject
    private FacesContext context;

    /**
     * Injecta el bundle definit dins faces-config.xml amb var = labels.
     */
    @Inject
    @ManagedProperty("#{labels}")
    private ResourceBundle labelsBundle;

    @EJB
    private UnitatOrganicaService unitatOrganicaService;

    // PROPIETATS + GETTERS/SETTERS

    /**
     * Model de dades emprat pel compoment dataTable de primefaces.
     */
    private LazyDataModel<UnitatOrganica> lazyModel;

    public LazyDataModel<UnitatOrganica> getLazyModel() {
        return lazyModel;
    }

    /**
     * Inicialitzam el bean amb les dades inicials.
     */
    @PostConstruct
    public void init() {
        LOG.debug("init");

        lazyModel = new LazyDataModel<>() {

            private static final long serialVersionUID = -1420774197768250112L;

            /*
            Primefaces cridarà automàticament aquest mètode quan necessita actualitzar les dades del dataTable
            per qualsevol circumstància (filtres, ordenació, canvi de pàgina ...)
            */
            @Override
            public List<UnitatOrganica> load(int first, int pageSize, List<SortMeta> multiSortMeta,
                                             Map<String, Object> filters) {
                try {
                    // Es necessari indicar el nombre de registres cada vegada que es carrega el model per si ha variat
                    setRowCount((int) unitatOrganicaService.countFiltered(filters));

                    // Transformam les classes específiques de Primefaces per representar l'ordenació cap a la nostra
                    // implementació amb la que funciona a la capa de serveis.
                    OrderBy[] orderByArray = new OrderBy[0];
                    if (multiSortMeta != null && !multiSortMeta.isEmpty()) {
                        List<OrderBy> orderByList = new ArrayList<>(multiSortMeta.size());
                        for (SortMeta sortMeta : multiSortMeta) {
                            OrderType orderType = (sortMeta.getSortOrder() == SortOrder.ASCENDING) ?
                                    OrderType.ASC : OrderType.DESC;
                            orderByList.add(new OrderBy(sortMeta.getSortField(), orderType));
                        }
                        orderByArray = orderByList.toArray(new OrderBy[0]);
                    }

                    return unitatOrganicaService.findFiltered(filters, first, pageSize, orderByArray);

                } catch (I18NException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    // ACCIONS

    /**
     * Esborra l'unitat orgànica amb l'identificador indicat. El mètode retorna void perquè no cal navegació ja que
     * l'eliminació es realitza des de la pàgina de llistat, i quedam en aquesta pàgina.
     *
     * @param id identificador de l'unitat orgànica
     */
    public void delete(Long id) {
        LOG.debug("delete");
        try {
            unitatOrganicaService.delete(id);
            context.addMessage(null, new FacesMessage(labelsBundle.getString("msg.eliminaciocorrecta")));

            // No cal actualitzar el model perquè no aparegui el registre eliminat perquè primefaces cridarà
            // automàticament el load del lazyDataModel en refrescar el component del datatable.

        } catch (I18NException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
    }
}
