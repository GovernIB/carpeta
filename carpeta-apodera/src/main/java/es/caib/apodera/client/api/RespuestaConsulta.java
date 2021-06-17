
package es.caib.apodera.client.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="consultaApoderamientosResponse" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}consultaApoderamientosResponse" minOccurs="0"/&gt;
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
    "consultaApoderamientosResponse"
})
@XmlRootElement(name = "RespuestaConsulta")
public class RespuestaConsulta {

    protected ConsultaApoderamientosResponse consultaApoderamientosResponse;

    /**
     * Obtiene el valor de la propiedad consultaApoderamientosResponse.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaApoderamientosResponse }
     *     
     */
    public ConsultaApoderamientosResponse getConsultaApoderamientosResponse() {
        return consultaApoderamientosResponse;
    }

    /**
     * Define el valor de la propiedad consultaApoderamientosResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaApoderamientosResponse }
     *     
     */
    public void setConsultaApoderamientosResponse(ConsultaApoderamientosResponse value) {
        this.consultaApoderamientosResponse = value;
    }

}
