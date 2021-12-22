<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(seccioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(seccioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty seccioForm.titleCode}">
    <fmt:message key="${seccioForm.titleCode}" >
      <fmt:param value="${seccioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty seccioForm.entityNameCode}">
      <fmt:message var="entityname" key="seccio.seccio"/>
    </c:if>
    <c:if test="${not empty seccioForm.entityNameCode}">
      <fmt:message var="entityname" key="${seccioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${seccioForm.nou?'genapp.createtitle':(seccioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty seccioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(seccioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(seccioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${seccioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>