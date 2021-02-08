
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PluginQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PluginQueryPath() {
  }

  protected PluginQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), PluginFields.PLUGINID);
  }

  public LongField NOMID() {
    return new LongField(getQueryPath(), PluginFields.NOMID);
  }

  public LongField DESCRIPCIOID() {
    return new LongField(getQueryPath(), PluginFields.DESCRIPCIOID);
  }

  public LongField LOGOID() {
    return new LongField(getQueryPath(), PluginFields.LOGOID);
  }

  public StringField CLASSE() {
    return new StringField(getQueryPath(), PluginFields.CLASSE);
  }

  public StringField PROPIETATS() {
    return new StringField(getQueryPath(), PluginFields.PROPIETATS);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), PluginFields.ACTIU);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), PluginFields.TIPUS);
  }

  public LongField SECCIOID() {
    return new LongField(getQueryPath(), PluginFields.SECCIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PluginFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AvisQueryPath AVISS() {
    return new AvisQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "aviss" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITATS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "entitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PluginEntitatQueryPath PLUGINENTITATS() {
    return new PluginEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "pluginEntitats" + ".";
      }
    });
  }
*/

  public TraduccioQueryPath NOM() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "nom" + ".";
      }
    });
  }

  public TraduccioQueryPath DESCRIPCIO() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "descripcio" + ".";
      }
    });
  }

  public FitxerQueryPath LOGO() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "logo" + ".";
      }
    });
  }

  public SeccioQueryPath SECCIO() {
    return new SeccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "seccio" + ".";
      }
    });
  }

}
