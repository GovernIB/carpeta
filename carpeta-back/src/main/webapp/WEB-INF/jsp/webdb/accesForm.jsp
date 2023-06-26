
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="accesForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="accesFormTitle.jsp" %>
 
  <c:set var="contexte" value="${accesForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="accesFormCorePre.jsp" %>

  <%@include file="accesFormCore.jsp" %>

  <%@include file="accesFormCorePost.jsp" %>

  <%@include file="accesFormButtons.jsp" %>

  <c:if test="${not empty accesForm.sections}">
     <c:set var="__basename" value="acces" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${accesForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/accesFormModificable.jsp" %>
  </c:if>

</form:form>


