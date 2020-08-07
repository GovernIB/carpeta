
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="auditoriaFormTitle.jsp" %>


<form:form modelAttribute="auditoriaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${auditoriaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="auditoriaFormCorePre.jsp" %>
  <%@include file="auditoriaFormCore.jsp" %>

  <%@include file="auditoriaFormCorePost.jsp" %>

  <%@include file="auditoriaFormButtons.jsp" %>

  <c:if test="${auditoriaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/auditoriaFormModificable.jsp" %>
  </c:if>

</form:form>


