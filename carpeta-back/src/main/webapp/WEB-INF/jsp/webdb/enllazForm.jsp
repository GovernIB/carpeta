
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="enllazFormTitle.jsp" %>


<form:form modelAttribute="enllazForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${enllazForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="enllazFormCorePre.jsp" %>

  <%@include file="enllazFormCore.jsp" %>

  <%@include file="enllazFormCorePost.jsp" %>

  <%@include file="enllazFormButtons.jsp" %>

  <c:if test="${not empty enllazForm.sections}">
     <c:set var="__basename" value="enllaz" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${enllazForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/enllazFormModificable.jsp" %>
  </c:if>

</form:form>


