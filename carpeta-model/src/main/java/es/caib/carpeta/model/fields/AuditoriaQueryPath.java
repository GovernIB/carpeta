
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

  public LongField USUARIID() {
    return new LongField(getQueryPath(), AuditoriaFields.USUARIID);
  }

  public StringField USUARICLAVE() {
    return new StringField(getQueryPath(), AuditoriaFields.USUARICLAVE);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), AuditoriaFields.ENTITATID);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), AuditoriaFields.PLUGINID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AuditoriaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariQueryPath USUARI() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AuditoriaQueryPath.this.getQueryPath() + "usuari" + ".";
      }
    });
  }

}
