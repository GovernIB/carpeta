
package es.caib.apodera.client.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="datosAuditoriaType" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}datosAuditoriaType"/&gt;
 *         &lt;element name="datosConsultaType" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}datosConsultaType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datosAuditoriaType",
    "datosConsultaType"
})
@XmlRootElement(name = "PeticionConsulta", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta")
public class PeticionConsulta {

    @XmlElement(namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", required = true)
    protected DatosAuditoriaType datosAuditoriaType;
    @XmlElement(namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", required = true)
    protected DatosConsultaType datosConsultaType;

    /**
     * Obtiene el valor de la propiedad datosAuditoriaType.
     * 
     * @return
     *     possible object is
     *     {@link DatosAuditoriaType }
     *     
     */
    public DatosAuditoriaType getDatosAuditoriaType() {
        return datosAuditoriaType;
    }

    /**
     * Define el valor de la propiedad datosAuditoriaType.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosAuditoriaType }
     *     
     */
    public void setDatosAuditoriaType(DatosAuditoriaType value) {
        this.datosAuditoriaType = value;
    }

    /**
     * Obtiene el valor de la propiedad datosConsultaType.
     * 
     * @return
     *     possible object is
     *     {@link DatosConsultaType }
     *     
     */
    public DatosConsultaType getDatosConsultaType() {
        return datosConsultaType;
    }

    /**
     * Define el valor de la propiedad datosConsultaType.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosConsultaType }
     *     
     */
    public void setDatosConsultaType(DatosConsultaType value) {
        this.datosConsultaType = value;
    }

}
