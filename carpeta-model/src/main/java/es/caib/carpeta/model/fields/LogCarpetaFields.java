
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface LogCarpetaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_log";


  public static final String _TABLE_MODEL = "logCarpeta";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField LOGID = new LongField(_TABLE_MODEL, "logID", "logid");  // PK
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final IntegerField ESTAT = new IntegerField(_TABLE_MODEL, "estat", "estat");
	 public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginID", "pluginid");
	 public static final StringField ENTITATCODI = new StringField(_TABLE_MODEL, "entitatCodi", "entitatcodi");
	 public static final LongField TEMPS = new LongField(_TABLE_MODEL, "temps", "temps");
	 public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "dataInici", "datainici");
	 public static final StringField PETICIO = new StringField(_TABLE_MODEL, "peticio", "peticio");
	 public static final StringField ERROR = new StringField(_TABLE_MODEL, "error", "error");
	 public static final StringField EXCEPCIO = new StringField(_TABLE_MODEL, "excepcio", "excepcio");
	 public static final StringField IDSESSIO = new StringField(_TABLE_MODEL, "idSessio", "idsessio");


  public static final Field<?>[] ALL_LOGCARPETA_FIELDS = {
    LOGID,
    DESCRIPCIO,
    TIPUS,
    ESTAT,
    PLUGINID,
    ENTITATCODI,
    TEMPS,
    DATAINICI,
    PETICIO,
    ERROR,
    EXCEPCIO,
    IDSESSIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
LOGID
  };
}
