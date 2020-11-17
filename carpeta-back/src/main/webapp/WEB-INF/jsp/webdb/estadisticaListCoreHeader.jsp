<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstadisticaFields" className="es.caib.carpeta.model.fields.EstadisticaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.ESTADISTICAID)}">
        <th>${car:getSortIcons(__theFilterForm,EstadisticaFields.ESTADISTICAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.TIPUS)}">
        <th>${car:getSortIcons(__theFilterForm,EstadisticaFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.DATAESTADISTICA)}">
        <th>${car:getSortIcons(__theFilterForm,EstadisticaFields.DATAESTADISTICA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.COMPTADOR)}">
        <th>${car:getSortIcons(__theFilterForm,EstadisticaFields.COMPTADOR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.PLUGINID)}">
        <th>${car:getSortIcons(__theFilterForm,EstadisticaFields.PLUGINID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EstadisticaFields.ENTITATID)}">
        <th>${car:getSortIcons(__theFilterForm,EstadisticaFields.ENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

