
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.persistence.PropietatGlobalJPA;
import es.caib.carpeta.persistence.PropietatGlobalIJPAManager;
import es.caib.carpeta.model.dao.IPropietatGlobalManager;

@Local
public interface PropietatGlobalLocal extends PropietatGlobalIJPAManager,IPropietatGlobalManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/PropietatGlobalEJB!es.caib.carpeta.ejb.PropietatGlobalLocal";

    public PropietatGlobalJPA findByPrimaryKey(Long _ID_);
}
