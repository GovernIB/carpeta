package es.caib.carpeta.persistence.test;

import es.caib.carpeta.persistence.TipoUsuario;
import es.caib.carpeta.persistence.Usuario;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.TypedQuery;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 12/06/2020
 */

/**
 * Realitza tests de persistència i validació damunt Usuaris
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */

@RunWith(Arquillian.class)
public class TestUsuario extends TestBase{


   /**
    * Crea un usuario
    */
   @Test
   @InSequence(1)
   public void testCreateUsuario() {

      Usuario usuario = new Usuario();
      usuario.setNombre("Marilen");
      usuario.setApellido1("Gonzalez");
      usuario.setEmail("mgonzalez@fundaciobit.org");
      usuario.setUsername("mgonzalez");
      usuario.setTipo(TipoUsuario.PERSONA);
      usuario.setIdioma("ca");
      em.persist(usuario);

      em.flush();

      Assert.assertNotNull(usuario.getId());
   }

   @Test
   @InSequence(1)
   public void testObtenerUsuario() {

      TypedQuery<Usuario> query = em.createQuery(
         "select u from Usuario u where u.username = :username", Usuario.class);
      query.setParameter("username", "mgonzalez");
      Usuario usuario = query.getSingleResult();



      // Comprobamos el username del usuario encontrado
      Assert.assertEquals("mgonzalez", usuario.getUsername());

   }







}
