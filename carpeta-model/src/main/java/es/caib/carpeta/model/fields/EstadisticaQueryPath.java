
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EstadisticaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EstadisticaQueryPath() {
  }

  protected EstadisticaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ESTADISTICAID() {
    return new LongField(getQueryPath(), EstadisticaFields.ESTADISTICAID);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), EstadisticaFields.ENTITATID);
  }

  public LongField ACCESID() {
    return new LongField(getQueryPath(), EstadisticaFields.ACCESID);
  }

  public IntegerField ACCIO() {
    return new IntegerField(getQueryPath(), EstadisticaFields.ACCIO);
  }

  public StringField ELEMENT() {
    return new StringField(getQueryPath(), EstadisticaFields.ELEMENT);
  }

  public TimestampField DATAESTADISTICA() {
    return new TimestampField(getQueryPath(), EstadisticaFields.DATAESTADISTICA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EstadisticaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstadisticaQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public AccesQueryPath ACCES() {
    return new AccesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstadisticaQueryPath.this.getQueryPath() + "acces" + ".";
      }
    });
  }

}
