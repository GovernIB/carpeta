<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(auditoriaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(auditoriaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty auditoriaForm.titleCode}">
    <fmt:message key="${auditoriaForm.titleCode}" >
      <fmt:param value="${auditoriaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty auditoriaForm.entityNameCode}">
      <fmt:message var="entityname" key="auditoria.auditoria"/>
    </c:if>
    <c:if test="${not empty auditoriaForm.entityNameCode}">
      <fmt:message var="entityname" key="${auditoriaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${auditoriaForm.nou?'genapp.createtitle':(auditoriaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty auditoriaForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(auditoriaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(auditoriaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${auditoriaForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>