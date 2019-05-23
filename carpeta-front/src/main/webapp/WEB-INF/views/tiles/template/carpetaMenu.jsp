<!-- Menú per pantalles grosses -->
<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="imc--c">
    <div class="imc--dades">
        <div>
            <ul>
                <li class="menuInici">
                    <a href="inici.html"><p class="hide"><fmt:message key="menu.inici"/></p></a>
                </li>
                <li>
                    <a href="#"><p><fmt:message key="menu.gestions"/></p></a>
                    <ul>
                        <li><p><a href="tramitList.html"><fmt:message key="menu.tramits.noacabats"/></a></p></li>
                        <li><p><a href="registreList.html"><fmt:message key="menu.tramits.registres"/></a></p></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>