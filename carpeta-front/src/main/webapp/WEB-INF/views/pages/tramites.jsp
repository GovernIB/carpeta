<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="imc--explicacio-detallada imc--e-d llista" id="imc--explicacio-detallada">

    <c:if test="${empty tramites}">
        <p>No existen tramites pendientes</p>
    </c:if>

    <c:if test="${not empty tramites}">
        <p>
            <fmt:message key="tramites.resultados">
                <fmt:param value="${fn:length(tramites)}"/>
            </fmt:message>
        </p>

        <ul>
            <c:forEach items="${tramites}" var="tramite" varStatus="index">
                <li class="llista-fila">
                    <div>
                        <span class="imc--num">${index.count}</span>
                        <a href="<c:url value="/tramite/${tramite.idSesionTramitacion}"/>" target="_blank">
                            <div class="imc--doc"><p class="pad-left0">${tramite.descripcionTramite}</p></div>
                        </a>
                    </div>
                    <div class="llista-fila-info">
                        <p class="pad-left0">
                            <span class="imc--extensions"><fmt:message key="tramite.id"/> <strong>${tramite.idTramite}</strong></span>
                            <span class="imc--extensions"><fmt:message key="tramite.fecha"/> <strong><fmt:formatDate value="${tramite.fechaInicio}" pattern="dd/MM/yyyy HH:mm"/></strong></span>
                            <span class="imc--extensions"><fmt:message key="tramite.acceso"/> <strong><fmt:formatDate value="${tramite.fechaUltimoAcceso}" pattern="dd/MM/yyyy HH:mm"/></strong></span>
                        </p>
                    </div>
                </li>
            </c:forEach>
        </ul>

    </c:if>

    <div class="imc-molla-pa" id="imc-molla-pa">
        <nav aria-label="Breadcrumb" class="imc--c">
            <ol>
                <li class="imc--mostra">
                    <div class="imc--selec">
                        <span class="imc--num">1</span>
                    </div>
                </li>
                <li class="imc--mostra">
                    <a href="tramites.html">
                        <span class="imc--num">2</span>
                    </a>
                </li>
                <li class="imc--mostra">
                    <a href="tramites.html">
                        <span class="imc--num">3</span>
                    </a>
                </li>
                <li class="imc--mostra">
                    <a href="tramites.html">
                        <span class="imc--num">4</span>
                    </a>
                </li>
                <li class="imc--mostra">
                    <a href="tramites.html" class="darreraPagina">
                        <span class="imc--num">5</span>
                    </a>
                </li>
            </ol>
        </nav>
    </div>
</div>