
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PreguntesFrequentsFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_preguntesfrequents";


  public static final String _TABLE_MODEL = "preguntesFrequents";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PREGUNTESFREQUENTSID = new LongField(_TABLE_MODEL, "preguntesFrequentsID", "preguntesfrequentsid");  // PK
	 public static final LongField ENUNCIATID = new LongField(_TABLE_MODEL, "enunciatID", "enunciatid");
	 public static final LongField RESPOSTAID = new LongField(_TABLE_MODEL, "respostaID", "respostaid");
	 public static final IntegerField ORDRE = new IntegerField(_TABLE_MODEL, "ordre", "ordre");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final LongField FITXER1ID = new LongField(_TABLE_MODEL, "fitxer1ID", "fitxer1id");
	 public static final LongField FITXER2ID = new LongField(_TABLE_MODEL, "fitxer2ID", "fitxer2id");
	 public static final LongField FITXER3ID = new LongField(_TABLE_MODEL, "fitxer3ID", "fitxer3id");


  public static final Field<?>[] ALL_PREGUNTESFREQUENTS_FIELDS = {
    PREGUNTESFREQUENTSID,
    ENUNCIATID,
    RESPOSTAID,
    ORDRE,
    ENTITATID,
    FITXER1ID,
    FITXER2ID,
    FITXER3ID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PREGUNTESFREQUENTSID
  };
}
