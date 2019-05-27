<!-- Menú per pantalles grosses -->
<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="imc--c">
    <div class="imc--dades">
        <div>
            <ul>
                <li class="menuInici">
                    <a href="<c:url value="/"/>"><p class="hide"><fmt:message key="menu.inicio"/></p></a>
                </li>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="#"><p><fmt:message key="menu.gestiones"/></p></a>
                        <ul>
                            <li><p><a href="<c:url value="/tramites"/>"><fmt:message key="menu.tramites.no.acabados"/></a></p></li>
                            <li><p><a href="<c:url value="/registros"/>"><fmt:message key="menu.registros"/></a></p></li>
                        </ul>
                    </li>
                </sec:authorize>

            </ul>
        </div>
    </div>
</div>