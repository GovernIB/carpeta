package es.caib.carpeta.persistence;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Fundació BIT.
 * @author mgonzalez
 * Date: 14/05/2020
 *
 */

@Entity
@SequenceGenerator(name = "archivo-sequence", sequenceName = "CAR_ARCHIVO_SEQ", allocationSize = 1)
@Table(name = "CAR_ARCHIVO",
   indexes = {
      @Index(name = "CAR_ARCHIVO_PK_I", columnList = "ID")
   }
)
@Schema(name = "Archivo")
public class Archivo implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "archivo-sequence")
    @Column(name = "ID", nullable = false, length = 19)
    private Long id;


    /**
     * Nombre del archivo
     */
    @NotNull
    @Column(name="NOMBRE",nullable=false)
    private String nombre;

    /**
     * Mime del archivo
     */
    @NotNull
    @Column(name="MIME",nullable=false)
    private String mime;

    /**
     * Tamaño del archivo
     */
    @NotNull
    @Column(name="TAMANO",nullable=false)
    private Long tamano;


    public Archivo(){

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


    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }


    public Long getTamano() {
        return tamano;
    }

    public void setTamano(Long tamano) {
        this.tamano = tamano;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Archivo archivo = (Archivo) o;

        if (!Objects.equals(id, archivo.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Archivo{" +
           "id=" + id +
           ", nombre='" + nombre + '\'' +
           '}';
    }
}
