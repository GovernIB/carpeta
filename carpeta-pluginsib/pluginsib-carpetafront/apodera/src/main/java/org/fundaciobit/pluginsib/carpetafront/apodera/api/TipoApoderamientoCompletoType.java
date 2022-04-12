
package org.fundaciobit.pluginsib.carpetafront.apodera.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoApoderamientoCompletoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tipoApoderamientoCompletoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaCategorias" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}categoria" minOccurs="0"/&gt;
 *         &lt;element name="listaOrganismos" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}organismo" minOccurs="0"/&gt;
 *         &lt;element name="materia" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}materiaType"/&gt;
 *         &lt;element name="listaProcedimientos" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}procedimiento" minOccurs="0"/&gt;
 *         &lt;element name="listaTramites" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}tramite" minOccurs="0"/&gt;
 *         &lt;element name="subTipoApod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoApod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoApoderamientoCompletoType", propOrder = {
    "listaCategorias",
    "listaOrganismos",
    "materia",
    "listaProcedimientos",
    "listaTramites",
    "subTipoApod",
    "tipoApod"
})
public class TipoApoderamientoCompletoType {

    @XmlElementRef(name = "listaCategorias", namespace = "https://consultaApoderamientos.minhap.es/respuestaConsulta", type = JAXBElement.class, required = false)
    protected JAXBElement<Categoria> listaCategorias;
    @XmlElementRef(name = "listaOrganismos", namespace = "https://consultaApoderamientos.minhap.es/respuestaConsulta", type = JAXBElement.class, required = false)
    protected JAXBElement<Organismo2> listaOrganismos;
    @XmlElement(required = true)
    protected MateriaType2 materia;
    @XmlElementRef(name = "listaProcedimientos", namespace = "https://consultaApoderamientos.minhap.es/respuestaConsulta", type = JAXBElement.class, required = false)
    protected JAXBElement<Procedimiento2> listaProcedimientos;
    @XmlElementRef(name = "listaTramites", namespace = "https://consultaApoderamientos.minhap.es/respuestaConsulta", type = JAXBElement.class, required = false)
    protected JAXBElement<Tramite2> listaTramites;
    protected String subTipoApod;
    protected String tipoApod;

    /**
     * Obtiene el valor de la propiedad listaCategorias.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Categoria }{@code >}
     *     
     */
    public JAXBElement<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    /**
     * Define el valor de la propiedad listaCategorias.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Categoria }{@code >}
     *     
     */
    public void setListaCategorias(JAXBElement<Categoria> value) {
        this.listaCategorias = value;
    }

    /**
     * Obtiene el valor de la propiedad listaOrganismos.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Organismo2 }{@code >}
     *     
     */
    public JAXBElement<Organismo2> getListaOrganismos() {
        return listaOrganismos;
    }

    /**
     * Define el valor de la propiedad listaOrganismos.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Organismo2 }{@code >}
     *     
     */
    public void setListaOrganismos(JAXBElement<Organismo2> value) {
        this.listaOrganismos = value;
    }

    /**
     * Obtiene el valor de la propiedad materia.
     * 
     * @return
     *     possible object is
     *     {@link MateriaType2 }
     *     
     */
    public MateriaType2 getMateria() {
        return materia;
    }

    /**
     * Define el valor de la propiedad materia.
     * 
     * @param value
     *     allowed object is
     *     {@link MateriaType2 }
     *     
     */
    public void setMateria(MateriaType2 value) {
        this.materia = value;
    }

    /**
     * Obtiene el valor de la propiedad listaProcedimientos.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Procedimiento2 }{@code >}
     *     
     */
    public JAXBElement<Procedimiento2> getListaProcedimientos() {
        return listaProcedimientos;
    }

    /**
     * Define el valor de la propiedad listaProcedimientos.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Procedimiento2 }{@code >}
     *     
     */
    public void setListaProcedimientos(JAXBElement<Procedimiento2> value) {
        this.listaProcedimientos = value;
    }

    /**
     * Obtiene el valor de la propiedad listaTramites.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Tramite2 }{@code >}
     *     
     */
    public JAXBElement<Tramite2> getListaTramites() {
        return listaTramites;
    }

    /**
     * Define el valor de la propiedad listaTramites.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Tramite2 }{@code >}
     *     
     */
    public void setListaTramites(JAXBElement<Tramite2> value) {
        this.listaTramites = value;
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

}
