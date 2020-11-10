<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AuditoriaFields" className="es.caib.carpeta.model.fields.AuditoriaFields"/>
  
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
              <form:errors path="auditoria.dataAudit" cssClass="errorField alert alert-error" />
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
          <form:errors path="auditoria.tipus" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AuditoriaFields.TIPUS)}" >
          <form:hidden path="auditoria.tipus"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.auditoria.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AuditoriaFields.TIPUS)}" >
          <form:select id="auditoria_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-4" path="auditoria.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
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
          <form:errors path="auditoria.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AuditoriaFields.ENTITATID)}" >
          <form:hidden path="auditoria.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.auditoria.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AuditoriaFields.ENTITATID)}" >
          <form:select id="auditoria_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-4" path="auditoria.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AuditoriaFields.USUARIID)}">
        <tr id="auditoria_usuariID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AuditoriaFields.USUARIID])?'auditoria.usuariID':__theForm.labels[AuditoriaFields.USUARIID]}" />
              <c:if test="${not empty __theForm.help[AuditoriaFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AuditoriaFields.USUARIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="auditoria.usuariID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AuditoriaFields.USUARIID)}" >
          <form:hidden path="auditoria.usuariID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.auditoria.usuariID,__theForm.listOfUsuariForUsuariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AuditoriaFields.USUARIID)}" >
          <form:select id="auditoria_usuariID"  onchange="if(typeof onChangeUsuariID == 'function') {  onChangeUsuariID(this); };"  cssClass="form-control col-md-4" path="auditoria.usuariID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfUsuariForUsuariID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AuditoriaFields.TICKETLOGINIB)}">
        <tr id="auditoria_ticketLoginIB_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AuditoriaFields.TICKETLOGINIB])?'auditoria.ticketLoginIB':__theForm.labels[AuditoriaFields.TICKETLOGINIB]}" />
              <c:if test="${not empty __theForm.help[AuditoriaFields.TICKETLOGINIB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AuditoriaFields.TICKETLOGINIB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="auditoria.ticketLoginIB" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AuditoriaFields.TICKETLOGINIB)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,AuditoriaFields.TICKETLOGINIB)? ' uneditable-input' : ''}"  maxlength="256" path="auditoria.ticketLoginIB"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AuditoriaFields.PLUGINID)}">
        <tr id="auditoria_pluginID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AuditoriaFields.PLUGINID])?'auditoria.pluginID':__theForm.labels[AuditoriaFields.PLUGINID]}" />
              <c:if test="${not empty __theForm.help[AuditoriaFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AuditoriaFields.PLUGINID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="auditoria.pluginID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AuditoriaFields.PLUGINID)}" >
          <form:hidden path="auditoria.pluginID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.auditoria.pluginID,__theForm.listOfValuesForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AuditoriaFields.PLUGINID)}" >
          <form:select id="auditoria_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-4" path="auditoria.pluginID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfValuesForPluginID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
