package es.caib.carpeta.logic;

import javax.ejb.Local;

import es.caib.carpeta.ejb.FitxerLocal;
import es.caib.carpeta.jpa.FitxerJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface FitxerLogicaLocal extends FitxerLocal {

    public static final String JNDI_NAME = "java:app/carpeta-logic/FitxerLogicaEJB!es.caib.carpeta.logic.FitxerLogicaLocal";

    @Override
    public FitxerJPA findByPrimaryKey(Long _ID_);

    @Override
    public FitxerJPA findByPrimaryKey(long _ID_);

}
