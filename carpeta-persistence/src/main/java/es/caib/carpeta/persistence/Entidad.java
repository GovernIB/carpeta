package es.caib.carpeta.persistence;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.*;


/**
 * Created by Fundació BIT.
 * @author mgonzalez
 * Date: 11/05/2020
 *
 */

@Entity
@SequenceGenerator(name="entidad-sequence",sequenceName = "CAR_ENTIDAD_SEQ", allocationSize = 1)
@Table(name = "CAR_ENTIDAD",
   uniqueConstraints = {
      @UniqueConstraint(name = "CAR_ENTIDAD_CODIGODIR3_UK", columnNames = "CODIGODIR3")
   },
   indexes = {
      @Index(name = "CAR_ENTIDAD_PK_I", columnList = "ID"),
      @Index(name = "CAR_ENTIDAD_CODIGODIR3_UK_I", columnList = "CODIGODIR3"),
      @Index(name = "CAR_ENTIDAD_LOGOMENU_FK_I", columnList = "LOGOMENU"),
      @Index(name = "CAR_ENTIDAD_LOGOPIE_FK_I", columnList = "LOGOPIE"),
      @Index(name = "CAR_ENTIDAD_FICHEROCSS_FK_I", columnList = "FICHEROCSS")
   }
)


@Schema(name = "Entidad")
public class Entidad extends Traducible<TraduccionBase> implements Serializable  {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidad-sequence")
    @Column(name = "ID", nullable = false, length = 19)
    private Long id;


    /**
     * Código DiR3 que identifica la entidad. Es único, i por tanto una clave natural.
     *
     */
    @NotNull
    @Pattern(regexp = "[AEIJLU][0-9]{8}", message = "{persistence.Entidad.codigodir3.Pattern.message}")
    @Column(name="CODIGODIR3", nullable = false, unique = true, length = 9)
    private String codigoDir3;



    /**
     * Lista de administradores de la entidad que son UsuarioEntidad
     */
    /**
     * Buenas practicas
     * //https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
     */
    @OneToMany(
       mappedBy = "entidad",
       cascade = CascadeType.ALL,
       orphanRemoval = true
    )
    private List<UsuarioEntidad> administradores = new ArrayList<>();

    /**
     * Color del menú de la entidad
     */
    @Column(name="COLORMENU")
    private String colorMenu = "#ff9523";

    /**
     * Texto del pie de la entidad
     */
    @Column(name = "TEXTOPIE", length = 4000)
    private String textoPie;


    /**
     * Logo del menú de la entidad
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGOMENU",
       foreignKey = @ForeignKey(name = "CAR_ENTIDAD_LOGOMENU_FK"))
    @JsonbTransient
    private Archivo logoMenu;


    /**
     * Logo del pie de la entidad
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGOPIE",
       foreignKey = @ForeignKey(name = "CAR_ENTIDAD_LOGOPIE_FK"))
    @JsonbTransient
    private Archivo logoPie;


    /**
     * Fichero de estilos de la entidad
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FICHEROCSS",
       foreignKey = @ForeignKey(name = "CAR_ENTIDAD_FICHEROCSS_FK"))
    @JsonbTransient
    private Archivo ficheroCss;

    /**
     * Indica si la entidad está activa o no
     */
    @NotNull
    @Column(name="ACTIVO",nullable= false)
    private Boolean activa = true;

    /**
     * url del commit actual en marcha
     */
    @Column(name="COMIT")
    private String comit;

    /**
     * Contexto de la entidad
     */
    @NotNull
    @Column(name="CONTEXTO", nullable = false)
    private String contexto;

    /**
     * versión actual en marcha
     */
    @Column(name="VERSION")
    private String version;


    /**
     * Traducciones
     */
    @ElementCollection(fetch =FetchType.LAZY, targetClass = TraduccionBase.class )
    @CollectionTable(name = "CAR_TRA_ENTIDAD",
       joinColumns = {@JoinColumn(name = "IDENTIDAD", referencedColumnName = "id")},foreignKey = @ForeignKey(name = "CAR_ENTIDAD_TRAENTID_FK"))
    @MapKeyColumn(name="LANG")
    private Map<String, TraduccionBase> traducciones = new HashMap<>();




    public Entidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCodigoDir3() {
        return codigoDir3;
    }

    public void setCodigoDir3(String codigoDir3) {
        this.codigoDir3 = codigoDir3;
    }


    public String getColorMenu() {
        return colorMenu;
    }

    public void setColorMenu(String colorMenu) {
        this.colorMenu = colorMenu;
    }


    public String getTextoPie() {
        return textoPie;
    }

    public void setTextoPie(String textoPie) {
        this.textoPie = textoPie;
    }


    public Archivo getLogoMenu() {
        return logoMenu;
    }

    public void setLogoMenu(Archivo logoMenu) {
        this.logoMenu = logoMenu;
    }


    public Archivo getLogoPie() {
        return logoPie;
    }

    public void setLogoPie(Archivo logoPie) {
        this.logoPie = logoPie;
    }


    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public Archivo getFicheroCss() {
        return ficheroCss;
    }

    public void setFicheroCss(Archivo ficheroCss) {
        this.ficheroCss = ficheroCss;
    }

    public String getComit() {
        return comit;
    }

    public void setComit(String comit) {
        this.comit = comit;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<UsuarioEntidad> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<UsuarioEntidad> administradores) {
        this.administradores = administradores;
    }


    /**
     *
     * Buenas practicas ver enlace arriba
     */
    public void addAdministrador(Usuario usuario) {
        UsuarioEntidad usuarioEntidad = new UsuarioEntidad(usuario, this);
        administradores.add(usuarioEntidad);

    }

    public void removeAdministrador(Usuario usuario) {
        for (Iterator<UsuarioEntidad> iterator = administradores.iterator();
             iterator.hasNext(); ) {
            UsuarioEntidad usuarioEntidad = iterator.next();

            if (usuarioEntidad.getEntidad().equals(this) &&
               usuarioEntidad.getUsuario().equals(usuario)) {
                iterator.remove();
                usuarioEntidad.setEntidad(null);
                usuarioEntidad.setUsuario(null);
            }
        }
    }

    @Override
    public Map<String, TraduccionBase> getTraducciones() {
        return this.traducciones;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entidad entidad = (Entidad) o;
        return getCodigoDir3().equals(entidad.getCodigoDir3());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoDir3());
    }

    @Override
    public String toString() {
        return "Entidad{" +
           "id=" + id +
           ", codigoDir3='" + codigoDir3 + '\'' +
           '}';
    }


}
