<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(pluginEntitatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(pluginEntitatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty pluginEntitatForm.titleCode}">
    <fmt:message key="${pluginEntitatForm.titleCode}" >
      <fmt:param value="${pluginEntitatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty pluginEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="pluginEntitat.pluginEntitat"/>
    </c:if>
    <c:if test="${not empty pluginEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="${pluginEntitatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${pluginEntitatForm.nou?'genapp.createtitle':(pluginEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty pluginEntitatForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(pluginEntitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(pluginEntitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${pluginEntitatForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>