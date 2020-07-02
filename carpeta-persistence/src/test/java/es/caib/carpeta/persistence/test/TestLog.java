package es.caib.carpeta.persistence.test;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 23/06/2020
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
import java.sql.Timestamp;
import java.util.List;

/**
 * Realitza tests de persistència i validació damunt els logs
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestLog {

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
    * Crea un Log
    */
   @Test
   @InSequence(1)
   public void testCreateLog() {



      TypedQuery<Entidad> query = em.createQuery(
         "select u from Entidad u where u.codigoDir3 = :codigoDir3", Entidad.class);
      query.setParameter("codigoDir3", "A04003007");
      Entidad entidad = query.getSingleResult();

      TypedQuery<Plugin> query2 = em.createQuery(
         "select p from Plugin p where p.tipo = :tipo", Plugin.class);
      query2.setParameter("tipo", TipoPlugin.PLUGIN_SISTRA);
      List<Plugin> plugins = query2.getResultList();



      Log log = new Log();
      log.setEstado(EstadoLog.OK);
      log.setDescripcion("Iniciado tramite");
      log.setFecha(new Timestamp(System.currentTimeMillis()));
      if(!plugins.isEmpty()) {
         log.setPlugin(plugins.get(0));
      }
      log.setEntidad(entidad);
      log.setTiempo(10L);
      log.setPeticion("Iniciado Tramite");
      log.setTipo(TipoLog.INTEGRACION_SISTRA);


      em.persist(log);

      em.flush();

      Assert.assertNotNull(log.getId());
   }

   


   @Test
   @InSequence(1)
   public void testObtenerLogByTipo() {

      TypedQuery<Log> query = em.createQuery(
         "select a from Log a where a.tipo = :tipo", Log.class);
      query.setParameter("tipo", TipoLog.INTEGRACION_SISTRA);
      Log log = query.getSingleResult();

      // Comprovam el tipo de log seleccionat
      Assert.assertEquals(TipoLog.INTEGRACION_SISTRA, log.getTipo());

   }

}
