
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="notificacioAppFormTitle.jsp" %>


<form:form modelAttribute="notificacioAppForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${notificacioAppForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="notificacioAppFormCorePre.jsp" %>

  <%@include file="notificacioAppFormCore.jsp" %>

  <%@include file="notificacioAppFormCorePost.jsp" %>

  <%@include file="notificacioAppFormButtons.jsp" %>

  <c:if test="${not empty notificacioAppForm.sections}">
     <c:set var="__basename" value="notificacioApp" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${notificacioAppForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/notificacioAppFormModificable.jsp" %>
  </c:if>

</form:form>


