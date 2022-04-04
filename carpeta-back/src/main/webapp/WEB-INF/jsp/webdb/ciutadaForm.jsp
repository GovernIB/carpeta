
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="ciutadaFormTitle.jsp" %>


<form:form modelAttribute="ciutadaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${ciutadaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="ciutadaFormCorePre.jsp" %>

  <%@include file="ciutadaFormCore.jsp" %>

  <%@include file="ciutadaFormCorePost.jsp" %>

  <%@include file="ciutadaFormButtons.jsp" %>

  <c:if test="${not empty ciutadaForm.sections}">
     <c:set var="__basename" value="ciutada" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${ciutadaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/ciutadaFormModificable.jsp" %>
  </c:if>

</form:form>


