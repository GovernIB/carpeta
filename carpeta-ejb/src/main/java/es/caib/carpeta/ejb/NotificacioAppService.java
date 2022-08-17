
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.NotificacioAppJPA;
import es.caib.carpeta.persistence.NotificacioAppIJPAManager;
import es.caib.carpeta.model.dao.INotificacioAppManager;

import es.caib.carpeta.model.entity.NotificacioApp;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface NotificacioAppService extends NotificacioAppIJPAManager,INotificacioAppManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/NotificacioAppEJB!es.caib.carpeta.ejb.NotificacioAppService";

    public NotificacioAppJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(NotificacioApp instance, FitxerService fitxerEjb) throws I18NException;
}
