<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<a class="dropdown-item" href="<c:url value="/common/principal.html"/>">
  <span style="${(fn:contains(url, 'principal'))? "font-weight: bold;" : ""}">Pàgina Inicial</span>
</a>


<hr/>

<a class="dropdown-item" href="<c:url value="/common/systemproperties"/>"> <span
	style="${(fn:contains(url, 'option1'))? " font-weight:bold;" : ""}">SystemProperties</span>
</a>
