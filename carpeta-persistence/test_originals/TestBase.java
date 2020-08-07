package es.caib.carpeta.persistence.test;

import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.jpa.UsuariJPA;
import es.caib.carpeta.model.entity.Usuari;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;

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
 * Date: 02/07/2020
 */

public class TestBase {

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


   public EntitatJPA obtenerEntidad(String codigo){
      TypedQuery<EntitatJPA> query = em.createQuery(
         "select u from Entidad u where u.codigoDir3 = :codigoDir3", EntitatJPA.class);
      query.setParameter("codigoDir3", codigo);
      return query.getSingleResult();
   }


   public UsuariJPA obtenerUsuario(String username){
      TypedQuery<UsuariJPA> query = em.createQuery(
         "select u from Usuario u where u.username = :username", UsuariJPA.class);
      query.setParameter("username", "username");
      return query.getSingleResult();
   }
}
