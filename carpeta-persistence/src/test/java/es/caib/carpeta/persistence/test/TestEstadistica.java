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
 * Realitza tests de persistència i validació damunt les estadisticas
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestEstadistica {

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
    * Crea un Estadistica
    */
   @Test
   @InSequence(1)
   public void testCreateEstadistica() {

      TypedQuery<Entidad> query = em.createQuery(
         "select u from Entidad u where u.codigoDir3 = :codigoDir3", Entidad.class);
      query.setParameter("codigoDir3", "A04003007");
      Entidad entidad = query.getSingleResult();

      TypedQuery<Acceso> query2 = em.createQuery("select a from Acceso a", Acceso.class);

      List<Acceso> accesoList = query2.getResultList();




      Estadistica estadistica = new Estadistica();
      estadistica.setAccion(TipoAccion.CREADO);
      estadistica.setElemento(TipoElemento.AVISO);
      estadistica.setFecha(new Timestamp(System.currentTimeMillis()));
      estadistica.setEntidad(entidad);
      if(!accesoList.isEmpty()){
         estadistica.setAcceso(accesoList.get(0));
      }


      em.persist(estadistica);

      em.flush();

      Assert.assertNotNull(estadistica.getId());
   }

   


   @Test
   @InSequence(1)
   public void testObtenerEstadisticaByTipoElemento() {

      TypedQuery<Estadistica> query = em.createQuery(
         "select e from Estadistica e where e.elemento = :elemento", Estadistica.class);
      query.setParameter("elemento", TipoElemento.AVISO);
      Estadistica estadistica = query.getSingleResult();

      // Comprovam el tipus de l'avis seleccionat
      Assert.assertEquals(TipoElemento.AVISO, estadistica.getElemento());

   }

}
