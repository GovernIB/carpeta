<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CiutadaFields" className="es.caib.carpeta.model.fields.CiutadaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.NIF)}">
        <tr id="ciutada_nif_rowid">
          <td id="ciutada_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.NIF])?'ciutada.nif':__theForm.labels[CiutadaFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_nif_columnvalueid">
            <form:errors path="ciutada.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.NIF)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CiutadaFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="100" path="ciutada.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.LLINATGE1)}">
        <tr id="ciutada_llinatge1_rowid">
          <td id="ciutada_llinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.LLINATGE1])?'ciutada.llinatge1':__theForm.labels[CiutadaFields.LLINATGE1]}" />
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.LLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_llinatge1_columnvalueid">
            <form:errors path="ciutada.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.LLINATGE1)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CiutadaFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="255" path="ciutada.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.LLINATGE2)}">
        <tr id="ciutada_llinatge2_rowid">
          <td id="ciutada_llinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.LLINATGE2])?'ciutada.llinatge2':__theForm.labels[CiutadaFields.LLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.LLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_llinatge2_columnvalueid">
            <form:errors path="ciutada.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.LLINATGE2)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CiutadaFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="255" path="ciutada.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.NOM)}">
        <tr id="ciutada_nom_rowid">
          <td id="ciutada_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.NOM])?'ciutada.nom':__theForm.labels[CiutadaFields.NOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_nom_columnvalueid">
            <form:errors path="ciutada.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CiutadaFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="ciutada.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.EMPRESA)}">
        <tr id="ciutada_empresa_rowid">
          <td id="ciutada_empresa_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.EMPRESA])?'ciutada.empresa':__theForm.labels[CiutadaFields.EMPRESA]}" />
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.EMPRESA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.EMPRESA]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_empresa_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CiutadaFields.EMPRESA)}" >
              <form:errors path="ciutada.empresa" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.EMPRESA)? 'false' : 'true'}" path="ciutada.empresa" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CiutadaFields.EMPRESA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.ciutada.empresa}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.REPRESENTANTNIF)}">
        <tr id="ciutada_representantNif_rowid">
          <td id="ciutada_representantNif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.REPRESENTANTNIF])?'ciutada.representantNif':__theForm.labels[CiutadaFields.REPRESENTANTNIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.REPRESENTANTNIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.REPRESENTANTNIF]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_representantNif_columnvalueid">
            <form:errors path="ciutada.representantNif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.REPRESENTANTNIF)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CiutadaFields.REPRESENTANTNIF)? ' uneditable-input' : ''}"  style="" maxlength="100" path="ciutada.representantNif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.REPRESENTANTLLINATGE1)}">
        <tr id="ciutada_representantLlinatge1_rowid">
          <td id="ciutada_representantLlinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.REPRESENTANTLLINATGE1])?'ciutada.representantLlinatge1':__theForm.labels[CiutadaFields.REPRESENTANTLLINATGE1]}" />
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.REPRESENTANTLLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.REPRESENTANTLLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_representantLlinatge1_columnvalueid">
            <form:errors path="ciutada.representantLlinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.REPRESENTANTLLINATGE1)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CiutadaFields.REPRESENTANTLLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="255" path="ciutada.representantLlinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.REPRESENTANTLLINATGE2)}">
        <tr id="ciutada_representantLlinatge2_rowid">
          <td id="ciutada_representantLlinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.REPRESENTANTLLINATGE2])?'ciutada.representantLlinatge2':__theForm.labels[CiutadaFields.REPRESENTANTLLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.REPRESENTANTLLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.REPRESENTANTLLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_representantLlinatge2_columnvalueid">
            <form:errors path="ciutada.representantLlinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.REPRESENTANTLLINATGE2)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CiutadaFields.REPRESENTANTLLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="255" path="ciutada.representantLlinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.REPRESENTANTNOM)}">
        <tr id="ciutada_representantNom_rowid">
          <td id="ciutada_representantNom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.REPRESENTANTNOM])?'ciutada.representantNom':__theForm.labels[CiutadaFields.REPRESENTANTNOM]}" />
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.REPRESENTANTNOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.REPRESENTANTNOM]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_representantNom_columnvalueid">
            <form:errors path="ciutada.representantNom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.REPRESENTANTNOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CiutadaFields.REPRESENTANTNOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="ciutada.representantNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.DATACREACIO)}">
        <tr id="ciutada_dataCreacio_rowid">
          <td id="ciutada_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.DATACREACIO])?'ciutada.dataCreacio':__theForm.labels[CiutadaFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_dataCreacio_columnvalueid">
    <form:errors path="ciutada.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="ciutada_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#ciutada_dataCreacio" path="ciutada.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,CiutadaFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#ciutada_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#ciutada_dataCreacio').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CiutadaFields.MOBILEID)}">
        <tr id="ciutada_mobileId_rowid">
          <td id="ciutada_mobileId_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CiutadaFields.MOBILEID])?'ciutada.mobileId':__theForm.labels[CiutadaFields.MOBILEID]}" />
             </label>
              <c:if test="${not empty __theForm.help[CiutadaFields.MOBILEID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CiutadaFields.MOBILEID]}" ></i>
              </c:if>
            </td>
          <td id="ciutada_mobileId_columnvalueid">
              <form:errors path="ciutada.mobileId" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,CiutadaFields.MOBILEID)? 'true' : 'false'}" path="ciutada.mobileId"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_mobileId" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_mobileId" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('ciutada.mobileId'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('ciutada.mobileId'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('ciutada.mobileId'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_mobileId').on('click', function(){
					var valor = ($('#dropdownMenuContainer_mobileId').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_mobileId').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
