<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.carpeta.model.fields.UsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.USUARIID)}">
        <tr id="usuariEntitat_usuariID_rowid">
          <td id="usuariEntitat_usuariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.USUARIID])?'usuariEntitat.usuariID':__theForm.labels[UsuariEntitatFields.USUARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_usuariID_columnvalueid">
          <form:errors path="usuariEntitat.usuariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIID)}" >
          <form:hidden path="usuariEntitat.usuariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.usuariID,__theForm.listOfUsuariForUsuariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitat_usuariID"  onchange="if(typeof onChangeUsuariID == 'function') {  onChangeUsuariID(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitat.usuariID">
            <c:forEach items="${__theForm.listOfUsuariForUsuariID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ENTITATID)}">
        <tr id="usuariEntitat_entitatID_rowid">
          <td id="usuariEntitat_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ENTITATID])?'usuariEntitat.entitatID':__theForm.labels[UsuariEntitatFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_entitatID_columnvalueid">
          <form:errors path="usuariEntitat.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <form:hidden path="usuariEntitat.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitat_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitat.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ACTIU)}">
        <tr id="usuariEntitat_actiu_rowid">
          <td id="usuariEntitat_actiu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ACTIU])?'usuariEntitat.actiu':__theForm.labels[UsuariEntitatFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.ACTIU]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_actiu_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)}" >
              <form:errors path="usuariEntitat.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)? 'false' : 'true'}" path="usuariEntitat.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariEntitat.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
