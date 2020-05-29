package es.caib.carpeta.persistence;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
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
@SequenceGenerator(name="acceso-sequence",sequenceName = "CAR_ACCESO_SEQ", allocationSize = 1)
@Table(name = "CAR_ACCESO",
   indexes = {
      @Index(name = "CAR_ACCESO_PK_I", columnList = "ID"),
      @Index(name = "CAR_ACCESO_ENTIDAD_FK_I", columnList = "ENTIDAD")
   }
)

@Schema(name = "Acceso")
public class Acceso implements Serializable {


   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acceso-sequence")
   @Column(name = "ID", nullable = false, length = 19)
   private Long id;

   /**
    * Nombre del usuario autenticado
    */
   @NotNull
   @Column(name = "NOMBRE", nullable = false)
   private String nombre;

   /**
    * Apellidos del usuario autenticado
    */
   @NotNull
   @Column(name = "APELLIDOS", nullable = false)
   private String apellidos;

   /**
    * Nif del usuario autenticado.
    *
    */
   @NotNull
   @Column(name="NIF", nullable = false, length = 9)
   private String nif;

   /**
    * dirección ip del usuario autenticado
    */
   @NotNull
   @Pattern(regexp = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$", message = "{persistence.Acceso.nif.Pattern.message}")
   @Column(name = "DIRECCION_IP", nullable = false)
   private String direccionIP;

   /**
    * Proveedor de la entidad
    */
   @Column(name = "PROVEEDOR_ENTIDAD")
   private String proveedorEntidad;

   /**
    *Nivel de seguridad (qaa level)
    */
   @Column(name = "NIVEL_SEGURIDAD")
   private String nivelSeguridad;

   /**
    *Resultado de la autenticación
    */
   @Column(name = "RESULT_AUTENTICACION")
   private String resultadoAutenticacion;


   /**
    * Fecha de cuando se produjo el ultimo acceso. Debe ser el dia de hoy o un dia pasado (no puede ser futuro).
    * En la serializacion/deserialización JSON s'empra el format dd-mm-aaaa.
    */
   @NotNull
   @PastOrPresent
   @Column(name = "FECHA_ULTIMO_ACCESO", nullable = false)
   @JsonbDateFormat("dd-MM-yyyy")
   private Date fechaUltimoAcceso;

   /**
    *Idioma con el que se ha autenticado
    */
   @NotNull
   @Enumerated(EnumType.STRING)
   @Column(name = "IDIOMA", nullable = false)
   private Idioma idioma;


   /**
    * Entidad
    */
   @NotNull
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENTIDAD", nullable = false,
      foreignKey = @ForeignKey(name = "CAR_ACCESO_ENTIDAD_FK"))
   @JsonbTransient
   private Entidad entidad;


   public Acceso() {
   }

   public Acceso(@NotNull String nombre, @NotNull String apellidos, @NotNull @Pattern(regexp = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$", message = "{persistence.Acceso.nif.Pattern.message}") String direccionIP, @NotNull Entidad entidad) {
      this.nombre = nombre;
      this.apellidos = apellidos;
      this.direccionIP = direccionIP;
      this.entidad = entidad;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getApellidos() {
      return apellidos;
   }

   public void setApellidos(String apellidos) {
      this.apellidos = apellidos;
   }

   public String getNif() {
      return nif;
   }

   public void setNif(String nif) {
      this.nif = nif;
   }

   public String getDireccionIP() {
      return direccionIP;
   }

   public void setDireccionIP(String direccionIP) {
      this.direccionIP = direccionIP;
   }

   public String getProveedorEntidad() {
      return proveedorEntidad;
   }

   public void setProveedorEntidad(String proveedorEntidad) {
      this.proveedorEntidad = proveedorEntidad;
   }

   public String getNivelSeguridad() {
      return nivelSeguridad;
   }

   public void setNivelSeguridad(String nivelSeguridad) {
      this.nivelSeguridad = nivelSeguridad;
   }

   public String getResultadoAutenticacion() {
      return resultadoAutenticacion;
   }

   public void setResultadoAutenticacion(String resultadoAutenticacion) {
      this.resultadoAutenticacion = resultadoAutenticacion;
   }

   public Date getFechaUltimoAcceso() {
      return fechaUltimoAcceso;
   }

   public void setFechaUltimoAcceso(Date fechaUltimoAcceso) {
      this.fechaUltimoAcceso = fechaUltimoAcceso;
   }

   public Idioma getIdioma() {
      return idioma;
   }

   public void setIdioma(Idioma idioma) {
      this.idioma = idioma;
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
      Acceso acceso = (Acceso) o;
      return id.equals(acceso.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String toString() {
      return "Acceso{" +
         "id=" + id +
         ", direccionIP='" + direccionIP + '\'' +
         '}';
   }
}
