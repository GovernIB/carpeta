
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.persistence.UsuariEntitatIJPAManager;
import es.caib.carpeta.model.dao.IUsuariEntitatManager;

import es.caib.carpeta.model.entity.UsuariEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariEntitatService extends UsuariEntitatIJPAManager,IUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/UsuariEntitatEJB!es.caib.carpeta.ejb.UsuariEntitatService";

    public UsuariEntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(UsuariEntitat instance, FitxerService fitxerEjb) throws I18NException;
}
