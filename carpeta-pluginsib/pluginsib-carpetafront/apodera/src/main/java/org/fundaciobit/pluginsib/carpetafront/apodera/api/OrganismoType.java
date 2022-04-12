
package org.fundaciobit.pluginsib.carpetafront.apodera.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para organismoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="organismoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codOrganismo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="denomOrganismo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "organismoType", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", propOrder = {
    "codOrganismo",
    "denomOrganismo"
})
public class OrganismoType {

    @XmlElement(required = true)
    protected String codOrganismo;
    protected String denomOrganismo;

    /**
     * Obtiene el valor de la propiedad codOrganismo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodOrganismo() {
        return codOrganismo;
    }

    /**
     * Define el valor de la propiedad codOrganismo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodOrganismo(String value) {
        this.codOrganismo = value;
    }

    /**
     * Obtiene el valor de la propiedad denomOrganismo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenomOrganismo() {
        return denomOrganismo;
    }

    /**
     * Define el valor de la propiedad denomOrganismo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenomOrganismo(String value) {
        this.denomOrganismo = value;
    }

}
