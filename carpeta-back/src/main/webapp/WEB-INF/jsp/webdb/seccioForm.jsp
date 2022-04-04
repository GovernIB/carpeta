
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="seccioFormTitle.jsp" %>


<form:form modelAttribute="seccioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${seccioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="seccioFormCorePre.jsp" %>

  <%@include file="seccioFormCore.jsp" %>

  <%@include file="seccioFormCorePost.jsp" %>

  <%@include file="seccioFormButtons.jsp" %>

  <c:if test="${not empty seccioForm.sections}">
     <c:set var="__basename" value="seccio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${seccioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/seccioFormModificable.jsp" %>
  </c:if>

</form:form>


