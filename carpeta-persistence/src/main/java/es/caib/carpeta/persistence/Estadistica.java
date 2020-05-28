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
 * Esta clase representa las acciones que se realizan en el front
 *
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 19/05/2020
 */
@Entity
@SequenceGenerator(name="estadis-sequence",sequenceName = "CAR_ESTADISTICA_SEQ", allocationSize = 1)
@Table(name = "CAR_ESTADISTICA",

   indexes = {
      @Index(name = "CAR_ESTADISTICA_PK_I", columnList = "ID"),
      @Index(name = "CAR_ESTAD_ACCESO_FK_I", columnList = "ACCESO"),
      @Index(name = "CAR_ESTAD_ENTIDAD_FK_I", columnList = "ENTIDAD"),
   }
)

@Schema(name = "Estadistica")
public class Estadistica implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estadis-sequence")
   @Column(name = "ID", nullable = false, length = 19)
   private Long id;


   /**
    * Acción realizada (creado, eliminado, activado,iniciado)
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "ACCION", nullable = false)
   private TipoAccion accion;

   /**
    * Elemento del frontoffice sobre el que se realiza la acción(tramite, registro, etc)
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "ELEMENTO", nullable = false)
   private TipoElemento elemento;

   /**
    * Data de creación. Debe ser el dia de hoy o un dia pasado (no puede ser futuro).
    * En la serialitzacio/deserialització JSON s'empra el format dd-mm-aaaa.
    */
   @NotNull
   @PastOrPresent
   @Column(name = "FECHA", nullable = false)
   @JsonbDateFormat("dd-MM-yyyy")
   private Date fecha;


   /**
    * Acceso ( un usuario que se ha conectado al front)
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ACCESO", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_ESTAD_ACCESO_FK"))
   @JsonbTransient
   private Acceso acceso;


   /**
    * Entidad
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENTIDAD", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_ESTAD_ENTIDAD_FK"))
   @JsonbTransient
   private Entidad entidad;


   public Estadistica() {
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


   public Acceso getAcceso() {
      return acceso;
   }

   public void setAcceso(Acceso acceso) {
      this.acceso = acceso;
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
      Estadistica that = (Estadistica) o;
      return id.equals(that.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String toString() {
      return "Estadistica{" +
         "id=" + id +
         ", accion='" + accion + '\'' +
         ", elemento='" + elemento + '\'' +
         '}';
   }
}
