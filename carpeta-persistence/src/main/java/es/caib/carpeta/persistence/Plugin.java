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
 * Date: 21/05/2020
 */
@Entity
@SequenceGenerator(name="plugin-sequence",sequenceName = "CAR_PLUGIN_SEQ", allocationSize = 1)
@Table(name = "CAR_PLUGIN",

   indexes = {
      @Index(name = "CAR_PLUGIN_PK_I", columnList = "ID"),
      @Index(name = "CAR_PLUGIN_ENTIDAD_FK_I", columnList = "ENTIDAD")
   }
)

@Schema(name = "Plugin")
public class Plugin extends Traducible<TraduccionBase> implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plugin-sequence")
   @Column(name = "ID", nullable = false, length = 19)
   private Long id;

   /**
    * Clase del Plugin
    */
   @NotNull
   @Column(name = "CLASE", nullable = false)
   private String clase;

   /**
    * Prefijo de las propiedades Ex: es.caib.carpeta
    */
   @NotNull
   @Column(name = "PREFIJO_PROPS", nullable = false)
   private String prefijoPropiedades;

   /**
    * Prefijo de la entidad Ex: caib, cconsultiu
    */
   @NotNull
   @Column(name = "PREFIJO_ENTIDAD", nullable = false)
   private String prefijoEntidad;


   /**
    *Estado del plugin
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "ESTADO", nullable = false)
   private EstadoPlugin estado;



   /**
    *Tipo del plugin
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "TIPO", nullable = false)
   private TipoPlugin tipo;



   /**
    * Entidad
    */
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENTIDAD",
      foreignKey = @ForeignKey(name = "CAR_PLUGIN_ENTIDAD_FK"))
   @JsonbTransient
   private Entidad entidad;



   /**
    * Traducciones
    */
   @ElementCollection(fetch =FetchType.LAZY, targetClass = TraduccionBase.class )
   @CollectionTable(name = "CAR_TRA_PLUGIN",
      joinColumns = {@JoinColumn(name = "IDPLUGIN", referencedColumnName = "id")},foreignKey = @ForeignKey(name = "CAR_PLUGIN_TRAPLUG_FK"))
   @MapKeyColumn(name="LANG", insertable = false, updatable = false)
   private Map<String, TraduccionBase> traducciones;



   @Override
   public Map<String, TraduccionBase> getTraducciones() {
      return traducciones;
   }


   public Plugin() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getClase() {
      return clase;
   }

   public void setClase(String clase) {
      this.clase = clase;
   }

   public String getPrefijoPropiedades() {
      return prefijoPropiedades;
   }

   public void setPrefijoPropiedades(String prefijoPropiedades) {
      this.prefijoPropiedades = prefijoPropiedades;
   }

   public String getPrefijoEntidad() {
      return prefijoEntidad;
   }

   public void setPrefijoEntidad(String prefijoEntidad) {
      this.prefijoEntidad = prefijoEntidad;
   }

   public EstadoPlugin getEstado() {
      return estado;
   }

   public void setEstado(EstadoPlugin estado) {
      this.estado = estado;
   }

   public TipoPlugin getTipo() {
      return tipo;
   }

   public void setTipo(TipoPlugin tipo) {
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
      Plugin plugin = (Plugin) o;
      return id.equals(plugin.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String toString() {
      return "Plugin{" +
         "clase='" + clase + '\'' +
         '}';
   }
}
