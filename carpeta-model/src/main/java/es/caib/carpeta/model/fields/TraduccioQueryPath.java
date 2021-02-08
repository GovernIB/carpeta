
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TraduccioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TraduccioQueryPath() {
  }

  protected TraduccioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TRADUCCIOID() {
    return new LongField(getQueryPath(), TraduccioFields.TRADUCCIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TraduccioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AvisQueryPath AVISS() {
    return new AvisQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "aviss" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EnllazQueryPath ENLLAZ_NOMIDS() {
    return new EnllazQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "enllaz_nomids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EnllazQueryPath ENLLAZ_URLIDS() {
    return new EnllazQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "enllaz_urlids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_LOGINTEXTIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "entitat_logintextids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITAT_NOMIDS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "entitat_nomids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PluginQueryPath PLUGIN_DESCRIPCIOIDS() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "plugin_descripcioids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PluginQueryPath PLUGIN_NOMIDS() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "plugin_nomids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SeccioQueryPath SECCIO_DESCRIPCIOIDS() {
    return new SeccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "seccio_descripcioids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SeccioQueryPath SECCIO_NOMIDS() {
    return new SeccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "seccio_nomids" + ".";
      }
    });
  }
*/

}
