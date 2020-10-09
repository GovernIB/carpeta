<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EnllazFields" className="es.caib.carpeta.model.fields.EnllazFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.TIPUS)}">
        <tr id="enllaz_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.TIPUS])?'enllaz.tipus':__theForm.labels[EnllazFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EnllazFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="enllaz.tipus" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EnllazFields.TIPUS)}" >
          <form:hidden path="enllaz.tipus"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.enllaz.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EnllazFields.TIPUS)}" >
          <form:select id="enllaz_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-4" path="enllaz.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.NOMID)}">
        <tr id="enllaz_nomID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.NOMID])?'enllaz.nomID':__theForm.labels[EnllazFields.NOMID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EnllazFields.NOMID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.NOMID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="enllaz.nom" cssClass="errorField alert alert-error" />
       <div class="row-fluid  col-md-6">
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
               <form:errors path="enllaz.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="enllaz.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EnllazFields.NOMID)? ' uneditable-input' : ''}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.URLID)}">
        <tr id="enllaz_urlID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.URLID])?'enllaz.urlID':__theForm.labels[EnllazFields.URLID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EnllazFields.URLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.URLID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="enllaz.url" cssClass="errorField alert alert-error" />
       <div class="row-fluid  col-md-6">
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
               <form:errors path="enllaz.url.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="enllaz.url.traduccions['${idioma.idiomaID}'].valor" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EnllazFields.URLID)? ' uneditable-input' : ''}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.ENTITATID)}">
        <tr id="enllaz_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.ENTITATID])?'enllaz.entitatID':__theForm.labels[EnllazFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EnllazFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="enllaz.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EnllazFields.ENTITATID)}" >
          <form:hidden path="enllaz.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.enllaz.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EnllazFields.ENTITATID)}" >
          <form:select id="enllaz_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-4" path="enllaz.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EnllazFields.LOGOID)}">
        <tr id="enllaz_logoID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EnllazFields.LOGOID])?'enllaz.logoID':__theForm.labels[EnllazFields.LOGOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EnllazFields.LOGOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EnllazFields.LOGOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="enllaz.logoID" cssClass="errorField alert alert-error" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EnllazFields.LOGOID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(logoID.logoID)}"/>">${logoID.logoID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EnllazFields.LOGOID)}" >
              <div class="input-group">
                <div class="custom-file col-md-4">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EnllazFields.LOGOID)? 'true' : 'false'}" cssClass="custom-file-input col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EnllazFields.LOGOID)? ' uneditable-input' : ''}"   path="logoID" type="file" />
                  <label class="custom-file-label" for="logoID">
                  </label>
                </div>
                <c:if test="${not empty __theForm.enllaz.logo}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.enllaz.logo)}"/>">${__theForm.enllaz.logo.nom}</a>
</small>
                  </span>
                </div>
                </c:if>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
