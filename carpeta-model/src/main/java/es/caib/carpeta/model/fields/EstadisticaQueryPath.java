
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

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), EstadisticaFields.TIPUS);
  }

  public TimestampField DATAESTADISTICA() {
    return new TimestampField(getQueryPath(), EstadisticaFields.DATAESTADISTICA);
  }

  public IntegerField COMPTADOR() {
    return new IntegerField(getQueryPath(), EstadisticaFields.COMPTADOR);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), EstadisticaFields.PLUGINID);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), EstadisticaFields.ENTITATID);
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

}
