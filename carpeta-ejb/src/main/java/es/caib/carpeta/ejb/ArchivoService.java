package es.caib.carpeta.ejb;

import es.caib.carpeta.persistence.Archivo;
import es.caib.carpeta.persistence.dao.DAO;

import javax.ejb.Local;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 11/06/2020
 */

@Local
public interface ArchivoService extends DAO<Archivo, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/ArchivoEJB!es.caib.carpeta.ejb.ArchivoService";
}
