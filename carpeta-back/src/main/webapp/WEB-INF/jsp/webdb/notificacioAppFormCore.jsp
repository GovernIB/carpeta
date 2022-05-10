<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="NotificacioAppFields" className="es.caib.carpeta.model.fields.NotificacioAppFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioAppFields.CODI)}">
        <tr id="notificacioApp_codi_rowid">
          <td id="notificacioApp_codi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioAppFields.CODI])?'notificacioApp.codi':__theForm.labels[NotificacioAppFields.CODI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[NotificacioAppFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioAppFields.CODI]}" ></i>
              </c:if>
            </td>
          <td id="notificacioApp_codi_columnvalueid">
            <form:errors path="notificacioApp.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.CODI)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="50" path="notificacioApp.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioAppFields.TITOLID)}">
        <tr id="notificacioApp_titolID_rowid">
          <td id="notificacioApp_titolID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioAppFields.TITOLID])?'notificacioApp.titolID':__theForm.labels[NotificacioAppFields.TITOLID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[NotificacioAppFields.TITOLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioAppFields.TITOLID]}" ></i>
              </c:if>
            </td>
          <td id="notificacioApp_titolID_columnvalueid">
       <form:errors path="notificacioApp.titol" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_titol_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_titol_${idioma.idiomaID}">
               <form:errors path="notificacioApp.titol.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="notificacioApp.titol.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.TITOLID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.TITOLID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioAppFields.MISSATGEID)}">
        <tr id="notificacioApp_missatgeID_rowid">
          <td id="notificacioApp_missatgeID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioAppFields.MISSATGEID])?'notificacioApp.missatgeID':__theForm.labels[NotificacioAppFields.MISSATGEID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[NotificacioAppFields.MISSATGEID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioAppFields.MISSATGEID]}" ></i>
              </c:if>
            </td>
          <td id="notificacioApp_missatgeID_columnvalueid">
       <form:errors path="notificacioApp.missatge" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_missatge_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_missatge_${idioma.idiomaID}">
               <form:errors path="notificacioApp.missatge.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:textarea path="notificacioApp.missatge.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.MISSATGEID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.MISSATGEID)}" maxlength="4000"  rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioAppFields.FRONTPLUGINID)}">
        <tr id="notificacioApp_frontPluginID_rowid">
          <td id="notificacioApp_frontPluginID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioAppFields.FRONTPLUGINID])?'notificacioApp.frontPluginID':__theForm.labels[NotificacioAppFields.FRONTPLUGINID]}" />
             </label>
              <c:if test="${not empty __theForm.help[NotificacioAppFields.FRONTPLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioAppFields.FRONTPLUGINID]}" ></i>
              </c:if>
            </td>
          <td id="notificacioApp_frontPluginID_columnvalueid">
          <form:errors path="notificacioApp.frontPluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.FRONTPLUGINID)}" >
          <form:hidden path="notificacioApp.frontPluginID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.notificacioApp.frontPluginID,__theForm.listOfPluginForFrontPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.FRONTPLUGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="notificacioApp_frontPluginID"  onchange="if(typeof onChangeFrontPluginID == 'function') {  onChangeFrontPluginID(this); };"  cssClass="form-control col-md-9-optional" path="notificacioApp.frontPluginID">
            <c:forEach items="${__theForm.listOfPluginForFrontPluginID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.notificacioApp.frontPluginID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.notificacioApp.frontPluginID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioAppFields.AJUDA)}">
        <tr id="notificacioApp_ajuda_rowid">
          <td id="notificacioApp_ajuda_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioAppFields.AJUDA])?'notificacioApp.ajuda':__theForm.labels[NotificacioAppFields.AJUDA]}" />
             </label>
              <c:if test="${not empty __theForm.help[NotificacioAppFields.AJUDA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioAppFields.AJUDA]}" ></i>
              </c:if>
            </td>
          <td id="notificacioApp_ajuda_columnvalueid">
              <form:errors path="notificacioApp.ajuda" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.AJUDA)? 'true' : 'false'}" path="notificacioApp.ajuda"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_ajuda" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_ajuda" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('notificacioApp.ajuda'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('notificacioApp.ajuda'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('notificacioApp.ajuda'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_ajuda').on('click', function(){
					var valor = ($('#dropdownMenuContainer_ajuda').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_ajuda').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioAppFields.ACTIVA)}">
        <tr id="notificacioApp_activa_rowid">
          <td id="notificacioApp_activa_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioAppFields.ACTIVA])?'notificacioApp.activa':__theForm.labels[NotificacioAppFields.ACTIVA]}" />
             </label>
              <c:if test="${not empty __theForm.help[NotificacioAppFields.ACTIVA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioAppFields.ACTIVA]}" ></i>
              </c:if>
            </td>
          <td id="notificacioApp_activa_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.ACTIVA)}" >
              <form:errors path="notificacioApp.activa" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.ACTIVA)? 'false' : 'true'}" path="notificacioApp.activa" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.ACTIVA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.notificacioApp.activa}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioAppFields.ENTITATID)}">
        <tr id="notificacioApp_entitatID_rowid">
          <td id="notificacioApp_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioAppFields.ENTITATID])?'notificacioApp.entitatID':__theForm.labels[NotificacioAppFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[NotificacioAppFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioAppFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="notificacioApp_entitatID_columnvalueid">
          <form:errors path="notificacioApp.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.ENTITATID)}" >
          <form:hidden path="notificacioApp.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.notificacioApp.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioAppFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="notificacioApp_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="notificacioApp.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
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
        
