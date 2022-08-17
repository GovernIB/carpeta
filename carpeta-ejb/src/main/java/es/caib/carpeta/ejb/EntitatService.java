
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.persistence.EntitatIJPAManager;
import es.caib.carpeta.model.dao.IEntitatManager;

import es.caib.carpeta.model.entity.Entitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EntitatService extends EntitatIJPAManager,IEntitatManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/EntitatEJB!es.caib.carpeta.ejb.EntitatService";

    public EntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Entitat instance, FitxerService fitxerEjb) throws I18NException;
}
