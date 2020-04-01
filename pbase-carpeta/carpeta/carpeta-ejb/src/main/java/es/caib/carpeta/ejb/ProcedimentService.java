package es.caib.carpeta.ejb;

import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.persistence.Procediment;
import es.caib.carpeta.persistence.dao.DAO;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;


/**
 * Interfície del servei per gestionar {@link Procediment}
 *
 * @author areus
 * @author anadal
 */
@Local
public interface ProcedimentService extends DAO<Procediment, Long> {

    /**
     * Crea un procediment depenent d'una unitat orgànica.
     *
     * @param procediment      procediment.
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return El procediment creat.
     */
    Procediment create(Procediment procediment, Long unitatOrganicaId) throws I18NException;

    /**
     * Obté una llista de tots els procediments d'una unitat orgànica.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return llista de procediments de la unitat orgànica.
     */
    List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId);

    /**
     * Obté el nombre de procediments d'una unitat orgànica
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return nombre de procediments de la unitat orgànica
     */
    Long countAllByUnitatOrganica(Long unitatOrganicaId);

    /**
     * Obté una llista dels procediments d'una unitat orgànica que compleixen un filtre.
     * La cadena de filtre es cerca dins els camps de tipus string: codiSia i nom.
     * Si filter és <code>null</code> no aplica cap filtre.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @param filters          map on les claus són el nom d'atribut i el valor pel qual s'ha de filtrar.
     * @return llista de procediments de la unitat orgànica que compleixen el filtre.
     */
    List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId, Map<String, Object> filters);

    /**
     * Obté el nombre de procediments d'una unitat orgànica que compleixein un filtre.
     * Si filter és <code>null</code> no aplica cap filtre.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @param filters          map on les claus són el nom d'atribut i el valor pel qual s'ha de filtrar.
     * @return nombre de procediments relacionats amb la unitat orgànica que compleixen el filtre.
     */
    long countAllByUnitatOrganica(Long unitatOrganicaId, Map<String, Object> filters);

}
