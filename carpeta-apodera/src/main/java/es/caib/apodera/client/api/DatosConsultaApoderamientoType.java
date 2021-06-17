
package es.caib.apodera.client.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosConsultaApoderamientoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosConsultaApoderamientoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codApoderamientoEXT" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="codApoderamientoINT" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="tipoApoderamiento" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}tipoApoderamientoType" minOccurs="0"/&gt;
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="periodoRegistro" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}periodoType" minOccurs="0"/&gt;
 *         &lt;element name="datosPoderdante" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}datosPoderdanteType" minOccurs="0"/&gt;
 *         &lt;element name="datosApoderado" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}datosApoderadoType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosConsultaApoderamientoType", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", propOrder = {
    "codApoderamientoEXT",
    "codApoderamientoINT",
    "tipoApoderamiento",
    "estado",
    "periodoRegistro",
    "datosPoderdante",
    "datosApoderado"
})
public class DatosConsultaApoderamientoType {

    protected Long codApoderamientoEXT;
    protected Long codApoderamientoINT;
    protected TipoApoderamientoType tipoApoderamiento;
    protected String estado;
    protected PeriodoType periodoRegistro;
    protected DatosPoderdanteType datosPoderdante;
    protected DatosApoderadoType datosApoderado;

    /**
     * Obtiene el valor de la propiedad codApoderamientoEXT.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodApoderamientoEXT() {
        return codApoderamientoEXT;
    }

    /**
     * Define el valor de la propiedad codApoderamientoEXT.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodApoderamientoEXT(Long value) {
        this.codApoderamientoEXT = value;
    }

    /**
     * Obtiene el valor de la propiedad codApoderamientoINT.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodApoderamientoINT() {
        return codApoderamientoINT;
    }

    /**
     * Define el valor de la propiedad codApoderamientoINT.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodApoderamientoINT(Long value) {
        this.codApoderamientoINT = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoApoderamiento.
     * 
     * @return
     *     possible object is
     *     {@link TipoApoderamientoType }
     *     
     */
    public TipoApoderamientoType getTipoApoderamiento() {
        return tipoApoderamiento;
    }

    /**
     * Define el valor de la propiedad tipoApoderamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoApoderamientoType }
     *     
     */
    public void setTipoApoderamiento(TipoApoderamientoType value) {
        this.tipoApoderamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad periodoRegistro.
     * 
     * @return
     *     possible object is
     *     {@link PeriodoType }
     *     
     */
    public PeriodoType getPeriodoRegistro() {
        return periodoRegistro;
    }

    /**
     * Define el valor de la propiedad periodoRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodoType }
     *     
     */
    public void setPeriodoRegistro(PeriodoType value) {
        this.periodoRegistro = value;
    }

    /**
     * Obtiene el valor de la propiedad datosPoderdante.
     * 
     * @return
     *     possible object is
     *     {@link DatosPoderdanteType }
     *     
     */
    public DatosPoderdanteType getDatosPoderdante() {
        return datosPoderdante;
    }

    /**
     * Define el valor de la propiedad datosPoderdante.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosPoderdanteType }
     *     
     */
    public void setDatosPoderdante(DatosPoderdanteType value) {
        this.datosPoderdante = value;
    }

    /**
     * Obtiene el valor de la propiedad datosApoderado.
     * 
     * @return
     *     possible object is
     *     {@link DatosApoderadoType }
     *     
     */
    public DatosApoderadoType getDatosApoderado() {
        return datosApoderado;
    }

    /**
     * Define el valor de la propiedad datosApoderado.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosApoderadoType }
     *     
     */
    public void setDatosApoderado(DatosApoderadoType value) {
        this.datosApoderado = value;
    }

}
