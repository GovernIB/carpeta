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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PreguntesFrequentsFields.RESPOSTAID)}">
        <tr id="preguntesFrequents_respostaID_rowid">
          <td id="preguntesFrequents_respostaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PreguntesFrequentsFields.RESPOSTAID])?'preguntesFrequents.respostaID':__theForm.labels[PreguntesFrequentsFields.RESPOSTAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PreguntesFrequentsFields.RESPOSTAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PreguntesFrequentsFields.RESPOSTAID]}" ></i>
              </c:if>
            </td>
          <td id="preguntesFrequents_respostaID_columnvalueid">
       <form:errors path="preguntesFrequents.resposta" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_resposta_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_resposta_${idioma.idiomaID}">
               <form:errors path="preguntesFrequents.resposta.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="preguntesFrequents.resposta.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control  ${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.RESPOSTAID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.RESPOSTAID)}" maxlength="4000" />
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PreguntesFrequentsFields.FITXER1ID)}">
        <tr id="preguntesFrequents_fitxer1ID_rowid">
          <td id="preguntesFrequents_fitxer1ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PreguntesFrequentsFields.FITXER1ID])?'preguntesFrequents.fitxer1ID':__theForm.labels[PreguntesFrequentsFields.FITXER1ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PreguntesFrequentsFields.FITXER1ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PreguntesFrequentsFields.FITXER1ID]}" ></i>
              </c:if>
            </td>
          <td id="preguntesFrequents_fitxer1ID_columnvalueid">
              <form:errors path="preguntesFrequents.fitxer1ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER1ID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.preguntesFrequents.fitxer1)}"/>">${__theForm.preguntesFrequents.fitxer1.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER1ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER1ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER1ID)? ' uneditable-input' : ''}"   path="fitxer1ID" type="file" />
                  <label class="custom-file-label" for="fitxer1ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.preguntesFrequents.fitxer1}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.preguntesFrequents.fitxer1)}"/>">${__theForm.preguntesFrequents.fitxer1.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxer1IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxer1ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxer1ID').on('change', function(){
						var ruta = $('#fitxer1ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxer1ID-custom-file-label').css('display','block');
						$('#fitxer1ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PreguntesFrequentsFields.FITXER2ID)}">
        <tr id="preguntesFrequents_fitxer2ID_rowid">
          <td id="preguntesFrequents_fitxer2ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PreguntesFrequentsFields.FITXER2ID])?'preguntesFrequents.fitxer2ID':__theForm.labels[PreguntesFrequentsFields.FITXER2ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PreguntesFrequentsFields.FITXER2ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PreguntesFrequentsFields.FITXER2ID]}" ></i>
              </c:if>
            </td>
          <td id="preguntesFrequents_fitxer2ID_columnvalueid">
              <form:errors path="preguntesFrequents.fitxer2ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER2ID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.preguntesFrequents.fitxer2)}"/>">${__theForm.preguntesFrequents.fitxer2.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER2ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER2ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER2ID)? ' uneditable-input' : ''}"   path="fitxer2ID" type="file" />
                  <label class="custom-file-label" for="fitxer2ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.preguntesFrequents.fitxer2}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.preguntesFrequents.fitxer2)}"/>">${__theForm.preguntesFrequents.fitxer2.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxer2IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxer2ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxer2ID').on('change', function(){
						var ruta = $('#fitxer2ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxer2ID-custom-file-label').css('display','block');
						$('#fitxer2ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PreguntesFrequentsFields.FITXER3ID)}">
        <tr id="preguntesFrequents_fitxer3ID_rowid">
          <td id="preguntesFrequents_fitxer3ID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[PreguntesFrequentsFields.FITXER3ID])?'preguntesFrequents.fitxer3ID':__theForm.labels[PreguntesFrequentsFields.FITXER3ID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PreguntesFrequentsFields.FITXER3ID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PreguntesFrequentsFields.FITXER3ID]}" ></i>
              </c:if>
            </td>
          <td id="preguntesFrequents_fitxer3ID_columnvalueid">
              <form:errors path="preguntesFrequents.fitxer3ID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER3ID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.preguntesFrequents.fitxer3)}"/>">${__theForm.preguntesFrequents.fitxer3.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER3ID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER3ID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PreguntesFrequentsFields.FITXER3ID)? ' uneditable-input' : ''}"   path="fitxer3ID" type="file" />
                  <label class="custom-file-label" for="fitxer3ID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.preguntesFrequents.fitxer3}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.preguntesFrequents.fitxer3)}"/>">${__theForm.preguntesFrequents.fitxer3.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxer3IDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxer3ID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxer3ID').on('change', function(){
						var ruta = $('#fitxer3ID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxer3ID-custom-file-label').css('display','block');
						$('#fitxer3ID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
