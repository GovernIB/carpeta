
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PreguntesFrequentsQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PreguntesFrequentsQueryPath() {
  }

  protected PreguntesFrequentsQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PREGUNTESFREQUENTSID() {
    return new LongField(getQueryPath(), PreguntesFrequentsFields.PREGUNTESFREQUENTSID);
  }

  public LongField ENUNCIATID() {
    return new LongField(getQueryPath(), PreguntesFrequentsFields.ENUNCIATID);
  }

  public IntegerField ORDRE() {
    return new IntegerField(getQueryPath(), PreguntesFrequentsFields.ORDRE);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), PreguntesFrequentsFields.ENTITATID);
  }

  public StringField RESPOSTACA() {
    return new StringField(getQueryPath(), PreguntesFrequentsFields.RESPOSTACA);
  }

  public StringField RESPOSTAES() {
    return new StringField(getQueryPath(), PreguntesFrequentsFields.RESPOSTAES);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PreguntesFrequentsFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TraduccioQueryPath ENUNCIAT() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PreguntesFrequentsQueryPath.this.getQueryPath() + "enunciat" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PreguntesFrequentsQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
