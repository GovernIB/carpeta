
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.IdiomaJPA;
import es.caib.carpeta.persistence.IdiomaIJPAManager;
import es.caib.carpeta.model.dao.IIdiomaManager;

import es.caib.carpeta.model.entity.Idioma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface IdiomaService extends IdiomaIJPAManager,IIdiomaManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/IdiomaEJB!es.caib.carpeta.ejb.IdiomaService";

    public IdiomaJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(Idioma instance, FitxerService fitxerEjb) throws I18NException;
}
