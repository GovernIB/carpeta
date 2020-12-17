package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.validation.constraints.NotNull;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.ejb.AccesLocal;
import java.sql.Timestamp;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 16/12/2020
 */

public interface AccesLogicaLocal extends AccesLocal {
    public static final String JNDI_NAME = "java:app/carpeta-logic/AccesLogicaEJB!es.caib.carpeta.logic.AccesLogicaLocal";

    public void crearAcces(UsuarioClave usuarioClave,@NotNull int tipus, String codiEntitat, Timestamp dataDarrerAcces,String idioma, String ipAddress) throws I18NException;
}
