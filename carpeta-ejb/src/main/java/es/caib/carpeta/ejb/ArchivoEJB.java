package es.caib.carpeta.ejb;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.interceptor.Logged;
import es.caib.carpeta.persistence.Archivo;
import es.caib.carpeta.persistence.dao.AbstractDAO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 11/06/2020
 */

@Logged
@Stateless
@RolesAllowed(Constants.CAR_ADMIN)
public class ArchivoEJB extends AbstractDAO<Archivo, Long> implements ArchivoService {
}
