
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



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PluginFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public LogCarpetaQueryPath LOGCARPETAS() {
    return new LogCarpetaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "logCarpetas" + ".";
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

}
