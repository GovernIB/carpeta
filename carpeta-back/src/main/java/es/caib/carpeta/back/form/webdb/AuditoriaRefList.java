
package es.caib.carpeta.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.carpeta.ejb.AuditoriaLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.fields.AuditoriaFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AuditoriaRefList extends RefListBase
    implements AuditoriaFields {

  @EJB(mappedName = AuditoriaLocal.JNDI_NAME)
  private AuditoriaLocal auditoriaEjb;

  public AuditoriaRefList(AuditoriaRefList __clone) {
    super(__clone);
    this.auditoriaEjb = __clone.auditoriaEjb;
  }
  public AuditoriaRefList() {
    setSelects(new Select<?>[] { AUDITORIAID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = auditoriaEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
