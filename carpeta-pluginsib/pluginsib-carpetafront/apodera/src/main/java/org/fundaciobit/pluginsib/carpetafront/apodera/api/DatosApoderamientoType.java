
package org.fundaciobit.pluginsib.carpetafront.apodera.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosApoderamientoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosApoderamientoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anexos" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}anexoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="codApoderamientoEXT" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="codApoderamientoINT" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="datosApoderado" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}datosApoderadoCompletoType" minOccurs="0"/&gt;
 *         &lt;element name="datosPoderdante" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}datosPoderdanteCompletoType" minOccurs="0"/&gt;
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="periodoVigencia" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}periodoType" minOccurs="0"/&gt;
 *         &lt;element name="tipoApoderamiento" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}tipoApoderamientoCompletoType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosApoderamientoType", propOrder = {
    "anexos",
    "codApoderamientoEXT",
    "codApoderamientoINT",
    "datosApoderado",
    "datosPoderdante",
    "estado",
    "periodoVigencia",
    "tipoApoderamiento"
})
public class DatosApoderamientoType {

    @XmlElement(nillable = true)
    protected List<AnexoType> anexos;
    protected long codApoderamientoEXT;
    protected long codApoderamientoINT;
    protected DatosApoderadoCompletoType datosApoderado;
    protected DatosPoderdanteCompletoType datosPoderdante;
    protected String estado;
    protected PeriodoType2 periodoVigencia;
    protected TipoApoderamientoCompletoType tipoApoderamiento;

    /**
     * Gets the value of the anexos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the anexos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnexos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnexoType }
     * 
     * 
     */
    public List<AnexoType> getAnexos() {
        if (anexos == null) {
            anexos = new ArrayList<AnexoType>();
        }
        return this.anexos;
    }

    /**
     * Obtiene el valor de la propiedad codApoderamientoEXT.
     * 
     */
    public long getCodApoderamientoEXT() {
        return codApoderamientoEXT;
    }

    /**
     * Define el valor de la propiedad codApoderamientoEXT.
     * 
     */
    public void setCodApoderamientoEXT(long value) {
        this.codApoderamientoEXT = value;
    }

    /**
     * Obtiene el valor de la propiedad codApoderamientoINT.
     * 
     */
    public long getCodApoderamientoINT() {
        return codApoderamientoINT;
    }

    /**
     * Define el valor de la propiedad codApoderamientoINT.
     * 
     */
    public void setCodApoderamientoINT(long value) {
        this.codApoderamientoINT = value;
    }

    /**
     * Obtiene el valor de la propiedad datosApoderado.
     * 
     * @return
     *     possible object is
     *     {@link DatosApoderadoCompletoType }
     *     
     */
    public DatosApoderadoCompletoType getDatosApoderado() {
        return datosApoderado;
    }

    /**
     * Define el valor de la propiedad datosApoderado.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosApoderadoCompletoType }
     *     
     */
    public void setDatosApoderado(DatosApoderadoCompletoType value) {
        this.datosApoderado = value;
    }

    /**
     * Obtiene el valor de la propiedad datosPoderdante.
     * 
     * @return
     *     possible object is
     *     {@link DatosPoderdanteCompletoType }
     *     
     */
    public DatosPoderdanteCompletoType getDatosPoderdante() {
        return datosPoderdante;
    }

    /**
     * Define el valor de la propiedad datosPoderdante.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosPoderdanteCompletoType }
     *     
     */
    public void setDatosPoderdante(DatosPoderdanteCompletoType value) {
        this.datosPoderdante = value;
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
     * Obtiene el valor de la propiedad periodoVigencia.
     * 
     * @return
     *     possible object is
     *     {@link PeriodoType2 }
     *     
     */
    public PeriodoType2 getPeriodoVigencia() {
        return periodoVigencia;
    }

    /**
     * Define el valor de la propiedad periodoVigencia.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodoType2 }
     *     
     */
    public void setPeriodoVigencia(PeriodoType2 value) {
        this.periodoVigencia = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoApoderamiento.
     * 
     * @return
     *     possible object is
     *     {@link TipoApoderamientoCompletoType }
     *     
     */
    public TipoApoderamientoCompletoType getTipoApoderamiento() {
        return tipoApoderamiento;
    }

    /**
     * Define el valor de la propiedad tipoApoderamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoApoderamientoCompletoType }
     *     
     */
    public void setTipoApoderamiento(TipoApoderamientoCompletoType value) {
        this.tipoApoderamiento = value;
    }

}
