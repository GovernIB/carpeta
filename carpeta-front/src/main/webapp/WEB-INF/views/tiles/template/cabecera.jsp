<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="navbar-brand menuGovern">

    <div class="logoGovern">
        <a class="mr-auto" href="<c:url value="/"/>"><img src="${pageContext.request.contextPath}/static/img/STT_logo2.png" width="50" alt="Govern Balear"/></a>
    </div>

    <div>
        <span class="titulo"><h1><fmt:message key="carga.carpeta"/></h1></span>
    </div>
    <div>

        <sec:authorize access="isAuthenticated()">
            <sec:authentication var="user" property="principal.usuarioClave.nombreCompleto" />
            <div>
                <strong class="subtitulo"><fmt:message key="menu.usuario"/></strong>
                <span class="subtituloMay">
                    <c:if test="${user != null}">
                        <sec:authentication property="principal.usuarioClave.nombreCompleto" />
                    </c:if>
                </span>
            </div>
<%--No ha de sortir a la capsalera, ha de sortir a dades personals--%>
<%--            <div>--%>
<%--                <strong class="subtitulo">DNI:</strong>--%>
<%--                <span class="subtitulo"><sec:authentication property="principal.usuarioClave.nif" /></span>--%>
<%--            </div>--%>
        </sec:authorize>

    </div>

</div>