
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.FitxerIJPAManager;
import es.caib.carpeta.jpa.FitxerJPA;
import es.caib.carpeta.model.dao.IFitxerManager;

import javax.ejb.Local;

@Local
public interface FitxerLocal extends FitxerIJPAManager,IFitxerManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/FitxerEJB!es.caib.carpeta.ejb.FitxerLocal";

    public FitxerJPA findByPrimaryKey(Long _ID_);
}
