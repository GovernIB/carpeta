
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.AvisJPA;
import es.caib.carpeta.persistence.AvisIJPAManager;
import es.caib.carpeta.model.dao.IAvisManager;

import es.caib.carpeta.model.entity.Avis;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface AvisService extends AvisIJPAManager,IAvisManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/AvisEJB!es.caib.carpeta.ejb.AvisService";

    public AvisJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Avis instance, FitxerService fitxerEjb) throws I18NException;
}
