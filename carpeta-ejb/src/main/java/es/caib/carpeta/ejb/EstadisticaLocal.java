
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.EstadisticaIJPAManager;
import es.caib.carpeta.jpa.EstadisticaJPA;
import es.caib.carpeta.model.dao.IEstadisticaManager;

import javax.ejb.Local;

@Local
public interface EstadisticaLocal extends EstadisticaIJPAManager,IEstadisticaManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/EstadisticaEJB!es.caib.carpeta.ejb.EstadisticaLocal";

    public EstadisticaJPA findByPrimaryKey(Long _ID_);
}
