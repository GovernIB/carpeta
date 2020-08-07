
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PropietatGlobalQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PropietatGlobalQueryPath() {
  }

  protected PropietatGlobalQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PROPIETAGLOBALID() {
    return new LongField(getQueryPath(), PropietatGlobalFields.PROPIETAGLOBALID);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), PropietatGlobalFields.CODI);
  }

  public StringField VALUE() {
    return new StringField(getQueryPath(), PropietatGlobalFields.VALUE);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), PropietatGlobalFields.DESCRIPCIO);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), PropietatGlobalFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PropietatGlobalFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PropietatGlobalQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
