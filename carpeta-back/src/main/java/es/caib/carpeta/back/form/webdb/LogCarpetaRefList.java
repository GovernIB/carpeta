
package es.caib.carpeta.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.carpeta.ejb.LogCarpetaService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.fields.LogCarpetaFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class LogCarpetaRefList extends RefListBase
    implements LogCarpetaFields {

  @EJB(mappedName = LogCarpetaService.JNDI_NAME)
  private LogCarpetaService logCarpetaEjb;

  public LogCarpetaRefList(LogCarpetaRefList __clone) {
    super(__clone);
    this.logCarpetaEjb = __clone.logCarpetaEjb;
  }
  public LogCarpetaRefList() {
    setSelects(new Select<?>[] { LOGID.select, DESCRIPCIO.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = logCarpetaEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
