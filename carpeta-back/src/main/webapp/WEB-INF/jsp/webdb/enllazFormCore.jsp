<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EnllazFields" className="es.caib.carpeta.model.fields.EnllazFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.TIPUS)}">
        <tr id="enllaz_tipus_rowid">
          <td id="enllaz_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.TIPUS])?'enllaz.tipus':__theForm.labels[EnllazFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EnllazFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="enllaz_tipus_columnvalueid">
          <form:errors path="enllaz.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EnllazFields.TIPUS)}" >
          <form:hidden path="enllaz.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.enllaz.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EnllazFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="enllaz_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="enllaz.tipus">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.NOMID)}">
        <tr id="enllaz_nomID_rowid">
          <td id="enllaz_nomID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.NOMID])?'enllaz.nomID':__theForm.labels[EnllazFields.NOMID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EnllazFields.NOMID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.NOMID]}" ></i>
              </c:if>
            </td>
          <td id="enllaz_nomID_columnvalueid">
       <form:errors path="enllaz.nom" cssClass="errorField alert alert-danger" />
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
               <form:errors path="enllaz.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="enllaz.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,EnllazFields.NOMID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,EnllazFields.NOMID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.DESCRIPCIOID)}">
        <tr id="enllaz_descripcioID_rowid">
          <td id="enllaz_descripcioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.DESCRIPCIOID])?'enllaz.descripcioID':__theForm.labels[EnllazFields.DESCRIPCIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EnllazFields.DESCRIPCIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.DESCRIPCIOID]}" ></i>
              </c:if>
            </td>
          <td id="enllaz_descripcioID_columnvalueid">
       <form:errors path="enllaz.descripcio" cssClass="errorField alert alert-danger" />
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
               <form:errors path="enllaz.descripcio.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="enllaz.descripcio.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,EnllazFields.DESCRIPCIOID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,EnllazFields.DESCRIPCIOID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.URLID)}">
        <tr id="enllaz_urlID_rowid">
          <td id="enllaz_urlID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.URLID])?'enllaz.urlID':__theForm.labels[EnllazFields.URLID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EnllazFields.URLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.URLID]}" ></i>
              </c:if>
            </td>
          <td id="enllaz_urlID_columnvalueid">
       <form:errors path="enllaz.url" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_url_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_url_${idioma.idiomaID}">
               <form:errors path="enllaz.url.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="enllaz.url.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,EnllazFields.URLID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,EnllazFields.URLID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.ENTITATID)}">
        <tr id="enllaz_entitatID_rowid">
          <td id="enllaz_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.ENTITATID])?'enllaz.entitatID':__theForm.labels[EnllazFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EnllazFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="enllaz_entitatID_columnvalueid">
          <form:errors path="enllaz.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EnllazFields.ENTITATID)}" >
          <form:hidden path="enllaz.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.enllaz.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EnllazFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="enllaz_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="enllaz.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.LOGOID)}">
        <tr id="enllaz_logoID_rowid">
          <td id="enllaz_logoID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.LOGOID])?'enllaz.logoID':__theForm.labels[EnllazFields.LOGOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EnllazFields.LOGOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.LOGOID]}" ></i>
              </c:if>
            </td>
          <td id="enllaz_logoID_columnvalueid">
              <form:errors path="enllaz.logoID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EnllazFields.LOGOID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.enllaz.logo)}"/>">${__theForm.enllaz.logo.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EnllazFields.LOGOID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EnllazFields.LOGOID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,EnllazFields.LOGOID)? ' uneditable-input' : ''}"   path="logoID" type="file" />
                  <label class="custom-file-label" for="logoID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.enllaz.logo}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.enllaz.logo)}"/>">${__theForm.enllaz.logo.nom}</a>
</small>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.SECCIOID)}">
        <tr id="enllaz_seccioID_rowid">
          <td id="enllaz_seccioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.SECCIOID])?'enllaz.seccioID':__theForm.labels[EnllazFields.SECCIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EnllazFields.SECCIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.SECCIOID]}" ></i>
              </c:if>
            </td>
          <td id="enllaz_seccioID_columnvalueid">
          <form:errors path="enllaz.seccioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EnllazFields.SECCIOID)}" >
          <form:hidden path="enllaz.seccioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.enllaz.seccioID,__theForm.listOfSeccioForSeccioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EnllazFields.SECCIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="enllaz_seccioID"  onchange="if(typeof onChangeSeccioID == 'function') {  onChangeSeccioID(this); };"  cssClass="form-control col-md-9-optional" path="enllaz.seccioID">
            <c:forEach items="${__theForm.listOfSeccioForSeccioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.enllaz.seccioID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.enllaz.seccioID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.ACTIU)}">
        <tr id="enllaz_actiu_rowid">
          <td id="enllaz_actiu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.ACTIU])?'enllaz.actiu':__theForm.labels[EnllazFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[EnllazFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.ACTIU]}" ></i>
              </c:if>
            </td>
          <td id="enllaz_actiu_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EnllazFields.ACTIU)}" >
              <form:errors path="enllaz.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EnllazFields.ACTIU)? 'false' : 'true'}" path="enllaz.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EnllazFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.enllaz.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.ORDRE)}">
        <tr id="enllaz_ordre_rowid">
          <td id="enllaz_ordre_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.ORDRE])?'enllaz.ordre':__theForm.labels[EnllazFields.ORDRE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EnllazFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.ORDRE]}" ></i>
              </c:if>
            </td>
          <td id="enllaz_ordre_columnvalueid">
            <form:errors path="enllaz.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EnllazFields.ORDRE)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,EnllazFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="enllaz.ordre"   />

           </td>
        </tr>
        </c:if>
        
