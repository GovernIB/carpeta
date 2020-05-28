package es.caib.carpeta.persistence;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 20/05/2020
 */

@Embeddable
public class UsuarioEntidadId implements Serializable {

   @Column(name = "USUARIO_ID")
   private Long usuarioId;

   @Column(name = "ENTIDAD_ID")
   private Long entidadId;


   private UsuarioEntidadId() {}


   public UsuarioEntidadId(Long usuarioId, Long entidadId) {
      this.usuarioId = usuarioId;
      this.entidadId = entidadId;
   }

   public Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   public Long getEntidadId() {
      return entidadId;
   }

   public void setEntidadId(Long entidadId) {
      this.entidadId = entidadId;
   }



   @Override
   public boolean equals(Object o) {
      if (this == o) return true;

      if (o == null || getClass() != o.getClass())
         return false;

      UsuarioEntidadId that = (UsuarioEntidadId) o;
      return Objects.equals(usuarioId, that.usuarioId) &&
         Objects.equals(entidadId, that.entidadId);
   }

   @Override
   public int hashCode() {
      return Objects.hash(usuarioId, entidadId);
   }
}

