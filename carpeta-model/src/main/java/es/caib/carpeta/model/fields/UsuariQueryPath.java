
package es.caib.carpeta.model.fields;

import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.QueryPath;
import org.fundaciobit.genapp.common.query.StringField;

public class UsuariQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariQueryPath() {
  }

  protected UsuariQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), UsuariFields.USUARIID);
  }

  public StringField USERNAME() {
    return new StringField(getQueryPath(), UsuariFields.USERNAME);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), UsuariFields.NOM);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), UsuariFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), UsuariFields.LLINATGE2);
  }

  public StringField EMAIL() {
    return new StringField(getQueryPath(), UsuariFields.EMAIL);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), UsuariFields.NIF);
  }

  public LongField DARRERAENTITAT() {
    return new LongField(getQueryPath(), UsuariFields.DARRERAENTITAT);
  }

  public StringField IDIOMAID() {
    return new StringField(getQueryPath(), UsuariFields.IDIOMAID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AuditoriaQueryPath AUDITORIAS() {
    return new AuditoriaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariQueryPath.this.getQueryPath() + "auditorias" + ".";
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
          return UsuariQueryPath.this.getQueryPath() + "usuariEntitats" + ".";
      }
    });
  }
*/

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public IdiomaQueryPath IDIOMA() {
    return new IdiomaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariQueryPath.this.getQueryPath() + "idioma" + ".";
      }
    });
  }

}
