<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(enllazForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(enllazForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty enllazForm.titleCode}">
    <fmt:message key="${enllazForm.titleCode}" >
      <fmt:param value="${enllazForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty enllazForm.entityNameCode}">
      <fmt:message var="entityname" key="enllaz.enllaz"/>
    </c:if>
    <c:if test="${not empty enllazForm.entityNameCode}">
      <fmt:message var="entityname" key="${enllazForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${enllazForm.nou?'genapp.createtitle':(enllazForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty enllazForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(enllazForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(enllazForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${enllazForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>