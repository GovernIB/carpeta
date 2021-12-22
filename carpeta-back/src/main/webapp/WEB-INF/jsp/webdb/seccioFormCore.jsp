<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SeccioFields" className="es.caib.carpeta.model.fields.SeccioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,SeccioFields.NOMID)}">
        <tr id="seccio_nomID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SeccioFields.NOMID])?'seccio.nomID':__theForm.labels[SeccioFields.NOMID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SeccioFields.NOMID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SeccioFields.NOMID]}" ></i>
              </c:if>
            </td>
            <td>
       <form:errors path="seccio.nom" cssClass="errorField alert alert-danger" />
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
               <form:errors path="seccio.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="seccio.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,SeccioFields.NOMID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,SeccioFields.NOMID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SeccioFields.DESCRIPCIOID)}">
        <tr id="seccio_descripcioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SeccioFields.DESCRIPCIOID])?'seccio.descripcioID':__theForm.labels[SeccioFields.DESCRIPCIOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SeccioFields.DESCRIPCIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SeccioFields.DESCRIPCIOID]}" ></i>
              </c:if>
            </td>
            <td>
       <form:errors path="seccio.descripcio" cssClass="errorField alert alert-danger" />
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
               <form:errors path="seccio.descripcio.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="seccio.descripcio.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,SeccioFields.DESCRIPCIOID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,SeccioFields.DESCRIPCIOID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SeccioFields.CONTEXTE)}">
        <tr id="seccio_contexte_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SeccioFields.CONTEXTE])?'seccio.contexte':__theForm.labels[SeccioFields.CONTEXTE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SeccioFields.CONTEXTE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SeccioFields.CONTEXTE]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="seccio.contexte" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SeccioFields.CONTEXTE)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,SeccioFields.CONTEXTE)? ' uneditable-input' : ''}"  style="" maxlength="50" path="seccio.contexte"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SeccioFields.ACTIVA)}">
        <tr id="seccio_activa_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SeccioFields.ACTIVA])?'seccio.activa':__theForm.labels[SeccioFields.ACTIVA]}" />
             </label>
              <c:if test="${not empty __theForm.help[SeccioFields.ACTIVA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SeccioFields.ACTIVA]}" ></i>
              </c:if>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SeccioFields.ACTIVA)}" >
              <form:errors path="seccio.activa" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,SeccioFields.ACTIVA)? 'false' : 'true'}" path="seccio.activa" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SeccioFields.ACTIVA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.seccio.activa}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SeccioFields.ICONAID)}">
        <tr id="seccio_iconaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SeccioFields.ICONAID])?'seccio.iconaID':__theForm.labels[SeccioFields.ICONAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SeccioFields.ICONAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SeccioFields.ICONAID]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="seccio.iconaID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,SeccioFields.ICONAID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(iconaID.iconaID)}"/>">${iconaID.iconaID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,SeccioFields.ICONAID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,SeccioFields.ICONAID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,SeccioFields.ICONAID)? ' uneditable-input' : ''}"   path="iconaID" type="file" />
                  <label class="custom-file-label" for="iconaID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.seccio.icona}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.seccio.icona)}"/>">${__theForm.seccio.icona.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="iconaID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#iconaID').on('change', function(){
						var ruta = $('#iconaID').val(); 
						var rutaArray = ruta.split('\\');
						$('#iconaID-custom-file-label').css('display','block');
						$('#iconaID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SeccioFields.SECCIOPAREID)}">
        <tr id="seccio_seccioPareID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SeccioFields.SECCIOPAREID])?'seccio.seccioPareID':__theForm.labels[SeccioFields.SECCIOPAREID]}" />
             </label>
              <c:if test="${not empty __theForm.help[SeccioFields.SECCIOPAREID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SeccioFields.SECCIOPAREID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="seccio.seccioPareID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SeccioFields.SECCIOPAREID)}" >
          <form:hidden path="seccio.seccioPareID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.seccio.seccioPareID,__theForm.listOfValuesForSeccioPareID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SeccioFields.SECCIOPAREID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="seccio_seccioPareID"  onchange="if(typeof onChangeSeccioPareID == 'function') {  onChangeSeccioPareID(this); };"  cssClass="form-control col-md-9-optional" path="seccio.seccioPareID">
            <c:forEach items="${__theForm.listOfValuesForSeccioPareID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.seccio.seccioPareID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.seccio.seccioPareID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SeccioFields.ENTITATID)}">
        <tr id="seccio_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SeccioFields.ENTITATID])?'seccio.entitatID':__theForm.labels[SeccioFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SeccioFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SeccioFields.ENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="seccio.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,SeccioFields.ENTITATID)}" >
          <form:hidden path="seccio.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.seccio.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,SeccioFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="seccio_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="seccio.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,SeccioFields.ORDRE)}">
        <tr id="seccio_ordre_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[SeccioFields.ORDRE])?'seccio.ordre':__theForm.labels[SeccioFields.ORDRE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[SeccioFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[SeccioFields.ORDRE]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="seccio.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,SeccioFields.ORDRE)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,SeccioFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="seccio.ordre"   />

           </td>
        </tr>
        </c:if>
        
