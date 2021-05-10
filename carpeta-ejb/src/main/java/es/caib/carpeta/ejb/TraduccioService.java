
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.TraduccioJPA;
import es.caib.carpeta.persistence.TraduccioIJPAManager;
import es.caib.carpeta.model.dao.ITraduccioManager;

@Local
public interface TraduccioService extends TraduccioIJPAManager,ITraduccioManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/TraduccioEJB!es.caib.carpeta.ejb.TraduccioService";

    public TraduccioJPA findByPrimaryKey(Long _ID_);
}
