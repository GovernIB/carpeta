package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.persistence.UsuariJPA;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 11/01/2021
 */
@Local
public interface AuthenticationLogicaLocal {

    public static final String JNDI_NAME = "java:app/carpeta-logic/AuthenticationLogicaEJB!es.caib.carpeta.logic.AuthenticationLogicaLocal";

    public void crearLog(String descripcio, Long temps, StringBuilder peticio, Throwable th, String error,String entitatCodi);

    public void crearAuditoria(Long entitatID,String username) throws I18NException;

    public UsuariJPA findByUsername(String username) throws I18NException;

    public Long executeQueryOne( String defaultEntityCode) throws I18NException;

    public UsuariJPA crearUsuari( UsuariJPA usuario) throws I18NException;

    public UsuariEntitatJPA create( UsuariEntitatJPA usuariEntitat) throws I18NException;

    public List<UsuariEntitatJPA> findAllByUsuariIdWithEntitat(@NotNull long usuarioID) throws I18NException;
}
