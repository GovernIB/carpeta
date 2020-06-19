<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.security.core.context.SecurityContext"%><%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%><%@ page
	language="java"%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div class="clear"></div>
<div class="spacer"></div>

<c:choose>

	<c:when test="${lang eq 'ca'}">
		<%@ include file="avislegal_ca.jsp"%>
	</c:when>

	<c:when test="${lang eq 'es'}">
		<%@ include file="avislegal_es.jsp"%>
	</c:when>

	<c:when test="${lang eq 'en'}">
		<%@ include file="avislegal_es.jsp"%>
	</c:when>

	<c:otherwise>
		<%@ include file="avislegal_ca.jsp"%>
	</c:otherwise>
</c:choose>

