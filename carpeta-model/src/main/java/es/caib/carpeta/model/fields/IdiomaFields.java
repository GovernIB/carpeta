
package es.caib.carpeta.model.fields;

import org.fundaciobit.genapp.common.query.BooleanField;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.IntegerField;
import org.fundaciobit.genapp.common.query.StringField;
public interface IdiomaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_idioma";


  public static final String _TABLE_MODEL = "idioma";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final BooleanField SUPORTAT = new BooleanField(_TABLE_MODEL, "suportat", "suportat");
	 public static final IntegerField ORDRE = new IntegerField(_TABLE_MODEL, "ordre", "ordre");


  public static final Field<?>[] ALL_IDIOMA_FIELDS = {
    IDIOMAID,
    NOM,
    SUPORTAT,
    ORDRE
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
IDIOMAID
  };
}
