<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ca" data-estil="pd" class=" js rgba multiplebgs backgroundsize borderradius boxshadow textshadow opacity cssanimations cssgradients csstransforms csstransitions generatedcontent">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;">
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