
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_entitat";


  public static final String _TABLE_MODEL = "entitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");  // PK
	 public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nomid");
	 public static final LongField DESCRIPCIOID = new LongField(_TABLE_MODEL, "descripcioID", "descripcioid");
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final StringField CODIDIR3 = new StringField(_TABLE_MODEL, "codiDir3", "codidir3");
	 public static final BooleanField ACTIVA = new BooleanField(_TABLE_MODEL, "activa", "activa");
	 public static final StringField COLORMENU = new StringField(_TABLE_MODEL, "colorMenu", "colormenu");
	 public static final LongField LOGOCAPBACKID = new LongField(_TABLE_MODEL, "logoCapBackID", "logocapbackid");
	 public static final LongField LOGOPEUBACKID = new LongField(_TABLE_MODEL, "logoPeuBackID", "logopeubackid");
	 public static final LongField LOGOLATERALFRONTID = new LongField(_TABLE_MODEL, "logoLateralFrontID", "logolateralfrontid");
	 public static final StringField VERSIO = new StringField(_TABLE_MODEL, "versio", "versio");
	 public static final LongField ICONID = new LongField(_TABLE_MODEL, "iconID", "iconid");
	 public static final StringField WEBENTITAT = new StringField(_TABLE_MODEL, "webEntitat", "webentitat");
	 public static final StringField ENTITATDESCFRONT = new StringField(_TABLE_MODEL, "entitatDescFront", "entitatdescfront");
	 public static final StringField SUPORTWEB = new StringField(_TABLE_MODEL, "suportWeb", "suportweb");
	 public static final StringField SUPORTTELEFON = new StringField(_TABLE_MODEL, "suportTelefon", "suporttelefon");
	 public static final StringField SUPORTEMAIL = new StringField(_TABLE_MODEL, "suportEmail", "suportemail");
	 public static final StringField SUPORTFAQ = new StringField(_TABLE_MODEL, "suportFAQ", "suportfaq");
	 public static final StringField SUPORTQSSI = new StringField(_TABLE_MODEL, "suportqssi", "suportqssi");
	 public static final StringField SUPORTAUTENTICACIO = new StringField(_TABLE_MODEL, "suportautenticacio", "suportautenticacio");
	 public static final LongField PLUGINLOGINID = new LongField(_TABLE_MODEL, "pluginLoginID", "pluginloginid");
	 public static final LongField LOGINTEXTID = new LongField(_TABLE_MODEL, "loginTextID", "logintextid");
	 public static final LongField FITXERCSSID = new LongField(_TABLE_MODEL, "fitxerCssID", "fitxercss");
	 public static final StringField CONTEXT = new StringField(_TABLE_MODEL, "context", "context");
	 public static final StringField COMMIT = new StringField(_TABLE_MODEL, "commit", "commit");
	 public static final StringField AVISLEGAL = new StringField(_TABLE_MODEL, "avisLegal", "avislegal");
	 public static final StringField ACCESSIBILITAT = new StringField(_TABLE_MODEL, "accessibilitat", "accessibilitat");


  public static final Field<?>[] ALL_ENTITAT_FIELDS = {
    ENTITATID,
    NOMID,
    DESCRIPCIOID,
    CODI,
    CODIDIR3,
    ACTIVA,
    COLORMENU,
    LOGOCAPBACKID,
    LOGOPEUBACKID,
    LOGOLATERALFRONTID,
    VERSIO,
    ICONID,
    WEBENTITAT,
    ENTITATDESCFRONT,
    SUPORTWEB,
    SUPORTTELEFON,
    SUPORTEMAIL,
    SUPORTFAQ,
    SUPORTQSSI,
    SUPORTAUTENTICACIO,
    PLUGINLOGINID,
    LOGINTEXTID,
    FITXERCSSID,
    CONTEXT,
    COMMIT,
    AVISLEGAL,
    ACCESSIBILITAT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ENTITATID
  };
}
