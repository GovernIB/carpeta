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
 * Realitza tests de persistència i validació damunt les estadisticas
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestEstadistica extends TestBase{



   /**
    * Crea un Estadistica
    */
   @Test
   @InSequence(1)
   public void testCreateEstadistica() {


      Estadistica estadistica = new Estadistica();
      estadistica.setAccion(TipoAccion.CREADO);
      estadistica.setElemento(TipoElemento.AVISO);
      estadistica.setFecha(new Timestamp(System.currentTimeMillis()));


      //Obtenemos acceso
      TypedQuery<Acceso> query2 = em.createQuery("select a from Acceso a", Acceso.class);
      List<Acceso> accesoList = query2.getResultList();
      if(!accesoList.isEmpty()){
         estadistica.setAcceso(accesoList.get(0));
      }

      //Obtenemos la entidad
      Entidad entidad = obtenerEntidad("A04003007");
      estadistica.setEntidad(entidad);


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
