
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class LogCarpetaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public LogCarpetaQueryPath() {
  }

  protected LogCarpetaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField LOGID() {
    return new LongField(getQueryPath(), LogCarpetaFields.LOGID);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), LogCarpetaFields.DESCRIPCIO);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), LogCarpetaFields.ENTITATID);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), LogCarpetaFields.PLUGINID);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), LogCarpetaFields.TIPUS);
  }

  public IntegerField ESTAT() {
    return new IntegerField(getQueryPath(), LogCarpetaFields.ESTAT);
  }

  public LongField TEMPS() {
    return new LongField(getQueryPath(), LogCarpetaFields.TEMPS);
  }

  public TimestampField DATAINICI() {
    return new TimestampField(getQueryPath(), LogCarpetaFields.DATAINICI);
  }

  public StringField PETICIO() {
    return new StringField(getQueryPath(), LogCarpetaFields.PETICIO);
  }

  public StringField ERROR() {
    return new StringField(getQueryPath(), LogCarpetaFields.ERROR);
  }

  public StringField EXCEPCIO() {
    return new StringField(getQueryPath(), LogCarpetaFields.EXCEPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (LogCarpetaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LogCarpetaQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LogCarpetaQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

}
