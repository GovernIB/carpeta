<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginEntitatFields" className="es.caib.carpeta.model.fields.PluginEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginEntitatFields.PLUGINID)}">
        <tr id="pluginEntitat_pluginID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginEntitatFields.PLUGINID])?'pluginEntitat.pluginID':__theForm.labels[PluginEntitatFields.PLUGINID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginEntitatFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginEntitatFields.PLUGINID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="pluginEntitat.pluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.PLUGINID)}" >
          <form:hidden path="pluginEntitat.pluginID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginEntitat.pluginID,__theForm.listOfPluginForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.PLUGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pluginEntitat_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-9-optional" path="pluginEntitat.pluginID">
            <c:forEach items="${__theForm.listOfPluginForPluginID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginEntitatFields.ENTITATID)}">
        <tr id="pluginEntitat_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginEntitatFields.ENTITATID])?'pluginEntitat.entitatID':__theForm.labels[PluginEntitatFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginEntitatFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginEntitatFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="pluginEntitat.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ENTITATID)}" >
          <form:hidden path="pluginEntitat.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginEntitat.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pluginEntitat_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="pluginEntitat.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginEntitatFields.ACTIU)}">
        <tr id="pluginEntitat_actiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginEntitatFields.ACTIU])?'pluginEntitat.actiu':__theForm.labels[PluginEntitatFields.ACTIU]}" />
              <c:if test="${not empty __theForm.help[PluginEntitatFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginEntitatFields.ACTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ACTIU)}" >
              <form:errors path="pluginEntitat.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ACTIU)? 'false' : 'true'}" path="pluginEntitat.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.pluginEntitat.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginEntitatFields.SECCIOID)}">
        <tr id="pluginEntitat_seccioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginEntitatFields.SECCIOID])?'pluginEntitat.seccioID':__theForm.labels[PluginEntitatFields.SECCIOID]}" />
              <c:if test="${not empty __theForm.help[PluginEntitatFields.SECCIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginEntitatFields.SECCIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="pluginEntitat.seccioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.SECCIOID)}" >
          <form:hidden path="pluginEntitat.seccioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginEntitat.seccioID,__theForm.listOfSeccioForSeccioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.SECCIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pluginEntitat_seccioID"  onchange="if(typeof onChangeSeccioID == 'function') {  onChangeSeccioID(this); };"  cssClass="form-control col-md-9-optional" path="pluginEntitat.seccioID">
            <c:forEach items="${__theForm.listOfSeccioForSeccioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pluginEntitat.seccioID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pluginEntitat.seccioID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginEntitatFields.ORDRE)}">
        <tr id="pluginEntitat_ordre_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginEntitatFields.ORDRE])?'pluginEntitat.ordre':__theForm.labels[PluginEntitatFields.ORDRE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginEntitatFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginEntitatFields.ORDRE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="pluginEntitat.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ORDRE)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="pluginEntitat.ordre"   />

           </td>
        </tr>
        </c:if>
        
