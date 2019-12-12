<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${pageContext.response.locale.language}" lang="${pageContext.response.locale.language}">
<head>
    <link rel="shortcut icon" href="<c:url value="/static/img/GOIB_fav.png"/>" />

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Language" content="${pageContext.response.locale.language}"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="_csrf" content="">
    <meta name="_csrf_header" content="">

    <title><spring:message code="carpeta.titulo"/></title>

    <script src="<c:url value="/static/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/static/js/datatables.min.js"/>"></script>
    <script src="<c:url value="/static/js/moment-with-locales.min.js"/>"></script>
    <script src="<c:url value="/static/js/datetime-moment.js"/>"></script>
    <script src="<c:url value="/static/js/bootstrap-datetimepicker.js"/>"></script>
    <%--<script src="<c:url value="/static/js/bootstrap-datetimepicker.ca.js"/>"></script>--%>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<c:url value="/static/js/ie10-viewport-bug-workaround.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/carpeta.js"/>"></script>

    <!-- Estilos -->
    <link href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/carpeta.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/open-iconic-bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/datatables.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet">

</head>

<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark bg-carpeta">

            <button class="navbar-toggler botonMovil" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Cabecera -->
            <tiles:insertAttribute name="cabecera" />

            <!-- Menú principal -->
            <tiles:insertAttribute name="menu" />

        </nav>
    </header>

    <!-- Miga de pan -->
    <tiles:insertAttribute name="miga" />

    <!-- Contenido -->
    <div class="container p-3carpeta">

        <div class="my-3 p-3 bg-white rounded box-shadow">

            <!-- Contenido -->
            <tiles:insertAttribute name="body" />

            <!-- LOPD -->
            <%--tiles:insertAttribute name="lopd" />--%>

        </div>

    </div><!-- /.container -->

    <!-- Contacto -->
    <tiles:insertAttribute name="contacto" />

    <!-- Pie -->
    <tiles:insertAttribute name="pie" />

</body>

</html>