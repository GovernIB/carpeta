<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstadisticaFields" className="es.caib.carpeta.model.fields.EstadisticaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.TIPUS)}">
        <tr id="estadistica_tipus_rowid">
          <td id="estadistica_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.TIPUS])?'estadistica.tipus':__theForm.labels[EstadisticaFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstadisticaFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="estadistica_tipus_columnvalueid">
          <form:errors path="estadistica.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.TIPUS)}" >
          <form:hidden path="estadistica.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estadistica.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estadistica_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="estadistica.tipus">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.DATAESTADISTICA)}">
        <tr id="estadistica_dataEstadistica_rowid">
          <td id="estadistica_dataEstadistica_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.DATAESTADISTICA])?'estadistica.dataEstadistica':__theForm.labels[EstadisticaFields.DATAESTADISTICA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstadisticaFields.DATAESTADISTICA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.DATAESTADISTICA]}" ></i>
              </c:if>
            </td>
          <td id="estadistica_dataEstadistica_columnvalueid">
    <form:errors path="estadistica.dataEstadistica" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
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
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.COMPTADOR)}">
        <tr id="estadistica_comptador_rowid">
          <td id="estadistica_comptador_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.COMPTADOR])?'estadistica.comptador':__theForm.labels[EstadisticaFields.COMPTADOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstadisticaFields.COMPTADOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.COMPTADOR]}" ></i>
              </c:if>
            </td>
          <td id="estadistica_comptador_columnvalueid">
            <form:errors path="estadistica.comptador" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.COMPTADOR)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.COMPTADOR)? ' uneditable-input' : ''}"  style=""  path="estadistica.comptador"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.PLUGINID)}">
        <tr id="estadistica_pluginID_rowid">
          <td id="estadistica_pluginID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.PLUGINID])?'estadistica.pluginID':__theForm.labels[EstadisticaFields.PLUGINID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstadisticaFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.PLUGINID]}" ></i>
              </c:if>
            </td>
          <td id="estadistica_pluginID_columnvalueid">
          <form:errors path="estadistica.pluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.PLUGINID)}" >
          <form:hidden path="estadistica.pluginID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estadistica.pluginID,__theForm.listOfValuesForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.PLUGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estadistica_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-9-optional" path="estadistica.pluginID">
            <c:forEach items="${__theForm.listOfValuesForPluginID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.estadistica.pluginID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.estadistica.pluginID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.ENTITATID)}">
        <tr id="estadistica_entitatID_rowid">
          <td id="estadistica_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.ENTITATID])?'estadistica.entitatID':__theForm.labels[EstadisticaFields.ENTITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstadisticaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="estadistica_entitatID_columnvalueid">
          <form:errors path="estadistica.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTITATID)}" >
          <form:hidden path="estadistica.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estadistica.entitatID,__theForm.listOfValuesForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estadistica_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="estadistica.entitatID">
            <c:forEach items="${__theForm.listOfValuesForEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.estadistica.entitatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.estadistica.entitatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
