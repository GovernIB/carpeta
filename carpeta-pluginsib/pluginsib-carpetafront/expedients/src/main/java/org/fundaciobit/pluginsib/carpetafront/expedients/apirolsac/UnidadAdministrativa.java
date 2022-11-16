package org.fundaciobit.pluginsib.carpetafront.expedients.apirolsac;

/**
 * 
 * @author anadal
 *
 */
public class UnidadAdministrativa {

    public LinkPadre link_padre;
    public String presentacion;

    public String abreviatura;
    public String codigo;
    public String codigoDIR3;
    public String codigoEstandar;
    public String fax;
    public String idioma;
    public LinkFotog link_fotog;
    public LinkFotop link_fotop;
    public LinkLogoh link_logoh;
    public LinkLogot link_logot;
    public LinkLogov link_logov;
    public String nombre;
    public String numfoto1;
    public String numfoto2;
    public String numfoto3;
    public String numfoto4;
    public String orden;
    public String responsable;
    public String sexoResponsable;
    public String telefono;
    public Tratamiento tratamiento;
    public String validacion;

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoDIR3() {
        return codigoDIR3;
    }

    public void setCodigoDIR3(String codigoDIR3) {
        this.codigoDIR3 = codigoDIR3;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public LinkPadre getLink_padre() {
        return link_padre;
    }

    public void setLink_padre(LinkPadre link_padre) {
        this.link_padre = link_padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumfoto1() {
        return numfoto1;
    }

    public void setNumfoto1(String numfoto1) {
        this.numfoto1 = numfoto1;
    }

    public String getNumfoto2() {
        return numfoto2;
    }

    public void setNumfoto2(String numfoto2) {
        this.numfoto2 = numfoto2;
    }

    public String getNumfoto3() {
        return numfoto3;
    }

    public void setNumfoto3(String numfoto3) {
        this.numfoto3 = numfoto3;
    }

    public String getNumfoto4() {
        return numfoto4;
    }

    public void setNumfoto4(String numfoto4) {
        this.numfoto4 = numfoto4;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getSexoResponsable() {
        return sexoResponsable;
    }

    public void setSexoResponsable(String sexoResponsable) {
        this.sexoResponsable = sexoResponsable;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public class LinkFotog {
        public String codigo;
        public String href;
        public String rel;
    }

    public class LinkFotop {
        public String codigo;
        public String href;
        public String rel;
    }

    public class LinkLogoh {
        public String codigo;
        public String href;
        public String rel;
    }

    public class LinkLogot {
        public String codigo;
        public String href;
        public String rel;
    }

    public class LinkLogov {
        public String codigo;
        public String href;
        public String rel;
    }

    public String getCodigoEstandar() {
        return codigoEstandar;
    }

    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public LinkFotog getLink_fotog() {
        return link_fotog;
    }

    public void setLink_fotog(LinkFotog link_fotog) {
        this.link_fotog = link_fotog;
    }

    public LinkFotop getLink_fotop() {
        return link_fotop;
    }

    public void setLink_fotop(LinkFotop link_fotop) {
        this.link_fotop = link_fotop;
    }

    public LinkLogoh getLink_logoh() {
        return link_logoh;
    }

    public void setLink_logoh(LinkLogoh link_logoh) {
        this.link_logoh = link_logoh;
    }

    public LinkLogot getLink_logot() {
        return link_logot;
    }

    public void setLink_logot(LinkLogot link_logot) {
        this.link_logot = link_logot;
    }

    public LinkLogov getLink_logov() {
        return link_logov;
    }

    public void setLink_logov(LinkLogov link_logov) {
        this.link_logov = link_logov;
    }

}
