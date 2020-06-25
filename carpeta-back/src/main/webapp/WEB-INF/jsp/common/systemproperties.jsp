<%@page import="java.util.TreeMap"
%><%@page import="java.util.Map.Entry"%><%@page import="java.util.Set"%><%@page
	import="java.util.Properties"%><%@page
	import="org.springframework.security.core.Authentication"%><%@page
	import="org.springframework.security.core.context.SecurityContext"%><%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%><%@ page
	language="java"%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<div class="clear"></div>
<div class="spacer"></div>

<center>
	<h1>System Properties</h1>
</center>

<table border="1">
	<%
		TreeMap<Object, Object> prop = new TreeMap<Object, Object>(System.getProperties());

		Set<Entry<Object, Object>> entrySet = prop.entrySet();

		for (Entry<Object, Object> entry : entrySet) {
	%>
	<tr>
		<td><%=entry.getKey()%></td>
		<td><%=entry.getValue()%></td>
	</tr>
	<%
		}
	%>
</table>