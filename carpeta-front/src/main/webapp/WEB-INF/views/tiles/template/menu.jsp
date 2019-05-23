<!-- Menú per pantalles grosses -->
<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="imc--c">
    <div class="imc--dades">
        <div>
            <ul>
                <li class="menuInici">
                    <a href="inicio.html"><p class="hide"><fmt:message key="menu.inicio"/></p></a>
                </li>
                <li>
                    <a href="#"><p><fmt:message key="menu.gestiones"/></p></a>
                    <ul>
                        <li><p><a href="tramites.html"><fmt:message key="menu.tramites.no.acabados"/></a></p></li>
                        <li><p><a href="registros.html"><fmt:message key="menu.registros"/></a></p></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>