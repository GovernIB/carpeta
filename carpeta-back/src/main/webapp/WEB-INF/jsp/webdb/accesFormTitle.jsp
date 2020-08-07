<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(accesForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(accesForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty accesForm.titleCode}">
    <fmt:message key="${accesForm.titleCode}" >
      <fmt:param value="${accesForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty accesForm.entityNameCode}">
      <fmt:message var="entityname" key="acces.acces"/>
    </c:if>
    <c:if test="${not empty accesForm.entityNameCode}">
      <fmt:message var="entityname" key="${accesForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${accesForm.nou?'genapp.createtitle':(accesForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty accesForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(accesForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(accesForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${accesForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>