<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${notificacioAppFilterForm.contexte}"/>
  <c:set var="formName" value="notificacioApp" />
  <c:set var="__theFilterForm" value="${notificacioAppFilterForm}" />
  <c:if test="${empty notificacioAppFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="notificacioApp.notificacioApp"/>
  </c:if>
  <c:if test="${not empty notificacioAppFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${notificacioAppFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty notificacioAppFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="notificacioApp.notificacioApp"/>
  </c:if>
  <c:if test="${not empty notificacioAppFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${notificacioAppFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.notificacioApp.submit();  
  }
</script>
