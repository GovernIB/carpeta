<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>

<a class="dropdown-item" href="<c:url value="/admin/option1"/>"> <span
	style="${(fn:contains(url, 'option1'))? " font-weight:bold;" : ""}">Menú
		ADMIN Option 1</span>
</a>


<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item" href="<c:url value="/admin/option2"/>"> <span
	style="${(fn:contains(url, 'option2'))? " font-weight:bold;" : ""}">Menú
		ADMIN Option 2</span>
</a>


