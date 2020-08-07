<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(usuariForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(usuariForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty usuariForm.titleCode}">
    <fmt:message key="${usuariForm.titleCode}" >
      <fmt:param value="${usuariForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty usuariForm.entityNameCode}">
      <fmt:message var="entityname" key="usuari.usuari"/>
    </c:if>
    <c:if test="${not empty usuariForm.entityNameCode}">
      <fmt:message var="entityname" key="${usuariForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${usuariForm.nou?'genapp.createtitle':(usuariForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty usuariForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(usuariForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(usuariForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${usuariForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>