
package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.ejb.PropietatGlobalLocal;
import es.caib.carpeta.model.fields.PropietatGlobalFields;
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
public class PropietatGlobalRefList extends RefListBase
    implements PropietatGlobalFields {

  @EJB(mappedName = PropietatGlobalLocal.JNDI_NAME)
  private PropietatGlobalLocal propietatGlobalEjb;

  public PropietatGlobalRefList(PropietatGlobalRefList __clone) {
    super(__clone);
    this.propietatGlobalEjb = __clone.propietatGlobalEjb;
  }
  public PropietatGlobalRefList() {
    setSelects(new Select<?>[] { PROPIETAGLOBALID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = propietatGlobalEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
