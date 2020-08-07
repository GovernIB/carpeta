package es.caib.carpeta.persistence;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 11/05/2020
 *
 * Representación del conjunto de datos que conforman una traducción base.
 */
@Embeddable
public class TraduccionBase implements Traduccion {

   private String nombre;

   @NotNull
   @Column(name = "NOMBRE", nullable = false)
   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }



   @Column(name = "DESCRIPCION")
   private String descripcion;

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }
}
