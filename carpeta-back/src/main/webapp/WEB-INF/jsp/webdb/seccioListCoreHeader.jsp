<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SeccioFields" className="es.caib.carpeta.model.fields.SeccioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SeccioFields.SECCIOID)}">
        <th>${car:getSortIcons(__theFilterForm,SeccioFields.SECCIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SeccioFields.NOMID)}">
        <th>${car:getSortIcons(__theFilterForm,SeccioFields.NOMID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SeccioFields.DESCRIPCIOID)}">
        <th>${car:getSortIcons(__theFilterForm,SeccioFields.DESCRIPCIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SeccioFields.ACTIVA)}">
        <th>${car:getSortIcons(__theFilterForm,SeccioFields.ACTIVA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SeccioFields.ICONAID)}">
        <th>${car:getSortIcons(__theFilterForm,SeccioFields.ICONAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SeccioFields.SECCIOPAREID)}">
        <th>${car:getSortIcons(__theFilterForm,SeccioFields.SECCIOPAREID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

