
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.SeccioJPA;
import es.caib.carpeta.persistence.SeccioIJPAManager;
import es.caib.carpeta.model.dao.ISeccioManager;

import es.caib.carpeta.model.entity.Seccio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface SeccioService extends SeccioIJPAManager,ISeccioManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/SeccioEJB!es.caib.carpeta.ejb.SeccioService";

    public SeccioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Seccio instance, FitxerService fitxerEjb) throws I18NException;
}
