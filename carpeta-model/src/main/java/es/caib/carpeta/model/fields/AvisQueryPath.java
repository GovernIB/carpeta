
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AvisQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AvisQueryPath() {
  }

  protected AvisQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField AVISID() {
    return new LongField(getQueryPath(), AvisFields.AVISID);
  }

  public LongField DESCRIPCIOID() {
    return new LongField(getQueryPath(), AvisFields.DESCRIPCIOID);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), AvisFields.ENTITATID);
  }

  public TimestampField DATAINICI() {
    return new TimestampField(getQueryPath(), AvisFields.DATAINICI);
  }

  public TimestampField DATAFI() {
    return new TimestampField(getQueryPath(), AvisFields.DATAFI);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), AvisFields.TIPUS);
  }

  public IntegerField GRAVETAT() {
    return new IntegerField(getQueryPath(), AvisFields.GRAVETAT);
  }

  public LongField PLUGINFRONTID() {
    return new LongField(getQueryPath(), AvisFields.PLUGINFRONTID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AvisFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TraduccioQueryPath DESCRIPCIO() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AvisQueryPath.this.getQueryPath() + "descripcio" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AvisQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AvisQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

}
