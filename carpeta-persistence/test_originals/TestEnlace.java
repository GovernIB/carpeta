package es.caib.carpeta.persistence.test;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 23/06/2020
 */

import es.caib.carpeta.persistence.Enlace;
import es.caib.carpeta.persistence.Entidad;
import es.caib.carpeta.persistence.TipoEnlace;
import es.caib.carpeta.persistence.TraduccionEnlace;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

/**
 * Realitza tests de persistència i validació damunt els enllaços
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestEnlace extends TestBase{


   /**
    * Crea un Enlace
    */
   @Test
   @InSequence(1)
   public void testCreateEnlace() {

      Enlace enlace = new Enlace();

      enlace.setTipo(TipoEnlace.EXTERNO);

      //Obtenemos la entidad
      Entidad entidad = obtenerEntidad("A04003007");
      enlace.setEntidad(entidad);

      //Traducciones
      TraduccionEnlace traduccionEnlace = new TraduccionEnlace();
      traduccionEnlace.setNombre("Enlace Seu Electronica");
      traduccionEnlace.setDescripcion("Enlace a la Seu Electrònica");
      traduccionEnlace.setUrl("https://www.caib.es/seucaib/ca/202/administracions/");

      Map<String,TraduccionEnlace> traducciones = new HashMap<String,TraduccionEnlace>();
      traducciones.put("es", traduccionEnlace);
      enlace.setTraducciones(traducciones);


      em.persist(enlace);

      em.flush();

      Assert.assertNotNull(enlace.getId());
   }

   


   @Test
   @InSequence(1)
   public void testObtenerEnlaceByTipo() {

      TypedQuery<Enlace> query = em.createQuery(
         "select e from Enlace e where e.tipo = :tipo", Enlace.class);
      query.setParameter("tipo", TipoEnlace.EXTERNO);
      Enlace enlace = query.getSingleResult();

      // Comprovam el tipus de l'enllaç seleccionat
      Assert.assertEquals(TipoEnlace.EXTERNO, enlace.getTipo());

   }

}
