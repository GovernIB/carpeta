<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AccesFields" className="es.caib.carpeta.model.fields.AccesFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.ACCESID)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.ACCESID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.NOM)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.LLINATGES)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.LLINATGES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.NIF)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.IP)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.IP)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.PROVEIDORIDENTITAT)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.PROVEIDORIDENTITAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.NIVELLSEGURETAT)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.NIVELLSEGURETAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.RESULTATAUTENTICACIO)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.RESULTATAUTENTICACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.DATADARRERACCES)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.DATADARRERACCES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.IDIOMA)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.IDIOMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.ENTITATID)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,AccesFields.TIPUS)}">
        <th>${car:getSortIcons(__theFilterForm,AccesFields.TIPUS)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

