<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AccesFields" className="es.caib.carpeta.model.fields.AccesFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[acces.accesID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.ACCESID)}">
          <td>
          ${acces.accesID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.NOM)}">
          <td>
          ${acces.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.LLINATGES)}">
          <td>
          ${acces.llinatges}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.NIF)}">
          <td>
          ${acces.nif}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.IP)}">
          <td>
          ${acces.ip}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.PROVEIDORIDENTITAT)}">
          <td>
          ${acces.proveidorIdentitat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.NIVELLSEGURETAT)}">
          <td>
          ${acces.nivellSeguretat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.RESULTATAUTENTICACIO)}">
          <td>
          ${acces.resultatAutenticacio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.ENTITATID)}">
          <td>
          <c:set var="tmp">${acces.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.TIPUS)}">
          <td>
          <c:set var="tmp">${acces.tipus}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipus[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.DATADARRERACCES)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${acces.dataDarrerAcces}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.IDIOMA)}">
          <td>
          ${acces.idioma}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[acces.accesID]}" />
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


