<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="navbar-brand menuGovern">

    <div class="logoGovern">
        <a class="mr-auto" href="<c:url value="/"/>"><img src="${pageContext.request.contextPath}/static/img/STT_logo2.png" class="imatgeGovern" alt="Govern Balear"/></a>
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
                        <sec:authentication property="principal.usuarioClave.nombreCompleto" />
                    </c:if>
                </span>
            </div>
        </sec:authorize>

    </div>

</div>