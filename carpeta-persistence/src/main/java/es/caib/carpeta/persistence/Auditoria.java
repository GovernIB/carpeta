package es.caib.carpeta.persistence;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 19/05/2020
 */

@Entity
@SequenceGenerator(name="audit-sequence",sequenceName = "CAR_AUDITORIA_SEQ", allocationSize = 1)
@Table(name = "CAR_AUDITORIA",

   indexes = {
      @Index(name = "CAR_AUDITORIA_PK_I", columnList = "ID"),
      @Index(name = "CAR_AUDIT_USUARIO_FK_I", columnList = "USUARIO"),
      @Index(name = "CAR_AUDIT_ENTIDAD_FK_I", columnList = "ENTIDAD")
   }
)

@Schema(name = "Auditoria")
public class Auditoria implements Serializable {


   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit-sequence")
   @Column(name = "ID", nullable = false, length = 19)
   private Long id;


   /**
    * Acción realizada (creado, eliminado, activado)
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "ACCION", nullable = false)
   private TipoAccion accion;

   /**
    * Elemento del backoffice sobre el que se realiza la acción(plugin, entidad, etc)
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "ELEMENTO", nullable = false)
   private TipoElemento elemento;

   /**
    * Data de creación. Debe ser el dia de hoy o un dia pasado (no puede ser futuro).
    * En la serializacion/deserialización JSON s'empra el format dd-mm-aaaa.
    */
   @NotNull
   @PastOrPresent
   @Column(name = "FECHA", nullable = false)
   @JsonbDateFormat("dd-MM-yyyy")
   private Date fecha;


   /**
    * Usuario
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "USUARIO", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_AUDIT_USUARIO_FK"))
   @JsonbTransient
   private Usuario usuario;


   /**
    * Entidad
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENTIDAD", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_AUDIT_ENTIDAD_FK"))
   @JsonbTransient
   private Entidad entidad;


   public Auditoria() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public TipoAccion getAccion() {
      return accion;
   }

   public void setAccion(TipoAccion accion) {
      this.accion = accion;
   }

   public TipoElemento getElemento() {
      return elemento;
   }

   public void setElemento(TipoElemento elemento) {
      this.elemento = elemento;
   }

   public Date getFecha() {
      return fecha;
   }

   public void setFecha(Date fecha) {
      this.fecha = fecha;
   }


   public Usuario getUsuario() {
      return usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public Entidad getEntidad() {
      return entidad;
   }

   public void setEntidad(Entidad entidad) {
      this.entidad = entidad;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Auditoria auditoria = (Auditoria) o;
      return id.equals(auditoria.id);
   }



   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String toString() {
      return "Auditoria{" +
         "id=" + id +
         ", accion='" + accion + '\'' +
         ", elemento='" + elemento + '\'' +
         '}';
   }
}
