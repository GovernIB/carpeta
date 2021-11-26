
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AccesQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AccesQueryPath() {
  }

  protected AccesQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ACCESID() {
    return new LongField(getQueryPath(), AccesFields.ACCESID);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), AccesFields.TIPUS);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), AccesFields.NOM);
  }

  public StringField LLINATGES() {
    return new StringField(getQueryPath(), AccesFields.LLINATGES);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), AccesFields.NIF);
  }

  public StringField IP() {
    return new StringField(getQueryPath(), AccesFields.IP);
  }

  public StringField PROVEIDORIDENTITAT() {
    return new StringField(getQueryPath(), AccesFields.PROVEIDORIDENTITAT);
  }

  public StringField METODEAUTENTICACIO() {
    return new StringField(getQueryPath(), AccesFields.METODEAUTENTICACIO);
  }

  public IntegerField QAA() {
    return new IntegerField(getQueryPath(), AccesFields.QAA);
  }

  public TimestampField DATAACCES() {
    return new TimestampField(getQueryPath(), AccesFields.DATAACCES);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), AccesFields.PLUGINID);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), AccesFields.ENTITATID);
  }

  public StringField IDIOMA() {
    return new StringField(getQueryPath(), AccesFields.IDIOMA);
  }

  public BooleanField RESULTAT() {
    return new BooleanField(getQueryPath(), AccesFields.RESULTAT);
  }

  public StringField IDSESSIO() {
    return new StringField(getQueryPath(), AccesFields.IDSESSIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AccesFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AccesQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
