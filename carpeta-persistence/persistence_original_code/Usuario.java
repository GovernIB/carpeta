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
@SequenceGenerator(name="usu-sequence",sequenceName = "CAR_USUARIO_SEQ", allocationSize = 1)
@Table(name = "CAR_USUARIO",
   uniqueConstraints = {
      @UniqueConstraint(name = "CAR_USERNAME_UK", columnNames = "USERNAME")
   },
   indexes = {
      @Index(name = "CAR_USUARIO_PK_I", columnList = "ID"),
      @Index(name = "CAR_USERNAME_UK_I", columnList = "USERNAME"),
   }
)

@Schema(name = "Usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usu-sequence")
    @Column(name = "ID", nullable = false, length = 19)
    private Long id;

    /**
     * Nombre del usuario
     */
    @NotNull
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    /**
     * Primer apellido del usuario
     */
    @Column(name = "APELLIDO1")
    private String apellido1;

    /**
     * Segundo apellido del usuario
     */
    @Column(name = "APELLIDO2")
    private String apellido2;


    /**
     * Email del usuario
     */
    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;


    /**
     * Username del usuario y por tanto una clave natural
     */
    @NotNull
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;


    /**
     * Documento identificativo del usuario
     */
    @Column(name = "DOCUMENTO")
    private String documento;


    /**
     * Tipo de persona (persona, aplicación)
     */
    @NotNull
    @Column(name = "TIPO", nullable = false)
    private TipoUsuario tipo;


    /**
     * Idioma del usuario
     */
    @NotNull
    @Column(name = "IDIOMA", nullable = false)
    private String idioma;




    public Usuario() {
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


    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }


    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    @Transient
    public String getNombreCompleto(){

        String nombreCompleto = getNombre();
        if(getApellido1() != null){
            nombreCompleto = nombreCompleto + " " + getApellido1();
        }
        if(getApellido2() != null){
            nombreCompleto = nombreCompleto + " " + getApellido2();
        }

        return nombreCompleto;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Transient
    public String getNombreUserName(){

        return getNombreCompleto() + " ("+getUsername()+")";
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;


        Usuario usuario = (Usuario) o;
        return username.equals(usuario.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "Usuario{" +
           "id=" + id +
           ", username='" + username + '\'' +
           '}';
    }

}
