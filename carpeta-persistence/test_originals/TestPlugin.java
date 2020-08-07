package es.caib.carpeta.persistence.test;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 23/06/2020
 */

import es.caib.carpeta.persistence.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

/**
 * Realitza tests de persistència i validació damunt Plugins
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestPlugin extends TestBase{



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

      //Traducciones
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


      //Obtenemos la entidad
      Entidad entidad = obtenerEntidad("A04003007");


      Plugin plugin = new Plugin();

      plugin.setClase("es.caib.carpeta.regweb3");
      plugin.setEstado(EstadoPlugin.INACTIVO);
      plugin.setTipo(TipoPlugin.PLUGIN_REGISTRO);
      plugin.setPrefijoEntidad("caib");
      plugin.setEntidad(entidad);

      //traducciones
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
