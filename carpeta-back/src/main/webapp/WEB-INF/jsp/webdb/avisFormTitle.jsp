<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(avisForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(avisForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty avisForm.titleCode}">
    <fmt:message key="${avisForm.titleCode}" >
      <fmt:param value="${avisForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty avisForm.entityNameCode}">
      <fmt:message var="entityname" key="avis.avis"/>
    </c:if>
    <c:if test="${not empty avisForm.entityNameCode}">
      <fmt:message var="entityname" key="${avisForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${avisForm.nou?'genapp.createtitle':(avisForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty avisForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(avisForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(avisForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${avisForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>