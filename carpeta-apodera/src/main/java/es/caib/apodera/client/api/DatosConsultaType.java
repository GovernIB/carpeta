
package es.caib.apodera.client.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosConsultaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosConsultaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tipoConsulta" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="datosConsultaApoderamiento" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}datosConsultaApoderamientoType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosConsultaType", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", propOrder = {
    "tipoConsulta",
    "datosConsultaApoderamiento"
})
public class DatosConsultaType {

    protected boolean tipoConsulta;
    @XmlElement(required = true)
    protected DatosConsultaApoderamientoType datosConsultaApoderamiento;

    /**
     * Obtiene el valor de la propiedad tipoConsulta.
     * 
     */
    public boolean isTipoConsulta() {
        return tipoConsulta;
    }

    /**
     * Define el valor de la propiedad tipoConsulta.
     * 
     */
    public void setTipoConsulta(boolean value) {
        this.tipoConsulta = value;
    }

    /**
     * Obtiene el valor de la propiedad datosConsultaApoderamiento.
     * 
     * @return
     *     possible object is
     *     {@link DatosConsultaApoderamientoType }
     *     
     */
    public DatosConsultaApoderamientoType getDatosConsultaApoderamiento() {
        return datosConsultaApoderamiento;
    }

    /**
     * Define el valor de la propiedad datosConsultaApoderamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosConsultaApoderamientoType }
     *     
     */
    public void setDatosConsultaApoderamiento(DatosConsultaApoderamientoType value) {
        this.datosConsultaApoderamiento = value;
    }

}
