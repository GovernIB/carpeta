
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_entitat";


  public static final String _TABLE_MODEL = "entitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");  // PK
	 public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nomid");
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final StringField CODIDIR3 = new StringField(_TABLE_MODEL, "codiDir3", "codidir3");
	 public static final BooleanField ACTIVA = new BooleanField(_TABLE_MODEL, "activa", "activa");
	 public static final LongField LOGOMENUID = new LongField(_TABLE_MODEL, "logoMenuID", "logomenuid");
	 public static final StringField COLORMENU = new StringField(_TABLE_MODEL, "colorMenu", "colormenu");
	 public static final StringField TEXTEPEU = new StringField(_TABLE_MODEL, "textePeu", "textepeu");
	 public static final LongField LOGOPEUID = new LongField(_TABLE_MODEL, "logoPeuID", "logopeuid");
	 public static final StringField VERSIO = new StringField(_TABLE_MODEL, "versio", "versio");
	 public static final StringField COMMIT = new StringField(_TABLE_MODEL, "commit", "commit");
	 public static final LongField FITXERCSSID = new LongField(_TABLE_MODEL, "fitxerCssID", "fitxercss");
	 public static final StringField CONTEXT = new StringField(_TABLE_MODEL, "context", "context");


  public static final Field<?>[] ALL_ENTITAT_FIELDS = {
    ENTITATID,
    NOMID,
    CODI,
    CODIDIR3,
    ACTIVA,
    LOGOMENUID,
    COLORMENU,
    TEXTEPEU,
    LOGOPEUID,
    VERSIO,
    COMMIT,
    FITXERCSSID,
    CONTEXT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ENTITATID
  };
}
