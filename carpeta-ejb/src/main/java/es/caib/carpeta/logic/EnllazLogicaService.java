package es.caib.carpeta.logic;

import java.util.Set;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.EnllazService;
import es.caib.carpeta.model.entity.Enllaz;

/**
 * 
 * @author jagarcia
 * @author anadal
 *
 */
@Local
public interface EnllazLogicaService extends EnllazService {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/EnllazLogicaEJB!es.caib.carpeta.logic.EnllazLogicaService";

    public Set<Long> deleteFullRecursive(Enllaz enllaz) throws I18NException;

    public void deleteFull(Enllaz enllaz) throws I18NException;

}
