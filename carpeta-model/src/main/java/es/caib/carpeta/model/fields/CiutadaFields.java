
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface CiutadaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_ciutada";


  public static final String _TABLE_MODEL = "ciutada";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField CIUTADAID = new LongField(_TABLE_MODEL, "ciutadaID", "ciutadaid");  // PK
	 public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif");
	 public static final StringField LLINATGE1 = new StringField(_TABLE_MODEL, "llinatge1", "llinatge1");
	 public static final StringField LLINATGE2 = new StringField(_TABLE_MODEL, "llinatge2", "llinatge2");
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final BooleanField EMPRESA = new BooleanField(_TABLE_MODEL, "empresa", "empresa");
	 public static final StringField REPRESENTANTNIF = new StringField(_TABLE_MODEL, "representantNif", "representantnif");
	 public static final StringField REPRESENTANTLLINATGE1 = new StringField(_TABLE_MODEL, "representantLlinatge1", "representantllinatge1");
	 public static final StringField REPRESENTANTLLINATGE2 = new StringField(_TABLE_MODEL, "representantLlinatge2", "representantllinatge2");
	 public static final StringField REPRESENTANTNOM = new StringField(_TABLE_MODEL, "representantNom", "representantnom");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final StringField MOBILEID = new StringField(_TABLE_MODEL, "mobileId", "mobileid");


  public static final Field<?>[] ALL_CIUTADA_FIELDS = {
    CIUTADAID,
    NIF,
    LLINATGE1,
    LLINATGE2,
    NOM,
    EMPRESA,
    REPRESENTANTNIF,
    REPRESENTANTLLINATGE1,
    REPRESENTANTLLINATGE2,
    REPRESENTANTNOM,
    DATACREACIO,
    MOBILEID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
CIUTADAID
  };
}
