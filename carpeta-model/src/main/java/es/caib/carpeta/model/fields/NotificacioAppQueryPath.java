
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class NotificacioAppQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public NotificacioAppQueryPath() {
  }

  protected NotificacioAppQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField NOTIFICACIOAPPID() {
    return new LongField(getQueryPath(), NotificacioAppFields.NOTIFICACIOAPPID);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), NotificacioAppFields.CODI);
  }

  public LongField TITOLID() {
    return new LongField(getQueryPath(), NotificacioAppFields.TITOLID);
  }

  public LongField MISSATGEID() {
    return new LongField(getQueryPath(), NotificacioAppFields.MISSATGEID);
  }

  public LongField FRONTPLUGINID() {
    return new LongField(getQueryPath(), NotificacioAppFields.FRONTPLUGINID);
  }

  public StringField AJUDA() {
    return new StringField(getQueryPath(), NotificacioAppFields.AJUDA);
  }

  public BooleanField ACTIVA() {
    return new BooleanField(getQueryPath(), NotificacioAppFields.ACTIVA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (NotificacioAppFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TraduccioQueryPath TITOL() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return NotificacioAppQueryPath.this.getQueryPath() + "titol" + ".";
      }
    });
  }

  public TraduccioQueryPath MISSATGE() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return NotificacioAppQueryPath.this.getQueryPath() + "missatge" + ".";
      }
    });
  }

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return NotificacioAppQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

}
