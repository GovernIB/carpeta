
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.UsuariJPA;
import es.caib.carpeta.persistence.UsuariIJPAManager;
import es.caib.carpeta.model.dao.IUsuariManager;

import es.caib.carpeta.model.entity.Usuari;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariService extends UsuariIJPAManager,IUsuariManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/UsuariEJB!es.caib.carpeta.ejb.UsuariService";

    public UsuariJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Usuari instance, FitxerService fitxerEjb) throws I18NException;
}
