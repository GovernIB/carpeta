<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PropietatGlobalFields" className="es.caib.carpeta.model.fields.PropietatGlobalFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.CODI)}">
        <tr id="propietatGlobal_codi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.CODI])?'propietatGlobal.codi':__theForm.labels[PropietatGlobalFields.CODI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PropietatGlobalFields.CODI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="propietatGlobal.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.CODI)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="250" path="propietatGlobal.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.VALUE)}">
        <tr id="propietatGlobal_value_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.VALUE])?'propietatGlobal.value':__theForm.labels[PropietatGlobalFields.VALUE]}" />
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.VALUE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PropietatGlobalFields.VALUE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="propietatGlobal.value" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;max-width:90%;" cssClass="form-control " readonly="${ gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.VALUE)? 'true' : 'false'}" path="propietatGlobal.value"  />
      <div class="dropdown" style="vertical-align:top;display:inline;">
        <button id="dropdownMenuButton_value" class="btn btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin-left:0px;"><span class="caret"></span></button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton_value">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.value'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.value'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.value'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.DESCRIPCIO)}">
        <tr id="propietatGlobal_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.DESCRIPCIO])?'propietatGlobal.descripcio':__theForm.labels[PropietatGlobalFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PropietatGlobalFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="propietatGlobal.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea cssClass="col-md-8 ${gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.DESCRIPCIO)? 'mceEditorReadOnly':'mceEditor'}"  path="propietatGlobal.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PropietatGlobalFields.ENTITATID)}">
        <tr id="propietatGlobal_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PropietatGlobalFields.ENTITATID])?'propietatGlobal.entitatID':__theForm.labels[PropietatGlobalFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[PropietatGlobalFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PropietatGlobalFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="propietatGlobal.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.ENTITATID)}" >
          <form:hidden path="propietatGlobal.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.propietatGlobal.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PropietatGlobalFields.ENTITATID)}" >
          <form:select id="propietatGlobal_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-8" path="propietatGlobal.entitatID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
