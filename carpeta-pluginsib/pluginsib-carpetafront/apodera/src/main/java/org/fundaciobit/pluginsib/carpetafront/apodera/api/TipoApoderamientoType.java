
package org.fundaciobit.pluginsib.carpetafront.apodera.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoApoderamientoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tipoApoderamientoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tipoApod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="subTipoApod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listaOrganismos" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}organismo" minOccurs="0"/&gt;
 *         &lt;element name="materia" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}materiaType" minOccurs="0"/&gt;
 *         &lt;element name="listaProcedimientos" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}procedimiento" minOccurs="0"/&gt;
 *         &lt;element name="listaTramites" type="{https://consultaApoderamientos.minhap.es/peticionConsulta}tramite" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoApoderamientoType", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", propOrder = {
    "tipoApod",
    "subTipoApod",
    "listaOrganismos",
    "materia",
    "listaProcedimientos",
    "listaTramites"
})
public class TipoApoderamientoType {

    protected String tipoApod;
    protected String subTipoApod;
    @XmlElementRef(name = "listaOrganismos", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", type = JAXBElement.class, required = false)
    protected JAXBElement<Organismo> listaOrganismos;
    protected MateriaType materia;
    @XmlElementRef(name = "listaProcedimientos", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", type = JAXBElement.class, required = false)
    protected JAXBElement<Procedimiento> listaProcedimientos;
    @XmlElementRef(name = "listaTramites", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", type = JAXBElement.class, required = false)
    protected JAXBElement<Tramite> listaTramites;

    /**
     * Obtiene el valor de la propiedad tipoApod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoApod() {
        return tipoApod;
    }

    /**
     * Define el valor de la propiedad tipoApod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoApod(String value) {
        this.tipoApod = value;
    }

    /**
     * Obtiene el valor de la propiedad subTipoApod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubTipoApod() {
        return subTipoApod;
    }

    /**
     * Define el valor de la propiedad subTipoApod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubTipoApod(String value) {
        this.subTipoApod = value;
    }

    /**
     * Obtiene el valor de la propiedad listaOrganismos.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Organismo }{@code >}
     *     
     */
    public JAXBElement<Organismo> getListaOrganismos() {
        return listaOrganismos;
    }

    /**
     * Define el valor de la propiedad listaOrganismos.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Organismo }{@code >}
     *     
     */
    public void setListaOrganismos(JAXBElement<Organismo> value) {
        this.listaOrganismos = value;
    }

    /**
     * Obtiene el valor de la propiedad materia.
     * 
     * @return
     *     possible object is
     *     {@link MateriaType }
     *     
     */
    public MateriaType getMateria() {
        return materia;
    }

    /**
     * Define el valor de la propiedad materia.
     * 
     * @param value
     *     allowed object is
     *     {@link MateriaType }
     *     
     */
    public void setMateria(MateriaType value) {
        this.materia = value;
    }

    /**
     * Obtiene el valor de la propiedad listaProcedimientos.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Procedimiento }{@code >}
     *     
     */
    public JAXBElement<Procedimiento> getListaProcedimientos() {
        return listaProcedimientos;
    }

    /**
     * Define el valor de la propiedad listaProcedimientos.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Procedimiento }{@code >}
     *     
     */
    public void setListaProcedimientos(JAXBElement<Procedimiento> value) {
        this.listaProcedimientos = value;
    }

    /**
     * Obtiene el valor de la propiedad listaTramites.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Tramite }{@code >}
     *     
     */
    public JAXBElement<Tramite> getListaTramites() {
        return listaTramites;
    }

    /**
     * Define el valor de la propiedad listaTramites.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Tramite }{@code >}
     *     
     */
    public void setListaTramites(JAXBElement<Tramite> value) {
        this.listaTramites = value;
    }

}
