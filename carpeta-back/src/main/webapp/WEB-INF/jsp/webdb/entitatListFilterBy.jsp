<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.carpeta.model.fields.EntitatFields"/>

  <%-- HIDDEN PARAMS: FILTER BY --%> 
  <form:hidden path="visibleFilterBy"/>

  <%-- FILTRAR PER - INICI --%>
  
  <c:set var="displayFilterDiv" value="${__theFilterForm.visibleFilterBy?'':'display:none;'}" />  
  
  <div id="FilterDiv" class="well formbox" style="${displayFilterDiv} margin-bottom:3px; margin-left: 1px; padding:3px;">

      <div class="page-header">
        <fmt:message key="genapp.form.filterby"/>
        
        <div class="float-right">

           <a class="float-right" style="margin-left:10px" href="#"> <i title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline';" class="fas fa-trash"></i></a>
           <input style="margin-left: 3px" class="btn btn-warning float-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-warning float-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-primary float-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key < 0 && not empty __entry.value.searchBy }">
      <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ENTITATID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.entitatID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="entitatIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="entitatIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.NOMID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.nomID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="nomIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="nomIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.CODI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.codi" var="codi" />
              <fmt:message key="genapp.form.searchby" var="cercapercodi" >                
                 <fmt:param value="${codi}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codi}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodi}" path="codi" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.CODIDIR3)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.codiDir3" var="codiDir3" />
              <fmt:message key="genapp.form.searchby" var="cercapercodiDir3" >                
                 <fmt:param value="${codiDir3}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${codiDir3}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercodiDir3}" path="codiDir3" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ACTIVA)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.activa" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="activaDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="activaFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.COLORMENU)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.colorMenu" var="colorMenu" />
              <fmt:message key="genapp.form.searchby" var="cercapercolorMenu" >                
                 <fmt:param value="${colorMenu}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${colorMenu}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercolorMenu}" path="colorMenu" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.VERSIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.versio" var="versio" />
              <fmt:message key="genapp.form.searchby" var="cercaperversio" >                
                 <fmt:param value="${versio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${versio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperversio}" path="versio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.WEBENTITAT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.webEntitat" var="webEntitat" />
              <fmt:message key="genapp.form.searchby" var="cercaperwebEntitat" >                
                 <fmt:param value="${webEntitat}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${webEntitat}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperwebEntitat}" path="webEntitat" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.ENTITATDESCFRONT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.entitatDescFront" var="entitatDescFront" />
              <fmt:message key="genapp.form.searchby" var="cercaperentitatDescFront" >                
                 <fmt:param value="${entitatDescFront}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${entitatDescFront}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperentitatDescFront}" path="entitatDescFront" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTWEB)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.suportWeb" var="suportWeb" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportWeb" >                
                 <fmt:param value="${suportWeb}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportWeb}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportWeb}" path="suportWeb" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTTELEFON)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.suportTelefon" var="suportTelefon" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportTelefon" >                
                 <fmt:param value="${suportTelefon}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportTelefon}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportTelefon}" path="suportTelefon" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTEMAIL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.suportEmail" var="suportEmail" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportEmail" >                
                 <fmt:param value="${suportEmail}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportEmail}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportEmail}" path="suportEmail" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTFAQ)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.suportFAQ" var="suportFAQ" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportFAQ" >                
                 <fmt:param value="${suportFAQ}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportFAQ}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportFAQ}" path="suportFAQ" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTQSSI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.suportqssi" var="suportqssi" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportqssi" >                
                 <fmt:param value="${suportqssi}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportqssi}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportqssi}" path="suportqssi" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.SUPORTAUTENTICACIO)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.suportautenticacio" var="suportautenticacio" />
              <fmt:message key="genapp.form.searchby" var="cercapersuportautenticacio" >                
                 <fmt:param value="${suportautenticacio}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${suportautenticacio}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapersuportautenticacio}" path="suportautenticacio" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.PLUGINLOGINID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.pluginLoginID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="pluginLoginIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="pluginLoginIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.LOGINTEXTID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="entitat.loginTextID" />:</span>

              <span class="add-on"><fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="loginTextIDDesde" />


              <span class="add-on"><fmt:message key="genapp.to" /></span>

              <form:input cssClass="input-append input-small search-query" path="loginTextIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.CONTEXT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.context" var="context" />
              <fmt:message key="genapp.form.searchby" var="cercapercontext" >                
                 <fmt:param value="${context}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${context}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercontext}" path="context" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,EntitatFields.COMMIT)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="entitat.commit" var="commit" />
              <fmt:message key="genapp.form.searchby" var="cercapercommit" >                
                 <fmt:param value="${commit}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${commit}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapercommit}" path="commit" />
            </div>


        </c:if>

      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-prepend input-append" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
