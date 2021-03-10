<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariFields" className="es.caib.carpeta.model.fields.UsuariFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.USERNAME)}">
        <tr id="usuari_username_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.USERNAME])?'usuari.username':__theForm.labels[UsuariFields.USERNAME]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariFields.USERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.USERNAME]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuari.username" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.USERNAME)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,UsuariFields.USERNAME)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.username"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.NOM)}">
        <tr id="usuari_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.NOM])?'usuari.nom':__theForm.labels[UsuariFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuari.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.NOM)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,UsuariFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.LLINATGE1)}">
        <tr id="usuari_llinatge1_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.LLINATGE1])?'usuari.llinatge1':__theForm.labels[UsuariFields.LLINATGE1]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.LLINATGE1]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuari.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE1)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.LLINATGE2)}">
        <tr id="usuari_llinatge2_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.LLINATGE2])?'usuari.llinatge2':__theForm.labels[UsuariFields.LLINATGE2]}" />
              <c:if test="${not empty __theForm.help[UsuariFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.LLINATGE2]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuari.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE2)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.EMAIL)}">
        <tr id="usuari_email_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.EMAIL])?'usuari.email':__theForm.labels[UsuariFields.EMAIL]}" />
              <c:if test="${not empty __theForm.help[UsuariFields.EMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.EMAIL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuari.email" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.EMAIL)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,UsuariFields.EMAIL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.email"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.NIF)}">
        <tr id="usuari_nif_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.NIF])?'usuari.nif':__theForm.labels[UsuariFields.NIF]}" />
              <c:if test="${not empty __theForm.help[UsuariFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.NIF]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuari.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.NIF)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,UsuariFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="255" path="usuari.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.DARRERAENTITAT)}">
        <tr id="usuari_darreraEntitat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.DARRERAENTITAT])?'usuari.darreraEntitat':__theForm.labels[UsuariFields.DARRERAENTITAT]}" />
              <c:if test="${not empty __theForm.help[UsuariFields.DARRERAENTITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.DARRERAENTITAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuari.darreraEntitat" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariFields.DARRERAENTITAT)}" >
          <form:hidden path="usuari.darreraEntitat"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuari.darreraEntitat,__theForm.listOfEntitatForDarreraEntitat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariFields.DARRERAENTITAT)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuari_darreraEntitat"  onchange="if(typeof onChangeDarreraEntitat == 'function') {  onChangeDarreraEntitat(this); };"  cssClass="form-control col-md-8" path="usuari.darreraEntitat">
            <c:forEach items="${__theForm.listOfEntitatForDarreraEntitat}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.usuari.darreraEntitat }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.usuari.darreraEntitat }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.IDIOMAID)}">
        <tr id="usuari_idiomaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.IDIOMAID])?'usuari.idiomaID':__theForm.labels[UsuariFields.IDIOMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariFields.IDIOMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.IDIOMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuari.idiomaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariFields.IDIOMAID)}" >
          <form:hidden path="usuari.idiomaID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuari.idiomaID,__theForm.listOfIdiomaForIdiomaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariFields.IDIOMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuari_idiomaID"  onchange="if(typeof onChangeIdiomaID == 'function') {  onChangeIdiomaID(this); };"  cssClass="form-control col-md-8" path="usuari.idiomaID">
            <c:forEach items="${__theForm.listOfIdiomaForIdiomaID}" var="tmp">
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
        
