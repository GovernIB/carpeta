package es.caib.carpeta.persistence.test;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 23/06/2020
 */

import es.caib.carpeta.persistence.Plugin;
import es.caib.carpeta.persistence.PropiedadPlugin;
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
 * Realitza tests de persistència i validació damunt Propiedad Plugin
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestPropiedadPlugin {

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
   public void testCreatePropiedadPlugin() {


      TypedQuery<Plugin> query = em.createQuery(
         "select p from Plugin p where p.prefijoEntidad = :prefijoEntidad", Plugin.class);
      query.setParameter("prefijoEntidad", "caib");
      Plugin plugin = query.getSingleResult();

      PropiedadPlugin propiedadPlugin = new PropiedadPlugin();
      propiedadPlugin.setPlugin(plugin);
      propiedadPlugin.setCodigo("PROP1");
      propiedadPlugin.setDescripcion("PROPIEDAD 1");
      propiedadPlugin.setValor("Valor propiedad 1");


      em.persist(propiedadPlugin);

      em.flush();

      Assert.assertNotNull(propiedadPlugin.getId());
   }




   @Test
   @InSequence(1)
   public void testObtenerPropiedadPluginByPlugin() {

      Long idPlugin = 3L;

      TypedQuery<PropiedadPlugin> query = em.createQuery(
         "select pP from PropiedadPlugin  pP where pP.codigo = :codigo and pP.plugin.id =:idPlugin ", PropiedadPlugin.class);
      query.setParameter("codigo", "PROP1");
      query.setParameter("idPlugin", idPlugin);
      PropiedadPlugin propiedadPlugin = query.getSingleResult();

      // Comprobamos el codigo de la propiedad seleccionada
      Assert.assertEquals("PROP1", propiedadPlugin.getCodigo());

   }

}
