
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PluginEntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PluginEntitatQueryPath() {
  }

  protected PluginEntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PLUGINENTITATID() {
    return new LongField(getQueryPath(), PluginEntitatFields.PLUGINENTITATID);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), PluginEntitatFields.PLUGINID);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), PluginEntitatFields.ENTITATID);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), PluginEntitatFields.ACTIU);
  }

  public LongField SECCIOID() {
    return new LongField(getQueryPath(), PluginEntitatFields.SECCIOID);
  }

  public IntegerField ORDRE() {
    return new IntegerField(getQueryPath(), PluginEntitatFields.ORDRE);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PluginEntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginEntitatQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginEntitatQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public SeccioQueryPath SECCIO() {
    return new SeccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginEntitatQueryPath.this.getQueryPath() + "seccio" + ".";
      }
    });
  }

}
