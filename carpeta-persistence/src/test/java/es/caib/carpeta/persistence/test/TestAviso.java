package es.caib.carpeta.persistence.test;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 23/06/2020
 */

import es.caib.carpeta.persistence.Aviso;
import es.caib.carpeta.persistence.Entidad;
import es.caib.carpeta.persistence.TipoAviso;
import es.caib.carpeta.persistence.TraduccionAviso;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Realitza tests de persistència i validació damunt els avisos
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestAviso {

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
    * Crea un Aviso
    */
   @Test
   @InSequence(1)
   public void testCreateAviso() {

      TypedQuery<Entidad> query = em.createQuery(
         "select u from Entidad u where u.codigoDir3 = :codigoDir3", Entidad.class);
      query.setParameter("codigoDir3", "A04003007");
      Entidad entidad = query.getSingleResult();



      Aviso aviso = new Aviso();

      aviso.setFechaFin(new Timestamp(System.currentTimeMillis()));
      aviso.setFechaInicio(new Timestamp(System.currentTimeMillis()));
      aviso.setEntidad(entidad);
      aviso.setTipo(TipoAviso.INFO);


      TraduccionAviso traduccionAviso = new TraduccionAviso();
      traduccionAviso.setNombre("Aviso Covid 19");
      traduccionAviso.setDescripcion("Aviso Covid 19");
      traduccionAviso.setTextoAviso("Alerta Covid 19, los trámites presenciales quedan suspendidos hasta que acabe estado de alarma");
      

      Map<String,TraduccionAviso> traducciones = new HashMap<String,TraduccionAviso>();

      traducciones.put("es", traduccionAviso);


      aviso.setTraducciones(traducciones);

      em.persist(aviso);

      em.flush();

      Assert.assertNotNull(aviso.getId());
   }

   


   @Test
   @InSequence(1)
   public void testObtenerAvisoByEntidad() {

      TypedQuery<Aviso> query = em.createQuery(
         "select a from Aviso a where a.tipo = :tipo", Aviso.class);
      query.setParameter("tipo", TipoAviso.INFO);
      Aviso aviso = query.getSingleResult();

      // Comprovam el tipus de l'avis seleccionat
      Assert.assertEquals(TipoAviso.INFO, aviso.getTipo());

   }

}
