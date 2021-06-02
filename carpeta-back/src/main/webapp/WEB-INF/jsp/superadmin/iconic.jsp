<%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>

<h4><spring:message code="iconic.menu" /></h4>
<b><spring:message code="iconic.selecciona" />:</b>
<form method="post" action="<c:url value="/superadmin/iconic"/>" name="upform" >
  <table border="0" cellspacing="1" cellpadding="1" align="center" class="style1">
    <tr>
      <td align="left"></td>
    </tr>
    <tr>
       <td align="right"><a href="https://useiconic.com/open#icons" target="_blank">ICONIC</a>:</td>
       <td><input type="text"  name="iconic" value="home"></td>
    </tr>
	<tr>
	<tr>
       <td align="right"> <spring:message code="iconic.color" />:</td>
       <td><input type="color" id="color" name="color" value="#32814B"></td>
    </tr>
    <tr>
      <td align="center">        
        <input type="submit" class="btn " name="Submit" value="<spring:message code="iconic.convertir" />">        
      </td>
    </tr>
  </table>
</form>
</body>
</html>