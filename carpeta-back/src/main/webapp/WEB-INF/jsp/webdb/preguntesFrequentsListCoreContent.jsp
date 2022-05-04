<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PreguntesFrequentsFields" className="es.caib.carpeta.model.fields.PreguntesFrequentsFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[preguntesFrequents.preguntesFrequentsID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.PREGUNTESFREQUENTSID)}">
          <td>
          ${preguntesFrequents.preguntesFrequentsID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.ENUNCIATID)}">
          <td>
          <c:set var="tmp">${preguntesFrequents.enunciatID}</c:set>
          <c:if test="${not empty tmp}">
          ${preguntesFrequents.enunciat.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.RESPOSTAID)}">
          <td>
          <c:set var="tmp">${preguntesFrequents.respostaID}</c:set>
          <c:if test="${not empty tmp}">
          ${preguntesFrequents.resposta.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.ORDRE)}">
          <td>
          ${preguntesFrequents.ordre}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.ENTITATID)}">
          <td>
          <c:set var="tmp">${preguntesFrequents.entitatID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfEntitatForEntitatID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.FITXER1ID)}">
          <td>
            <c:if test="${not empty preguntesFrequents.fitxer1}">
              <a target="_blank" href="<c:url value="${car:fileUrl(preguntesFrequents.fitxer1)}"/>">${preguntesFrequents.fitxer1.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.FITXER2ID)}">
          <td>
            <c:if test="${not empty preguntesFrequents.fitxer2}">
              <a target="_blank" href="<c:url value="${car:fileUrl(preguntesFrequents.fitxer2)}"/>">${preguntesFrequents.fitxer2.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.FITXER3ID)}">
          <td>
            <c:if test="${not empty preguntesFrequents.fitxer3}">
              <a target="_blank" href="<c:url value="${car:fileUrl(preguntesFrequents.fitxer3)}"/>">${preguntesFrequents.fitxer3.nom}</a>
            </c:if>
           </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[preguntesFrequents.preguntesFrequentsID]}" />
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


