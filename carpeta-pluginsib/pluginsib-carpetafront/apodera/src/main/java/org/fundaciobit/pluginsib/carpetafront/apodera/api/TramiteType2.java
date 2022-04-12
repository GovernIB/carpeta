
package es.caib.apodera.client.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tramiteType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tramiteType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codTramite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descTramite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tramiteType", propOrder = {
    "codTramite",
    "descTramite"
})
public class TramiteType2 {

    protected String codTramite;
    protected String descTramite;

    /**
     * Obtiene el valor de la propiedad codTramite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodTramite() {
        return codTramite;
    }

    /**
     * Define el valor de la propiedad codTramite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodTramite(String value) {
        this.codTramite = value;
    }

    /**
     * Obtiene el valor de la propiedad descTramite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescTramite() {
        return descTramite;
    }

    /**
     * Define el valor de la propiedad descTramite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescTramite(String value) {
        this.descTramite = value;
    }

}
