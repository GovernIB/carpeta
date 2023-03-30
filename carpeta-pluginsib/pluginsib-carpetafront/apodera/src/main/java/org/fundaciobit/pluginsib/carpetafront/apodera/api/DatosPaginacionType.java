
package org.fundaciobit.pluginsib.carpetafront.apodera.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para datosPaginacionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="datosPaginacionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pagina" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosPaginacionType", namespace = "https://consultaApoderamientos.minhap.es/peticionConsulta", propOrder = {
    "pagina"
})
public class DatosPaginacionType {

    protected int pagina;

    /**
     * Obtiene el valor de la propiedad pagina.
     * 
     */
    public int getPagina() {
        return pagina;
    }

    /**
     * Define el valor de la propiedad pagina.
     * 
     */
    public void setPagina(int value) {
        this.pagina = value;
    }

}
