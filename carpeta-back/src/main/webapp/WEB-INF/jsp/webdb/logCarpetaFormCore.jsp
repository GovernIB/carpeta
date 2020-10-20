<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LogCarpetaFields" className="es.caib.carpeta.model.fields.LogCarpetaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.DESCRIPCIO)}">
        <tr id="logCarpeta_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.DESCRIPCIO])?'logCarpeta.descripcio':__theForm.labels[LogCarpetaFields.DESCRIPCIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[LogCarpetaFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="logCarpeta.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;" cssClass="form-control col-md-8" readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.DESCRIPCIO)? 'true' : 'false'}" path="logCarpeta.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.ENTITATID)}">
        <tr id="logCarpeta_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.ENTITATID])?'logCarpeta.entitatID':__theForm.labels[LogCarpetaFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[LogCarpetaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="logCarpeta.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.ENTITATID)}" >
          <form:hidden path="logCarpeta.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.logCarpeta.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.ENTITATID)}" >
          <form:select id="logCarpeta_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-4" path="logCarpeta.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.PLUGINID)}">
        <tr id="logCarpeta_pluginID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.PLUGINID])?'logCarpeta.pluginID':__theForm.labels[LogCarpetaFields.PLUGINID]}" />
              <c:if test="${not empty __theForm.help[LogCarpetaFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.PLUGINID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="logCarpeta.pluginID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.PLUGINID)}" >
          <form:hidden path="logCarpeta.pluginID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.logCarpeta.pluginID,__theForm.listOfPluginForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.PLUGINID)}" >
          <form:select id="logCarpeta_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-4" path="logCarpeta.pluginID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfPluginForPluginID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.TIPUS)}">
        <tr id="logCarpeta_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.TIPUS])?'logCarpeta.tipus':__theForm.labels[LogCarpetaFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[LogCarpetaFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="logCarpeta.tipus" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.TIPUS)}" >
          <form:hidden path="logCarpeta.tipus"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.logCarpeta.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.TIPUS)}" >
          <form:select id="logCarpeta_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-4" path="logCarpeta.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.ESTAT)}">
        <tr id="logCarpeta_estat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.ESTAT])?'logCarpeta.estat':__theForm.labels[LogCarpetaFields.ESTAT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[LogCarpetaFields.ESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.ESTAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="logCarpeta.estat" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.ESTAT)}" >
          <form:hidden path="logCarpeta.estat"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.logCarpeta.estat,__theForm.listOfValuesForEstat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.ESTAT)}" >
          <form:select id="logCarpeta_estat"  onchange="if(typeof onChangeEstat == 'function') {  onChangeEstat(this); };"  cssClass="form-control col-md-4" path="logCarpeta.estat">
            <c:forEach items="${__theForm.listOfValuesForEstat}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.TEMPS)}">
        <tr id="logCarpeta_temps_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.TEMPS])?'logCarpeta.temps':__theForm.labels[LogCarpetaFields.TEMPS]}" />
              <c:if test="${not empty __theForm.help[LogCarpetaFields.TEMPS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.TEMPS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="logCarpeta.temps" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.TEMPS)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.TEMPS)? ' uneditable-input' : ''}"   path="logCarpeta.temps"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.DATAINICI)}">
        <tr id="logCarpeta_dataInici_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.DATAINICI])?'logCarpeta.dataInici':__theForm.labels[LogCarpetaFields.DATAINICI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[LogCarpetaFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.DATAINICI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="logCarpeta.dataInici" cssClass="errorField alert alert-error" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="logCarpeta_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#logCarpeta_dataInici" path="logCarpeta.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#logCarpeta_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#logCarpeta_dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.PETICIO)}">
        <tr id="logCarpeta_peticio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.PETICIO])?'logCarpeta.peticio':__theForm.labels[LogCarpetaFields.PETICIO]}" />
              <c:if test="${not empty __theForm.help[LogCarpetaFields.PETICIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.PETICIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="logCarpeta.peticio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;" cssClass="form-control col-md-8" readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.PETICIO)? 'true' : 'false'}" path="logCarpeta.peticio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.peticio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.peticio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.peticio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.ERROR)}">
        <tr id="logCarpeta_error_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.ERROR])?'logCarpeta.error':__theForm.labels[LogCarpetaFields.ERROR]}" />
              <c:if test="${not empty __theForm.help[LogCarpetaFields.ERROR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.ERROR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="logCarpeta.error" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;" cssClass="form-control col-md-8" readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.ERROR)? 'true' : 'false'}" path="logCarpeta.error"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.error'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.error'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.error'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.EXCEPCIO)}">
        <tr id="logCarpeta_excepcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.EXCEPCIO])?'logCarpeta.excepcio':__theForm.labels[LogCarpetaFields.EXCEPCIO]}" />
              <c:if test="${not empty __theForm.help[LogCarpetaFields.EXCEPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.EXCEPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="logCarpeta.excepcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;" cssClass="form-control col-md-8" readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.EXCEPCIO)? 'true' : 'false'}" path="logCarpeta.excepcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.excepcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.excepcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.excepcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
