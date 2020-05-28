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
 * Date: 20/05/2020
 */

@Entity
@SequenceGenerator(name="log-sequence",sequenceName = "CAR_LOG_SEQ", allocationSize = 1)
@Table(name = "CAR_LOG",
   indexes = {
      @Index(name = "CAR_LOG_PK_I", columnList = "ID"),
      @Index(name = "CAR_LOG_PLUGIN_FK_I", columnList = "PLUGIN"),
      @Index(name = "CAR_LOG_ENTIDAD_FK_I", columnList = "ENTIDAD")
   }
)

@Schema(name = "Log")
public class Log implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log-sequence")
   @Column(name = "ID", nullable = false, length = 19)
   private Long id;

   /**
    *Tipo del Log
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "TIPO", nullable = false)
   private TipoLog tipo;

   /**
    *Estado del log
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "ESTADO", nullable = false)
   private EstadoLog estado;

   /**
    * Tiempo que ha tardado el log
    */
   @NotNull
   @Column(name = "TIEMPO", nullable = false)
   private Long tiempo;

   /**
    * Fecha de creación del log
    */
   @NotNull
   @PastOrPresent
   @Column(name = "FECHA", nullable = false)
   @JsonbDateFormat("dd-MM-yyyy")
   private Date fecha;


   /**
    * Descripción del log
    */
   @Column(name = "DESCRIPCION")
   private String descripcion;

   /**
    * Datos de la petición del log
    */
   @Column(name = "PETICION")
   private String peticion;

   /**
    * Traza del error del log
    */
   @Column(name = "ERROR")
   private String error;

   /**
    * Excepción que se ha producido
    */
   @Column(name = "EXCEPCION")
   private String excepcion;



   /**
    * Plugin
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "PLUGIN", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_LOG_PLUGIN_FK"))
   @JsonbTransient
   private Plugin plugin;

   /**
    * Entidad
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENTIDAD", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_LOG_ENTIDAD_FK"))
   @JsonbTransient
   private Entidad entidad;

   public Log() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public TipoLog getTipo() {
      return tipo;
   }

   public void setTipo(TipoLog tipo) {
      this.tipo = tipo;
   }

   public EstadoLog getEstado() {
      return estado;
   }

   public void setEstado(EstadoLog estado) {
      this.estado = estado;
   }

   public Date getFecha() {
      return fecha;
   }

   public void setFecha(Date fecha) {
      this.fecha = fecha;
   }

   public Long getTiempo() {
      return tiempo;
   }

   public void setTiempo(Long tiempo) {
      this.tiempo = tiempo;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getPeticion() {
      return peticion;
   }

   public void setPeticion(String peticion) {
      this.peticion = peticion;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }

   public String getExcepcion() {
      return excepcion;
   }

   public void setExcepcion(String excepcion) {
      this.excepcion = excepcion;
   }

   public Plugin getPlugin() {
      return plugin;
   }

   public void setPlugin(Plugin plugin) {
      this.plugin = plugin;
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
      Log log = (Log) o;
      return id.equals(log.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String toString() {
      return "Log{" +
         "tipo=" + tipo +
         ", descripcion='" + descripcion + '\'' +
         '}';
   }
}
