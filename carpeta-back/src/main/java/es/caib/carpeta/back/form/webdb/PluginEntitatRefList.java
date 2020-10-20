
package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.ejb.PluginEntitatLocal;
import es.caib.carpeta.model.fields.PluginEntitatFields;
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
public class PluginEntitatRefList extends RefListBase
    implements PluginEntitatFields {

  @EJB(mappedName = PluginEntitatLocal.JNDI_NAME)
  private PluginEntitatLocal pluginEntitatEjb;

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
