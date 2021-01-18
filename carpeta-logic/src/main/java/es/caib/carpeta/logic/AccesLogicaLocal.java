package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.ejb.AccesLocal;
import es.caib.carpeta.jpa.AccesJPA;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez Date: 16/12/2020
 */
@Local
public interface AccesLogicaLocal extends AccesLocal {
    public static final String JNDI_NAME = "java:app/carpeta-logic/AccesLogicaEJB!es.caib.carpeta.logic.AccesLogicaLocal";

    public void crearAcces(UsuarioClave usuarioClave, @NotNull int tipus, long entitatID, Long pluginID,
            Timestamp dataDarrerAcces, String idioma, String ipAddress, boolean resultat) throws I18NException;

    /* Llistat de accesos entre dues dates ordenat per data descendent */
    public List<AccesJPA> findBetweenDates(Date inici, Date fi, String codiEntitat) throws I18NException;
}
