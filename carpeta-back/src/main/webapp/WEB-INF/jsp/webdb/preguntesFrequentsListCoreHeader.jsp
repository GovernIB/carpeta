<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PreguntesFrequentsFields" className="es.caib.carpeta.model.fields.PreguntesFrequentsFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.PREGUNTESFREQUENTSID)}">
        <th>${car:getSortIcons(__theFilterForm,PreguntesFrequentsFields.PREGUNTESFREQUENTSID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.ENUNCIATID)}">
        <th>${car:getSortIcons(__theFilterForm,PreguntesFrequentsFields.ENUNCIATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.RESPOSTAID)}">
        <th>${car:getSortIcons(__theFilterForm,PreguntesFrequentsFields.RESPOSTAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.ORDRE)}">
        <th>${car:getSortIcons(__theFilterForm,PreguntesFrequentsFields.ORDRE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PreguntesFrequentsFields.ENTITATID)}">
        <th>${car:getSortIcons(__theFilterForm,PreguntesFrequentsFields.ENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

