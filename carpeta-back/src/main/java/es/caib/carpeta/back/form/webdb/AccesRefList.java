
package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.ejb.AccesLocal;
import es.caib.carpeta.model.fields.AccesFields;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.controller.RefListBase;
import org.springframework.stereotype.Component;

import javax.ejb.EJB;
import java.util.List;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AccesRefList extends RefListBase
    implements AccesFields {

  @EJB(mappedName = AccesLocal.JNDI_NAME)
  private AccesLocal accesEjb;

  public AccesRefList(AccesRefList __clone) {
    super(__clone);
    this.accesEjb = __clone.accesEjb;
  }
  public AccesRefList() {
    setSelects(new Select<?>[] { NOM.select, NIF.select, DATADARRERACCES.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = accesEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
