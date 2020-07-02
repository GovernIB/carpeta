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
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.TypedQuery;
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
public class TestAviso extends TestBase{


   /**
    * Crea un Aviso
    */
   @Test
   @InSequence(1)
   public void testCreateAviso() {

      Aviso aviso = new Aviso();
      aviso.setFechaFin(new Timestamp(System.currentTimeMillis()));
      aviso.setFechaInicio(new Timestamp(System.currentTimeMillis()));
      aviso.setTipo(TipoAviso.INFO);

      //Obtenemos la entidad
      Entidad entidad = obtenerEntidad("A04003007");
      aviso.setEntidad(entidad);

      //Traducciones
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
   public void testObtenerAvisoByTipo() {

      TypedQuery<Aviso> query = em.createQuery(
         "select a from Aviso a where a.tipo = :tipo", Aviso.class);
      query.setParameter("tipo", TipoAviso.INFO);
      Aviso aviso = query.getSingleResult();

      // Comprovam el tipus de l'avis seleccionat
      Assert.assertEquals(TipoAviso.INFO, aviso.getTipo());

   }

}
