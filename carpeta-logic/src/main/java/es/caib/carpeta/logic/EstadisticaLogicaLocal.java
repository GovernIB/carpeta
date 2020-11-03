package es.caib.carpeta.logic;

import es.caib.carpeta.ejb.EstadisticaLocal;

import javax.ejb.Local;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 29/10/2020
 */
@Local
public interface EstadisticaLogicaLocal extends EstadisticaLocal {
   public static final String JNDI_NAME = "java:app/carpeta-logic/EstadisticaLogicaEJB!es.caib.carpeta.logic.EstadisticaLogicaLocal";
}
