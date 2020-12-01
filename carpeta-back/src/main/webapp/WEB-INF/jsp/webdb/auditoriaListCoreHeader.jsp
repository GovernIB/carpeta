<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AuditoriaFields" className="es.caib.carpeta.model.fields.AuditoriaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AuditoriaFields.AUDITORIAID)}">
        <th>${car:getSortIcons(__theFilterForm,AuditoriaFields.AUDITORIAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AuditoriaFields.DATAAUDIT)}">
        <th>${car:getSortIcons(__theFilterForm,AuditoriaFields.DATAAUDIT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AuditoriaFields.TIPUS)}">
        <th>${car:getSortIcons(__theFilterForm,AuditoriaFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AuditoriaFields.USERNAME)}">
        <th>${car:getSortIcons(__theFilterForm,AuditoriaFields.USERNAME)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AuditoriaFields.USUARICLAVE)}">
        <th>${car:getSortIcons(__theFilterForm,AuditoriaFields.USUARICLAVE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AuditoriaFields.ENTITATID)}">
        <th>${car:getSortIcons(__theFilterForm,AuditoriaFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AuditoriaFields.PLUGINID)}">
        <th>${car:getSortIcons(__theFilterForm,AuditoriaFields.PLUGINID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

