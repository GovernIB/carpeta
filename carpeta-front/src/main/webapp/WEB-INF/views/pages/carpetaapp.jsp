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

    <form id="redirectForm" method="post" action="${urlApp}" enctype="application/x-www-form-urlencoded">
    </form>
    </form>
    <br />
    <br />
    <h2>Pitja en aquest enllaç i tornaràs a l'APP ANDROID</h2>
    <br />
    <h1>
        <input type="button" style="font-size: 44"
            value="${urlApp}"
            onclick="window.location.href='${urlApp}'" />
        <script>
           document.write(document.cookie);
        </script>           

    </h1>
</body>
</html>