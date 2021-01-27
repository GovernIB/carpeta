package es.caib.carpeta.logic;

import javax.ejb.Local;

import es.caib.carpeta.ejb.FitxerService;
import es.caib.carpeta.persistence.FitxerJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface FitxerLogicaService extends FitxerService {

    public static final String JNDI_NAME = "java:app/carpeta-logic/FitxerLogicaEJB!es.caib.carpeta.logic.FitxerLogicaService";

    @Override
    public FitxerJPA findByPrimaryKey(Long _ID_);

    @Override
    public FitxerJPA findByPrimaryKey(long _ID_);

}
