
package es.caib.carpeta.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.carpeta.ejb.PreguntesFrequentsService;
import es.caib.carpeta.ejb.TraduccioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.fields.PreguntesFrequentsFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PreguntesFrequentsRefList extends RefListBase
    implements PreguntesFrequentsFields {

  @EJB(mappedName = PreguntesFrequentsService.JNDI_NAME)
  private PreguntesFrequentsService preguntesFrequentsEjb;

  @EJB(mappedName = TraduccioService.JNDI_NAME)
  private TraduccioService traduccioEjb;
  public PreguntesFrequentsRefList(PreguntesFrequentsRefList __clone) {
    super(__clone);
    this.preguntesFrequentsEjb = __clone.preguntesFrequentsEjb;
    this.traduccioEjb = __clone.traduccioEjb;
  }
  public PreguntesFrequentsRefList() {
    setSelects(new Select<?>[] { PREGUNTESFREQUENTSID.select });
    addCampTraduible(ENUNCIATID.select);
    addCampTraduible(RESPOSTAID.select);
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<Long> _transSelect = checkTranslationFields();
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = preguntesFrequentsEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    if (_transSelect == null) {
      return list;
    }
    // key => TransID | value => preguntesFrequentsEjb_PK
    java.util.Map<String,String> keysMap = org.fundaciobit.genapp.common.utils.Utils.listToMapInverse(list);
    org.fundaciobit.genapp.common.query.Where _w1 = es.caib.carpeta.model.fields.TraduccioFields.TRADUCCIOID.in(preguntesFrequentsEjb.executeQuery(_transSelect, where));
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
