<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EnllazFields" className="es.caib.carpeta.model.fields.EnllazFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.ENLLAZID)}">
        <th>${car:getSortIcons(__theFilterForm,EnllazFields.ENLLAZID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.TIPUS)}">
        <th>${car:getSortIcons(__theFilterForm,EnllazFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.NOMID)}">
        <th>${car:getSortIcons(__theFilterForm,EnllazFields.NOMID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.URLID)}">
        <th>${car:getSortIcons(__theFilterForm,EnllazFields.URLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.ENTITATID)}">
        <th>${car:getSortIcons(__theFilterForm,EnllazFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.LOGOID)}">
        <th>${car:getSortIcons(__theFilterForm,EnllazFields.LOGOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EnllazFields.SECCIOID)}">
        <th>${car:getSortIcons(__theFilterForm,EnllazFields.SECCIOID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

