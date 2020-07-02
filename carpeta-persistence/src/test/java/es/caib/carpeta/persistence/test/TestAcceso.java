package es.caib.carpeta.persistence.test;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 23/06/2020
 */

import es.caib.carpeta.persistence.Acceso;
import es.caib.carpeta.persistence.Entidad;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.TypedQuery;
import java.sql.Timestamp;

/**
 * Realitza tests de persistència i validació damunt els accesos
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestAcceso extends TestBase{


   /**
    * Crea un Acceso
    */
   @Test
   @InSequence(1)
   public void testCreateAcceso() {


      Acceso acceso = new Acceso();
      acceso.setApellidos("Apellido1 Apellido 2");
      acceso.setNombre("Nombre");
      acceso.setDireccionIP("122.122.122.12");
      acceso.setFechaUltimoAcceso(new Timestamp(System.currentTimeMillis()));
      acceso.setIdioma("ca");
      acceso.setNif("12345678Z");
      acceso.setNivelSeguridad("Bajo"); //Posibles valores???
      acceso.setProveedorEntidad("CAIB"); //Posibles valores???
      acceso.setResultadoAutenticacion(""); //Posibles valores???

      //Obtenemos la entidad
      Entidad entidad = obtenerEntidad("A04003007");
      acceso.setEntidad(entidad);


      em.persist(acceso);

      em.flush();

      Assert.assertNotNull(acceso.getId());
   }

   


   @Test
   @InSequence(1)
   public void testObtenerAccesoByDireccionIP() {

      TypedQuery<Acceso> query = em.createQuery(
         "select a from Acceso a where a.direccionIP= :direccionIP", Acceso.class);
      query.setParameter("direccionIP", "122.122.122.12");
      Acceso acceso = query.getSingleResult();

      // Comprovam el tipus de l'avis seleccionat
      Assert.assertEquals("122.122.122.12", acceso.getDireccionIP());

   }

}
