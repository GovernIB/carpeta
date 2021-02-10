
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class SeccioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public SeccioQueryPath() {
  }

  protected SeccioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField SECCIOID() {
    return new LongField(getQueryPath(), SeccioFields.SECCIOID);
  }

  public LongField NOMID() {
    return new LongField(getQueryPath(), SeccioFields.NOMID);
  }

  public LongField DESCRIPCIOID() {
    return new LongField(getQueryPath(), SeccioFields.DESCRIPCIOID);
  }

  public BooleanField ACTIVA() {
    return new BooleanField(getQueryPath(), SeccioFields.ACTIVA);
  }

  public LongField ICONAID() {
    return new LongField(getQueryPath(), SeccioFields.ICONAID);
  }

  public LongField SECCIOPAREID() {
    return new LongField(getQueryPath(), SeccioFields.SECCIOPAREID);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), SeccioFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (SeccioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EnllazQueryPath ENLLAZS() {
    return new EnllazQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SeccioQueryPath.this.getQueryPath() + "enllazs" + ".";
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
          return SeccioQueryPath.this.getQueryPath() + "pluginEntitats" + ".";
      }
    });
  }
*/

  public TraduccioQueryPath NOM() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SeccioQueryPath.this.getQueryPath() + "nom" + ".";
      }
    });
  }

  public TraduccioQueryPath DESCRIPCIO() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SeccioQueryPath.this.getQueryPath() + "descripcio" + ".";
      }
    });
  }

  public FitxerQueryPath ICONA() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SeccioQueryPath.this.getQueryPath() + "icona" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SeccioQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
