
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.persistence.IdiomaJPA;
import es.caib.carpeta.persistence.IdiomaIJPAManager;
import es.caib.carpeta.model.dao.IIdiomaManager;

@Local
public interface IdiomaService extends IdiomaIJPAManager,IIdiomaManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/IdiomaEJB!es.caib.carpeta.ejb.IdiomaService";

    public IdiomaJPA findByPrimaryKey(String _ID_);
}
