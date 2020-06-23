package es.caib.carpeta.persistence.test;

import es.caib.carpeta.persistence.Idioma;
import es.caib.carpeta.persistence.TipoUsuario;
import es.caib.carpeta.persistence.Usuario;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

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
public class TestUsuario {

   /**
    * Crea l'arxiu de deploy que es desplegarà sobre JBoss per fer els tests.
    *
    * @return arxiu desplegable.
    */
   @Deployment
   public static JavaArchive createDeployment() {
      JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
         .addPackages(true, "es.caib.carpeta.persistence")
         .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
         .addAsResource("META-INF/arquillian-persistence.xml", "META-INF/persistence.xml");
      //System.out.println(jar.toString(true));
      return jar;
   }

   @PersistenceContext
   EntityManager em;

   @Inject
   UserTransaction utx;



   /**
    * Abans de cada test s'inciarà una transacció.
    *
    * @throws Exception Error durant l'inici de la transacció
    */
   @Before
   public void startTransaction() throws Exception {
      utx.begin();
      em.joinTransaction();
   }

   /**
    * Finalització d'una transacció. Es farà un commit, o un rollback si la transacció s'ha marcat com a rollbackonly
    *
    * @throws Exception Error durant el final de la transacció.
    */
   @After
   public void endTransaction() throws Exception {
      if (utx.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
         utx.rollback();
      } else {
         utx.commit();
      }
      em.clear();
   }


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
      usuario.setIdioma(Idioma.CA);
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
