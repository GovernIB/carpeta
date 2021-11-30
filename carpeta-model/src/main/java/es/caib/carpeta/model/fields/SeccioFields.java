
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface SeccioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_seccio";


  public static final String _TABLE_MODEL = "seccio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField SECCIOID = new LongField(_TABLE_MODEL, "seccioID", "seccioid");  // PK
	 public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nomid");
	 public static final LongField DESCRIPCIOID = new LongField(_TABLE_MODEL, "descripcioID", "descripcioid");
	 public static final StringField CONTEXTE = new StringField(_TABLE_MODEL, "contexte", "context");
	 public static final BooleanField ACTIVA = new BooleanField(_TABLE_MODEL, "activa", "activa");
	 public static final LongField ICONAID = new LongField(_TABLE_MODEL, "iconaID", "iconaid");
	 public static final LongField SECCIOPAREID = new LongField(_TABLE_MODEL, "seccioPareID", "secciopareid");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final IntegerField ORDRE = new IntegerField(_TABLE_MODEL, "ordre", "ordre");


  public static final Field<?>[] ALL_SECCIO_FIELDS = {
    SECCIOID,
    NOMID,
    DESCRIPCIOID,
    CONTEXTE,
    ACTIVA,
    ICONAID,
    SECCIOPAREID,
    ENTITATID,
    ORDRE
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
SECCIOID
  };
}
