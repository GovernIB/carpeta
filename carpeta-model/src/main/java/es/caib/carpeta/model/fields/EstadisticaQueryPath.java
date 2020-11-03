
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

  public TimestampField DATAESTADISTICA() {
    return new TimestampField(getQueryPath(), EstadisticaFields.DATAESTADISTICA);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), EstadisticaFields.TIPUS);
  }

  public IntegerField COMPTADOR() {
    return new IntegerField(getQueryPath(), EstadisticaFields.COMPTADOR);
  }

  public IntegerField PLUGINID() {
    return new IntegerField(getQueryPath(), EstadisticaFields.PLUGINID);
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
