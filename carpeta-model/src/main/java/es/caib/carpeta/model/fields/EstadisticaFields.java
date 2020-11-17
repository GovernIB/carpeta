
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EstadisticaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_estadistica";


  public static final String _TABLE_MODEL = "estadistica";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ESTADISTICAID = new LongField(_TABLE_MODEL, "estadisticaID", "estadisticaid");  // PK
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final TimestampField DATAESTADISTICA = new TimestampField(_TABLE_MODEL, "dataEstadistica", "dataestadistica");
	 public static final IntegerField COMPTADOR = new IntegerField(_TABLE_MODEL, "comptador", "comptador");
	 public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginID", "pluginid");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");


  public static final Field<?>[] ALL_ESTADISTICA_FIELDS = {
    ESTADISTICAID,
    TIPUS,
    DATAESTADISTICA,
    COMPTADOR,
    PLUGINID,
    ENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ESTADISTICAID
  };
}
