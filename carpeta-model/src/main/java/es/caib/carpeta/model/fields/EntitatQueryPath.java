
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

  public StringField COLORMENU() {
    return new StringField(getQueryPath(), EntitatFields.COLORMENU);
  }

  public LongField LOGOCAPBACKID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOCAPBACKID);
  }

  public LongField LOGOPEUBACKID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOPEUBACKID);
  }

  public LongField LOGOLATERALFRONTID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOLATERALFRONTID);
  }

  public StringField VERSIO() {
    return new StringField(getQueryPath(), EntitatFields.VERSIO);
  }

  public LongField ICONID() {
    return new LongField(getQueryPath(), EntitatFields.ICONID);
  }

  public StringField WEBENTITAT() {
    return new StringField(getQueryPath(), EntitatFields.WEBENTITAT);
  }

  public StringField ENTITATDESCFRONT() {
    return new StringField(getQueryPath(), EntitatFields.ENTITATDESCFRONT);
  }

  public StringField SUPORTWEB() {
    return new StringField(getQueryPath(), EntitatFields.SUPORTWEB);
  }

  public StringField SUPORTTELEFON() {
    return new StringField(getQueryPath(), EntitatFields.SUPORTTELEFON);
  }

  public StringField SUPORTEMAIL() {
    return new StringField(getQueryPath(), EntitatFields.SUPORTEMAIL);
  }

  public LongField PLUGINLOGINID() {
    return new LongField(getQueryPath(), EntitatFields.PLUGINLOGINID);
  }

  public LongField FITXERCSSID() {
    return new LongField(getQueryPath(), EntitatFields.FITXERCSSID);
  }

  public StringField CONTEXT() {
    return new StringField(getQueryPath(), EntitatFields.CONTEXT);
  }

  public StringField COMMIT() {
    return new StringField(getQueryPath(), EntitatFields.COMMIT);
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

  public FitxerQueryPath LOGOCAPBACK() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logoCapBack" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOPEUBACK() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logoPeuBack" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOLATERALFRONT() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logoLateralFront" + ".";
      }
    });
  }

  public FitxerQueryPath ICON() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "icon" + ".";
      }
    });
  }

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "plugin" + ".";
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
