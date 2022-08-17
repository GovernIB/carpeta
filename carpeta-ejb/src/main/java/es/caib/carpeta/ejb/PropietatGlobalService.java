
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.PropietatGlobalJPA;
import es.caib.carpeta.persistence.PropietatGlobalIJPAManager;
import es.caib.carpeta.model.dao.IPropietatGlobalManager;

import es.caib.carpeta.model.entity.PropietatGlobal;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PropietatGlobalService extends PropietatGlobalIJPAManager,IPropietatGlobalManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/PropietatGlobalEJB!es.caib.carpeta.ejb.PropietatGlobalService";

    public PropietatGlobalJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PropietatGlobal instance, FitxerService fitxerEjb) throws I18NException;
}
