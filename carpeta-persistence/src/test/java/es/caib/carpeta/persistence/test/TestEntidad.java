package es.caib.carpeta.persistence.test;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 29/05/2020
 */


import es.caib.carpeta.persistence.*;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Realitza tests de persistència i validació damunt Entitats
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestEntidad {

   /**
    * Crea l'arxiu de deploy que es desplegarà sobre JBoss per fer els tests.
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
    * @throws Exception Error durant l'inici de la transacció
    */
   @Before
   public void startTransaction() throws Exception {
      utx.begin();
      em.joinTransaction();
   }

   /**
    * Finalització d'una transacció. Es farà un commit, o un rollback si la transacció s'ha marcat com a rollbackonly
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
    * Crea una entitat.
    */
   @Test
   @InSequence(1)
   public void testCreateEntidad() {

      Entidad entidad = new Entidad();
      entidad.setActiva(true);
      entidad.setCodigoDir3("A04003007");
      entidad.setContexto("carpeta");

      TraduccionBase traduccionBase = new TraduccionBase();
      traduccionBase.setNombre("Govern de les illes balears");
      traduccionBase.setDescripcion("Govern de les illes balears");



      Map<String,TraduccionBase> traducciones = new HashMap<String,TraduccionBase>();
      traducciones.put("ca", traduccionBase);
      traducciones.put("es", traduccionBase);

      entidad.setTraducciones(traducciones);

      em.persist(entidad);

      em.flush();

      Assert.assertNotNull(entidad.getId());
   }


   @Test
   @InSequence(1)
   public void testObtenerEntidad() {

      TypedQuery<Entidad> query = em.createQuery(
         "select u from Entidad u where u.codigoDir3 = :codigoDir3", Entidad.class);
      query.setParameter("codigoDir3", "A04003007");
      Entidad entidad = query.getSingleResult();

      // Comprovam el nom de la unitat seleccionada
      Assert.assertEquals("A04003007", entidad.getCodigoDir3());

   }



   @Test
   @InSequence(1)
   public void testCreateUsuarioEntidad() {

      TypedQuery<Entidad> query = em.createQuery(
         "select u from Entidad u where u.codigoDir3 = :codigoDir3", Entidad.class);
      query.setParameter("codigoDir3", "A04003007");
      Entidad entidad = query.getSingleResult();

      TypedQuery<Usuario> query2 = em.createQuery(
         "select u from Usuario u where u.username = :username", Usuario.class);
      query2.setParameter("username", "mgonzalez");
      Usuario usuario = query2.getSingleResult();


      UsuarioEntidad usuarioEntidad = new UsuarioEntidad();
      usuarioEntidad.setEntidad(entidad);
      usuarioEntidad.setUsuario(usuario);
      usuarioEntidad.setActivo(true);
      usuarioEntidad.setUltimaEntidad(null);

      em.persist(usuarioEntidad);

      em.flush();

      Assert.assertNotNull(usuarioEntidad.getId());
   }

}
