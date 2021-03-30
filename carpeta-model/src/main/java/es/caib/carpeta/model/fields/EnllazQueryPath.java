
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EnllazQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EnllazQueryPath() {
  }

  protected EnllazQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ENLLAZID() {
    return new LongField(getQueryPath(), EnllazFields.ENLLAZID);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), EnllazFields.TIPUS);
  }

  public LongField NOMID() {
    return new LongField(getQueryPath(), EnllazFields.NOMID);
  }

  public LongField DESCRIPCIOID() {
    return new LongField(getQueryPath(), EnllazFields.DESCRIPCIOID);
  }

  public LongField URLID() {
    return new LongField(getQueryPath(), EnllazFields.URLID);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), EnllazFields.ENTITATID);
  }

  public LongField LOGOID() {
    return new LongField(getQueryPath(), EnllazFields.LOGOID);
  }

  public LongField SECCIOID() {
    return new LongField(getQueryPath(), EnllazFields.SECCIOID);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), EnllazFields.ACTIU);
  }

  public IntegerField ORDRE() {
    return new IntegerField(getQueryPath(), EnllazFields.ORDRE);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EnllazFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TraduccioQueryPath NOM() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EnllazQueryPath.this.getQueryPath() + "nom" + ".";
      }
    });
  }

  public TraduccioQueryPath DESCRIPCIO() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EnllazQueryPath.this.getQueryPath() + "descripcio" + ".";
      }
    });
  }

  public TraduccioQueryPath URL() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EnllazQueryPath.this.getQueryPath() + "url" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EnllazQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public FitxerQueryPath LOGO() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EnllazQueryPath.this.getQueryPath() + "logo" + ".";
      }
    });
  }

  public SeccioQueryPath SECCIO() {
    return new SeccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EnllazQueryPath.this.getQueryPath() + "seccio" + ".";
      }
    });
  }

}
