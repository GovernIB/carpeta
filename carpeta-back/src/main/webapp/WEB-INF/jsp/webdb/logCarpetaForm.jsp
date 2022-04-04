
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="logCarpetaFormTitle.jsp" %>


<form:form modelAttribute="logCarpetaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${logCarpetaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="logCarpetaFormCorePre.jsp" %>

  <%@include file="logCarpetaFormCore.jsp" %>

  <%@include file="logCarpetaFormCorePost.jsp" %>

  <%@include file="logCarpetaFormButtons.jsp" %>

  <c:if test="${not empty logCarpetaForm.sections}">
     <c:set var="__basename" value="logCarpeta" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${logCarpetaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/logCarpetaFormModificable.jsp" %>
  </c:if>

</form:form>


