<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.carpeta.model.fields.EntitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ENTITATID)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.NOMID)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.NOMID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.DESCRIPCIOID)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.DESCRIPCIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CODI)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.CODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CODIDIR3)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.CODIDIR3)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ACTIVA)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.ACTIVA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.COLORMENU)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.COLORMENU)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOCAPBACKID)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.LOGOCAPBACKID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOPEUBACKID)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.LOGOPEUBACKID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOLATERALFRONTID)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.LOGOLATERALFRONTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.VERSIO)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.VERSIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ICONID)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.ICONID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.WEBENTITAT)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.WEBENTITAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ENTITATDESCFRONT)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.ENTITATDESCFRONT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTWEB)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.SUPORTWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTTELEFON)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.SUPORTTELEFON)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTEMAIL)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.SUPORTEMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTFAQ)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.SUPORTFAQ)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTQSSI)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.SUPORTQSSI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTAUTENTICACIO)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.SUPORTAUTENTICACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINLOGINID)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.PLUGINLOGINID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGINTEXTID)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.LOGINTEXTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FITXERCSSID)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.FITXERCSSID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CONTEXT)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.CONTEXT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.COMMIT)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.COMMIT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.AVISLEGAL)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.AVISLEGAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ACCESSIBILITAT)}">
        <th>${car:getSortIcons(__theFilterForm,EntitatFields.ACCESSIBILITAT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${car:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

