
package es.caib.apodera.client.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosAuditoriaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosAuditoriaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="codAplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosAuditoriaType", propOrder = {
    "timestamp",
    "codAplicacion"
})
public class DatosAuditoriaType2 {

    protected long timestamp;
    @XmlElement(required = true)
    protected String codAplicacion;

    /**
     * Obtiene el valor de la propiedad timestamp.
     * 
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Define el valor de la propiedad timestamp.
     * 
     */
    public void setTimestamp(long value) {
        this.timestamp = value;
    }

    /**
     * Obtiene el valor de la propiedad codAplicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAplicacion() {
        return codAplicacion;
    }

    /**
     * Define el valor de la propiedad codAplicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAplicacion(String value) {
        this.codAplicacion = value;
    }

}
