<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EnllazFields" className="es.caib.carpeta.model.fields.EnllazFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[enllaz.enllazID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.ENLLAZID)}">
          <td>
          ${enllaz.enllazID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.TIPUS)}">
          <td>
          <c:set var="tmp">${enllaz.tipus}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipus[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.NOMID)}">
          <td>
          <c:set var="tmp">${enllaz.nomID}</c:set>
          <c:if test="${not empty tmp}">
          ${enllaz.nom.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.DESCRIPCIOID)}">
          <td>
          <c:set var="tmp">${enllaz.descripcioID}</c:set>
          <c:if test="${not empty tmp}">
          ${enllaz.descripcio.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.URLID)}">
          <td>
          <c:set var="tmp">${enllaz.urlID}</c:set>
          <c:if test="${not empty tmp}">
          ${enllaz.url.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.ENTITATID)}">
          <td>
          <c:set var="tmp">${enllaz.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.LOGOID)}">
          <td>
            <c:if test="${not empty enllaz.logo}">
              <a target="_blank" href="<c:url value="${car:fileUrl(enllaz.logo)}"/>">${enllaz.logo.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.SECCIOID)}">
          <td>
          <c:set var="tmp">${enllaz.seccioID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfSeccioForSeccioID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.ACTIU)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${enllaz.actiu?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.ORDRE)}">
          <td>
          ${enllaz.ordre}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[enllaz.enllazID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


