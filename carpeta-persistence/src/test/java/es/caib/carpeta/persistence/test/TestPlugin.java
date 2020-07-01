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
import java.util.HashMap;
import java.util.Map;

/**
 * Realitza tests de persistència i validació damunt Plugins
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestPlugin {

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
    * Crea un Plugin sin Entidad
    */
   @Test
   @InSequence(1)
   public void testCreatePluginSinEntidad() {


      Plugin plugin = new Plugin();

      plugin.setClase("es.caib.carpeta.sistra");
      plugin.setEstado(EstadoPlugin.INACTIVO);
      plugin.setTipo(TipoPlugin.PLUGIN_SISTRA);
      plugin.setPrefijoEntidad(null);
      //plugin.setEntidad(null);

      TraduccionBase traduccionBase = new TraduccionBase();
      traduccionBase.setNombre("Plugin de Sistra");
      traduccionBase.setDescripcion("Plugin que serveix per conectar-se a Sistra");

      Map<String,TraduccionBase> traducciones = new HashMap<String,TraduccionBase>();
      traducciones.put("ca", traduccionBase);
      traducciones.put("es", traduccionBase);


      plugin.setTraducciones(traducciones);

      em.persist(plugin);

      em.flush();

      Assert.assertNotNull(plugin.getId());
   }

   /**
    * Crea un Plugin con Entidad
    */
   @Test
   @InSequence(1)
   public void testCreatePluginConEntidad() {


      TypedQuery<Entidad> query = em.createQuery(
         "select u from Entidad u where u.codigoDir3 = :codigoDir3", Entidad.class);
      query.setParameter("codigoDir3", "A04003007");
      Entidad entidad = query.getSingleResult();


      Plugin plugin = new Plugin();

      plugin.setClase("es.caib.carpeta.regweb3");
      plugin.setEstado(EstadoPlugin.INACTIVO);
      plugin.setTipo(TipoPlugin.PLUGIN_REGISTRO);
      plugin.setPrefijoEntidad("caib");
      plugin.setEntidad(entidad);

      TraduccionBase traduccionBase = new TraduccionBase();
      traduccionBase.setNombre("Plugin de regweb");
      traduccionBase.setDescripcion("Plugin que serveix per conectar-se a registre");

      Map<String,TraduccionBase> traducciones = new HashMap<String,TraduccionBase>();
      traducciones.put("ca", traduccionBase);
      traducciones.put("es", traduccionBase);

      plugin.setTraducciones(traducciones);

      em.persist(plugin);

      em.flush();

      Assert.assertNotNull(plugin.getId());
   }


   @Test
   @InSequence(1)
   public void testObtenerPluginByPrefijoEntidad() {

      TypedQuery<Plugin> query = em.createQuery(
         "select p from Plugin p where p.prefijoEntidad = :prefijoEntidad", Plugin.class);
      query.setParameter("prefijoEntidad", "caib");
      Plugin plugin = query.getSingleResult();

      // comprobamos el prefijo de la entidad del plugin
      Assert.assertEquals("caib", plugin.getPrefijoEntidad());

   }

}
