package es.caib.carpeta.ejb;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.interceptor.Logged;
import es.caib.carpeta.persistence.UnitatOrganica;
import es.caib.carpeta.persistence.dao.AbstractDAO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 * Servei EJB per gestionar {@link UnitatOrganica}. Li aplicam l'interceptor {@link Logged},
 * per tant, totes les cridades es loguejeran.
 *
 * @author areus
 */
@Logged
@Stateless
@RolesAllowed(Constants.CAR_ADMIN)
public class UnitatOrganicaEJB extends AbstractDAO<UnitatOrganica, Long> implements UnitatOrganicaService {

}
