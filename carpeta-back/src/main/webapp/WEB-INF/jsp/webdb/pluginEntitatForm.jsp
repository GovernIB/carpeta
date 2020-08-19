
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="pluginEntitatFormTitle.jsp" %>


<form:form modelAttribute="pluginEntitatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${pluginEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pluginEntitatFormCorePre.jsp" %>
  <%@include file="pluginEntitatFormCore.jsp" %>

  <%@include file="pluginEntitatFormCorePost.jsp" %>

  <%@include file="pluginEntitatFormButtons.jsp" %>

  <c:if test="${pluginEntitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pluginEntitatFormModificable.jsp" %>
  </c:if>

</form:form>


