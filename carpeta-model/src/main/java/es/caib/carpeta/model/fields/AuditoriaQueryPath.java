
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AuditoriaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AuditoriaQueryPath() {
  }

  protected AuditoriaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField AUDITORIAID() {
    return new LongField(getQueryPath(), AuditoriaFields.AUDITORIAID);
  }

  public TimestampField DATAAUDIT() {
    return new TimestampField(getQueryPath(), AuditoriaFields.DATAAUDIT);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), AuditoriaFields.TIPUS);
  }

  public StringField USERNAME() {
    return new StringField(getQueryPath(), AuditoriaFields.USERNAME);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), AuditoriaFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AuditoriaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
