<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.carpeta.model.fields.EntitatFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[entitat.entitatID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ENTITATID)}">
          <td>
          ${entitat.entitatID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.NOMID)}">
          <td>
          <c:set var="tmp">${entitat.nomID}</c:set>
          <c:if test="${not empty tmp}">
          ${entitat.nom.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CODI)}">
          <td>
          ${entitat.codi}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CODIDIR3)}">
          <td>
          ${entitat.codiDir3}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ACTIVA)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${entitat.activa?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.COLORMENU)}">
          <td>
          ${entitat.colorMenu}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOCAPBACKID)}">
          <td>
            <c:if test="${not empty entitat.logoCapBack}">
              <a target="_blank" href="<c:url value="${car:fileUrl(entitat.logoCapBack)}"/>">${entitat.logoCapBack.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOPEUBACKID)}">
          <td>
            <c:if test="${not empty entitat.logoPeuBack}">
              <a target="_blank" href="<c:url value="${car:fileUrl(entitat.logoPeuBack)}"/>">${entitat.logoPeuBack.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOLATERALFRONTID)}">
          <td>
            <c:if test="${not empty entitat.logoLateralFront}">
              <a target="_blank" href="<c:url value="${car:fileUrl(entitat.logoLateralFront)}"/>">${entitat.logoLateralFront.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.VERSIO)}">
          <td>
          ${entitat.versio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ICONID)}">
          <td>
            <c:if test="${not empty entitat.icon}">
              <a target="_blank" href="<c:url value="${car:fileUrl(entitat.icon)}"/>">${entitat.icon.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.WEBENTITAT)}">
          <td>
                       <c:if test="${ not empty entitat.webEntitat}">
               <a href="${entitat.webEntitat}" target="_blank">${entitat.webEntitat}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ENTITATDESCFRONT)}">
          <td>
          ${entitat.entitatDescFront}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTWEB)}">
          <td>
                       <c:if test="${ not empty entitat.suportWeb}">
               <a href="${entitat.suportWeb}" target="_blank">${entitat.suportWeb}</a>
             </c:if>

          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTTELEFON)}">
          <td>
          ${entitat.suportTelefon}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTEMAIL)}">
          <td>
          ${entitat.suportEmail}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PLUGINLOGINID)}">
          <td>
          <c:set var="tmp">${entitat.pluginLoginID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfPluginForPluginLoginID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FITXERCSSID)}">
          <td>
            <c:if test="${not empty entitat.fitxerCss}">
              <a target="_blank" href="<c:url value="${car:fileUrl(entitat.fitxerCss)}"/>">${entitat.fitxerCss.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CONTEXT)}">
          <td>
          ${entitat.context}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.COMMIT)}">
          <td>
          ${entitat.commit}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[entitat.entitatID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


