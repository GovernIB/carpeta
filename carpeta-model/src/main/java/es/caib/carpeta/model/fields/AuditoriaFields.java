
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AuditoriaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "car_auditoria";


  public static final String _TABLE_MODEL = "auditoria";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField AUDITORIAID = new LongField(_TABLE_MODEL, "auditoriaID", "auditoriaid");  // PK
	 public static final TimestampField DATAAUDIT = new TimestampField(_TABLE_MODEL, "dataAudit", "dataaudit");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final StringField TICKETLOGINIB = new StringField(_TABLE_MODEL, "ticketLoginIB", "ticketloginib");
	 public static final IntegerField PLUGINID = new IntegerField(_TABLE_MODEL, "pluginID", "pluginid");


  public static final Field<?>[] ALL_AUDITORIA_FIELDS = {
    AUDITORIAID,
    DATAAUDIT,
    ENTITATID,
    USUARIID,
    TIPUS,
    TICKETLOGINIB,
    PLUGINID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
AUDITORIAID
  };
}
