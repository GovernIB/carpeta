
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.EstadisticaJPA;
import es.caib.carpeta.persistence.EstadisticaIJPAManager;
import es.caib.carpeta.model.dao.IEstadisticaManager;

import es.caib.carpeta.model.entity.Estadistica;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EstadisticaService extends EstadisticaIJPAManager,IEstadisticaManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/EstadisticaEJB!es.caib.carpeta.ejb.EstadisticaService";

    public EstadisticaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Estadistica instance, FitxerService fitxerEjb) throws I18NException;
}
