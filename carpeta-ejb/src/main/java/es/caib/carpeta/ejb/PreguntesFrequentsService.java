
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.PreguntesFrequentsJPA;
import es.caib.carpeta.persistence.PreguntesFrequentsIJPAManager;
import es.caib.carpeta.model.dao.IPreguntesFrequentsManager;

import es.caib.carpeta.model.entity.PreguntesFrequents;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PreguntesFrequentsService extends PreguntesFrequentsIJPAManager,IPreguntesFrequentsManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/PreguntesFrequentsEJB!es.caib.carpeta.ejb.PreguntesFrequentsService";

    public PreguntesFrequentsJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PreguntesFrequents instance, FitxerService fitxerEjb) throws I18NException;
}
