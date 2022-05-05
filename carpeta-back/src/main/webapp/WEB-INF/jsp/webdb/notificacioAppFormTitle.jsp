<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(notificacioAppForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(notificacioAppForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty notificacioAppForm.titleCode}">
    <fmt:message key="${notificacioAppForm.titleCode}" >
      <fmt:param value="${notificacioAppForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty notificacioAppForm.entityNameCode}">
      <fmt:message var="entityname" key="notificacioApp.notificacioApp"/>
    </c:if>
    <c:if test="${not empty notificacioAppForm.entityNameCode}">
      <fmt:message var="entityname" key="${notificacioAppForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${notificacioAppForm.nou?'genapp.createtitle':(notificacioAppForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty notificacioAppForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(notificacioAppForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(notificacioAppForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${notificacioAppForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>