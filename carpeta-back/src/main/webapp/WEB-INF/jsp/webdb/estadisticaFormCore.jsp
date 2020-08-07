<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstadisticaFields" className="es.caib.carpeta.model.fields.EstadisticaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.ENTITATID)}">
        <tr id="estadistica_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.ENTITATID])?'estadistica.entitatID':__theForm.labels[EstadisticaFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estadistica.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTITATID)}" >
          <form:hidden path="estadistica.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estadistica.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTITATID)}" >
          <form:select id="estadistica_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-4" path="estadistica.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.ACCESID)}">
        <tr id="estadistica_accesID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.ACCESID])?'estadistica.accesID':__theForm.labels[EstadisticaFields.ACCESID]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.ACCESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.ACCESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estadistica.accesID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ACCESID)}" >
          <form:hidden path="estadistica.accesID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estadistica.accesID,__theForm.listOfAccesForAccesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ACCESID)}" >
          <form:select id="estadistica_accesID"  onchange="if(typeof onChangeAccesID == 'function') {  onChangeAccesID(this); };"  cssClass="form-control col-md-4" path="estadistica.accesID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfAccesForAccesID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.ACCIO)}">
        <tr id="estadistica_accio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.ACCIO])?'estadistica.accio':__theForm.labels[EstadisticaFields.ACCIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.ACCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.ACCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estadistica.accio" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ACCIO)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ACCIO)? ' uneditable-input' : ''}"   path="estadistica.accio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.ELEMENT)}">
        <tr id="estadistica_element_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.ELEMENT])?'estadistica.element':__theForm.labels[EstadisticaFields.ELEMENT]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.ELEMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.ELEMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estadistica.element" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;" cssClass="form-control col-md-8" readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ELEMENT)? 'true' : 'false'}" path="estadistica.element"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estadistica.element'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estadistica.element'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estadistica.element'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
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
              <form:errors path="estadistica.dataEstadistica" cssClass="errorField alert alert-error" />
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
        
