
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EntitatQueryPath() {
  }

  protected EntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), EntitatFields.ENTITATID);
  }

  public LongField NOMID() {
    return new LongField(getQueryPath(), EntitatFields.NOMID);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), EntitatFields.CODI);
  }

  public StringField CODIDIR3() {
    return new StringField(getQueryPath(), EntitatFields.CODIDIR3);
  }

  public BooleanField ACTIVA() {
    return new BooleanField(getQueryPath(), EntitatFields.ACTIVA);
  }

  public LongField LOGOMENUID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOMENUID);
  }

  public StringField COLORMENU() {
    return new StringField(getQueryPath(), EntitatFields.COLORMENU);
  }

  public StringField TEXTEPEU() {
    return new StringField(getQueryPath(), EntitatFields.TEXTEPEU);
  }

  public LongField LOGOPEUID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOPEUID);
  }

  public StringField VERSIO() {
    return new StringField(getQueryPath(), EntitatFields.VERSIO);
  }

  public StringField COMMIT() {
    return new StringField(getQueryPath(), EntitatFields.COMMIT);
  }

  public LongField FITXERCSSID() {
    return new LongField(getQueryPath(), EntitatFields.FITXERCSSID);
  }

  public StringField CONTEXT() {
    return new StringField(getQueryPath(), EntitatFields.CONTEXT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AccesQueryPath ACCESS() {
    return new AccesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "access" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AuditoriaQueryPath AUDITORIAS() {
    return new AuditoriaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "auditorias" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AvisQueryPath AVISS() {
    return new AvisQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "aviss" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EnllazQueryPath ENLLAZS() {
    return new EnllazQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "enllazs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EstadisticaQueryPath ESTADISTICAS() {
    return new EstadisticaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "estadisticas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public LogCarpetaQueryPath LOGCARPETAS() {
    return new LogCarpetaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logCarpetas" + ".";
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
          return EntitatQueryPath.this.getQueryPath() + "pluginEntitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PropietatGlobalQueryPath PROPIETATGLOBALS() {
    return new PropietatGlobalQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "propietatGlobals" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariQueryPath USUARIS() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "usuaris" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariEntitatQueryPath USUARIENTITATS() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "usuariEntitats" + ".";
      }
    });
  }
*/

  public TraduccioQueryPath NOM() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "nom" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOMENU() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logoMenu" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOPEU() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logoPeu" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERCSS() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "fitxerCss" + ".";
      }
    });
  }

}
