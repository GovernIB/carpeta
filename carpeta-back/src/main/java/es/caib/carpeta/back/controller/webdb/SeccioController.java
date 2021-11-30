package es.caib.carpeta.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import es.caib.carpeta.back.form.webdb.*;
import es.caib.carpeta.back.form.webdb.SeccioForm;

import es.caib.carpeta.back.validator.webdb.SeccioWebValidator;

import es.caib.carpeta.model.entity.Fitxer;
import es.caib.carpeta.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.carpeta.persistence.SeccioJPA;
import es.caib.carpeta.model.entity.Seccio;
import es.caib.carpeta.model.fields.*;

/**
 * Controller per gestionar un Seccio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/seccio")
@SessionAttributes(types = { SeccioForm.class, SeccioFilterForm.class })
public class SeccioController
    extends es.caib.carpeta.back.controller.CarpetaFilesBaseController<Seccio, java.lang.Long, SeccioForm> implements SeccioFields {

  @EJB(mappedName = es.caib.carpeta.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.carpeta.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = es.caib.carpeta.ejb.SeccioService.JNDI_NAME)
  protected es.caib.carpeta.ejb.SeccioService seccioEjb;

  @Autowired
  private SeccioWebValidator seccioWebValidator;

  @Autowired
  protected SeccioRefList seccioRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes Seccio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    SeccioFilterForm ff;
    ff = (SeccioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Seccio de forma paginada
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
  public ModelAndView llistatPaginat(HttpServletRequest request,
    HttpServletResponse response, @PathVariable Integer pagina)
      throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileList());
    llistat(mav, request, getSeccioFilterForm(pagina, mav, request));
    return mav;
  }

  public SeccioFilterForm getSeccioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    SeccioFilterForm seccioFilterForm;
    seccioFilterForm = (SeccioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(seccioFilterForm == null) {
      seccioFilterForm = new SeccioFilterForm();
      seccioFilterForm.setContexte(getContextWeb());
      seccioFilterForm.setEntityNameCode(getEntityNameCode());
      seccioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      seccioFilterForm.setNou(true);
    } else {
      seccioFilterForm.setNou(false);
    }
    seccioFilterForm.setPage(pagina == null ? 1 : pagina);
    return seccioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Seccio de forma paginada
   * 
   * @param request
   * @param pagina
   * @param filterForm
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.POST)
  public ModelAndView llistatPaginat(HttpServletRequest request,
      HttpServletResponse response,@PathVariable Integer pagina,
      @ModelAttribute SeccioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getSeccioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Seccio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Seccio> llistat(ModelAndView mav, HttpServletRequest request,
     SeccioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Seccio> seccio = processarLlistat(seccioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("seccioItems", seccio);

    mav.addObject("seccioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, seccio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, seccio);

    return seccio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(SeccioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Seccio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field nomID
    {
      _listSKV = getReferenceListForNomID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForNomID(_tmp);
      if (filterForm.getGroupByFields().contains(NOMID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, NOMID, false);
      };
    }

    // Field descripcioID
    {
      _listSKV = getReferenceListForDescripcioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForDescripcioID(_tmp);
      if (filterForm.getGroupByFields().contains(DESCRIPCIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, DESCRIPCIOID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ACTIVA);

    // Field seccioPareID
    {
      _listSKV = getReferenceListForSeccioPareID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForSeccioPareID(_tmp);
      if (filterForm.getGroupByFields().contains(SECCIOPAREID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SECCIOPAREID, false);
      };
    }

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    SeccioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Seccio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_SECCIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(NOMID, filterForm.getMapOfTraduccioForNomID());
    __mapping.put(DESCRIPCIOID, filterForm.getMapOfTraduccioForDescripcioID());
    __mapping.put(SECCIOPAREID, filterForm.getMapOfValuesForSeccioPareID());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Seccio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearSeccioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    SeccioForm seccioForm = getSeccioForm(null, false, request, mav);
    
    if (seccioForm.getSeccio().getNom() == null){
      es.caib.carpeta.persistence.TraduccioJPA trad = new es.caib.carpeta.persistence.TraduccioJPA();
      for (es.caib.carpeta.model.entity.Idioma idioma : seccioForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.carpeta.persistence.TraduccioMapJPA());
      }
      seccioForm.getSeccio().setNom(trad);
    }

    
    if (seccioForm.getSeccio().getDescripcio() == null){
      es.caib.carpeta.persistence.TraduccioJPA trad = new es.caib.carpeta.persistence.TraduccioJPA();
      for (es.caib.carpeta.model.entity.Idioma idioma : seccioForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.carpeta.persistence.TraduccioMapJPA());
      }
      seccioForm.getSeccio().setDescripcio(trad);
    }

    mav.addObject("seccioForm" ,seccioForm);
    fillReferencesForForm(seccioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public SeccioForm getSeccioForm(SeccioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    SeccioForm seccioForm;
    if(_jpa == null) {
      seccioForm = new SeccioForm(new SeccioJPA(), true);
    } else {
      seccioForm = new SeccioForm(_jpa, false);
      seccioForm.setView(__isView);
    }
    seccioForm.setContexte(getContextWeb());
    seccioForm.setEntityNameCode(getEntityNameCode());
    seccioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    seccioForm.setIdiomesTraduccio(getIdiomesSuportats());
    return seccioForm;
  }

  public void fillReferencesForForm(SeccioForm seccioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (seccioForm.getListOfValuesForSeccioPareID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSeccioPareID(request, mav, seccioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      seccioForm.setListOfValuesForSeccioPareID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (seccioForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, seccioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      seccioForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }


  public List<es.caib.carpeta.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.carpeta.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.carpeta.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou Seccio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearSeccioPost(@ModelAttribute SeccioForm seccioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    SeccioJPA seccio = seccioForm.getSeccio();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, seccio, seccioForm); // FILE
      preValidate(request, seccioForm, result);
      getWebValidator().validate(seccioForm, result);
      postValidate(request,seccioForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        seccio = create(request, seccio);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", seccio.getSeccioID());
        seccioForm.setSeccio(seccio);
        return getRedirectWhenCreated(request, seccioForm);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{seccioID}", method = RequestMethod.GET)
  public ModelAndView veureSeccioGet(@PathVariable("seccioID") java.lang.Long seccioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewSeccioGet(seccioID,
        request, response, true);
  }


  protected ModelAndView editAndViewSeccioGet(@PathVariable("seccioID") java.lang.Long seccioID,
      HttpServletRequest request,
      HttpServletResponse response, boolean __isView) throws I18NException {
    if((!__isView) && !isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      if(__isView && !isActiveFormView()) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
      }
    }
    SeccioJPA seccio = findByPrimaryKey(request, seccioID);

    if (seccio == null) {
      createMessageWarning(request, "error.notfound", seccioID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, seccioID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      SeccioForm seccioForm = getSeccioForm(seccio, __isView, request, mav);
      seccioForm.setView(__isView);
      if(__isView) {
        seccioForm.setAllFieldsReadOnly(ALL_SECCIO_FIELDS);
        seccioForm.setSaveButtonVisible(false);
        seccioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(seccioForm, request, mav);
      mav.addObject("seccioForm", seccioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Seccio existent
   */
  @RequestMapping(value = "/{seccioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarSeccioGet(@PathVariable("seccioID") java.lang.Long seccioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewSeccioGet(seccioID,
        request, response, false);
  }



  /**
   * Editar un Seccio existent
   */
  @RequestMapping(value = "/{seccioID}/edit", method = RequestMethod.POST)
  public String editarSeccioPost(@ModelAttribute SeccioForm seccioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    SeccioJPA seccio = seccioForm.getSeccio();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, seccio, seccioForm); // FILE
      preValidate(request, seccioForm, result);
      getWebValidator().validate(seccioForm, result);
      postValidate(request, seccioForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        seccio = update(request, seccio);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", seccio.getSeccioID());
        status.setComplete();
        return getRedirectWhenModified(request, seccioForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          seccio.getSeccioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, seccioForm, __e);
    }

  }


  /**
   * Eliminar un Seccio existent
   */
  @RequestMapping(value = "/{seccioID}/delete")
  public String eliminarSeccio(@PathVariable("seccioID") java.lang.Long seccioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Seccio seccio = seccioEjb.findByPrimaryKey(seccioID);
      if (seccio == null) {
        String __msg =createMessageError(request, "error.notfound", seccioID);
        return getRedirectWhenDelete(request, seccioID, new Exception(__msg));
      } else {
        delete(request, seccio);
        createMessageSuccess(request, "success.deleted", seccioID);
        return getRedirectWhenDelete(request, seccioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", seccioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, seccioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute SeccioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarSeccio(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return java.lang.Long.parseLong(value, 10);
}

  @Override
  public String[] getArgumentsMissatge(Object __seccioID, Throwable e) {
    java.lang.Long seccioID = (java.lang.Long)__seccioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (seccioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(seccioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "seccio.seccio";
  }

  public String getEntityNameCodePlural() {
    return "seccio.seccio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("seccio.seccioID");
  }

  @InitBinder("seccioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("seccioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "seccio.seccioID", "seccio.contexte");
  }

  public SeccioWebValidator getWebValidator() {
    return seccioWebValidator;
  }


  public void setWebValidator(SeccioWebValidator __val) {
    if (__val != null) {
      this.seccioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Seccio
   */
  @RequestMapping(value = "/{seccioID}/cancel")
  public String cancelSeccio(@PathVariable("seccioID") java.lang.Long seccioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, seccioID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Seccio seccio,
      SeccioForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getIconaID(), form.isIconaIDDelete(),
        form.isNou()? null : seccio.getIcona());
    ((SeccioJPA)seccio).setIcona(f);
    if (f != null) { 
      seccio.setIconaID(f.getFitxerID());
    } else {
      seccio.setIconaID(0);
    }

  }

  // FILE
  @Override
  public void deleteFiles(Seccio seccio) {
    deleteFile(seccio.getIconaID());
  }
  // Mètodes a sobreescriure 

  public boolean isActiveList() {
    return true;
  }


  public boolean isActiveFormNew() {
    return true;
  }


  public boolean isActiveFormEdit() {
    return true;
  }


  public boolean isActiveDelete() {
    return true;
  }


  public boolean isActiveFormView() {
    return isActiveFormEdit();
  }

  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, SeccioFilterForm seccioFilterForm,
       List<Seccio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (seccioFilterForm.isHiddenField(NOMID)
      && !seccioFilterForm.isGroupByField(NOMID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(NOMID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Seccio _item : list) {
        _pkList.add(_item.getNomID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForNomID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }

  public List<StringKeyValue> getReferenceListForDescripcioID(HttpServletRequest request,
       ModelAndView mav, SeccioFilterForm seccioFilterForm,
       List<Seccio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (seccioFilterForm.isHiddenField(DESCRIPCIOID)
      && !seccioFilterForm.isGroupByField(DESCRIPCIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(DESCRIPCIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Seccio _item : list) {
        _pkList.add(_item.getDescripcioID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForDescripcioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForDescripcioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForSeccioPareID(HttpServletRequest request,
       ModelAndView mav, SeccioForm seccioForm, Where where)  throws I18NException {
    if (seccioForm.isHiddenField(SECCIOPAREID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForSeccioPareID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForSeccioPareID(HttpServletRequest request,
       ModelAndView mav, SeccioFilterForm seccioFilterForm,
       List<Seccio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (seccioFilterForm.isHiddenField(SECCIOPAREID)
      && !seccioFilterForm.isGroupByField(SECCIOPAREID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForSeccioPareID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSeccioPareID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, SeccioForm seccioForm, Where where)  throws I18NException {
    if (seccioForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (seccioForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(seccioForm.getSeccio().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, SeccioFilterForm seccioFilterForm,
       List<Seccio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (seccioFilterForm.isHiddenField(ENTITATID)
      && !seccioFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Seccio _item : list) {
        _pkList.add(_item.getEntitatID());
        }
        _w = EntitatFields.ENTITATID.in(_pkList);
      }
    return getReferenceListForEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
  }


  public void preValidate(HttpServletRequest request,SeccioForm seccioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,SeccioForm seccioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, SeccioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, SeccioFilterForm filterForm,  List<Seccio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, SeccioForm seccioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, SeccioForm seccioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long seccioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long seccioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "seccioFormWebDB";
  }

  public String getTileList() {
    return "seccioListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "SeccioWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public SeccioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long seccioID) throws I18NException {
    return (SeccioJPA) seccioEjb.findByPrimaryKey(seccioID);
  }


  public SeccioJPA create(HttpServletRequest request, SeccioJPA seccio)
    throws Exception,I18NException, I18NValidationException {
    return (SeccioJPA) seccioEjb.create(seccio);
  }


  public SeccioJPA update(HttpServletRequest request, SeccioJPA seccio)
    throws Exception,I18NException, I18NValidationException {
    return (SeccioJPA) seccioEjb.update(seccio);
  }


  public void delete(HttpServletRequest request, Seccio seccio) throws Exception,I18NException {
    seccioEjb.delete(seccio);
  }

} // Final de Classe

