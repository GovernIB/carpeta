
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.LogCarpetaJPA;
import es.caib.carpeta.persistence.LogCarpetaIJPAManager;
import es.caib.carpeta.model.dao.ILogCarpetaManager;

import es.caib.carpeta.model.entity.LogCarpeta;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface LogCarpetaService extends LogCarpetaIJPAManager,ILogCarpetaManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/LogCarpetaEJB!es.caib.carpeta.ejb.LogCarpetaService";

    public LogCarpetaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(LogCarpeta instance, FitxerService fitxerEjb) throws I18NException;
}
