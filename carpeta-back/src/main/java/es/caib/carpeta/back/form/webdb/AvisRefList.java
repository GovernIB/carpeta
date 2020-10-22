
package es.caib.carpeta.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.carpeta.ejb.AvisLocal;
import es.caib.carpeta.ejb.TraduccioLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.fields.AvisFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AvisRefList extends RefListBase
    implements AvisFields {

  @EJB(mappedName = AvisLocal.JNDI_NAME)
  private AvisLocal avisEjb;

  @EJB(mappedName = TraduccioLocal.JNDI_NAME)
  private TraduccioLocal traduccioEjb;
  public AvisRefList(AvisRefList __clone) {
    super(__clone);
    this.avisEjb = __clone.avisEjb;
    this.traduccioEjb = __clone.traduccioEjb;
  }
  public AvisRefList() {
    setSelects(new Select<?>[] { DESCRIPCIOID.select });
    addCampTraduible(DESCRIPCIOID.select);
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<Long> _transSelect = checkTranslationFields();
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = avisEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    if (_transSelect == null) {
      return list;
    }
    // key => TransID | value => avisEjb_PK
    java.util.Map<String,String> keysMap = org.fundaciobit.genapp.common.utils.Utils.listToMapInverse(list);
    org.fundaciobit.genapp.common.query.Where _w1 = es.caib.carpeta.model.fields.TraduccioFields.TRADUCCIOID.in(avisEjb.executeQuery(_transSelect, where));
    List<es.caib.carpeta.model.entity.Traduccio> traduccions = traduccioEjb.select(_w1);
    List<StringKeyValue> _list = new java.util.ArrayList<StringKeyValue>(traduccions.size());
    final String _lang = org.fundaciobit.genapp.common.web.i18n.I18NUtils.getLocale().getLanguage();
    for (es.caib.carpeta.model.entity.Traduccio traduccio : traduccions) {
      es.caib.carpeta.jpa.TraduccioJPA traduccioJPA = (es.caib.carpeta.jpa.TraduccioJPA) traduccio;
      String key = keysMap.get(String.valueOf(traduccioJPA.getTraduccioID()));
      String value = traduccioJPA.getTraduccio(_lang).getValor();
      StringKeyValue skv = new StringKeyValue(key, value);
      _list.add(skv);
    }
    if (!_list.isEmpty()) {
      java.util.Collections.sort(_list, new org.fundaciobit.genapp.common.KeyValue.KeyValueComparator<String>());
    }
    return _list;

  }
}
