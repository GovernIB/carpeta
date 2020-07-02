package es.caib.carpeta.persistence.test;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 22/06/2020
 */

import es.caib.carpeta.persistence.Entidad;
import es.caib.carpeta.persistence.Usuario;
import es.caib.carpeta.persistence.UsuarioEntidad;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Realitza tests de persistència i validació damunt Entitats
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestUsuarioEntidad extends TestBase{


   @Test
   @InSequence(1)
   public void testCreateUsuarioEntidad() {

      UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
      usuarioEntidad.setActivo(true);
      usuarioEntidad.setUltimaEntidad(null);
      usuarioEntidad.setAdminEntidad(true);

      //Obtenemos la entidad
      Entidad entidad = obtenerEntidad("A04003007");
      usuarioEntidad.setEntidad(entidad);

      //Obtenemos el usuario
      Usuario usuario = obtenerUsuario("mgonzalez");
      usuarioEntidad.setUsuario(usuario);

      em.persist(usuarioEntidad);

      em.flush();

      Assert.assertNotNull(usuarioEntidad.getId());
   }

}
