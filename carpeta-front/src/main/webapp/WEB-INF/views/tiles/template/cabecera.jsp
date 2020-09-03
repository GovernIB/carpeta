<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="navbar-brand menuGovern">

    <div class="logoGovern">
        <a class="mr-auto" href="<c:url value="/"/>" title="Logo Govern Balear"><img src="${pageContext.request.contextPath}/static/img/STT_logo2.png" class="imatgeGovern" alt="<fmt:message key="menu.inicio.carpeta"/>"/></a>
    </div>

    <div>
        <h1 class="titulo"><fmt:message key="carga.carpeta"/></h1>
    </div>
    <div>

        <sec:authorize access="isAuthenticated()">
            <sec:authentication var="user" property="principal.usuarioClave.nombreCompleto" />
            <div>
                <strong class="subtitulo quitarMovil"><fmt:message key="menu.usuario"/></strong>
                <span class="subtituloMay">
                    <c:if test="${user != null}">
                        <sec:authentication property="principal.usuarioClave.nombreCompleto" />, &nbsp;
                    </c:if>
                </span>
                <strong class="subtitulo quitarMovil"><fmt:message key="menu.qaa"/></strong>
                <span class="subtituloMay">
                    <c:if test="${user != null}">
                        <sec:authentication property="principal.usuarioClave.qaa" />
                    </c:if>
                </span>
                <span class="">

                </span>
            </div>
        </sec:authorize>

    </div>

</div>