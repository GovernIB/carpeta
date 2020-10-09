
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AvisFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_avis";


  public static final String _TABLE_MODEL = "avis";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField AVISID = new LongField(_TABLE_MODEL, "avisID", "avisid");  // PK
	 public static final LongField DESCRIPCIOID = new LongField(_TABLE_MODEL, "descripcioID", "descripcioid");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "dataInici", "datainici");
	 public static final TimestampField DATAFI = new TimestampField(_TABLE_MODEL, "dataFi", "datafi");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final LongField PLUGINFRONTID = new LongField(_TABLE_MODEL, "pluginFrontID", "pluginfrontid");


  public static final Field<?>[] ALL_AVIS_FIELDS = {
    AVISID,
    DESCRIPCIOID,
    ENTITATID,
    DATAINICI,
    DATAFI,
    TIPUS,
    PLUGINFRONTID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
AVISID
  };
}
