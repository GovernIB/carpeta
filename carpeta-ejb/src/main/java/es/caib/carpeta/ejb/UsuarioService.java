package es.caib.carpeta.ejb;

import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.persistence.TipoUsuario;
import es.caib.carpeta.persistence.Usuario;
import es.caib.carpeta.persistence.dao.DAO;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 09/06/2020
 */
@Local
public interface UsuarioService extends DAO<Usuario, Long > {

   public static final String JNDI_NAME = "java:app/carpeta-ejb-1.1.1/UsuarioEJB!es.caib.carpeta.ejb.UsuarioService";


   /**
    * Crea un usuario obteniendo los datos de keycloak
    *
    * @param usuario   usuario.
    * @return El usuario creado.
    */
   Usuario crearUsuario(Usuario usuario) throws I18NException;



   List<Usuario> busquedaUsuario(Map<String, Object> filters, String username, String nombre, String apellido1, String apellido2,  TipoUsuario tipoUsuario);


   /**
    * Elimina una Usuario y todos sus datos dependientes
    * @param id
    * @throws I18NException
    */
   void eliminarUsuario(Long id) throws I18NException;


}
