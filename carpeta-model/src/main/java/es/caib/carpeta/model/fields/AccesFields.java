
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AccesFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_acces";


  public static final String _TABLE_MODEL = "acces";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ACCESID = new LongField(_TABLE_MODEL, "accesID", "accesid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField LLINATGES = new StringField(_TABLE_MODEL, "llinatges", "llinatges");
	 public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif");
	 public static final StringField IP = new StringField(_TABLE_MODEL, "ip", "ip");
	 public static final StringField PROVEIDORIDENTITAT = new StringField(_TABLE_MODEL, "proveidorIdentitat", "proveidoridentitat");
	 public static final StringField NIVELLSEGURETAT = new StringField(_TABLE_MODEL, "nivellSeguretat", "nivellseguretat");
	 public static final IntegerField RESULTATAUTENTICACIO = new IntegerField(_TABLE_MODEL, "resultatAutenticacio", "resultatautenticacio");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final TimestampField DATADARRERACCES = new TimestampField(_TABLE_MODEL, "dataDarrerAcces", "datadarreracces");
	 public static final StringField IDIOMA = new StringField(_TABLE_MODEL, "idioma", "idioma");


  public static final Field<?>[] ALL_ACCES_FIELDS = {
    ACCESID,
    NOM,
    LLINATGES,
    NIF,
    IP,
    PROVEIDORIDENTITAT,
    NIVELLSEGURETAT,
    RESULTATAUTENTICACIO,
    ENTITATID,
    TIPUS,
    DATADARRERACCES,
    IDIOMA
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ACCESID
  };
}
