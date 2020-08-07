
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

  public IntegerField ACCIO() {
    return new IntegerField(getQueryPath(), AuditoriaFields.ACCIO);
  }

  public StringField ELEMENT() {
    return new StringField(getQueryPath(), AuditoriaFields.ELEMENT);
  }

  public TimestampField DATAAUDIT() {
    return new TimestampField(getQueryPath(), AuditoriaFields.DATAAUDIT);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), AuditoriaFields.ENTITATID);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), AuditoriaFields.USUARIID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AuditoriaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AuditoriaQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public UsuariQueryPath USUARI() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AuditoriaQueryPath.this.getQueryPath() + "usuari" + ".";
      }
    });
  }

}
