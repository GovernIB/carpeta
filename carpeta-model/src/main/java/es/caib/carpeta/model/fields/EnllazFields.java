
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EnllazFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_enllaz";


  public static final String _TABLE_MODEL = "enllaz";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ENLLAZID = new LongField(_TABLE_MODEL, "enllazID", "enllazid");  // PK
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomID", "nomid");
	 public static final LongField DESCRIPCIOID = new LongField(_TABLE_MODEL, "descripcioID", "descripcioid");
	 public static final LongField URLID = new LongField(_TABLE_MODEL, "urlID", "urlid");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final LongField LOGOID = new LongField(_TABLE_MODEL, "logoID", "logoid");
	 public static final LongField SECCIOID = new LongField(_TABLE_MODEL, "seccioID", "seccioid");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");


  public static final Field<?>[] ALL_ENLLAZ_FIELDS = {
    ENLLAZID,
    TIPUS,
    NOMID,
    DESCRIPCIOID,
    URLID,
    ENTITATID,
    LOGOID,
    SECCIOID,
    ACTIU
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ENLLAZID
  };
}
