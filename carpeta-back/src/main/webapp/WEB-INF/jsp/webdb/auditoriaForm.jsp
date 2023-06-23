
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="auditoriaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="auditoriaFormTitle.jsp" %>
 
  <c:set var="contexte" value="${auditoriaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="auditoriaFormCorePre.jsp" %>

  <%@include file="auditoriaFormCore.jsp" %>

  <%@include file="auditoriaFormCorePost.jsp" %>

  <%@include file="auditoriaFormButtons.jsp" %>

  <c:if test="${not empty auditoriaForm.sections}">
     <c:set var="__basename" value="auditoria" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${auditoriaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/auditoriaFormModificable.jsp" %>
  </c:if>

</form:form>


