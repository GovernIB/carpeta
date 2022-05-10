<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="NotificacioAppFields" className="es.caib.carpeta.model.fields.NotificacioAppFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioAppFields.NOTIFICACIOAPPID)}">
        <th>${car:getSortIcons(__theFilterForm,NotificacioAppFields.NOTIFICACIOAPPID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioAppFields.CODI)}">
        <th>${car:getSortIcons(__theFilterForm,NotificacioAppFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioAppFields.TITOLID)}">
        <th>${car:getSortIcons(__theFilterForm,NotificacioAppFields.TITOLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioAppFields.MISSATGEID)}">
        <th>${car:getSortIcons(__theFilterForm,NotificacioAppFields.MISSATGEID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioAppFields.FRONTPLUGINID)}">
        <th>${car:getSortIcons(__theFilterForm,NotificacioAppFields.FRONTPLUGINID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioAppFields.AJUDA)}">
        <th>${car:getSortIcons(__theFilterForm,NotificacioAppFields.AJUDA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioAppFields.ACTIVA)}">
        <th>${car:getSortIcons(__theFilterForm,NotificacioAppFields.ACTIVA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,NotificacioAppFields.ENTITATID)}">
        <th>${car:getSortIcons(__theFilterForm,NotificacioAppFields.ENTITATID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

