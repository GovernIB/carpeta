
package org.fundaciobit.pluginsib.carpetafront.apodera.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosPoderdanteCompletoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosPoderdanteCompletoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personaFisica" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}personaFisicaType" minOccurs="0"/&gt;
 *         &lt;element name="personaJuridica" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}personaJuridicaType" minOccurs="0"/&gt;
 *         &lt;element name="representante" type="{https://consultaApoderamientos.minhap.es/respuestaConsulta}personaFisicaType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosPoderdanteCompletoType", propOrder = {
    "personaFisica",
    "personaJuridica",
    "representante"
})
public class DatosPoderdanteCompletoType {

    protected PersonaFisicaType personaFisica;
    protected PersonaJuridicaType personaJuridica;
    protected PersonaFisicaType representante;

    /**
     * Obtiene el valor de la propiedad personaFisica.
     * 
     * @return
     *     possible object is
     *     {@link PersonaFisicaType }
     *     
     */
    public PersonaFisicaType getPersonaFisica() {
        return personaFisica;
    }

    /**
     * Define el valor de la propiedad personaFisica.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonaFisicaType }
     *     
     */
    public void setPersonaFisica(PersonaFisicaType value) {
        this.personaFisica = value;
    }

    /**
     * Obtiene el valor de la propiedad personaJuridica.
     * 
     * @return
     *     possible object is
     *     {@link PersonaJuridicaType }
     *     
     */
    public PersonaJuridicaType getPersonaJuridica() {
        return personaJuridica;
    }

    /**
     * Define el valor de la propiedad personaJuridica.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonaJuridicaType }
     *     
     */
    public void setPersonaJuridica(PersonaJuridicaType value) {
        this.personaJuridica = value;
    }

    /**
     * Obtiene el valor de la propiedad representante.
     * 
     * @return
     *     possible object is
     *     {@link PersonaFisicaType }
     *     
     */
    public PersonaFisicaType getRepresentante() {
        return representante;
    }

    /**
     * Define el valor de la propiedad representante.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonaFisicaType }
     *     
     */
    public void setRepresentante(PersonaFisicaType value) {
        this.representante = value;
    }

}
