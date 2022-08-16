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
import es.caib.carpeta.back.form.webdb.PreguntesFrequentsForm;

import es.caib.carpeta.back.validator.webdb.PreguntesFrequentsWebValidator;

import es.caib.carpeta.model.entity.Fitxer;
import es.caib.carpeta.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.carpeta.persistence.PreguntesFrequentsJPA;
import es.caib.carpeta.model.entity.PreguntesFrequents;
import es.caib.carpeta.model.fields.*;

/**
 * Controller per gestionar un PreguntesFrequents
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/preguntesFrequents")
@SessionAttributes(types = { PreguntesFrequentsForm.class, PreguntesFrequentsFilterForm.class })
public class PreguntesFrequentsController
    extends es.caib.carpeta.back.controller.CarpetaFilesBaseController<PreguntesFrequents, java.lang.Long, PreguntesFrequentsForm> implements PreguntesFrequentsFields {

  @EJB(mappedName = es.caib.carpeta.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.carpeta.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = es.caib.carpeta.ejb.PreguntesFrequentsService.JNDI_NAME)
  protected es.caib.carpeta.ejb.PreguntesFrequentsService preguntesFrequentsEjb;

  @Autowired
  private PreguntesFrequentsWebValidator preguntesFrequentsWebValidator;

  @Autowired
  protected PreguntesFrequentsRefList preguntesFrequentsRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes PreguntesFrequents
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PreguntesFrequentsFilterForm ff;
    ff = (PreguntesFrequentsFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PreguntesFrequents de forma paginada
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
    llistat(mav, request, getPreguntesFrequentsFilterForm(pagina, mav, request));
    return mav;
  }

  public PreguntesFrequentsFilterForm getPreguntesFrequentsFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PreguntesFrequentsFilterForm preguntesFrequentsFilterForm;
    preguntesFrequentsFilterForm = (PreguntesFrequentsFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(preguntesFrequentsFilterForm == null) {
      preguntesFrequentsFilterForm = new PreguntesFrequentsFilterForm();
      preguntesFrequentsFilterForm.setContexte(getContextWeb());
      preguntesFrequentsFilterForm.setEntityNameCode(getEntityNameCode());
      preguntesFrequentsFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      preguntesFrequentsFilterForm.setNou(true);
    } else {
      preguntesFrequentsFilterForm.setNou(false);
    }
    preguntesFrequentsFilterForm.setPage(pagina == null ? 1 : pagina);
    return preguntesFrequentsFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PreguntesFrequents de forma paginada
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
      @ModelAttribute PreguntesFrequentsFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPreguntesFrequentsFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PreguntesFrequents de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PreguntesFrequents> llistat(ModelAndView mav, HttpServletRequest request,
     PreguntesFrequentsFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PreguntesFrequents> preguntesFrequents = processarLlistat(preguntesFrequentsEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("preguntesFrequentsItems", preguntesFrequents);

    mav.addObject("preguntesFrequentsFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, preguntesFrequents, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, preguntesFrequents);

    return preguntesFrequents;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PreguntesFrequentsFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PreguntesFrequents> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field enunciatID
    {
      _listSKV = getReferenceListForEnunciatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForEnunciatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENUNCIATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENUNCIATID, false);
      };
    }

    // Field respostaID
    {
      _listSKV = getReferenceListForRespostaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForRespostaID(_tmp);
      if (filterForm.getGroupByFields().contains(RESPOSTAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, RESPOSTAID, false);
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
    PreguntesFrequentsFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PreguntesFrequents> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PREGUNTESFREQUENTS_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENUNCIATID, filterForm.getMapOfTraduccioForEnunciatID());
    __mapping.put(RESPOSTAID, filterForm.getMapOfTraduccioForRespostaID());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PreguntesFrequents
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPreguntesFrequentsGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PreguntesFrequentsForm preguntesFrequentsForm = getPreguntesFrequentsForm(null, false, request, mav);
    
    if (preguntesFrequentsForm.getPreguntesFrequents().getEnunciat() == null){
      es.caib.carpeta.persistence.TraduccioJPA trad = new es.caib.carpeta.persistence.TraduccioJPA();
      for (es.caib.carpeta.model.entity.Idioma idioma : preguntesFrequentsForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.carpeta.persistence.TraduccioMapJPA());
      }
      preguntesFrequentsForm.getPreguntesFrequents().setEnunciat(trad);
    }

    
    if (preguntesFrequentsForm.getPreguntesFrequents().getResposta() == null){
      es.caib.carpeta.persistence.TraduccioJPA trad = new es.caib.carpeta.persistence.TraduccioJPA();
      for (es.caib.carpeta.model.entity.Idioma idioma : preguntesFrequentsForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.carpeta.persistence.TraduccioMapJPA());
      }
      preguntesFrequentsForm.getPreguntesFrequents().setResposta(trad);
    }

    mav.addObject("preguntesFrequentsForm" ,preguntesFrequentsForm);
    fillReferencesForForm(preguntesFrequentsForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PreguntesFrequentsForm getPreguntesFrequentsForm(PreguntesFrequentsJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PreguntesFrequentsForm preguntesFrequentsForm;
    if(_jpa == null) {
      preguntesFrequentsForm = new PreguntesFrequentsForm(new PreguntesFrequentsJPA(), true);
    } else {
      preguntesFrequentsForm = new PreguntesFrequentsForm(_jpa, false);
      preguntesFrequentsForm.setView(__isView);
    }
    preguntesFrequentsForm.setContexte(getContextWeb());
    preguntesFrequentsForm.setEntityNameCode(getEntityNameCode());
    preguntesFrequentsForm.setEntityNameCodePlural(getEntityNameCodePlural());
    preguntesFrequentsForm.setIdiomesTraduccio(getIdiomesSuportats());
    return preguntesFrequentsForm;
  }

  public void fillReferencesForForm(PreguntesFrequentsForm preguntesFrequentsForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (preguntesFrequentsForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, preguntesFrequentsForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      preguntesFrequentsForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }


  public List<es.caib.carpeta.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.carpeta.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.carpeta.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou PreguntesFrequents
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPreguntesFrequentsPost(@ModelAttribute PreguntesFrequentsForm preguntesFrequentsForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PreguntesFrequentsJPA preguntesFrequents = preguntesFrequentsForm.getPreguntesFrequents();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, preguntesFrequents, preguntesFrequentsForm); // FILE
      preValidate(request, preguntesFrequentsForm, result);
      getWebValidator().validate(preguntesFrequentsForm, result);
      postValidate(request,preguntesFrequentsForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        preguntesFrequents = create(request, preguntesFrequents);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", preguntesFrequents.getPreguntesFrequentsID());
        preguntesFrequentsForm.setPreguntesFrequents(preguntesFrequents);
        return getRedirectWhenCreated(request, preguntesFrequentsForm);
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

  @RequestMapping(value = "/view/{preguntesFrequentsID}", method = RequestMethod.GET)
  public ModelAndView veurePreguntesFrequentsGet(@PathVariable("preguntesFrequentsID") java.lang.Long preguntesFrequentsID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPreguntesFrequentsGet(preguntesFrequentsID,
        request, response, true);
  }


  protected ModelAndView editAndViewPreguntesFrequentsGet(@PathVariable("preguntesFrequentsID") java.lang.Long preguntesFrequentsID,
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
    PreguntesFrequentsJPA preguntesFrequents = findByPrimaryKey(request, preguntesFrequentsID);

    if (preguntesFrequents == null) {
      createMessageWarning(request, "error.notfound", preguntesFrequentsID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, preguntesFrequentsID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PreguntesFrequentsForm preguntesFrequentsForm = getPreguntesFrequentsForm(preguntesFrequents, __isView, request, mav);
      preguntesFrequentsForm.setView(__isView);
      if(__isView) {
        preguntesFrequentsForm.setAllFieldsReadOnly(ALL_PREGUNTESFREQUENTS_FIELDS);
        preguntesFrequentsForm.setSaveButtonVisible(false);
        preguntesFrequentsForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(preguntesFrequentsForm, request, mav);
      mav.addObject("preguntesFrequentsForm", preguntesFrequentsForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PreguntesFrequents existent
   */
  @RequestMapping(value = "/{preguntesFrequentsID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPreguntesFrequentsGet(@PathVariable("preguntesFrequentsID") java.lang.Long preguntesFrequentsID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPreguntesFrequentsGet(preguntesFrequentsID,
        request, response, false);
  }



  /**
   * Editar un PreguntesFrequents existent
   */
  @RequestMapping(value = "/{preguntesFrequentsID}/edit", method = RequestMethod.POST)
  public String editarPreguntesFrequentsPost(@ModelAttribute PreguntesFrequentsForm preguntesFrequentsForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PreguntesFrequentsJPA preguntesFrequents = preguntesFrequentsForm.getPreguntesFrequents();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, preguntesFrequents, preguntesFrequentsForm); // FILE
      preValidate(request, preguntesFrequentsForm, result);
      getWebValidator().validate(preguntesFrequentsForm, result);
      postValidate(request, preguntesFrequentsForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        preguntesFrequents = update(request, preguntesFrequents);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", preguntesFrequents.getPreguntesFrequentsID());
        status.setComplete();
        return getRedirectWhenModified(request, preguntesFrequentsForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          preguntesFrequents.getPreguntesFrequentsID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, preguntesFrequentsForm, __e);
    }

  }


  /**
   * Eliminar un PreguntesFrequents existent
   */
  @RequestMapping(value = "/{preguntesFrequentsID}/delete")
  public String eliminarPreguntesFrequents(@PathVariable("preguntesFrequentsID") java.lang.Long preguntesFrequentsID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PreguntesFrequents preguntesFrequents = preguntesFrequentsEjb.findByPrimaryKey(preguntesFrequentsID);
      if (preguntesFrequents == null) {
        String __msg =createMessageError(request, "error.notfound", preguntesFrequentsID);
        return getRedirectWhenDelete(request, preguntesFrequentsID, new Exception(__msg));
      } else {
        delete(request, preguntesFrequents);
        createMessageSuccess(request, "success.deleted", preguntesFrequentsID);
        return getRedirectWhenDelete(request, preguntesFrequentsID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", preguntesFrequentsID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, preguntesFrequentsID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PreguntesFrequentsFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPreguntesFrequents(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __preguntesFrequentsID, Throwable e) {
    java.lang.Long preguntesFrequentsID = (java.lang.Long)__preguntesFrequentsID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (preguntesFrequentsID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(preguntesFrequentsID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "preguntesFrequents.preguntesFrequents";
  }

  public String getEntityNameCodePlural() {
    return "preguntesFrequents.preguntesFrequents.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("preguntesFrequents.preguntesFrequentsID");
  }

  @InitBinder("preguntesFrequentsFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("preguntesFrequentsForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "preguntesFrequents.preguntesFrequentsID");
  }

  public PreguntesFrequentsWebValidator getWebValidator() {
    return preguntesFrequentsWebValidator;
  }


  public void setWebValidator(PreguntesFrequentsWebValidator __val) {
    if (__val != null) {
      this.preguntesFrequentsWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PreguntesFrequents
   */
  @RequestMapping(value = "/{preguntesFrequentsID}/cancel")
  public String cancelPreguntesFrequents(@PathVariable("preguntesFrequentsID") java.lang.Long preguntesFrequentsID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, preguntesFrequentsID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, PreguntesFrequents preguntesFrequents,
      PreguntesFrequentsForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxer1ID(), form.isFitxer1IDDelete(),
        form.isNou()? null : preguntesFrequents.getFitxer1());
    ((PreguntesFrequentsJPA)preguntesFrequents).setFitxer1(f);
    if (f != null) { 
      preguntesFrequents.setFitxer1ID(f.getFitxerID());
    } else {
      preguntesFrequents.setFitxer1ID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getFitxer2ID(), form.isFitxer2IDDelete(),
        form.isNou()? null : preguntesFrequents.getFitxer2());
    ((PreguntesFrequentsJPA)preguntesFrequents).setFitxer2(f);
    if (f != null) { 
      preguntesFrequents.setFitxer2ID(f.getFitxerID());
    } else {
      preguntesFrequents.setFitxer2ID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getFitxer3ID(), form.isFitxer3IDDelete(),
        form.isNou()? null : preguntesFrequents.getFitxer3());
    ((PreguntesFrequentsJPA)preguntesFrequents).setFitxer3(f);
    if (f != null) { 
      preguntesFrequents.setFitxer3ID(f.getFitxerID());
    } else {
      preguntesFrequents.setFitxer3ID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(PreguntesFrequents preguntesFrequents) {
    deleteFile(preguntesFrequents.getFitxer1ID());
    deleteFile(preguntesFrequents.getFitxer2ID());
    deleteFile(preguntesFrequents.getFitxer3ID());
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

  public List<StringKeyValue> getReferenceListForEnunciatID(HttpServletRequest request,
       ModelAndView mav, PreguntesFrequentsFilterForm preguntesFrequentsFilterForm,
       List<PreguntesFrequents> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (preguntesFrequentsFilterForm.isHiddenField(ENUNCIATID)
      && !preguntesFrequentsFilterForm.isGroupByField(ENUNCIATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENUNCIATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PreguntesFrequents _item : list) {
        _pkList.add(_item.getEnunciatID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForEnunciatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEnunciatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }

  public List<StringKeyValue> getReferenceListForRespostaID(HttpServletRequest request,
       ModelAndView mav, PreguntesFrequentsFilterForm preguntesFrequentsFilterForm,
       List<PreguntesFrequents> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (preguntesFrequentsFilterForm.isHiddenField(RESPOSTAID)
      && !preguntesFrequentsFilterForm.isGroupByField(RESPOSTAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(RESPOSTAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PreguntesFrequents _item : list) {
        _pkList.add(_item.getRespostaID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForRespostaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForRespostaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, PreguntesFrequentsForm preguntesFrequentsForm, Where where)  throws I18NException {
    if (preguntesFrequentsForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (preguntesFrequentsForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(preguntesFrequentsForm.getPreguntesFrequents().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, PreguntesFrequentsFilterForm preguntesFrequentsFilterForm,
       List<PreguntesFrequents> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (preguntesFrequentsFilterForm.isHiddenField(ENTITATID)
      && !preguntesFrequentsFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PreguntesFrequents _item : list) {
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PreguntesFrequentsForm preguntesFrequentsForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PreguntesFrequentsForm preguntesFrequentsForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PreguntesFrequentsFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PreguntesFrequentsFilterForm filterForm,  List<PreguntesFrequents> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PreguntesFrequentsForm preguntesFrequentsForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PreguntesFrequentsForm preguntesFrequentsForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long preguntesFrequentsID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long preguntesFrequentsID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "preguntesFrequentsFormWebDB";
  }

  public String getTileList() {
    return "preguntesFrequentsListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PreguntesFrequentsWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PreguntesFrequentsJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long preguntesFrequentsID) throws I18NException {
    return (PreguntesFrequentsJPA) preguntesFrequentsEjb.findByPrimaryKey(preguntesFrequentsID);
  }


  public PreguntesFrequentsJPA create(HttpServletRequest request, PreguntesFrequentsJPA preguntesFrequents)
    throws I18NException, I18NValidationException {
    return (PreguntesFrequentsJPA) preguntesFrequentsEjb.create(preguntesFrequents);
  }


  public PreguntesFrequentsJPA update(HttpServletRequest request, PreguntesFrequentsJPA preguntesFrequents)
    throws I18NException, I18NValidationException {
    return (PreguntesFrequentsJPA) preguntesFrequentsEjb.update(preguntesFrequents);
  }


  public void delete(HttpServletRequest request, PreguntesFrequents preguntesFrequents) throws I18NException {
    preguntesFrequentsEjb.delete(preguntesFrequents);
  }

} // Final de Classe

