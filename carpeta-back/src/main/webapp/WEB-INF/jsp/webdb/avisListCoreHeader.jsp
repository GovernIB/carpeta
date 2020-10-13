<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AvisFields" className="es.caib.carpeta.model.fields.AvisFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AvisFields.AVISID)}">
        <th>${car:getSortIcons(__theFilterForm,AvisFields.AVISID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AvisFields.DESCRIPCIOID)}">
        <th>${car:getSortIcons(__theFilterForm,AvisFields.DESCRIPCIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AvisFields.ENTITATID)}">
        <th>${car:getSortIcons(__theFilterForm,AvisFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AvisFields.DATAINICI)}">
        <th>${car:getSortIcons(__theFilterForm,AvisFields.DATAINICI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AvisFields.DATAFI)}">
        <th>${car:getSortIcons(__theFilterForm,AvisFields.DATAFI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AvisFields.TIPUS)}">
        <th>${car:getSortIcons(__theFilterForm,AvisFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AvisFields.GRAVETAT)}">
        <th>${car:getSortIcons(__theFilterForm,AvisFields.GRAVETAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AvisFields.PLUGINFRONTID)}">
        <th>${car:getSortIcons(__theFilterForm,AvisFields.PLUGINFRONTID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

