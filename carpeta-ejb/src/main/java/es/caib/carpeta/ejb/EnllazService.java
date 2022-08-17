
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.EnllazJPA;
import es.caib.carpeta.persistence.EnllazIJPAManager;
import es.caib.carpeta.model.dao.IEnllazManager;

import es.caib.carpeta.model.entity.Enllaz;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EnllazService extends EnllazIJPAManager,IEnllazManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/EnllazEJB!es.caib.carpeta.ejb.EnllazService";

    public EnllazJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Enllaz instance, FitxerService fitxerEjb) throws I18NException;
}
