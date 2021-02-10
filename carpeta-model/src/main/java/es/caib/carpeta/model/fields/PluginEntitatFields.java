
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PluginEntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_pluginentitat";


  public static final String _TABLE_MODEL = "pluginEntitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PLUGINENTITATID = new LongField(_TABLE_MODEL, "pluginEntitatID", "pluginentitatid");  // PK
	 public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginID", "pluginid");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");
	 public static final LongField SECCIOID = new LongField(_TABLE_MODEL, "seccioID", "seccioid");


  public static final Field<?>[] ALL_PLUGINENTITAT_FIELDS = {
    PLUGINENTITATID,
    PLUGINID,
    ENTITATID,
    ACTIU,
    SECCIOID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PLUGINENTITATID
  };
}
