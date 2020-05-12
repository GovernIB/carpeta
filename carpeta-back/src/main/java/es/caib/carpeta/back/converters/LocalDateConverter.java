package es.caib.carpeta.back.converters;

import org.primefaces.component.datepicker.DatePicker;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Necessari per poder emprar {@link LocalDate} dins JSF i amb el component {@link DatePicker} de Primefaces.
 * Emprarà el patró definit al component si hi és, i sinó el patró <code>dd-MM-yyyy</code>.
 *
 * @author areus
 */
@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter<Object> {

    /**
     * Obté un LocalDate a partir d'un String
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component));
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Obté un String a partir d'un LocalDate
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || (value instanceof String && ((String) value).isEmpty())) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component));
        return formatter.format((LocalDate) value);
    }

    private String extractPattern(UIComponent component) {
        if (component instanceof DatePicker) {
            DatePicker datePicker = (DatePicker) component;
            return datePicker.getPattern();
        }

        return "dd-MM-yyyy";
    }
}
