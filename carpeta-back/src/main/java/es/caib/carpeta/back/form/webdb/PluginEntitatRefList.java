
package es.caib.carpeta.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.carpeta.ejb.PluginEntitatService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.fields.PluginEntitatFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PluginEntitatRefList extends RefListBase
    implements PluginEntitatFields {

  @EJB(mappedName = PluginEntitatService.JNDI_NAME)
  private PluginEntitatService pluginEntitatEjb;

  public PluginEntitatRefList(PluginEntitatRefList __clone) {
    super(__clone);
    this.pluginEntitatEjb = __clone.pluginEntitatEjb;
  }
  public PluginEntitatRefList() {
    setSelects(new Select<?>[] { PLUGINID.select, ENTITATID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = pluginEntitatEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
