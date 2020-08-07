<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LogCarpetaFields" className="es.caib.carpeta.model.fields.LogCarpetaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.LOGID)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.LOGID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.DESCRIPCIO)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.ENTITATID)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.PLUGINID)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.PLUGINID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.TIPUS)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.ESTAT)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.ESTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.TEMPS)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.TEMPS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.DATAINICI)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.PETICIO)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.PETICIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.ERROR)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.ERROR)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,LogCarpetaFields.EXCEPCIO)}">
        <th>${car:getSortIcons(__theFilterForm,LogCarpetaFields.EXCEPCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

