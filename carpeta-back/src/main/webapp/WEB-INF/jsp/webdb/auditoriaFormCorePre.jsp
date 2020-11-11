<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AuditoriaFields" className="es.caib.carpeta.model.fields.AuditoriaFields"/>
  
  <c:set var="__theForm" value="${auditoriaForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:if test="${not __theForm.view}">    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.auditoria,hiddenField)}">
        <form:errors path="auditoria.${hiddenField}" cssClass="errorField alert alert-danger" />
        <form:hidden path="auditoria.${hiddenField}"/>
      </c:if>
    </c:forEach>
    </c:if>

    <form:errors cssClass="errorField alert alert-danger" delimiter="&lt;p/&gt;" />
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody>      

