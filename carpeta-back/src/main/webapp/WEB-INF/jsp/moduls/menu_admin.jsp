<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>

<!-- Gestión de entidades -->
<a class="dropdown-item" href="<c:url value="/entidad/list"/>"> <span
		style="${(fn:contains(url, 'option1'))? " font-weight:bold;" : ""}"><spring:message code="entidad.entidades"/></span>
</a>


<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item" href="<c:url value="/admin/option2"/>"> <span
	style="${(fn:contains(url, 'option2'))? " font-weight:bold;" : ""}">Menú
		ADMIN Option 2</span>
</a>


