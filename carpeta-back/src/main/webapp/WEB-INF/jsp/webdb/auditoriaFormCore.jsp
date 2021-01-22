<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AuditoriaFields" className="es.caib.carpeta.model.fields.AuditoriaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AuditoriaFields.TIPUS)}">
        <tr id="auditoria_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AuditoriaFields.TIPUS])?'auditoria.tipus':__theForm.labels[AuditoriaFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AuditoriaFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AuditoriaFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="auditoria.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AuditoriaFields.TIPUS)}" >
          <form:hidden path="auditoria.tipus"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.auditoria.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AuditoriaFields.TIPUS)}" >
          <form:select id="auditoria_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-8" path="auditoria.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AuditoriaFields.OBJECTE)}">
        <tr id="auditoria_objecte_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AuditoriaFields.OBJECTE])?'auditoria.objecte':__theForm.labels[AuditoriaFields.OBJECTE]}" />
              <c:if test="${not empty __theForm.help[AuditoriaFields.OBJECTE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AuditoriaFields.OBJECTE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="auditoria.objecte" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;max-width:90%;" cssClass="form-control " readonly="${ gen:contains(__theForm.readOnlyFields ,AuditoriaFields.OBJECTE)? 'true' : 'false'}" path="auditoria.objecte"  />
      <div class="dropdown" style="vertical-align:top;display:inline;">
        <button id="dropdownMenuButton_objecte" class="btn btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin-left:0px;"><span class="caret"></span></button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton_objecte">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('auditoria.objecte'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('auditoria.objecte'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('auditoria.objecte'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AuditoriaFields.DATAAUDIT)}">
        <tr id="auditoria_dataAudit_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AuditoriaFields.DATAAUDIT])?'auditoria.dataAudit':__theForm.labels[AuditoriaFields.DATAAUDIT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AuditoriaFields.DATAAUDIT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AuditoriaFields.DATAAUDIT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="auditoria.dataAudit" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="auditoria_dataAudit" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AuditoriaFields.DATAAUDIT)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#auditoria_dataAudit" path="auditoria.dataAudit" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,AuditoriaFields.DATAAUDIT)}" >
                    <div class="input-group-append"  data-target="#auditoria_dataAudit"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#auditoria_dataAudit').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AuditoriaFields.USERNAME)}">
        <tr id="auditoria_username_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AuditoriaFields.USERNAME])?'auditoria.username':__theForm.labels[AuditoriaFields.USERNAME]}" />
              <c:if test="${not empty __theForm.help[AuditoriaFields.USERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AuditoriaFields.USERNAME]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="auditoria.username" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AuditoriaFields.USERNAME)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,AuditoriaFields.USERNAME)? ' uneditable-input' : ''}"  style="" maxlength="255" path="auditoria.username"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AuditoriaFields.ENTITATID)}">
        <tr id="auditoria_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AuditoriaFields.ENTITATID])?'auditoria.entitatID':__theForm.labels[AuditoriaFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[AuditoriaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AuditoriaFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="auditoria.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AuditoriaFields.ENTITATID)}" >
          <form:hidden path="auditoria.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.auditoria.entitatID,__theForm.listOfValuesForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AuditoriaFields.ENTITATID)}" >
          <form:select id="auditoria_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-8" path="auditoria.entitatID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfValuesForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
