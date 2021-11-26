
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="preguntesFrequentsFormTitle.jsp" %>


<form:form modelAttribute="preguntesFrequentsForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${preguntesFrequentsForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="preguntesFrequentsFormCorePre.jsp" %>
  <%@include file="preguntesFrequentsFormCore.jsp" %>

  <%@include file="preguntesFrequentsFormCorePost.jsp" %>

  <%@include file="preguntesFrequentsFormButtons.jsp" %>

  <c:if test="${preguntesFrequentsForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/preguntesFrequentsFormModificable.jsp" %>
  </c:if>

</form:form>


