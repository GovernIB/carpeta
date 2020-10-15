
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.TraduccioIJPAManager;
import es.caib.carpeta.jpa.TraduccioJPA;
import es.caib.carpeta.model.dao.ITraduccioManager;

import javax.ejb.Local;

@Local
public interface TraduccioLocal extends TraduccioIJPAManager,ITraduccioManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/TraduccioEJB!es.caib.carpeta.ejb.TraduccioLocal";

    public TraduccioJPA findByPrimaryKey(Long _ID_);
}
