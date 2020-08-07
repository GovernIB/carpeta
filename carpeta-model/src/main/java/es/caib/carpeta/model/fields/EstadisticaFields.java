
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EstadisticaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_estadistica";


  public static final String _TABLE_MODEL = "estadistica";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ESTADISTICAID = new LongField(_TABLE_MODEL, "estadisticaID", "estadisticaid");  // PK
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final LongField ACCESID = new LongField(_TABLE_MODEL, "accesID", "accesid");
	 public static final IntegerField ACCIO = new IntegerField(_TABLE_MODEL, "accio", "accio");
	 public static final StringField ELEMENT = new StringField(_TABLE_MODEL, "element", "element");
	 public static final TimestampField DATAESTADISTICA = new TimestampField(_TABLE_MODEL, "dataEstadistica", "dataestadistica");


  public static final Field<?>[] ALL_ESTADISTICA_FIELDS = {
    ESTADISTICAID,
    ENTITATID,
    ACCESID,
    ACCIO,
    ELEMENT,
    DATAESTADISTICA
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ESTADISTICAID
  };
}
