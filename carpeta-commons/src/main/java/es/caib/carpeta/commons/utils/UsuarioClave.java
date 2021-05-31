package es.caib.carpeta.commons.utils;

/**
 * 
 * @author anadal
 *
 */
public class UsuarioClave {

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nif;
    private String metodoAutentificacion;
    private int qaa;
    private String proveedorDeIdentidad;
    private boolean empresa;

    private UsuarioClaveRepresentante usuarioClaveRepresentante;

    public UsuarioClave(String nombre, String apellido1, String apellido2, String nif, boolean empresa,
            String metodoAutentificacion, int qaa, String proveedorDeIdentidad,
            UsuarioClaveRepresentante usuarioClaveRepresentante) {
        super();
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nif = nif;
        this.empresa = empresa;
        this.metodoAutentificacion = metodoAutentificacion;
        this.qaa = qaa;
        this.proveedorDeIdentidad = proveedorDeIdentidad;
        this.usuarioClaveRepresentante = usuarioClaveRepresentante;
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public boolean isEmpresa() {
        return empresa;
    }

    public void setEmpresa(boolean empresa) {
        this.empresa = empresa;
    }

    public String getMetodoAutentificacion() {
        return metodoAutentificacion;
    }

    public void setMetodoAutentificacion(String metodoAutentificacion) {
        this.metodoAutentificacion = metodoAutentificacion;
    }

    public int getQaa() {
        return qaa;
    }

    public void setQaa(int qaa) {
        this.qaa = qaa;
    }

    public String getProveedorDeIdentidad() {
        return proveedorDeIdentidad;
    }

    public void setProveedorDeIdentidad(String proveedorDeIdentidad) {
        this.proveedorDeIdentidad = proveedorDeIdentidad;
    }

    public UsuarioClaveRepresentante getUsuarioClaveRepresentante() {
        return usuarioClaveRepresentante;
    }

    public void setUsuarioClaveRepresentante(UsuarioClaveRepresentante usuarioClaveRepresentante) {
        this.usuarioClaveRepresentante = usuarioClaveRepresentante;
    }

    public String getNombreCompleto() {

        String nombreCompleto = "";

        if (StringUtils.isNotEmpty(getApellido1()) && StringUtils.isNotEmpty(getApellido2())) {
            nombreCompleto += getApellido1() + " " + getApellido2() + ", ";
        } else if (StringUtils.isNotEmpty(getApellido1())) {
            nombreCompleto += getApellido1() + ", ";
        }

        nombreCompleto += getNombre();

        return nombreCompleto;
    }
}
