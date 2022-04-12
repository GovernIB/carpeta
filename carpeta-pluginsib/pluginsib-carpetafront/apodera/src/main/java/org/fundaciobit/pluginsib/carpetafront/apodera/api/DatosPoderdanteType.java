
package org.fundaciobit.pluginsib.carpetafront.apodera.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosPoderdanteType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosPoderdanteType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nifNiePoderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nifNieReprPoderdante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosPoderdanteType", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", propOrder = {
    "nifNiePoderdante",
    "nifNieReprPoderdante"
})
public class DatosPoderdanteType {

    protected String nifNiePoderdante;
    protected String nifNieReprPoderdante;

    /**
     * Obtiene el valor de la propiedad nifNiePoderdante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNifNiePoderdante() {
        return nifNiePoderdante;
    }

    /**
     * Define el valor de la propiedad nifNiePoderdante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNifNiePoderdante(String value) {
        this.nifNiePoderdante = value;
    }

    /**
     * Obtiene el valor de la propiedad nifNieReprPoderdante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNifNieReprPoderdante() {
        return nifNieReprPoderdante;
    }

    /**
     * Define el valor de la propiedad nifNieReprPoderdante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNifNieReprPoderdante(String value) {
        this.nifNieReprPoderdante = value;
    }

}
