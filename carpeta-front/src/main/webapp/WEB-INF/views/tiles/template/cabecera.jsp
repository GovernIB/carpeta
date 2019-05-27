<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="imc--c">

    <div class="imc--logo" title="Govern Illes Balears"></div>

    <div class="imc--dades">

        <h2><fmt:message key="carga.carpeta"/></h2>

        <sec:authorize access="isAuthenticated()">
            <div class="imc--usuari">
                <strong><fmt:message key="menu.usuario"/></strong>
                <span><sec:authentication property="principal.usuarioClave.nombreCompleto" /></span>
            </div>

            <div class="imc--clau">
                <strong>DNI:</strong>
                <span><sec:authentication property="principal.usuarioClave.nif" /></span>
            </div>
        </sec:authorize>

    </div>

    <ul class="imc--opcions" id="opcionsLlarg">
        <li>
            <button type="button" id="imc-bt-accessibilitat" class="imc-bt" onclick="location.href='accessibilitat.html';">
                <p class="text1"><img src="${pageContext.request.contextPath}/static/img/icones/ico_accessibilitat.svg" class="icona-capsalera" alt=""/><fmt:message key="menu.accesibilidad"/></p>
            </button>
        </li>
        <li class="imc-idioma imc-bt" id="imc-bt-idioma">
            <ul>
                <li>
                    <p class="text1"><img src="${pageContext.request.contextPath}/static/img/icones/ico_idioma.svg" class="icona-capsalera" alt=""/><fmt:message key="menu.idioma"/></p>
                </li>
                <li class="opcioOculta" id="idioma">
                    <ul>
                        <li><a href="<c:url value="/?idioma=ca"/>" id="id_ca">Catala</a></li>
                        <li><a href=" <c:url value="/?idioma=es"/>" id="id_es">Castella</a></li>
                    </ul>
                </li>

            </ul>
        </li>
        <sec:authorize access="isAuthenticated()">
            <li>
                <c:url var="logoutUrl" value="/logout" />
                <form action="${logoutUrl}" id="logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <button type="button" id="imc-bt-desconecta" class="imc-bt" onclick="sortirCarpeta()"><p class="text1"><img src="${pageContext.request.contextPath}/static/img/icones/ico_desconecta.svg" class="icona-capsalera" alt=""/><fmt:message key="menu.salir"/></p></button>
            </li>
        </sec:authorize>

    </ul>

    <button type="button" class="imc-bt-menu" id="menuMobil" title="Menú" onclick="desplegarMenu()"><span>Menú</span></button>

</div>