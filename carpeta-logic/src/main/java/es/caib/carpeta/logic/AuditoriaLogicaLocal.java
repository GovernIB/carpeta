package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import es.caib.carpeta.ejb.AuditoriaLocal;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 10/11/2020
 */
@Local
public interface AuditoriaLogicaLocal extends AuditoriaLocal {
    public static final String JNDI_NAME = "java:app/carpeta-logic/AuditoriaLogicaEJB!es.caib.carpeta.logic.AuditoriaLogicaLocal";

    public void crearAuditoria(@NotNull int tipus, Long entitatID, Long usuariID, String ticketLoginIB, Integer pluginID) throws I18NException;
}
