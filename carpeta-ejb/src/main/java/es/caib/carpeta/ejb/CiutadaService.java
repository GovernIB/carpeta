
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.CiutadaJPA;
import es.caib.carpeta.persistence.CiutadaIJPAManager;
import es.caib.carpeta.model.dao.ICiutadaManager;

@Local
public interface CiutadaService extends CiutadaIJPAManager,ICiutadaManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/CiutadaEJB!es.caib.carpeta.ejb.CiutadaService";

    public CiutadaJPA findByPrimaryKey(Long _ID_);
}
