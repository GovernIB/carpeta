
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.FitxerJPA;
import es.caib.carpeta.persistence.FitxerIJPAManager;
import es.caib.carpeta.model.dao.IFitxerManager;

@Local
public interface FitxerService extends FitxerIJPAManager,IFitxerManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/FitxerEJB!es.caib.carpeta.ejb.FitxerService";

    public FitxerJPA findByPrimaryKey(Long _ID_);
}
