
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.EstadisticaJPA;
import es.caib.carpeta.persistence.EstadisticaIJPAManager;
import es.caib.carpeta.model.dao.IEstadisticaManager;

@Local
public interface EstadisticaService extends EstadisticaIJPAManager,IEstadisticaManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/EstadisticaEJB!es.caib.carpeta.ejb.EstadisticaService";

    public EstadisticaJPA findByPrimaryKey(Long _ID_);
}
