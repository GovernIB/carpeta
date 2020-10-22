
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PropietatGlobalFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_propietatglobal";


  public static final String _TABLE_MODEL = "propietatGlobal";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PROPIETAGLOBALID = new LongField(_TABLE_MODEL, "propietaGlobalID", "propietatglobalid");  // PK
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final StringField VALUE = new StringField(_TABLE_MODEL, "value", "value");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");


  public static final Field<?>[] ALL_PROPIETATGLOBAL_FIELDS = {
    PROPIETAGLOBALID,
    CODI,
    VALUE,
    DESCRIPCIO,
    ENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PROPIETAGLOBALID
  };
}
