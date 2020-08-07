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

/**
 * Realitza tests de persistència i validació damunt els auditorias
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class TestAuditoria extends TestBase{


   /**
    * Crea un Auditoria
    */
   @Test
   @InSequence(1)
   public void testCreateAuditoria() {


      Auditoria auditoria = new Auditoria();
      auditoria.setAccion(TipoAccion.CREADO);
      auditoria.setElemento(TipoElemento.ENLACE);
      auditoria.setFecha(new Timestamp(System.currentTimeMillis()));

      //Obtenemos la entidad
      Entidad entidad = obtenerEntidad("A04003007");
      auditoria.setEntidad(entidad);

      //Obtenemos usuario
      Usuario usuario = obtenerUsuario("mgonzalez");
      auditoria.setUsuario(usuario);

      em.persist(auditoria);

      em.flush();

      Assert.assertNotNull(auditoria.getId());
   }

   


   @Test
   @InSequence(1)
   public void testObtenerAuditoriaByUsuario() {

      TypedQuery<Auditoria> query = em.createQuery(
         "select a from Auditoria a where a.usuario.username = :username", Auditoria.class);
      query.setParameter("username", "mgonzalez");
      Auditoria auditoria = query.getSingleResult();

      // Comprovam el tipus de l'avis seleccionat
      Assert.assertEquals(TipoElemento.ENLACE, auditoria.getElemento());

   }

}
