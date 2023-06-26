<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AccesFields" className="es.caib.carpeta.model.fields.AccesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.TIPUS)}">
        <tr id="acces_tipus_rowid">
          <td id="acces_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.TIPUS])?'acces.tipus':__theForm.labels[AccesFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="acces_tipus_columnvalueid">
          <form:errors path="acces.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AccesFields.TIPUS)}" >
          <form:hidden path="acces.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.acces.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="acces_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="acces.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.NOM)}">
        <tr id="acces_nom_rowid">
          <td id="acces_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.NOM])?'acces.nom':__theForm.labels[AccesFields.NOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="acces_nom_columnvalueid">
            <form:errors path="acces.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,AccesFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="acces.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.LLINATGES)}">
        <tr id="acces_llinatges_rowid">
          <td id="acces_llinatges_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.LLINATGES])?'acces.llinatges':__theForm.labels[AccesFields.LLINATGES]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.LLINATGES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.LLINATGES]}" ></i>
              </c:if>
            </td>
          <td id="acces_llinatges_columnvalueid">
            <form:errors path="acces.llinatges" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.LLINATGES)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,AccesFields.LLINATGES)? ' uneditable-input' : ''}"  style="" maxlength="255" path="acces.llinatges"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.NIF)}">
        <tr id="acces_nif_rowid">
          <td id="acces_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.NIF])?'acces.nif':__theForm.labels[AccesFields.NIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="acces_nif_columnvalueid">
            <form:errors path="acces.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.NIF)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,AccesFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="50" path="acces.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.IP)}">
        <tr id="acces_ip_rowid">
          <td id="acces_ip_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.IP])?'acces.ip':__theForm.labels[AccesFields.IP]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.IP]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.IP]}" ></i>
              </c:if>
            </td>
          <td id="acces_ip_columnvalueid">
            <form:errors path="acces.ip" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.IP)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,AccesFields.IP)? ' uneditable-input' : ''}"  style="" maxlength="100" path="acces.ip"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.PROVEIDORIDENTITAT)}">
        <tr id="acces_proveidorIdentitat_rowid">
          <td id="acces_proveidorIdentitat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.PROVEIDORIDENTITAT])?'acces.proveidorIdentitat':__theForm.labels[AccesFields.PROVEIDORIDENTITAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.PROVEIDORIDENTITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.PROVEIDORIDENTITAT]}" ></i>
              </c:if>
            </td>
          <td id="acces_proveidorIdentitat_columnvalueid">
            <form:errors path="acces.proveidorIdentitat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.PROVEIDORIDENTITAT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,AccesFields.PROVEIDORIDENTITAT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="acces.proveidorIdentitat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.METODEAUTENTICACIO)}">
        <tr id="acces_metodeAutenticacio_rowid">
          <td id="acces_metodeAutenticacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.METODEAUTENTICACIO])?'acces.metodeAutenticacio':__theForm.labels[AccesFields.METODEAUTENTICACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.METODEAUTENTICACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.METODEAUTENTICACIO]}" ></i>
              </c:if>
            </td>
          <td id="acces_metodeAutenticacio_columnvalueid">
              <form:errors path="acces.metodeAutenticacio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.METODEAUTENTICACIO)? 'true' : 'false'}" path="acces.metodeAutenticacio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_metodeAutenticacio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_metodeAutenticacio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('acces.metodeAutenticacio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('acces.metodeAutenticacio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('acces.metodeAutenticacio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_metodeAutenticacio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_metodeAutenticacio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_metodeAutenticacio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.QAA)}">
        <tr id="acces_qaa_rowid">
          <td id="acces_qaa_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.QAA])?'acces.qaa':__theForm.labels[AccesFields.QAA]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.QAA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.QAA]}" ></i>
              </c:if>
            </td>
          <td id="acces_qaa_columnvalueid">
            <form:errors path="acces.qaa" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.QAA)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,AccesFields.QAA)? ' uneditable-input' : ''}"  style=""  path="acces.qaa"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.DATAACCES)}">
        <tr id="acces_dataAcces_rowid">
          <td id="acces_dataAcces_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.DATAACCES])?'acces.dataAcces':__theForm.labels[AccesFields.DATAACCES]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.DATAACCES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.DATAACCES]}" ></i>
              </c:if>
            </td>
          <td id="acces_dataAcces_columnvalueid">
    <form:errors path="acces.dataAcces" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="acces_dataAcces" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.DATAACCES)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#acces_dataAcces" path="acces.dataAcces" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.DATAACCES)}" >
                    <div class="input-group-append"  data-target="#acces_dataAcces"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#acces_dataAcces').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.PLUGINID)}">
        <tr id="acces_pluginID_rowid">
          <td id="acces_pluginID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.PLUGINID])?'acces.pluginID':__theForm.labels[AccesFields.PLUGINID]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.PLUGINID]}" ></i>
              </c:if>
            </td>
          <td id="acces_pluginID_columnvalueid">
          <form:errors path="acces.pluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AccesFields.PLUGINID)}" >
          <form:hidden path="acces.pluginID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.acces.pluginID,__theForm.listOfValuesForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.PLUGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="acces_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-9-optional" path="acces.pluginID">
            <c:forEach items="${__theForm.listOfValuesForPluginID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.acces.pluginID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.acces.pluginID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.ENTITATID)}">
        <tr id="acces_entitatID_rowid">
          <td id="acces_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.ENTITATID])?'acces.entitatID':__theForm.labels[AccesFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="acces_entitatID_columnvalueid">
          <form:errors path="acces.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AccesFields.ENTITATID)}" >
          <form:hidden path="acces.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.acces.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="acces_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="acces.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.IDIOMA)}">
        <tr id="acces_idioma_rowid">
          <td id="acces_idioma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.IDIOMA])?'acces.idioma':__theForm.labels[AccesFields.IDIOMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.IDIOMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.IDIOMA]}" ></i>
              </c:if>
            </td>
          <td id="acces_idioma_columnvalueid">
            <form:errors path="acces.idioma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.IDIOMA)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,AccesFields.IDIOMA)? ' uneditable-input' : ''}"  style="" maxlength="50" path="acces.idioma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.RESULTAT)}">
        <tr id="acces_resultat_rowid">
          <td id="acces_resultat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.RESULTAT])?'acces.resultat':__theForm.labels[AccesFields.RESULTAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.RESULTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.RESULTAT]}" ></i>
              </c:if>
            </td>
          <td id="acces_resultat_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.RESULTAT)}" >
              <form:errors path="acces.resultat" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,AccesFields.RESULTAT)? 'false' : 'true'}" path="acces.resultat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AccesFields.RESULTAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.acces.resultat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.IDSESSIO)}">
        <tr id="acces_idsessio_rowid">
          <td id="acces_idsessio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.IDSESSIO])?'acces.idsessio':__theForm.labels[AccesFields.IDSESSIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[AccesFields.IDSESSIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.IDSESSIO]}" ></i>
              </c:if>
            </td>
          <td id="acces_idsessio_columnvalueid">
            <form:errors path="acces.idsessio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.IDSESSIO)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,AccesFields.IDSESSIO)? ' uneditable-input' : ''}"  style="" maxlength="255" path="acces.idsessio"   />

           </td>
        </tr>
        </c:if>
        
