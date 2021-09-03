<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
function redireccionar() {
	//setTimeout(function() {
		
	
		
	window.location.href = 'carpetaapp://carpeta/show/${codiLogin}';
	//}, 2000);

    //document.forms['redirectForm'].submit();
  
}
</script>
</head>
<body onload="redireccionar()">

    <form id="redirectForm" method="post" action="carpetaapp://carpeta/show/${codiLogin}" enctype="application/x-www-form-urlencoded">
    </form>
    </form>
    <br />
    <br />
    <h2>Pitja en aquest enllaç i tornaràs a l'APP ANDROID</h2>
    <br />
    <h1>
        <input type="button" style="font-size: 44"
            value="carpetaapp://carpeta/show/${codiLogin}"
            onclick="window.location.href='carpetaapp://carpeta/show/${codiLogin}'" />
        <script>
           document.write(document.cookie);
        </script>           

    </h1>
</body>
</html>