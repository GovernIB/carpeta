<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/views/includes.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<c:out value="${pageContext.response.locale.language}"/>"  lang="<c:out value="${pageContext.response.locale.language}"/>" data-estil="pd" class=" js rgba multiplebgs backgroundsize borderradius boxshadow textshadow opacity cssanimations cssgradients csstransforms csstransitions generatedcontent">
<head>
    <link rel="shortcut icon" href="<c:url value="/static/img/logo_carpeta.png"/>" />
    <%--<meta charset="UTF-8">--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Language" content="ca"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="_csrf" content="">
    <meta name="_csrf_header" content="">

    <title>GOIB - Carpeta Ciudadana</title>

    <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/static/css/imc-sf.css">

    <script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js"></script>

    <!-- inicia! -->
    <script src="${pageContext.request.contextPath}/static/js/configuracion.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/imc-sf.js"></script>

    <!-- AFEGIT PER JO-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/carpeta.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/carpeta.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/imc-menu-desplegable.js"></script>

</head>

<body>

    <section id="carrega">
        <tiles:insertAttribute name="carrega" />
    </section>

    <!-- contenidor -->
    <div id="imc-contenidor" class="imc-contenidor imc-mostra" aria-hidden="false"><!-- cap -->

        <!-- Header i Menu -->
        <div class="imc-cap--fixe" id="imc-cap--fixe">

            <!-- Header -->
            <header class="imc-cap" id="header" data-autenticacio="a">
                <tiles:insertAttribute name="header" />
            </header>

            <!-- Menú mòbil -->
            <section id="mobil">
                <tiles:insertAttribute name="mobil" />
            </section>

            <!-- Menu -->
            <header class="imc-menu" id="imc-menu">
                <tiles:insertAttribute name="menu" />
            </header>

            <!-- Molla de pa -->
            <section id="miga">
                <tiles:insertAttribute name="miga" />
            </section>

        </div>

        <!-- Contingut pàgina -->
        <div class="imc-contingut" id="imc-contingut">
            <div class="imc--c"><!-- cal saber -->

                <div class="imc-pas">

                    <!-- Explicació -->
                    <section id="explicacio">
                        <tiles:insertAttribute name="explicacio" />
                    </section>

                    <!-- Avisos -->
                    <section id="avis">
                        <tiles:insertAttribute name="avis" />
                    </section>

                    <!-- Contingut -->
                    <section id="site-content">
                        <tiles:insertAttribute name="body" />
                    </section>

                    <!-- LOPD -->
                    <section id="lopd">
                        <tiles:insertAttribute name="lopd" />
                    </section>

                </div>
            </div>
        </div>

        <!-- Contacte -->
        <aside id="contacte" class="imc-contacte">
            <tiles:insertAttribute name="contacte" />
        </aside>

        <!-- Peu i LOPD -->
        <footer id="footer" class="imc-peu">
            <tiles:insertAttribute name="footer" />
        </footer>

    </div>
    <!-- /contenidor -->

    <!-- Missatge -->
    <footer id="missatge">
        <tiles:insertAttribute name="missatge" />
    </footer>

</body>

</html>