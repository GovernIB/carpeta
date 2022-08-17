
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.FitxerJPA;
import es.caib.carpeta.persistence.FitxerIJPAManager;
import es.caib.carpeta.model.dao.IFitxerManager;

import es.caib.carpeta.model.entity.Fitxer;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FitxerService extends FitxerIJPAManager,IFitxerManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/FitxerEJB!es.caib.carpeta.ejb.FitxerService";

    public FitxerJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Fitxer instance, FitxerService fitxerEjb) throws I18NException;
}
