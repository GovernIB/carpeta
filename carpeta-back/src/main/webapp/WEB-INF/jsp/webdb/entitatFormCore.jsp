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
       <form:errors path="entitat.nom" cssClass="errorField alert alert-danger" />
       <div class="row-fluid  col-md-8">
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
               <form:errors path="entitat.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="entitat.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.NOMID)? ' uneditable-input' : ''}" maxlength="4000" />
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
            <form:errors path="entitat.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CODI)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="30" path="entitat.codi"   />

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
            <form:errors path="entitat.codiDir3" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CODIDIR3)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.CODIDIR3)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.codiDir3"   />

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
              <form:errors path="entitat.activa" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="form-control" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)? 'false' : 'true'}" path="entitat.activa"  style="width:1%"/>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.ACTIVA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitat.activa}" />
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
            <form:errors path="entitat.colorMenu" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.COLORMENU)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.COLORMENU)? ' uneditable-input' : ''}"  style="" maxlength="100" path="entitat.colorMenu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOCAPBACKID)}">
        <tr id="entitat_logoCapBackID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOCAPBACKID])?'entitat.logoCapBackID':__theForm.labels[EntitatFields.LOGOCAPBACKID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOCAPBACKID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOCAPBACKID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.logoCapBackID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOCAPBACKID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(logoCapBackID.logoCapBackID)}"/>">${logoCapBackID.logoCapBackID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOCAPBACKID)}" >
              <div class="input-group">
                <div class="custom-file col-md-8">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOCAPBACKID)? 'true' : 'false'}" cssClass="custom-file-input form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOCAPBACKID)? ' uneditable-input' : ''}"   path="logoCapBackID" type="file" />
                  <label class="custom-file-label" for="logoCapBackID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.logoCapBack}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.entitat.logoCapBack)}"/>">${__theForm.entitat.logoCapBack.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoCapBackID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
                    $('#logoCapBackID').on('change', function(){
                        var ruta = $('#logoCapBackID').val(); 
                        var rutaArray = ruta.split('\\');
                        $('#logoCapBackID-custom-file-label').css('display','block');
                        $('#logoCapBackID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
                    });
                </script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOPEUBACKID)}">
        <tr id="entitat_logoPeuBackID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOPEUBACKID])?'entitat.logoPeuBackID':__theForm.labels[EntitatFields.LOGOPEUBACKID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOPEUBACKID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOPEUBACKID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.logoPeuBackID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOPEUBACKID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(logoPeuBackID.logoPeuBackID)}"/>">${logoPeuBackID.logoPeuBackID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOPEUBACKID)}" >
              <div class="input-group">
                <div class="custom-file col-md-8">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOPEUBACKID)? 'true' : 'false'}" cssClass="custom-file-input form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOPEUBACKID)? ' uneditable-input' : ''}"   path="logoPeuBackID" type="file" />
                  <label class="custom-file-label" for="logoPeuBackID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.logoPeuBack}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.entitat.logoPeuBack)}"/>">${__theForm.entitat.logoPeuBack.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoPeuBackID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
                    $('#logoPeuBackID').on('change', function(){
                        var ruta = $('#logoPeuBackID').val(); 
                        var rutaArray = ruta.split('\\');
                        $('#logoPeuBackID-custom-file-label').css('display','block');
                        $('#logoPeuBackID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
                    });
                </script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGOLATERALFRONTID)}">
        <tr id="entitat_logoLateralFrontID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGOLATERALFRONTID])?'entitat.logoLateralFrontID':__theForm.labels[EntitatFields.LOGOLATERALFRONTID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.LOGOLATERALFRONTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGOLATERALFRONTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.logoLateralFrontID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOLATERALFRONTID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(logoLateralFrontID.logoLateralFrontID)}"/>">${logoLateralFrontID.logoLateralFrontID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOLATERALFRONTID)}" >
              <div class="input-group">
                <div class="custom-file col-md-8">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOLATERALFRONTID)? 'true' : 'false'}" cssClass="custom-file-input form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGOLATERALFRONTID)? ' uneditable-input' : ''}"   path="logoLateralFrontID" type="file" />
                  <label class="custom-file-label" for="logoLateralFrontID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.logoLateralFront}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.entitat.logoLateralFront)}"/>">${__theForm.entitat.logoLateralFront.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoLateralFrontID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
                    $('#logoLateralFrontID').on('change', function(){
                        var ruta = $('#logoLateralFrontID').val(); 
                        var rutaArray = ruta.split('\\');
                        $('#logoLateralFrontID-custom-file-label').css('display','block');
                        $('#logoLateralFrontID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
                    });
                </script>                </c:otherwise>
                </c:choose>
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
            <form:errors path="entitat.versio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.VERSIO)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.VERSIO)? ' uneditable-input' : ''}"  style="" maxlength="50" path="entitat.versio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ICONID)}">
        <tr id="entitat_iconID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ICONID])?'entitat.iconID':__theForm.labels[EntitatFields.ICONID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.ICONID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ICONID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.iconID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.ICONID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(iconID.iconID)}"/>">${iconID.iconID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.ICONID)}" >
              <div class="input-group">
                <div class="custom-file col-md-8">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.ICONID)? 'true' : 'false'}" cssClass="custom-file-input form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.ICONID)? ' uneditable-input' : ''}"   path="iconID" type="file" />
                  <label class="custom-file-label" for="iconID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.icon}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${car:fileUrl(__theForm.entitat.icon)}"/>">${__theForm.entitat.icon.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="iconID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
                    $('#iconID').on('change', function(){
                        var ruta = $('#iconID').val(); 
                        var rutaArray = ruta.split('\\');
                        $('#iconID-custom-file-label').css('display','block');
                        $('#iconID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
                    });
                </script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.WEBENTITAT)}">
        <tr id="entitat_webEntitat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.WEBENTITAT])?'entitat.webEntitat':__theForm.labels[EntitatFields.WEBENTITAT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.WEBENTITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.WEBENTITAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
           <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.WEBENTITAT)}">

             <c:if test="${ not empty __theForm.entitat.webEntitat}">
               <a href="${__theForm.entitat.webEntitat}" target="_blank">${__theForm.entitat.webEntitat}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,EntitatFields.WEBENTITAT))}">

            <form:errors path="entitat.webEntitat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.WEBENTITAT)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.WEBENTITAT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.webEntitat"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.ENTITATDESCFRONT)}">
        <tr id="entitat_entitatDescFront_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.ENTITATDESCFRONT])?'entitat.entitatDescFront':__theForm.labels[EntitatFields.ENTITATDESCFRONT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatFields.ENTITATDESCFRONT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.ENTITATDESCFRONT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitat.entitatDescFront" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;max-width:90%;" cssClass="form-control " readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.ENTITATDESCFRONT)? 'true' : 'false'}" path="entitat.entitatDescFront"  />
      <div id="dropdownMenuButton_entitatDescFront" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_entitatDescFront" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.entitatDescFront'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('entitat.entitatDescFront'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('entitat.entitatDescFront'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">			
	                $('#dropdownMenuButton_entitatDescFront').on('click', function(){
					var valor = ($('#dropdownMenuContainer_entitatDescFront').css('display') != 'none') ? 'none' : 'block';
                    $('#dropdownMenuContainer_entitatDescFront').css('display', valor);
                    return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTWEB)}">
        <tr id="entitat_suportWeb_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTWEB])?'entitat.suportWeb':__theForm.labels[EntitatFields.SUPORTWEB]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTWEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
           <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTWEB)}">

             <c:if test="${ not empty __theForm.entitat.suportWeb}">
               <a href="${__theForm.entitat.suportWeb}" target="_blank">${__theForm.entitat.suportWeb}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTWEB))}">

            <form:errors path="entitat.suportWeb" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTWEB)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTWEB)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.suportWeb"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTTELEFON)}">
        <tr id="entitat_suportTelefon_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTTELEFON])?'entitat.suportTelefon':__theForm.labels[EntitatFields.SUPORTTELEFON]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTTELEFON]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTTELEFON]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.suportTelefon" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTTELEFON)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTTELEFON)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.suportTelefon"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTEMAIL)}">
        <tr id="entitat_suportEmail_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTEMAIL])?'entitat.suportEmail':__theForm.labels[EntitatFields.SUPORTEMAIL]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTEMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTEMAIL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.suportEmail" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTEMAIL)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTEMAIL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.suportEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTFAQ)}">
        <tr id="entitat_suportFAQ_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTFAQ])?'entitat.suportFAQ':__theForm.labels[EntitatFields.SUPORTFAQ]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTFAQ]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTFAQ]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.suportFAQ" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTFAQ)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTFAQ)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.suportFAQ"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTQSSI)}">
        <tr id="entitat_suportqssi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTQSSI])?'entitat.suportqssi':__theForm.labels[EntitatFields.SUPORTQSSI]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTQSSI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTQSSI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.suportqssi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTQSSI)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTQSSI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.suportqssi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.SUPORTAUTENTICACIO)}">
        <tr id="entitat_suportautenticacio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.SUPORTAUTENTICACIO])?'entitat.suportautenticacio':__theForm.labels[EntitatFields.SUPORTAUTENTICACIO]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.SUPORTAUTENTICACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.SUPORTAUTENTICACIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitat.suportautenticacio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTAUTENTICACIO)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.SUPORTAUTENTICACIO)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.suportautenticacio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.PLUGINLOGINID)}">
        <tr id="entitat_pluginLoginID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.PLUGINLOGINID])?'entitat.pluginLoginID':__theForm.labels[EntitatFields.PLUGINLOGINID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.PLUGINLOGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.PLUGINLOGINID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="entitat.pluginLoginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.PLUGINLOGINID)}" >
          <form:hidden path="entitat.pluginLoginID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.entitat.pluginLoginID,__theForm.listOfPluginForPluginLoginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.PLUGINLOGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entitat_pluginLoginID"  onchange="if(typeof onChangePluginLoginID == 'function') {  onChangePluginLoginID(this); };"  cssClass="form-control col-md-8" path="entitat.pluginLoginID">
            <c:forEach items="${__theForm.listOfPluginForPluginLoginID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
            <form:option value="" ></form:option>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatFields.LOGINTEXTID)}">
        <tr id="entitat_loginTextID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatFields.LOGINTEXTID])?'entitat.loginTextID':__theForm.labels[EntitatFields.LOGINTEXTID]}" />
              <c:if test="${not empty __theForm.help[EntitatFields.LOGINTEXTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntitatFields.LOGINTEXTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="entitat.loginText" cssClass="errorField alert alert-danger" />
       <div class="row-fluid  col-md-8">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_loginText_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_loginText_${idioma.idiomaID}">
               <form:errors path="entitat.loginText.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="entitat.loginText.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.LOGINTEXTID)? ' uneditable-input' : ''}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

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
              <form:errors path="entitat.fitxerCssID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatFields.FITXERCSSID)}" >
              <a target="_blank" href="<c:url value="${car:fileUrl(fitxerCssID.fitxerCssID)}"/>">${fitxerCssID.fitxerCssID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatFields.FITXERCSSID)}" >
              <div class="input-group">
                <div class="custom-file col-md-8">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.FITXERCSSID)? 'true' : 'false'}" cssClass="custom-file-input form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.FITXERCSSID)? ' uneditable-input' : ''}"   path="fitxerCssID" type="file" />
                  <label class="custom-file-label" for="fitxerCssID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.entitat.fitxerCss}">
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
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerCssID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
                    $('#fitxerCssID').on('change', function(){
                        var ruta = $('#fitxerCssID').val(); 
                        var rutaArray = ruta.split('\\');
                        $('#fitxerCssID-custom-file-label').css('display','block');
                        $('#fitxerCssID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
                    });
                </script>                </c:otherwise>
                </c:choose>
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
            <form:errors path="entitat.context" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.CONTEXT)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.CONTEXT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.context"   />

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
            <form:errors path="entitat.commit" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatFields.COMMIT)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EntitatFields.COMMIT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entitat.commit"   />

           </td>
        </tr>
        </c:if>
        
