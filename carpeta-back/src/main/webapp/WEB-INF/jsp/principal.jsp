<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
<br/>
<center>
<img src="<c:url value="/img/app-logo.png"/>"  alt="Carpeta" title="Carpeta"/>

<br/>
<br/>
This page is generated automatically. Please edit.

<br/>
<br/>
<table border="0" >
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td valign="top">
<a href="http://blog.fundaciobit.org/category/admindigital/" target="_blank">
<img src="<c:url value="/img/fundaciobit.png"/>"  alt="Fundació Bit" title="Fundació Bit"/>
</a>
</td>
</tr>
</table>
<br/>
</center>
 
</div>

<br/>



Username: ${loginInfo.username}<br/>



&#36;{car:hasRole(ROLE_ADMIN)}= ${car:hasRole('ROLE_ADMIN')}<br/>
&#36;{car:hasRole(ROLE_USER) }= ${car:hasRole('ROLE_USER') }<br/>
Locale = <%=LocaleContextHolder.getLocale() %> <br/>

lang = ${lang} <br/>
<br/>

<c:if test="${car:isDesenvolupament()}">
Only in Development Mode
</c:if>
