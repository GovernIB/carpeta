<!-- Menú lateral per a mòbils -->
<%@include file="/WEB-INF/views/includes.jsp"%>
<nav class="imc-marc invisible" id="menu-mobil">
    <div class="imc-marc-menu" id="imc-marc">
        <ul>
            <li>
                <a href="inici.html?lang=ca" class="imc-marc-ico imc--avanzada" title="Inici"><span><fmt:message key="menu.inici"/></span></a>
            </li>
            <li class="imc-marc-ico imc--idioma">
                <strong><fmt:message key="idioma.catala"/></strong> \ <a href="inici.html?idioma=es"><fmt:message key="idioma.castella"/></a>
            </li>
            <li>
                <a href="tramitList.html?lang=ca" class="imc-marc-ico imc--tramit" title="<fmt:message key="menu.mobil.tramits"/>"><span><fmt:message key="menu.mobil.tramits"/></span></a>
            </li>
            <li>
                <a href="registreList.html?lang=ca" class="imc-marc-ico imc--registre" title="<fmt:message key="menu.mobil.registres"/>"><span><fmt:message key="menu.mobil.registres"/></span></a>
            </li>
            <li>
                <a href="accessibilitat.html?lang=ca" class="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat" title="<fmt:message key="menu.mobil.accessibilitat"/>"><span><fmt:message key="menu.mobil.accessibilitat"/></span></a>
            </li>
            <li>
                <a href="#" class="imc-marc-ico imc--sortir" id="imc-marc-sortir" title="<fmt:message key="menu.mobil.sortir"/>" onclick="sortirCarpeta()"><span><fmt:message key="menu.mobil.sortir"/></span></a>
            </li>
        </ul>
    </div>
</nav>