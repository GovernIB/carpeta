
package es.caib.apodera.client.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaApoderamientosResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaApoderamientosResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="datosAuditoria" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}datosAuditoriaType" minOccurs="0"/&gt;
 *         &lt;element name="listaApoderamientos" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}datosApoderamientoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="resultadoError" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}errorType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaApoderamientosResponse", propOrder = {
    "datosAuditoria",
    "listaApoderamientos",
    "resultadoError"
})
public class ConsultaApoderamientosResponse {

    protected DatosAuditoriaType2 datosAuditoria;
    @XmlElement(nillable = true)
    protected List<DatosApoderamientoType> listaApoderamientos;
    protected ErrorType resultadoError;

    /**
     * Obtiene el valor de la propiedad datosAuditoria.
     * 
     * @return
     *     possible object is
     *     {@link DatosAuditoriaType2 }
     *     
     */
    public DatosAuditoriaType2 getDatosAuditoria() {
        return datosAuditoria;
    }

    /**
     * Define el valor de la propiedad datosAuditoria.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosAuditoriaType2 }
     *     
     */
    public void setDatosAuditoria(DatosAuditoriaType2 value) {
        this.datosAuditoria = value;
    }

    /**
     * Gets the value of the listaApoderamientos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaApoderamientos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaApoderamientos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatosApoderamientoType }
     * 
     * 
     */
    public List<DatosApoderamientoType> getListaApoderamientos() {
        if (listaApoderamientos == null) {
            listaApoderamientos = new ArrayList<DatosApoderamientoType>();
        }
        return this.listaApoderamientos;
    }

    /**
     * Obtiene el valor de la propiedad resultadoError.
     * 
     * @return
     *     possible object is
     *     {@link ErrorType }
     *     
     */
    public ErrorType getResultadoError() {
        return resultadoError;
    }

    /**
     * Define el valor de la propiedad resultadoError.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorType }
     *     
     */
    public void setResultadoError(ErrorType value) {
        this.resultadoError = value;
    }

}
