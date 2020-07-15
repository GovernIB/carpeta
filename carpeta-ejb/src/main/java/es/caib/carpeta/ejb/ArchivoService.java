package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.persistence.Archivo;
import es.caib.carpeta.persistence.dao.DAO;


/**
 * 
 */
@Local
public interface ArchivoService extends DAO<Archivo, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/ArchivoEJB!es.caib.carpeta.ejb.ArchivoService";

}

