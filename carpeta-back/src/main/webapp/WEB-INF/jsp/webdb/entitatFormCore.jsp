<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.carpeta.model.fields.EntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.NOMID)}">
        <tr id="entitat_nomID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.NOMID])?'entitat.nomID':__theForm.labels[EntitatFields.NOMID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.NOMID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.NOMID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="entitat.nom" cssClass="errorField alert alert-error" />
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
               <form:errors path="entitat.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="entitat.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.NOMID)? ' uneditable-input' : ''}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.CODI)}">
        <tr id="entitat_codi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.CODI])?'entitat.codi':__theForm.labels[EntitatFields.CODI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.CODI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.codi" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CODI)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.CODI)? ' uneditable-input' : ''}"  maxlength="30" path="entitat.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.CODIDIR3)}">
        <tr id="entitat_codiDir3_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.CODIDIR3])?'entitat.codiDir3':__theForm.labels[EntitatFields.CODIDIR3]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.CODIDIR3]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.CODIDIR3]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.codiDir3" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CODIDIR3)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.CODIDIR3)? ' uneditable-input' : ''}"  maxlength="255" path="entitat.codiDir3"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ACTIVA)}">
        <tr id="entitat_activa_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ACTIVA])?'entitat.activa':__theForm.labels[EntitatFields.ACTIVA]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.ACTIVA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ACTIVA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)}" >
              <form:errors path="entitat.activa" cssClass="errorField alert alert-error" />
              <form:checkbox cssClass="form-control" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)? 'false' : 'true'}" path="entitat.activa" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitat.activa}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOMENUID)}">
        <tr id="entitat_logoMenuID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOMENUID])?'entitat.logoMenuID':__theForm.labels[EntitatFields.LOGOMENUID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOMENUID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOMENUID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.logoMenuID" cssClass="errorField alert alert-error" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOMENUID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(logoMenuID.logoMenuID)}"/>">${logoMenuID.logoMenuID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOMENUID)}" >
              <div class="input-group">
                <div class="custom-file col-md-4">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOMENUID)? 'true' : 'false'}" cssClass="custom-file-input col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOMENUID)? ' uneditable-input' : ''}"   path="logoMenuID" type="file" />
                  <label class="custom-file-label" for="logoMenuID">
                  </label>
                </div>
                <c:if test="${not empty __theForm.entitat.logoMenu}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.entitat.logoMenu)}"/>">${__theForm.entitat.logoMenu.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="logoMenuIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:if>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.COLORMENU)}">
        <tr id="entitat_colorMenu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.COLORMENU])?'entitat.colorMenu':__theForm.labels[EntitatFields.COLORMENU]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.COLORMENU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.COLORMENU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.colorMenu" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.COLORMENU)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.COLORMENU)? ' uneditable-input' : ''}"  maxlength="100" path="entitat.colorMenu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.TEXTEPEU)}">
        <tr id="entitat_textePeu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.TEXTEPEU])?'entitat.textePeu':__theForm.labels[EntitatFields.TEXTEPEU]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.TEXTEPEU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.TEXTEPEU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.textePeu" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;" cssClass="form-control col-md-8" readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.TEXTEPEU)? 'true' : 'false'}" path="entitat.textePeu"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('entitat.textePeu'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('entitat.textePeu'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('entitat.textePeu'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOPEUID)}">
        <tr id="entitat_logoPeuID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOPEUID])?'entitat.logoPeuID':__theForm.labels[EntitatFields.LOGOPEUID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOPEUID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOPEUID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.logoPeuID" cssClass="errorField alert alert-error" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOPEUID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(logoPeuID.logoPeuID)}"/>">${logoPeuID.logoPeuID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOPEUID)}" >
              <div class="input-group">
                <div class="custom-file col-md-4">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOPEUID)? 'true' : 'false'}" cssClass="custom-file-input col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOPEUID)? ' uneditable-input' : ''}"   path="logoPeuID" type="file" />
                  <label class="custom-file-label" for="logoPeuID">
                  </label>
                </div>
                <c:if test="${not empty __theForm.entitat.logoPeu}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.entitat.logoPeu)}"/>">${__theForm.entitat.logoPeu.nom}</a>
</small>
                  </span>
                </div>
                </c:if>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.VERSIO)}">
        <tr id="entitat_versio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.VERSIO])?'entitat.versio':__theForm.labels[EntitatFields.VERSIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.VERSIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.VERSIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.versio" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.VERSIO)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.VERSIO)? ' uneditable-input' : ''}"  maxlength="50" path="entitat.versio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.COMMIT)}">
        <tr id="entitat_commit_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.COMMIT])?'entitat.commit':__theForm.labels[EntitatFields.COMMIT]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.COMMIT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.COMMIT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.commit" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.COMMIT)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.COMMIT)? ' uneditable-input' : ''}"  maxlength="255" path="entitat.commit"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.FITXERCSSID)}">
        <tr id="entitat_fitxerCssID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.FITXERCSSID])?'entitat.fitxerCssID':__theForm.labels[EntitatFields.FITXERCSSID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.FITXERCSSID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.FITXERCSSID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.fitxerCssID" cssClass="errorField alert alert-error" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.FITXERCSSID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(fitxerCssID.fitxerCssID)}"/>">${fitxerCssID.fitxerCssID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.FITXERCSSID)}" >
              <div class="input-group">
                <div class="custom-file col-md-4">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.FITXERCSSID)? 'true' : 'false'}" cssClass="custom-file-input col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.FITXERCSSID)? ' uneditable-input' : ''}"   path="fitxerCssID" type="file" />
                  <label class="custom-file-label" for="fitxerCssID">
                  </label>
                </div>
                <c:if test="${not empty __theForm.entitat.fitxerCss}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.entitat.fitxerCss)}"/>">${__theForm.entitat.fitxerCss.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerCssIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:if>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.CONTEXT)}">
        <tr id="entitat_context_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.CONTEXT])?'entitat.context':__theForm.labels[EntitatFields.CONTEXT]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.CONTEXT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.CONTEXT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.context" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CONTEXT)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.CONTEXT)? ' uneditable-input' : ''}"  maxlength="255" path="entitat.context"   />

           </td>
        </tr>
        </c:if>
        
