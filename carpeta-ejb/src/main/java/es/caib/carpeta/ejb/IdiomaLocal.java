
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.IdiomaIJPAManager;
import es.caib.carpeta.jpa.IdiomaJPA;
import es.caib.carpeta.model.dao.IIdiomaManager;

import javax.ejb.Local;

@Local
public interface IdiomaLocal extends IdiomaIJPAManager,IIdiomaManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/IdiomaEJB!es.caib.carpeta.ejb.IdiomaLocal";

    public IdiomaJPA findByPrimaryKey(String _ID_);
}
