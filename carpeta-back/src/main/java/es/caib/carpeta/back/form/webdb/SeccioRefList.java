
package es.caib.carpeta.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.carpeta.ejb.SeccioService;
import es.caib.carpeta.ejb.TraduccioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.fields.SeccioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class SeccioRefList extends RefListBase
    implements SeccioFields {

  @EJB(mappedName = SeccioService.JNDI_NAME)
  private SeccioService seccioEjb;

  @EJB(mappedName = TraduccioService.JNDI_NAME)
  private TraduccioService traduccioEjb;
  public SeccioRefList(SeccioRefList __clone) {
    super(__clone);
    this.seccioEjb = __clone.seccioEjb;
    this.traduccioEjb = __clone.traduccioEjb;
  }
  public SeccioRefList() {
    setSelects(new Select<?>[] { NOMID.select });
    addCampTraduible(NOMID.select);
    addCampTraduible(DESCRIPCIOID.select);
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<Long> _transSelect = checkTranslationFields();
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = seccioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    if (_transSelect == null) {
      return list;
    }
    // key => TransID | value => seccioEjb_PK
    java.util.Map<String,String> keysMap = org.fundaciobit.genapp.common.utils.Utils.listToMapInverse(list);
    org.fundaciobit.genapp.common.query.Where _w1 = es.caib.carpeta.model.fields.TraduccioFields.TRADUCCIOID.in(seccioEjb.executeQuery(_transSelect, where));
    List<es.caib.carpeta.model.entity.Traduccio> traduccions = traduccioEjb.select(_w1);
    List<StringKeyValue> _list = new java.util.ArrayList<StringKeyValue>(traduccions.size());
    final String _lang = org.fundaciobit.genapp.common.web.i18n.I18NUtils.getLocale().getLanguage();
    for (es.caib.carpeta.model.entity.Traduccio traduccio : traduccions) {
      es.caib.carpeta.persistence.TraduccioJPA traduccioJPA = (es.caib.carpeta.persistence.TraduccioJPA) traduccio;
      String key = keysMap.get(String.valueOf(traduccioJPA.getTraduccioID()));
      es.caib.carpeta.persistence.TraduccioMapJPA _tm = traduccioJPA.getTraduccio(_lang);
      String value;
      if (_tm == null) {
          value = "NO_TRADUCCIO_PER_CODI_" + traduccio.getTraduccioID() + "_[" + _lang + "]";
      } else {
          value= _tm.getValor();
      }
      StringKeyValue skv = new StringKeyValue(key, value);
      _list.add(skv);
    }
    if (!_list.isEmpty()) {
      java.util.Collections.sort(_list, new org.fundaciobit.genapp.common.KeyValue.KeyValueComparator<String>());
    }
    return _list;

  }
}
