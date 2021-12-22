<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AvisFields" className="es.caib.carpeta.model.fields.AvisFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.DESCRIPCIOID)}">
        <tr id="avis_descripcioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.DESCRIPCIOID])?'avis.descripcioID':__theForm.labels[AvisFields.DESCRIPCIOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.DESCRIPCIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.DESCRIPCIOID]}" ></i>
              </c:if>
            </td>
            <td>
       <form:errors path="avis.descripcio" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_descripcio_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_descripcio_${idioma.idiomaID}">
               <form:errors path="avis.descripcio.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="avis.descripcio.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,AvisFields.DESCRIPCIOID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,AvisFields.DESCRIPCIOID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.ENTITATID)}">
        <tr id="avis_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.ENTITATID])?'avis.entitatID':__theForm.labels[AvisFields.ENTITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.ENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="avis.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AvisFields.ENTITATID)}" >
          <form:hidden path="avis.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.avis.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AvisFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="avis_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="avis.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.avis.entitatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.avis.entitatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.DATAINICI)}">
        <tr id="avis_dataInici_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.DATAINICI])?'avis.dataInici':__theForm.labels[AvisFields.DATAINICI]}" />
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.DATAINICI]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="avis.dataInici" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="avis_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AvisFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#avis_dataInici" path="avis.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,AvisFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#avis_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#avis_dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.DATAFI)}">
        <tr id="avis_dataFi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.DATAFI])?'avis.dataFi':__theForm.labels[AvisFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.DATAFI]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="avis.dataFi" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="avis_dataFi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AvisFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#avis_dataFi" path="avis.dataFi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,AvisFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#avis_dataFi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#avis_dataFi').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.TIPUS)}">
        <tr id="avis_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.TIPUS])?'avis.tipus':__theForm.labels[AvisFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.TIPUS]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="avis.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AvisFields.TIPUS)}" >
          <form:hidden path="avis.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.avis.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AvisFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="avis_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="avis.tipus">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.GRAVETAT)}">
        <tr id="avis_gravetat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.GRAVETAT])?'avis.gravetat':__theForm.labels[AvisFields.GRAVETAT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.GRAVETAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.GRAVETAT]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="avis.gravetat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AvisFields.GRAVETAT)}" >
          <form:hidden path="avis.gravetat"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.avis.gravetat,__theForm.listOfValuesForGravetat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AvisFields.GRAVETAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="avis_gravetat"  onchange="if(typeof onChangeGravetat == 'function') {  onChangeGravetat(this); };"  cssClass="form-control col-md-9-optional" path="avis.gravetat">
            <c:forEach items="${__theForm.listOfValuesForGravetat}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.PLUGINFRONTID)}">
        <tr id="avis_pluginFrontID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.PLUGINFRONTID])?'avis.pluginFrontID':__theForm.labels[AvisFields.PLUGINFRONTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.PLUGINFRONTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.PLUGINFRONTID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="avis.pluginFrontID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AvisFields.PLUGINFRONTID)}" >
          <form:hidden path="avis.pluginFrontID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.avis.pluginFrontID,__theForm.listOfPluginForPluginFrontID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AvisFields.PLUGINFRONTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="avis_pluginFrontID"  onchange="if(typeof onChangePluginFrontID == 'function') {  onChangePluginFrontID(this); };"  cssClass="form-control col-md-9-optional" path="avis.pluginFrontID">
            <c:forEach items="${__theForm.listOfPluginForPluginFrontID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.avis.pluginFrontID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.avis.pluginFrontID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
