<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(logCarpetaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(logCarpetaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty logCarpetaForm.titleCode}">
    <fmt:message key="${logCarpetaForm.titleCode}" >
      <fmt:param value="${logCarpetaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty logCarpetaForm.entityNameCode}">
      <fmt:message var="entityname" key="logCarpeta.logCarpeta"/>
    </c:if>
    <c:if test="${not empty logCarpetaForm.entityNameCode}">
      <fmt:message var="entityname" key="${logCarpetaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${logCarpetaForm.nou?'genapp.createtitle':(logCarpetaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty logCarpetaForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(logCarpetaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(logCarpetaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${logCarpetaForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>