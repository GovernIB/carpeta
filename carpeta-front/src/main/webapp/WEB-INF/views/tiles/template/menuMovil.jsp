<!-- Menú lateral per a mòbils -->
<%@include file="/WEB-INF/views/includes.jsp"%>
<nav class="imc-marc invisible" id="menu-mobil">
    <div class="imc-marc-menu" id="imc-marc">
        <ul>
            <li>
                <a href="<c:url value="/"/>" class="imc-marc-ico imc--avanzada" title="Inici"><span><fmt:message key="menu.inicio"/></span></a>
            </li>
            <li class="imc-marc-ico imc--idioma">
                <strong><fmt:message key="idioma.catala"/></strong> \ <a href="<c:url value="/?idioma=es"/>"><fmt:message key="idioma.castellano"/></a>
            </li>
            <li>
                <a href="<c:url value="/tramites"/>" class="imc-marc-ico imc--tramit" title="<fmt:message key="menu.movil.tramites"/>"><span><fmt:message key="menu.movil.tramites"/></span></a>
            </li>
            <li>
                <a href="<c:url value="/registros"/>" class="imc-marc-ico imc--registre" title="<fmt:message key="menu.movil.registros"/>"><span><fmt:message key="menu.movil.registros"/></span></a>
            </li>
            <li>
                <a href="<c:url value="/accesibilidad"/>" class="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat" title="<fmt:message key="menu.movil.accesibilidad"/>"><span><fmt:message key="menu.movil.accesibilidad"/></span></a>
            </li>
            <li>
                <a href="#" class="imc-marc-ico imc--sortir" id="imc-marc-sortir" title="<fmt:message key="menu.movil.salir"/>" onclick="sortirCarpeta()"><span><fmt:message key="menu.movil.salir"/></span></a>
            </li>
        </ul>
    </div>
</nav>