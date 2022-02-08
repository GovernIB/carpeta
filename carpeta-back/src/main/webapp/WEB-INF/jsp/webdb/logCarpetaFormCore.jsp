<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LogCarpetaFields" className="es.caib.carpeta.model.fields.LogCarpetaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.DESCRIPCIO)}">
        <tr id="logCarpeta_descripcio_rowid">
          <td id="logCarpeta_descripcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.DESCRIPCIO])?'logCarpeta.descripcio':__theForm.labels[LogCarpetaFields.DESCRIPCIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_descripcio_columnvalueid">
              <form:errors path="logCarpeta.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.DESCRIPCIO)? 'true' : 'false'}" path="logCarpeta.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.TIPUS)}">
        <tr id="logCarpeta_tipus_rowid">
          <td id="logCarpeta_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.TIPUS])?'logCarpeta.tipus':__theForm.labels[LogCarpetaFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_tipus_columnvalueid">
          <form:errors path="logCarpeta.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.TIPUS)}" >
          <form:hidden path="logCarpeta.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.logCarpeta.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="logCarpeta_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="logCarpeta.tipus">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.ESTAT)}">
        <tr id="logCarpeta_estat_rowid">
          <td id="logCarpeta_estat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.ESTAT])?'logCarpeta.estat':__theForm.labels[LogCarpetaFields.ESTAT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.ESTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.ESTAT]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_estat_columnvalueid">
          <form:errors path="logCarpeta.estat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.ESTAT)}" >
          <form:hidden path="logCarpeta.estat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.logCarpeta.estat,__theForm.listOfValuesForEstat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.ESTAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="logCarpeta_estat"  onchange="if(typeof onChangeEstat == 'function') {  onChangeEstat(this); };"  cssClass="form-control col-md-9-optional" path="logCarpeta.estat">
            <c:forEach items="${__theForm.listOfValuesForEstat}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.PLUGINID)}">
        <tr id="logCarpeta_pluginID_rowid">
          <td id="logCarpeta_pluginID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.PLUGINID])?'logCarpeta.pluginID':__theForm.labels[LogCarpetaFields.PLUGINID]}" />
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.PLUGINID]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_pluginID_columnvalueid">
          <form:errors path="logCarpeta.pluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.PLUGINID)}" >
          <form:hidden path="logCarpeta.pluginID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.logCarpeta.pluginID,__theForm.listOfValuesForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.PLUGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="logCarpeta_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-9-optional" path="logCarpeta.pluginID">
            <c:forEach items="${__theForm.listOfValuesForPluginID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.logCarpeta.pluginID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.logCarpeta.pluginID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.ENTITATCODI)}">
        <tr id="logCarpeta_entitatCodi_rowid">
          <td id="logCarpeta_entitatCodi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.ENTITATCODI])?'logCarpeta.entitatCodi':__theForm.labels[LogCarpetaFields.ENTITATCODI]}" />
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.ENTITATCODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.ENTITATCODI]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_entitatCodi_columnvalueid">
            <form:errors path="logCarpeta.entitatCodi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.ENTITATCODI)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.ENTITATCODI)? ' uneditable-input' : ''}"  style="" maxlength="9" path="logCarpeta.entitatCodi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.TEMPS)}">
        <tr id="logCarpeta_temps_rowid">
          <td id="logCarpeta_temps_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.TEMPS])?'logCarpeta.temps':__theForm.labels[LogCarpetaFields.TEMPS]}" />
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.TEMPS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.TEMPS]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_temps_columnvalueid">
            <form:errors path="logCarpeta.temps" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.TEMPS)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.TEMPS)? ' uneditable-input' : ''}"  style=""  path="logCarpeta.temps"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.DATAINICI)}">
        <tr id="logCarpeta_dataInici_rowid">
          <td id="logCarpeta_dataInici_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.DATAINICI])?'logCarpeta.dataInici':__theForm.labels[LogCarpetaFields.DATAINICI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_dataInici_columnvalueid">
              <form:errors path="logCarpeta.dataInici" cssClass="errorField alert alert-danger" />
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
          </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.PETICIO)}">
        <tr id="logCarpeta_peticio_rowid">
          <td id="logCarpeta_peticio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.PETICIO])?'logCarpeta.peticio':__theForm.labels[LogCarpetaFields.PETICIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.PETICIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.PETICIO]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_peticio_columnvalueid">
            <form:errors path="logCarpeta.peticio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.PETICIO)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.PETICIO)? ' uneditable-input' : ''}"  style="" maxlength="255" path="logCarpeta.peticio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.ERROR)}">
        <tr id="logCarpeta_error_rowid">
          <td id="logCarpeta_error_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.ERROR])?'logCarpeta.error':__theForm.labels[LogCarpetaFields.ERROR]}" />
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.ERROR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.ERROR]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_error_columnvalueid">
              <form:errors path="logCarpeta.error" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.ERROR)? 'true' : 'false'}" path="logCarpeta.error"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_error" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_error" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.error'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.error'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.error'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_error').on('click', function(){
					var valor = ($('#dropdownMenuContainer_error').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_error').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.EXCEPCIO)}">
        <tr id="logCarpeta_excepcio_rowid">
          <td id="logCarpeta_excepcio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.EXCEPCIO])?'logCarpeta.excepcio':__theForm.labels[LogCarpetaFields.EXCEPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.EXCEPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.EXCEPCIO]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_excepcio_columnvalueid">
              <form:errors path="logCarpeta.excepcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.EXCEPCIO)? 'true' : 'false'}" path="logCarpeta.excepcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_excepcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_excepcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.excepcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.excepcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('logCarpeta.excepcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_excepcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_excepcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_excepcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,LogCarpetaFields.IDSESSIO)}">
        <tr id="logCarpeta_idSessio_rowid">
          <td id="logCarpeta_idSessio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[LogCarpetaFields.IDSESSIO])?'logCarpeta.idSessio':__theForm.labels[LogCarpetaFields.IDSESSIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[LogCarpetaFields.IDSESSIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[LogCarpetaFields.IDSESSIO]}" ></i>
              </c:if>
            </td>
          <td id="logCarpeta_idSessio_columnvalueid">
            <form:errors path="logCarpeta.idSessio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.IDSESSIO)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,LogCarpetaFields.IDSESSIO)? ' uneditable-input' : ''}"  style="" maxlength="255" path="logCarpeta.idSessio"   />

           </td>
        </tr>
        </c:if>
        
