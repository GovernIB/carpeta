
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_usuari";


  public static final String _TABLE_MODEL = "usuari";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid");  // PK
	 public static final StringField USERNAME = new StringField(_TABLE_MODEL, "username", "username");
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField LLINATGE1 = new StringField(_TABLE_MODEL, "llinatge1", "llinatge1");
	 public static final StringField LLINATGE2 = new StringField(_TABLE_MODEL, "llinatge2", "llinatge2");
	 public static final StringField EMAIL = new StringField(_TABLE_MODEL, "email", "email");
	 public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif");
	 public static final StringField IDIOMA = new StringField(_TABLE_MODEL, "idioma", "idioma");
	 public static final LongField DARRERAENTITAT = new LongField(_TABLE_MODEL, "darreraEntitat", "darreraentitat");


  public static final Field<?>[] ALL_USUARI_FIELDS = {
    USUARIID,
    USERNAME,
    NOM,
    LLINATGE1,
    LLINATGE2,
    EMAIL,
    NIF,
    IDIOMA,
    DARRERAENTITAT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIID
  };
}
