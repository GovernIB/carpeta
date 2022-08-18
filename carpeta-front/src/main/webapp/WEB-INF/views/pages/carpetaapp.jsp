<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function redireccionar() {
		window.location.href = '${urlApp}';
	}
</script>
</head>
<body onload="redireccionar()">

    <form id="redirectForm" method="post" action="${urlApp}"
        enctype="application/x-www-form-urlencoded"></form>
    </form>
    <br />
    <br />
    <h1>Carpeta APP</h1>


    <h2>
        Si s'ha obert Carpeta APP pots tancar aquesta pantalla pitjant
        <a href="#" onclick="window.close(); return false;">aquí</a>
    </h2>
    <br />
    <br />
    <h4>Si no s'ha obert l'aplicació mòbil pitja en el següent botó
        i tornaràs a Carpeta App GOIB</h4>
    <br />
    <input type="button" style="font-size: 44"
        value="Tornar a Carpeta App GOIB"
        onclick="window.location.href='${urlApp}'" />
</body>
</html>