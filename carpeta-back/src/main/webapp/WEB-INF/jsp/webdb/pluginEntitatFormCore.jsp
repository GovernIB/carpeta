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
          <form:errors path="pluginEntitat.pluginID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.PLUGINID)}" >
          <form:hidden path="pluginEntitat.pluginID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.pluginEntitat.pluginID,__theForm.listOfPluginForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.PLUGINID)}" >
          <form:select id="pluginEntitat_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-4" path="pluginEntitat.pluginID">
            <c:forEach items="${__theForm.listOfPluginForPluginID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
          <form:errors path="pluginEntitat.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ENTITATID)}" >
          <form:hidden path="pluginEntitat.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.pluginEntitat.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ENTITATID)}" >
          <form:select id="pluginEntitat_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-4" path="pluginEntitat.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
              <form:errors path="pluginEntitat.actiu" cssClass="errorField alert alert-error" />
              <form:checkbox cssClass="form-control" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ACTIU)? 'false' : 'true'}" path="pluginEntitat.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginEntitatFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.pluginEntitat.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
