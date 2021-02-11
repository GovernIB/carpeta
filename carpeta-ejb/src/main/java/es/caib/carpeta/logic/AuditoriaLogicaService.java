package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import es.caib.carpeta.ejb.AuditoriaService;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 10/11/2020
 */
@Local
public interface AuditoriaLogicaService extends AuditoriaService {
    public static final String JNDI_NAME = "java:app/carpeta-ejb/AuditoriaLogicaEJB!es.caib.carpeta.logic.AuditoriaLogicaService";

    public void crearAuditoria(@NotNull int tipus, Long entitatID, String username, String objecte) throws I18NException;
}
