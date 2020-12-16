<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AccesFields" className="es.caib.carpeta.model.fields.AccesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.NOM)}">
        <tr id="acces_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.NOM])?'acces.nom':__theForm.labels[AccesFields.NOM]}" />
              <c:if test="${not empty __theForm.help[AccesFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.NOM)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.NOM)? ' uneditable-input' : ''}"  maxlength="255" path="acces.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.LLINATGES)}">
        <tr id="acces_llinatges_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.LLINATGES])?'acces.llinatges':__theForm.labels[AccesFields.LLINATGES]}" />
              <c:if test="${not empty __theForm.help[AccesFields.LLINATGES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.LLINATGES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.llinatges" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.LLINATGES)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.LLINATGES)? ' uneditable-input' : ''}"  maxlength="255" path="acces.llinatges"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.NIF)}">
        <tr id="acces_nif_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.NIF])?'acces.nif':__theForm.labels[AccesFields.NIF]}" />
              <c:if test="${not empty __theForm.help[AccesFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.NIF]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.NIF)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.NIF)? ' uneditable-input' : ''}"  maxlength="50" path="acces.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.IP)}">
        <tr id="acces_ip_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.IP])?'acces.ip':__theForm.labels[AccesFields.IP]}" />
              <c:if test="${not empty __theForm.help[AccesFields.IP]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.IP]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.ip" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.IP)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.IP)? ' uneditable-input' : ''}"  maxlength="100" path="acces.ip"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.PROVEIDORIDENTITAT)}">
        <tr id="acces_proveidorIdentitat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.PROVEIDORIDENTITAT])?'acces.proveidorIdentitat':__theForm.labels[AccesFields.PROVEIDORIDENTITAT]}" />
              <c:if test="${not empty __theForm.help[AccesFields.PROVEIDORIDENTITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.PROVEIDORIDENTITAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.proveidorIdentitat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.PROVEIDORIDENTITAT)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.PROVEIDORIDENTITAT)? ' uneditable-input' : ''}"  maxlength="255" path="acces.proveidorIdentitat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.NIVELLSEGURETAT)}">
        <tr id="acces_nivellSeguretat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.NIVELLSEGURETAT])?'acces.nivellSeguretat':__theForm.labels[AccesFields.NIVELLSEGURETAT]}" />
              <c:if test="${not empty __theForm.help[AccesFields.NIVELLSEGURETAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.NIVELLSEGURETAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.nivellSeguretat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.NIVELLSEGURETAT)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.NIVELLSEGURETAT)? ' uneditable-input' : ''}"  maxlength="255" path="acces.nivellSeguretat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.RESULTATAUTENTICACIO)}">
        <tr id="acces_resultatAutenticacio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.RESULTATAUTENTICACIO])?'acces.resultatAutenticacio':__theForm.labels[AccesFields.RESULTATAUTENTICACIO]}" />
              <c:if test="${not empty __theForm.help[AccesFields.RESULTATAUTENTICACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.RESULTATAUTENTICACIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.resultatAutenticacio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.RESULTATAUTENTICACIO)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.RESULTATAUTENTICACIO)? ' uneditable-input' : ''}"   path="acces.resultatAutenticacio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.DATADARRERACCES)}">
        <tr id="acces_dataDarrerAcces_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.DATADARRERACCES])?'acces.dataDarrerAcces':__theForm.labels[AccesFields.DATADARRERACCES]}" />
              <c:if test="${not empty __theForm.help[AccesFields.DATADARRERACCES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.DATADARRERACCES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="acces.dataDarrerAcces" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="acces_dataDarrerAcces" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.DATADARRERACCES)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#acces_dataDarrerAcces" path="acces.dataDarrerAcces" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.DATADARRERACCES)}" >
                    <div class="input-group-append"  data-target="#acces_dataDarrerAcces"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#acces_dataDarrerAcces').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
          </script>        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.IDIOMA)}">
        <tr id="acces_idioma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.IDIOMA])?'acces.idioma':__theForm.labels[AccesFields.IDIOMA]}" />
              <c:if test="${not empty __theForm.help[AccesFields.IDIOMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.IDIOMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="acces.idioma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AccesFields.IDIOMA)? 'true' : 'false'}" cssClass="col-md-6 form-control ${gen:contains(__theForm.readOnlyFields ,AccesFields.IDIOMA)? ' uneditable-input' : ''}"  maxlength="50" path="acces.idioma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.ENTITATID)}">
        <tr id="acces_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.ENTITATID])?'acces.entitatID':__theForm.labels[AccesFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AccesFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="acces.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AccesFields.ENTITATID)}" >
          <form:hidden path="acces.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.acces.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.ENTITATID)}" >
          <form:select id="acces_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-4" path="acces.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AccesFields.TIPUS)}">
        <tr id="acces_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AccesFields.TIPUS])?'acces.tipus':__theForm.labels[AccesFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AccesFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AccesFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="acces.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AccesFields.TIPUS)}" >
          <form:hidden path="acces.tipus"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.acces.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AccesFields.TIPUS)}" >
          <form:select id="acces_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-4" path="acces.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
