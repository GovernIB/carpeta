package es.caib.carpeta.ejb;

import es.caib.carpeta.persistence.UnitatOrganica;
import es.caib.carpeta.persistence.dao.DAO;

import javax.ejb.Local;

/**
 * Interfície del servei per gestionar {@link UnitatOrganica}
 *
 * @author areus
 * @author anadal
 */
@Local
public interface UnitatOrganicaService extends DAO<UnitatOrganica, Long> {

}
