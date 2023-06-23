<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PreguntesFrequentsFields" className="es.caib.carpeta.model.fields.PreguntesFrequentsFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PreguntesFrequentsFields.ENUNCIATID)}">
        <tr id="preguntesFrequents_enunciatID_rowid">
          <td id="preguntesFrequents_enunciatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PreguntesFrequentsFields.ENUNCIATID])?'preguntesFrequents.enunciatID':__theForm.labels[PreguntesFrequentsFields.ENUNCIATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PreguntesFrequentsFields.ENUNCIATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PreguntesFrequentsFields.ENUNCIATID]}" ></i>
              </c:if>
            </td>
          <td id="preguntesFrequents_enunciatID_columnvalueid">
       <form:errors path="preguntesFrequents.enunciat" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_enunciat_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_enunciat_${idioma.idiomaID}">
               <form:errors path="preguntesFrequents.enunciat.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="preguntesFrequents.enunciat.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.ENUNCIATID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.ENUNCIATID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PreguntesFrequentsFields.ORDRE)}">
        <tr id="preguntesFrequents_ordre_rowid">
          <td id="preguntesFrequents_ordre_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PreguntesFrequentsFields.ORDRE])?'preguntesFrequents.ordre':__theForm.labels[PreguntesFrequentsFields.ORDRE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PreguntesFrequentsFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PreguntesFrequentsFields.ORDRE]}" ></i>
              </c:if>
            </td>
          <td id="preguntesFrequents_ordre_columnvalueid">
            <form:errors path="preguntesFrequents.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.ORDRE)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="preguntesFrequents.ordre"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PreguntesFrequentsFields.ENTITATID)}">
        <tr id="preguntesFrequents_entitatID_rowid">
          <td id="preguntesFrequents_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PreguntesFrequentsFields.ENTITATID])?'preguntesFrequents.entitatID':__theForm.labels[PreguntesFrequentsFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PreguntesFrequentsFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PreguntesFrequentsFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="preguntesFrequents_entitatID_columnvalueid">
          <form:errors path="preguntesFrequents.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.ENTITATID)}" >
          <form:hidden path="preguntesFrequents.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.preguntesFrequents.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="preguntesFrequents_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="preguntesFrequents.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PreguntesFrequentsFields.RESPOSTACA)}">
        <tr id="preguntesFrequents_respostaCa_rowid">
          <td id="preguntesFrequents_respostaCa_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PreguntesFrequentsFields.RESPOSTACA])?'preguntesFrequents.respostaCa':__theForm.labels[PreguntesFrequentsFields.RESPOSTACA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PreguntesFrequentsFields.RESPOSTACA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PreguntesFrequentsFields.RESPOSTACA]}" ></i>
              </c:if>
            </td>
          <td id="preguntesFrequents_respostaCa_columnvalueid">
              <form:errors path="preguntesFrequents.respostaCa" cssClass="errorField alert alert-danger" />
       <form:textarea cssClass=" ${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.RESPOSTACA)? 'mceEditorReadOnly':'mceEditor'}"  path="preguntesFrequents.respostaCa"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PreguntesFrequentsFields.RESPOSTAES)}">
        <tr id="preguntesFrequents_respostaEs_rowid">
          <td id="preguntesFrequents_respostaEs_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PreguntesFrequentsFields.RESPOSTAES])?'preguntesFrequents.respostaEs':__theForm.labels[PreguntesFrequentsFields.RESPOSTAES]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PreguntesFrequentsFields.RESPOSTAES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PreguntesFrequentsFields.RESPOSTAES]}" ></i>
              </c:if>
            </td>
          <td id="preguntesFrequents_respostaEs_columnvalueid">
              <form:errors path="preguntesFrequents.respostaEs" cssClass="errorField alert alert-danger" />
       <form:textarea cssClass=" ${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.RESPOSTAES)? 'mceEditorReadOnly':'mceEditor'}"  path="preguntesFrequents.respostaEs"  />
           </td>
        </tr>
        </c:if>
        
