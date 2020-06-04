package es.caib.carpeta.persistence;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 20/05/2020
 */
@Entity
@SequenceGenerator(name="enlace-sequence",sequenceName = "CAR_ENLACE_SEQ", allocationSize = 1)
@Table(name = "CAR_ENLACE",
   indexes = {
      @Index(name = "CAR_ENLACE_PK_I", columnList = "ID"),
      @Index(name = "CAR_ENLACE_ENTIDAD_FK_I", columnList = "ENTIDAD")
   }
)

@Schema(name = "Enlace")
public class Enlace extends Traducible<TraduccionEnlace> implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enlace-sequence")
   @Column(name = "ID", nullable = false, length = 19)
   private Long id;

   /**
    *Tipo del enlace
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "TIPO", nullable = false)
   private TipoEnlace tipo;


   /**
    * Entidad
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENTIDAD", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_ENLACE_ENTIDAD_FK"))
   @JsonbTransient
   private Entidad entidad;


   /**
    * Traducciones
    */
   @ElementCollection(fetch =FetchType.LAZY, targetClass = TraduccionEnlace.class )
   @CollectionTable(name = "CAR_TRA_ENLACE",
      joinColumns = {@JoinColumn(name = "IDENLACE", referencedColumnName = "id")},foreignKey = @ForeignKey(name = "CAR_ENLACE_TRAENLAC_FK"))
   @MapKeyColumn(name="LANG", insertable = false, updatable = false)
   private Map<String, TraduccionEnlace> traducciones;

   @Override
   public Map<String, TraduccionEnlace> getTraducciones() {
      return traducciones;
   }


   public Enlace() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public TipoEnlace getTipo() {
      return tipo;
   }

   public void setTipo(TipoEnlace tipo) {
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
      Enlace enlace = (Enlace) o;
      return id.equals(enlace.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String toString() {
      return "Enlace{" +
         "id=" + id +
         ", tipo=" + tipo +
         '}';
   }
}
