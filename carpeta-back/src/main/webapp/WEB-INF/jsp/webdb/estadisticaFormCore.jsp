<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstadisticaFields" className="es.caib.carpeta.model.fields.EstadisticaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.TIPUS)}">
        <tr id="estadistica_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.TIPUS])?'estadistica.tipus':__theForm.labels[EstadisticaFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estadistica.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.TIPUS)}" >
          <form:hidden path="estadistica.tipus"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estadistica.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.TIPUS)}" >
          <form:select id="estadistica_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-8" path="estadistica.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.DATAESTADISTICA)}">
        <tr id="estadistica_dataEstadistica_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.DATAESTADISTICA])?'estadistica.dataEstadistica':__theForm.labels[EstadisticaFields.DATAESTADISTICA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.DATAESTADISTICA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.DATAESTADISTICA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estadistica.dataEstadistica" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="estadistica_dataEstadistica" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.DATAESTADISTICA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#estadistica_dataEstadistica" path="estadistica.dataEstadistica" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.DATAESTADISTICA)}" >
                    <div class="input-group-append"  data-target="#estadistica_dataEstadistica"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#estadistica_dataEstadistica').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.COMPTADOR)}">
        <tr id="estadistica_comptador_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.COMPTADOR])?'estadistica.comptador':__theForm.labels[EstadisticaFields.COMPTADOR]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.COMPTADOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.COMPTADOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estadistica.comptador" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.COMPTADOR)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.COMPTADOR)? ' uneditable-input' : ''}"  style=""  path="estadistica.comptador"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.PLUGINID)}">
        <tr id="estadistica_pluginID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.PLUGINID])?'estadistica.pluginID':__theForm.labels[EstadisticaFields.PLUGINID]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.PLUGINID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estadistica.pluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.PLUGINID)}" >
          <form:hidden path="estadistica.pluginID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estadistica.pluginID,__theForm.listOfValuesForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.PLUGINID)}" >
          <form:select id="estadistica_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-8" path="estadistica.pluginID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.ENTITATID)}">
        <tr id="estadistica_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.ENTITATID])?'estadistica.entitatID':__theForm.labels[EstadisticaFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estadistica.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTITATID)}" >
          <form:hidden path="estadistica.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estadistica.entitatID,__theForm.listOfValuesForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTITATID)}" >
          <form:select id="estadistica_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-8" path="estadistica.entitatID">
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
        
