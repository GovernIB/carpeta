
package org.fundaciobit.pluginsib.carpetafront.apodera.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tramite complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tramite"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tramite" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}tramiteType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tramite", propOrder = {
    "tramite"
})
public class Tramite2 {

    @XmlElement(nillable = true)
    protected List<TramiteType2> tramite;

    /**
     * Gets the value of the tramite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tramite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTramite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TramiteType2 }
     * 
     * 
     */
    public List<TramiteType2> getTramite() {
        if (tramite == null) {
            tramite = new ArrayList<TramiteType2>();
        }
        return this.tramite;
    }

}
