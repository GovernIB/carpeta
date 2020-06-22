<%@ page language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<center><h1>Option Page ${optionNumber}</h1></center>

Pàgina 1 <br/>
Página 1 <br/>
Page 1 <br/>


<center><i class="fa fa-check fa-lg" aria-hidden="true"></i></center>

${usuario.username}


<c:forEach var="entidad" items="${entidades}">
    Codigo Dir3 : ${entidad.codigoDir3}
</c:forEach>




<center><img src="<c:url value="/img/icn_alert_success.png"/>"  alt="exemple" title="exemple"/></center>