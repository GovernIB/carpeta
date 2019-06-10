<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="container contenido">

    <div class="row">

    <c:if test="${empty asientos}">
        <p>No existen asientos registrales</p>
    </c:if>

    <c:if test="${not empty asientos}">
        <p>
            <fmt:message key="registro.resultados">
                <fmt:param value="${fn:length(asientos)}"/>
            </fmt:message>
        </p>

        <ul>

            <c:forEach items="${asientos}" var="asiento" varStatus="index">

                <li class="llista-fila">
                    <div>
                        <span class="imc--num">${index.count}</span>
                        <a href="<c:url value="/registreDetall"/>">
                            <div class="imc--doc"><span><fmt:message key="registro.tipo.${asiento.tipoRegistro}"/>: ${asiento.numeroRegistroFormateado}</span></div>
                        </a>
                    </div>
                    <div class="llista-fila-info">
                        <span class="imc--extensions"><fmt:message key="registro.fecha"/>: <strong><fmt:formatDate value="${asiento.fechaRegistro}" pattern="dd/MM/yyyy HH:mm"/></strong></span>
                        <span class="imc--extensions"><fmt:message key="registro.extracto"/>: <strong>${asiento.resumen}</strong></span>
                        <span class="imc--extensions"><fmt:message key="registro.oficina"/>: <strong>${asiento.entidadRegistralInicioDenominacion}</strong></span>

                        <%--Registro Entrada--%>
                        <c:if test="${asiento.tipoRegistro == 1}">
                            <span class="imc--extensions"><fmt:message key="registro.organisme"/>: <strong>${asiento.unidadTramitacionDestinoDenominacion}</strong></span>
                        </c:if>

                        <%--Registro Salida--%>
                        <c:if test="${asiento.tipoRegistro == 2}">

                        </c:if>

                    </div>
                </li>

            </c:forEach>

        </ul>

        <div class="imc-molla-pa" id="imc-molla-pa">
            <nav aria-label="Breadcrumb" class="imc--c">
                <ol>
                    <li class="imc--mostra">
                        <div class="imc--selec">
                            <span class="imc--num">1</span>
                        </div>
                    </li>
                    <li class="imc--mostra">
                        <a href="registros.html">
                            <span class="imc--num">2</span>
                        </a>
                    </li>
                    <li class="imc--mostra">
                        <a href="registros.html">
                            <span class="imc--num">3</span>
                        </a>
                    </li>
                    <li class="imc--mostra">
                        <a href="registros.html">
                            <span class="imc--num">4</span>
                        </a>
                    </li>
                    <li class="imc--mostra">
                        <a href="registros.html" class="darreraPagina">
                            <span class="imc--num">5</span>
                        </a>
                    </li>
                </ol>
            </nav>
        </div>
    </c:if>

    </div>
</div>