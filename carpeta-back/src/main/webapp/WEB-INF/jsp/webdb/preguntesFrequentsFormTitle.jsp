<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(preguntesFrequentsForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(preguntesFrequentsForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty preguntesFrequentsForm.titleCode}">
    <fmt:message key="${preguntesFrequentsForm.titleCode}" >
      <fmt:param value="${preguntesFrequentsForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty preguntesFrequentsForm.entityNameCode}">
      <fmt:message var="entityname" key="preguntesFrequents.preguntesFrequents"/>
    </c:if>
    <c:if test="${not empty preguntesFrequentsForm.entityNameCode}">
      <fmt:message var="entityname" key="${preguntesFrequentsForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${preguntesFrequentsForm.nou?'genapp.createtitle':(preguntesFrequentsForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty preguntesFrequentsForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(preguntesFrequentsForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(preguntesFrequentsForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${preguntesFrequentsForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>