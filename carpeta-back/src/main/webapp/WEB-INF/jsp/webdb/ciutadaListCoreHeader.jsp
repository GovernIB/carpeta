<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CiutadaFields" className="es.caib.carpeta.model.fields.CiutadaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.CIUTADAID)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.CIUTADAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.NIF)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.LLINATGE1)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.LLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.LLINATGE2)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.LLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.NOM)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.EMPRESA)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.EMPRESA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.REPRESENTANTNIF)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.REPRESENTANTNIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.REPRESENTANTLLINATGE1)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.REPRESENTANTLLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.REPRESENTANTLLINATGE2)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.REPRESENTANTLLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.REPRESENTANTNOM)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.REPRESENTANTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.DATACREACIO)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CiutadaFields.MOBILEID)}">
        <th>${car:getSortIcons(__theFilterForm,CiutadaFields.MOBILEID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

