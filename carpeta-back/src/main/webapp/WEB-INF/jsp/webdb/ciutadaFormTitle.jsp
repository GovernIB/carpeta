<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(ciutadaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(ciutadaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty ciutadaForm.titleCode}">
    <fmt:message key="${ciutadaForm.titleCode}" >
      <fmt:param value="${ciutadaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty ciutadaForm.entityNameCode}">
      <fmt:message var="entityname" key="ciutada.ciutada"/>
    </c:if>
    <c:if test="${not empty ciutadaForm.entityNameCode}">
      <fmt:message var="entityname" key="${ciutadaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${ciutadaForm.nou?'genapp.createtitle':(ciutadaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty ciutadaForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(ciutadaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(ciutadaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${ciutadaForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>