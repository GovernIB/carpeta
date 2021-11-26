
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.PreguntesFrequentsJPA;
import es.caib.carpeta.persistence.PreguntesFrequentsIJPAManager;
import es.caib.carpeta.model.dao.IPreguntesFrequentsManager;

@Local
public interface PreguntesFrequentsService extends PreguntesFrequentsIJPAManager,IPreguntesFrequentsManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/PreguntesFrequentsEJB!es.caib.carpeta.ejb.PreguntesFrequentsService";

    public PreguntesFrequentsJPA findByPrimaryKey(Long _ID_);
}
