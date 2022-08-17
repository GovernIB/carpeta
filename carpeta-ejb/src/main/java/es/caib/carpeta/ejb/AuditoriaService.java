
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.AuditoriaJPA;
import es.caib.carpeta.persistence.AuditoriaIJPAManager;
import es.caib.carpeta.model.dao.IAuditoriaManager;

import es.caib.carpeta.model.entity.Auditoria;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface AuditoriaService extends AuditoriaIJPAManager,IAuditoriaManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/AuditoriaEJB!es.caib.carpeta.ejb.AuditoriaService";

    public AuditoriaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Auditoria instance, FitxerService fitxerEjb) throws I18NException;
}
