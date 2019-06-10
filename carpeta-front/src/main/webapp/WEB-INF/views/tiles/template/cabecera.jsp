<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="navbar-brand menuGovern">

    <div class="logoGovern">
        <a class="mr-auto" href="<c:url value="/"/>"><img src="${pageContext.request.contextPath}/static/img/logo-govern.svg" width="50" alt="Govern Balear"/></a>
    </div>
    <div>
        <div>
            <span class="titulo"><h1><fmt:message key="carga.carpeta"/></h1></span>
        </div>
        <div>

            <sec:authorize access="isAuthenticated()">
                <div>
                    <strong class="subtitulo"><fmt:message key="menu.usuario"/></strong>
                    <span class="subtituloMay"><sec:authentication property="principal.usuarioClave.nombreCompleto" /></span>
                </div>

                <div>
                    <strong class="subtitulo">DNI:</strong>
                    <span class="subtitulo"><sec:authentication property="principal.usuarioClave.nif" /></span>
                </div>
            </sec:authorize>

        </div>
    </div>

</div>