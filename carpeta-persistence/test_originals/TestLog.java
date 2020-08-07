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
import java.sql.Timestamp;
import java.util.List;

/**
 * Realitza tests de persistència i validació damunt els logs
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestLog extends TestBase{


   /**
    * Crea un Log
    */
   @Test
   @InSequence(1)
   public void testCreateLog() {

      LogJPA log = new LogJPA();
      log.setEstado(EstadoLog.OK);
      log.setDescripcion("Iniciado tramite");
      log.setFecha(new Timestamp(System.currentTimeMillis()));
      log.setTiempo(10L);
      log.setPeticion("Iniciado Tramite");
      log.setTipo(TipoLog.INTEGRACION_SISTRA);


      //Obtenemos plugin
      TypedQuery<Plugin> query2 = em.createQuery(
         "select p from Plugin p where p.tipo = :tipo", Plugin.class);
      query2.setParameter("tipo", TipoPlugin.PLUGIN_SISTRA);
      List<Plugin> plugins = query2.getResultList();
      if(!plugins.isEmpty()) {
         log.setPlugin(plugins.get(0));
      }

      //Obtenemos la entidad
      Entidad entidad = obtenerEntidad("A04003007");
      log.setEntidad(entidad);


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
