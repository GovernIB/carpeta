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
      <div id="dropdownMenuButton_value" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_value" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.value'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.value'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('propietatGlobal.value'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_value').on('click', function(){
					var valor = ($('#dropdownMenuContainer_value').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_value').css('display', valor);
                 return false;
				});
      </script>           </td>
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
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="propietatGlobal_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-8" path="propietatGlobal.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.propietatGlobal.entitatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.propietatGlobal.entitatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
