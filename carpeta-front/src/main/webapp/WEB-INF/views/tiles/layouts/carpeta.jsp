<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${pageContext.response.locale.language}"  lang="${pageContext.response.locale.language}" data-estil="pd">
<head>
    <link rel="shortcut icon" href="<c:url value="/static/img/logo_carpeta.png"/>" />

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Language" content="${pageContext.response.locale.language}"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="_csrf" content="">
    <meta name="_csrf_header" content="">

    <title>GOIB - Carpeta Ciudadana</title>

    <!-- jQuery library -->

    <script src="<c:url value="/static/js/jquery-3.1.1.slim.min.js"/>"></script>
    <script src="<c:url value="/static/js/tether.min.js"/>"></script>
    <script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<c:url value="/static/js/ie10-viewport-bug-workaround.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/carpeta.js"/>"></script>

    <!-- Estilos -->
    <link href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/carpeta.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/open-iconic-bootstrap.css"/>" rel="stylesheet">

</head>

<body>

    <!-- contenidor -->
    <nav class="navbar navbar-toggleable-md navbar-inverse bg-carpeta fixed-top">

        <!-- botón menú móvil -->
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Cabecera -->
        <tiles:insertAttribute name="cabecera" />

        <!-- Menú principal -->
        <tiles:insertAttribute name="menu" />

    </nav>

    <!-- Contenido -->
    <div class="container">

        <div class="carpeta-contenido">

            <!-- Miga de pan -->
            <%--<tiles:insertAttribute name="miga" />--%>

            <!-- Contenido -->
            <tiles:insertAttribute name="body" />

            <!-- LOPD -->
            <tiles:insertAttribute name="lopd" />

            <!-- contacto -->
            <tiles:insertAttribute name="contacto" />

        </div>

    </div><!-- /.container -->

    <!-- Pie -->
    <tiles:insertAttribute name="pie" />

</body>

</html>