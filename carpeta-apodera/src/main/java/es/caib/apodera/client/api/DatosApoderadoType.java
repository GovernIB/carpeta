
package es.caib.apodera.client.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosApoderadoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosApoderadoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nifNieApoderado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosApoderadoType", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", propOrder = {
    "nifNieApoderado"
})
public class DatosApoderadoType {

    protected String nifNieApoderado;

    /**
     * Obtiene el valor de la propiedad nifNieApoderado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNifNieApoderado() {
        return nifNieApoderado;
    }

    /**
     * Define el valor de la propiedad nifNieApoderado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNifNieApoderado(String value) {
        this.nifNieApoderado = value;
    }

}
