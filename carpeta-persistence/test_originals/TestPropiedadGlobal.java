package es.caib.carpeta.persistence.test;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 23/06/2020
 */

import es.caib.carpeta.persistence.Entidad;
import es.caib.carpeta.persistence.PropiedadGlobal;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.TypedQuery;

/**
 * Realitza tests de persistència i validació damunt Propiedad Global
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestPropiedadGlobal extends TestBase{


   /**
    * Crea un Plugin sin Entidad
    */
   @Test
   @InSequence(1)
   public void testCreatePropiedadGlobal() {


      PropiedadGlobal propiedadGlobal = new PropiedadGlobal();
      propiedadGlobal.setCodigo("PG1");
      propiedadGlobal.setDescripcion("PROPIEDAD GLOBAL 1");
      propiedadGlobal.setValor("Valor propiedad globa 1");

      //Obtenemos la entidad
      Entidad entidad = obtenerEntidad("A04003007");
      propiedadGlobal.setEntidad(entidad);


      em.persist(propiedadGlobal);

      em.flush();

      Assert.assertNotNull(propiedadGlobal.getId());
   }




   @Test
   @InSequence(1)
   public void testObtenerPropiedadPluginByPlugin() {


      TypedQuery<PropiedadGlobal> query = em.createQuery(
         "select p from PropiedadGlobal  p where p.codigo = :codigo ", PropiedadGlobal.class);
      query.setParameter("codigo", "PG1");
      PropiedadGlobal propiedadGlobal = query.getSingleResult();

      // Comprobamos el codigo de la propiedad seleccionada
      Assert.assertEquals("PG1", propiedadGlobal.getCodigo());

   }

}
