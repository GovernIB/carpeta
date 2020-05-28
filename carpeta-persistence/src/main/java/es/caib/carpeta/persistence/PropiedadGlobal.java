package es.caib.carpeta.persistence;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 21/05/2020
 */

@Entity
@SequenceGenerator(name="propglobal-sequence",sequenceName = "CAR_PROPGLOBAL_SEQ", allocationSize = 1)
@Table(name = "CAR_PROPIEDAD_GLOBAL",
   uniqueConstraints = {
      @UniqueConstraint(name = "CAR_PROPGLOBAL_CODIGO_UK", columnNames = "CODIGO")
   },

   indexes = {
      @Index(name = "CAR_PROPGLOBAL_PK_I", columnList = "ID"),
      @Index(name = "CAR_PROPGLOBAL_CODIGO_UK_I", columnList = "CODIGO"),
      @Index(name = "CAR_PROPGLOB_ENTIDAD_FK_I", columnList = "ENTIDAD")
   }
)

@Schema(name = "PropiedadGlobal")
public class PropiedadGlobal implements Serializable {



   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propglobal-sequence")
   @Column(name = "ID", nullable = false, length = 19)
   private Long id;

   /**
    * Código de la propiedad
    */
   @NotNull
   @Column(name = "CODIGO", unique = true, nullable = false)
   private String codigo;

   /**
    * Valor de la propiedad
    */
   @Column(name = "VALOR")
   private String valor;

   /**
    * Descripción de la propiedad
    */
   @Column(name = "DESCRIPCION")
   private String descripcion;

   /**
    * Entidad
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENTIDAD", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_PROPGLOB_ENTIDAD_FK"))
   @JsonbTransient
   private Entidad entidad;

   public PropiedadGlobal() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getCodigo() {
      return codigo;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public String getValor() {
      return valor;
   }

   public void setValor(String valor) {
      this.valor = valor;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
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
      PropiedadGlobal that = (PropiedadGlobal) o;
      return codigo.equals(that.codigo);
   }

   @Override
   public int hashCode() {
      return Objects.hash(codigo);
   }

   @Override
   public String toString() {
      return "PropiedadGlobal{" +
         "codigo='" + codigo + '\'' +
         ", descripcion='" + descripcion + '\'' +
         '}';
   }
}
