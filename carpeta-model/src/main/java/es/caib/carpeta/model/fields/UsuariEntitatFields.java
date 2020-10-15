
package es.caib.carpeta.model.fields;

import org.fundaciobit.genapp.common.query.BooleanField;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.LongField;
public interface UsuariEntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_usuarientitat";


  public static final String _TABLE_MODEL = "usuariEntitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField USUARIENTITATID = new LongField(_TABLE_MODEL, "usuariEntitatID", "usuarientitatid");  // PK
	 public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");


  public static final Field<?>[] ALL_USUARIENTITAT_FIELDS = {
    USUARIENTITATID,
    USUARIID,
    ENTITATID,
    ACTIU
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIENTITATID
  };
}
