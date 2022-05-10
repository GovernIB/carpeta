
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface NotificacioAppFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_notificacioapp";


  public static final String _TABLE_MODEL = "notificacioApp";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField NOTIFICACIOAPPID = new LongField(_TABLE_MODEL, "notificacioAppID", "notificacioappid");  // PK
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final LongField TITOLID = new LongField(_TABLE_MODEL, "titolID", "titolid");
	 public static final LongField MISSATGEID = new LongField(_TABLE_MODEL, "missatgeID", "missatgeid");
	 public static final LongField FRONTPLUGINID = new LongField(_TABLE_MODEL, "frontPluginID", "frontpluginid");
	 public static final StringField AJUDA = new StringField(_TABLE_MODEL, "ajuda", "ajuda");
	 public static final BooleanField ACTIVA = new BooleanField(_TABLE_MODEL, "activa", "activa");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");


  public static final Field<?>[] ALL_NOTIFICACIOAPP_FIELDS = {
    NOTIFICACIOAPPID,
    CODI,
    TITOLID,
    MISSATGEID,
    FRONTPLUGINID,
    AJUDA,
    ACTIVA,
    ENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
NOTIFICACIOAPPID
  };
}
