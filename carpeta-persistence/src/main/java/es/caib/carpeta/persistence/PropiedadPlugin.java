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
@SequenceGenerator(name="propplugin-sequence",sequenceName = "CAR_PROPPLUGIN_SEQ", allocationSize = 1)
@Table(name = "CAR_PROPIEDAD_PLUGIN",
   uniqueConstraints = {
      @UniqueConstraint(name = "CAR_PROPPLUGIN_CODIGO_UK", columnNames = "CODIGO")

   },

   indexes = {
      @Index(name = "CAR_PROPPLUGIN_PK_I", columnList = "ID"),
      @Index(name = "CAR_PROPPLUGIN_CODIGO_UK_I", columnList = "CODIGO"),
      @Index(name = "CAR_PROPPLUG_PLUGIN_FK_I", columnList = "PLUGIN")
   }
)

@Schema(name = "PropiedadPlugin")
public class PropiedadPlugin implements Serializable {


   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propiedad-sequence")
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
    * Plugin
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "PLUGIN", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_PROPPLUG_PLUGIN_FK"))
   @JsonbTransient
   private Plugin plugin;

   public PropiedadPlugin() {
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

   public Plugin getPlugin() {
      return plugin;
   }

   public void setPlugin(Plugin plugin) {
      this.plugin = plugin;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      if (!super.equals(o)) return false;
      PropiedadPlugin that = (PropiedadPlugin) o;
      return codigo.equals(that.codigo);
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), codigo);
   }

   @Override
   public String toString() {
      return "PropiedadPlugin{" +
         "codigo='" + codigo + '\'' +
         ", descripcion='" + descripcion + '\'' +
         '}';
   }
}
