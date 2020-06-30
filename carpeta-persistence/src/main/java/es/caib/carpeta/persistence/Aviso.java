package es.caib.carpeta.persistence;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 21/05/2020
 */
@Entity
@SequenceGenerator(name="aviso-sequence",sequenceName = "CAR_AVISO_SEQ", allocationSize = 1)
@Table(name = "CAR_AVISO",

   indexes = {
      @Index(name = "CAR_AVISO_PK_I", columnList = "ID"),
      @Index(name = "CAR_AVISO_ENTIDAD_FK_I", columnList = "ENTIDAD")
   }
)

@Schema(name = "Aviso")
public class Aviso extends Traducible<TraduccionAviso> implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aviso-sequence")
   @Column(name = "ID", nullable = false, length = 19)
   private Long id;

   /**
    * Fecha de Inicio del aviso.
    * En la serializacion/deserialización JSON s'empra el format dd-mm-aaaa.
    */
   @NotNull
   @Column(name = "FECHA_INICIO", nullable = false)
   @JsonbDateFormat("dd-MM-yyyy")
   private Date fechaInicio;


   /**
    * Fecha de Fin del aviso.
    * En la serializacion/deserialización JSON s'empra el format dd-mm-aaaa.
    */
   @NotNull
   @Column(name = "FECHA_FIN", nullable = false)
   @JsonbDateFormat("dd-MM-yyyy")
   private Date fechaFin;


   /**
    *Idioma con el que se ha autenticado
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "TIPO", nullable = false)
   private TipoAviso tipo;


   /**
    * Entidad
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENTIDAD", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_AVISO_ENTIDAD_FK"))
   @JsonbTransient
   private Entidad entidad;

   /**
    * Traducciones
    */
   /**
    * Traducciones
    */
   @ElementCollection(fetch =FetchType.LAZY, targetClass = TraduccionAviso.class )
   @CollectionTable(name = "CAR_TRA_AVISO",
      joinColumns = {@JoinColumn(name = "IDAVISO", referencedColumnName = "id")},foreignKey = @ForeignKey(name = "CAR_AVISO_TRAAVISO_FK"))
   @MapKeyColumn(name="LANG")
   private Map<String, TraduccionAviso> traducciones  = new HashMap<>();

   @Override
   public Map<String, TraduccionAviso> getTraducciones() {
      return traducciones;
   }


   public Aviso() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   public Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(Date fechaFin) {
      this.fechaFin = fechaFin;
   }

   public TipoAviso getTipo() {
      return tipo;
   }

   public void setTipo(TipoAviso tipo) {
      this.tipo = tipo;
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
      Aviso aviso = (Aviso) o;
      return id.equals(aviso.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String toString() {
      return "Aviso{" +
         "id=" + id +
         ", tipo=" + tipo +
         ", entidad=" + entidad +
         '}';
   }
}
