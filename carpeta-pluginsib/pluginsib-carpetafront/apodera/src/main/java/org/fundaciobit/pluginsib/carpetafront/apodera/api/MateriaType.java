
package org.fundaciobit.pluginsib.carpetafront.apodera.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para materiaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="materiaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codMateria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="denomMateria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "materiaType", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", propOrder = {
    "codMateria",
    "denomMateria"
})
public class MateriaType {

    protected String codMateria;
    protected String denomMateria;

    /**
     * Obtiene el valor de la propiedad codMateria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodMateria() {
        return codMateria;
    }

    /**
     * Define el valor de la propiedad codMateria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodMateria(String value) {
        this.codMateria = value;
    }

    /**
     * Obtiene el valor de la propiedad denomMateria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenomMateria() {
        return denomMateria;
    }

    /**
     * Define el valor de la propiedad denomMateria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenomMateria(String value) {
        this.denomMateria = value;
    }

}
