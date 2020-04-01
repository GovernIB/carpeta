package es.caib.carpeta.commons.query;

import java.io.Serializable;

/**
 * Representació d'una clausula d'ordenació.
 *
 * @author anadal
 */
public class OrderBy implements Serializable {

    private static final long serialVersionUID = -5856452637655410106L;

    /**
     * Tipus d'ordenació
     */
    public final OrderType orderType;

    /**
     * Propietat a la que s'aplica l'ordenació
     */
    public final String property;

    /**
     * Crea una ordenació sobre una propietat amb l'ordre indicat.
     *
     * @param property propietat sobre la que s'aplicarà l'ordenació.
     * @param type     ordenació, ascendent o descendent
     */
    public OrderBy(String property, OrderType type) {
        this.property = property;
        this.orderType = type;
    }

    /**
     * Crea una ordenació sobre una propietat amb l'ordre per defecte (ascendent)
     *
     * @param property propietat sobre la que s'aplicarà l'ordenació.
     */
    public OrderBy(String property) {
        super();
        this.property = property;
        this.orderType = OrderType.ASC;
    }
}
