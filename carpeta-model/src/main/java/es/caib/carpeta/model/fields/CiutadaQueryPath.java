
package es.caib.carpeta.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class CiutadaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public CiutadaQueryPath() {
  }

  protected CiutadaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CIUTADAID() {
    return new LongField(getQueryPath(), CiutadaFields.CIUTADAID);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), CiutadaFields.NIF);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), CiutadaFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), CiutadaFields.LLINATGE2);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), CiutadaFields.NOM);
  }

  public BooleanField EMPRESA() {
    return new BooleanField(getQueryPath(), CiutadaFields.EMPRESA);
  }

  public StringField REPRESENTANTNIF() {
    return new StringField(getQueryPath(), CiutadaFields.REPRESENTANTNIF);
  }

  public StringField REPRESENTANTLLINATGE1() {
    return new StringField(getQueryPath(), CiutadaFields.REPRESENTANTLLINATGE1);
  }

  public StringField REPRESENTANTLLINATGE2() {
    return new StringField(getQueryPath(), CiutadaFields.REPRESENTANTLLINATGE2);
  }

  public StringField REPRESENTANTNOM() {
    return new StringField(getQueryPath(), CiutadaFields.REPRESENTANTNOM);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), CiutadaFields.DATACREACIO);
  }

  public StringField MOBILEID() {
    return new StringField(getQueryPath(), CiutadaFields.MOBILEID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (CiutadaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
