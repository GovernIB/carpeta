<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AccesFields" className="es.caib.carpeta.model.fields.AccesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.TIPUS)}">
        <tr id="acces_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.TIPUS])?'acces.tipus':__theForm.labels[AccesFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AccesFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="acces.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AccesFields.TIPUS)}" >
          <form:hidden path="acces.tipus"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.acces.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="acces_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-8" path="acces.tipus">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.NOM])?'acces.nom':__theForm.labels[AccesFields.NOM]}" />
              <c:if test="${not empty __theForm.help[AccesFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.NOM)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="acces.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.LLINATGES)}">
        <tr id="acces_llinatges_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.LLINATGES])?'acces.llinatges':__theForm.labels[AccesFields.LLINATGES]}" />
              <c:if test="${not empty __theForm.help[AccesFields.LLINATGES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.LLINATGES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.llinatges" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.LLINATGES)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.LLINATGES)? ' uneditable-input' : ''}"  style="" maxlength="255" path="acces.llinatges"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.NIF)}">
        <tr id="acces_nif_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.NIF])?'acces.nif':__theForm.labels[AccesFields.NIF]}" />
              <c:if test="${not empty __theForm.help[AccesFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.NIF]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.NIF)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="50" path="acces.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.IP)}">
        <tr id="acces_ip_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.IP])?'acces.ip':__theForm.labels[AccesFields.IP]}" />
              <c:if test="${not empty __theForm.help[AccesFields.IP]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.IP]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.ip" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.IP)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.IP)? ' uneditable-input' : ''}"  style="" maxlength="100" path="acces.ip"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.PROVEIDORIDENTITAT)}">
        <tr id="acces_proveidorIdentitat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.PROVEIDORIDENTITAT])?'acces.proveidorIdentitat':__theForm.labels[AccesFields.PROVEIDORIDENTITAT]}" />
              <c:if test="${not empty __theForm.help[AccesFields.PROVEIDORIDENTITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.PROVEIDORIDENTITAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.proveidorIdentitat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.PROVEIDORIDENTITAT)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.PROVEIDORIDENTITAT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="acces.proveidorIdentitat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.METODEAUTENTICACIO)}">
        <tr id="acces_metodeAutenticacio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.METODEAUTENTICACIO])?'acces.metodeAutenticacio':__theForm.labels[AccesFields.METODEAUTENTICACIO]}" />
              <c:if test="${not empty __theForm.help[AccesFields.METODEAUTENTICACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.METODEAUTENTICACIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="acces.metodeAutenticacio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;max-width:90%;" cssClass="form-control " readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.METODEAUTENTICACIO)? 'true' : 'false'}" path="acces.metodeAutenticacio"  />
      <div class="dropdown" style="vertical-align:top;display:inline;">
        <button id="dropdownMenuButton_metodeAutenticacio" class="btn btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin-left:0px;"><span class="caret"></span></button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton_metodeAutenticacio">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('acces.metodeAutenticacio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('acces.metodeAutenticacio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('acces.metodeAutenticacio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.QAA)}">
        <tr id="acces_qaa_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.QAA])?'acces.qaa':__theForm.labels[AccesFields.QAA]}" />
              <c:if test="${not empty __theForm.help[AccesFields.QAA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.QAA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.qaa" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.QAA)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.QAA)? ' uneditable-input' : ''}"  style=""  path="acces.qaa"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.DATAACCES)}">
        <tr id="acces_dataAcces_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.DATAACCES])?'acces.dataAcces':__theForm.labels[AccesFields.DATAACCES]}" />
              <c:if test="${not empty __theForm.help[AccesFields.DATAACCES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.DATAACCES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="acces.dataAcces" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
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
          </script>        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.PLUGINID)}">
        <tr id="acces_pluginID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.PLUGINID])?'acces.pluginID':__theForm.labels[AccesFields.PLUGINID]}" />
              <c:if test="${not empty __theForm.help[AccesFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.PLUGINID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="acces.pluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AccesFields.PLUGINID)}" >
          <form:hidden path="acces.pluginID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.acces.pluginID,__theForm.listOfValuesForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.PLUGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="acces_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-8" path="acces.pluginID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.ENTITATID])?'acces.entitatID':__theForm.labels[AccesFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AccesFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="acces.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AccesFields.ENTITATID)}" >
          <form:hidden path="acces.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.acces.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="acces_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-8" path="acces.entitatID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.IDIOMA])?'acces.idioma':__theForm.labels[AccesFields.IDIOMA]}" />
              <c:if test="${not empty __theForm.help[AccesFields.IDIOMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.IDIOMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.idioma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.IDIOMA)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.IDIOMA)? ' uneditable-input' : ''}"  style="" maxlength="50" path="acces.idioma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.RESULTAT)}">
        <tr id="acces_resultat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.RESULTAT])?'acces.resultat':__theForm.labels[AccesFields.RESULTAT]}" />
              <c:if test="${not empty __theForm.help[AccesFields.RESULTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.RESULTAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.RESULTAT)}" >
              <form:errors path="acces.resultat" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="form-control" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,AccesFields.RESULTAT)? 'false' : 'true'}" path="acces.resultat"  style="width:1%"/>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AccesFields.RESULTAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.acces.resultat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
