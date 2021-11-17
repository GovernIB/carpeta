<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFields" className="es.caib.carpeta.model.fields.PluginFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.NOMID)}">
        <tr id="plugin_nomID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.NOMID])?'plugin.nomID':__theForm.labels[PluginFields.NOMID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.NOMID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.NOMID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="plugin.nom" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_nom_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_nom_${idioma.idiomaID}">
               <form:errors path="plugin.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="plugin.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.NOMID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,PluginFields.NOMID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.DESCRIPCIOID)}">
        <tr id="plugin_descripcioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.DESCRIPCIOID])?'plugin.descripcioID':__theForm.labels[PluginFields.DESCRIPCIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.DESCRIPCIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.DESCRIPCIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="plugin.descripcio" cssClass="errorField alert alert-danger" />
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
               <form:errors path="plugin.descripcio.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="plugin.descripcio.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.DESCRIPCIOID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,PluginFields.DESCRIPCIOID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.TITOLLLARGID)}">
        <tr id="plugin_titolLlargID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.TITOLLLARGID])?'plugin.titolLlargID':__theForm.labels[PluginFields.TITOLLLARGID]}" />
              <c:if test="${not empty __theForm.help[PluginFields.TITOLLLARGID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.TITOLLLARGID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="plugin.titolLlarg" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_titolLlarg_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_titolLlarg_${idioma.idiomaID}">
               <form:errors path="plugin.titolLlarg.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="plugin.titolLlarg.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.TITOLLLARGID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,PluginFields.TITOLLLARGID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.SUBTITOLLLARGID)}">
        <tr id="plugin_subtitolLlargID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.SUBTITOLLLARGID])?'plugin.subtitolLlargID':__theForm.labels[PluginFields.SUBTITOLLLARGID]}" />
              <c:if test="${not empty __theForm.help[PluginFields.SUBTITOLLLARGID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.SUBTITOLLLARGID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="plugin.subtitolLlarg" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_subtitolLlarg_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_subtitolLlarg_${idioma.idiomaID}">
               <form:errors path="plugin.subtitolLlarg.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="plugin.subtitolLlarg.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.SUBTITOLLLARGID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,PluginFields.SUBTITOLLLARGID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.CONTEXT)}">
        <tr id="plugin_context_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.CONTEXT])?'plugin.context':__theForm.labels[PluginFields.CONTEXT]}" />
              <c:if test="${not empty __theForm.help[PluginFields.CONTEXT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.CONTEXT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="plugin.context" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.CONTEXT)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.CONTEXT)? ' uneditable-input' : ''}"  style="" maxlength="50" path="plugin.context"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.LOGOID)}">
        <tr id="plugin_logoID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.LOGOID])?'plugin.logoID':__theForm.labels[PluginFields.LOGOID]}" />
              <c:if test="${not empty __theForm.help[PluginFields.LOGOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.LOGOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="plugin.logoID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.LOGOID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(logoID.logoID)}"/>">${logoID.logoID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.LOGOID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.LOGOID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.LOGOID)? ' uneditable-input' : ''}"   path="logoID" type="file" />
                  <label class="custom-file-label" for="logoID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.plugin.logo}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.plugin.logo)}"/>">${__theForm.plugin.logo.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="logoIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#logoID').on('change', function(){
						var ruta = $('#logoID').val(); 
						var rutaArray = ruta.split('\\');
						$('#logoID-custom-file-label').css('display','block');
						$('#logoID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.CLASSE)}">
        <tr id="plugin_classe_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.CLASSE])?'plugin.classe':__theForm.labels[PluginFields.CLASSE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.CLASSE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.CLASSE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="plugin.classe" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.CLASSE)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.CLASSE)? ' uneditable-input' : ''}"  style="" maxlength="255" path="plugin.classe"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.PROPIETATS)}">
        <tr id="plugin_propietats_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.PROPIETATS])?'plugin.propietats':__theForm.labels[PluginFields.PROPIETATS]}" />
              <c:if test="${not empty __theForm.help[PluginFields.PROPIETATS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.PROPIETATS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="plugin.propietats" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.PROPIETATS)? 'true' : 'false'}" path="plugin.propietats"  />
      <div id="dropdownMenuButton_propietats" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_propietats" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('plugin.propietats'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('plugin.propietats'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('plugin.propietats'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_propietats').on('click', function(){
					var valor = ($('#dropdownMenuContainer_propietats').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_propietats').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.ACTIU)}">
        <tr id="plugin_actiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.ACTIU])?'plugin.actiu':__theForm.labels[PluginFields.ACTIU]}" />
              <c:if test="${not empty __theForm.help[PluginFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.ACTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)}" >
              <form:errors path="plugin.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)? 'false' : 'true'}" path="plugin.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.plugin.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.TIPUS)}">
        <tr id="plugin_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.TIPUS])?'plugin.tipus':__theForm.labels[PluginFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="plugin.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.TIPUS)}" >
          <form:hidden path="plugin.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.plugin.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="plugin_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="plugin.tipus">
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
        
