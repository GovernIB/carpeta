<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ca" style="" class=" js flexbox flexboxlegacy hashchange backgroundsize boxshadow textshadow opacity cssanimations cssgradients csstransforms csstransitions fontface generatedcontent localstorage svg" lang="ca">
<head>
    <title>Carpeta Ciutadana - Front React</title>
    <link rel="shortcut icon" type="image/x-ico" href="${pageContext.request.contextPath}/webui/icona/">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react-dom.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.21.1/babel.min.js" charset="utf-8"></script>

    <!-- Scripts -->
    <!-- google analytics -->
    <script type="text/javascript" async="" src="${pageContext.request.contextPath}/src/assets/js/ga.js"></script>
    <script src="${pageContext.request.contextPath}/src/assets/js/jquery-3.5.0.js"></script>
    <script src="${pageContext.request.contextPath}/src/assets/js/bootstrap.min.js"></script>
    <!-- Datetimpicker -->
    <script src="${pageContext.request.contextPath}/src/assets/js/moment-with-locales.min.js"></script>
    <script src="${pageContext.request.contextPath}/src/assets/js/bootstrap-datetimepicker.js"></script>
    <!-- Necessari per les cookies -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/assets/js/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/src/assets/js/modernizr.js"></script>
    <!-- Del Goib -->
    <script src="${pageContext.request.contextPath}/src/assets/js/globales.js" type="text/javascript"></script>
    <!-- Bootstrap compatibilitat amb IE -->
    <script src="${pageContext.request.contextPath}/src/assets/js/respond.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath}/src/assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/src/assets/js/datetime-moment.js" type="text/javascript"></script>

    <!-- Scripts per Components React -->
    <script src="${pageContext.request.contextPath}/src/utils/carpeta_react.js" type="text/javascript"></script>

    <!-- ESTILS -->
    <link href="${pageContext.request.contextPath}/src/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/src/assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/src/assets/css/goib.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/src/assets/css/carpeta.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/src/assets/css/open-iconic-bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/src/assets/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/src/assets/css/datatables.min.css" rel="stylesheet">

</head>

<body>

    <!-- Zona Contingut -->
    <div class="imc-continguts" id="continguts">

        <c:if test="${not empty entitats}">
            <table id="dataTable_paginate" class="table table-striped table-bordered table-hover" style="width:100%">
                <thead class="table-success">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">NOM</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${entitats}" var="entitat" varStatus="index">
                        <tr class="clickable-row" data-target="" data-href="<c:url value="/entitat/${entitat.key}"/>">
                            <td>
                                <label class="ponerMovil" data-toggle="tooltip" data-placement="top" title="${entitat.value}">
                                        ${entitat.value}
                                </label>
                                <p class="quitarMovil">${entitat.key}</p>
                            </td>
                            <td>
                                <label class="ponerMovil" data-toggle="tooltip" data-placement="top" title="${entitat.value}">
                                        ${entitat.key}
                                </label>
                                <a href="<c:url value="/entitat/${entitat.key}"/>"><p class="quitarMovil">${entitat.value}</p></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

    </div>
    <!-- Fi Zona Contingut -->

</div>

<!-- js react -->
<script src = "${pageContext.request.contextPath}/dist/reactjs_main.js" type="text/javascript"></script>
<!-- menú lateral -->
<script src="${pageContext.request.contextPath}/src/assets/js/menu-lateral.js" type="text/javascript"></script>
<!-- acceptar cookies -->
<script src="${pageContext.request.contextPath}/src/assets/js/aceptar_cookies.js" type="text/javascript"></script>

</body>
</html>
