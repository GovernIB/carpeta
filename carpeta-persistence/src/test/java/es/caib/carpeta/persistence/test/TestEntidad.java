package es.caib.carpeta.persistence.test;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 29/05/2020
 */


import es.caib.carpeta.persistence.Entidad;
import es.caib.carpeta.persistence.TraduccionBase;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

/**
 * Realitza tests de persistència i validació damunt Entitats
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestEntidad extends TestBase{



   /**
    * Crea una entitat.
    */
   @Test
   @InSequence(1)
   public void testCreateEntidad() {

      Entidad entidad = new Entidad();
      entidad.setActiva(true);
      entidad.setCodigoDir3("A04003007");
      entidad.setContexto("carpeta");

      //Traducciones
      TraduccionBase traduccionBase = new TraduccionBase();
      traduccionBase.setNombre("Govern de les illes balears");
      traduccionBase.setDescripcion("Govern de les illes balears");

      Map<String,TraduccionBase> traducciones = new HashMap<String,TraduccionBase>();
      traducciones.put("ca", traduccionBase);
      traducciones.put("es", traduccionBase);
      entidad.setTraducciones(traducciones);

      em.persist(entidad);

      em.flush();

      Assert.assertNotNull(entidad.getId());
   }


   @Test
   @InSequence(1)
   public void testObtenerEntidad() {

      TypedQuery<Entidad> query = em.createQuery(
         "select u from Entidad u where u.codigoDir3 = :codigoDir3", Entidad.class);
      query.setParameter("codigoDir3", "A04003007");
      Entidad entidad = query.getSingleResult();

      // Comprovam el nom de la unitat seleccionada
      Assert.assertEquals("A04003007", entidad.getCodigoDir3());

   }
}
