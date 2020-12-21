<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>

<!-- Gestión de entidades -->
<a class="dropdown-item" href="<c:url value="/superadmin/entitat/list"/>"> 
    <span  style="${(fn:contains(url, 'entitat'))? "font-weight:bold;" : ""}">
        <spring:message code="entitat.entitat.plural" />
    </span>
</a>

<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item"
    href="<c:url value="/superadmin/propietatglobal/list"/>"> <span
    style="${(fn:contains(url, 'option1'))? " font-weight:bold;" : ""}"><spring:message
            code="propietatGlobal.propietatGlobal.plural" /></span>
</a>

<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item" href="<c:url value="/superadmin/pluginfront/list"/>">
   <span
    style="${(fn:contains(url, 'superadmin/pluginfront/list'))? "font-weight:bold;" : ""}"><spring:message
            code="plugin.tipus.1.plural" /></span>
</a>



<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item"
    href="<c:url value="/superadmin/usuari/list"/>"> <span
    style="${(fn:contains(url, '/superadmin/usuari/'))? " font-weight:bold;" : ""}"><spring:message
            code="usuari.usuari.plural" /></span>
</a>

<a class="dropdown-item"
    href="<c:url value="/superadmin/usuarientitat/list"/>"> <span
    style="${(fn:contains(url, '/superadmin/usuarientitat'))? " font-weight:bold;" : ""}"><spring:message
            code="usuariEntitat.usuariEntitat.plural" /> </span>
</a>

<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item"
    href="<c:url value="/superadmin/avis/list"/>"> <span
    style="${(fn:contains(url, '/superadmin/avis'))? " font-weight:bold;" : ""}"><spring:message
            code="avis.avis.plural" />&nbsp;<c:if test="${sessionScope.numAvisos ne '0'}"><span class="badge badge-danger">${sessionScope.numAvisos}</span></c:if></span>
</a>

<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item" href="<c:url value="/superadmin/systemproperties"/>"> <span
    style="${(fn:contains(url, '/superadmin/systemproperties'))? " font-weight:bold;" : ""}"><spring:message
        code="superadmin.systemproperties" /> </span>
</a>

<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item"
   href="<c:url value="/superadmin/acces/list"/>"> <span
        style="${(fn:contains(url, '/superadmin/acces'))? " font-weight:bold;" : ""}"><spring:message
        code="acces.acces.plural" /> </span>
</a>

<a class="dropdown-item"
   href="<c:url value="/superadmin/logCarpeta/list"/>"> <span
        style="${(fn:contains(url, '/superadmin/logCarpeta'))? " font-weight:bold;" : ""}"><spring:message
        code="logCarpeta.logCarpeta.plural" /> </span>
</a>

<a class="dropdown-item"
   href="<c:url value="/superadmin/auditoria/list"/>"> <span
        style="${(fn:contains(url, '/superadmin/auditoria'))? " font-weight:bold;" : ""}"><spring:message
        code="auditoria.auditoria.plural" /> </span>
</a>

<a class="dropdown-item"
   href="<c:url value="/superadmin/fitxerorfes/list"/>"> <span
        style="${(fn:contains(url, '/superadmin/fitxerorfes'))? " font-weight:bold;" : ""}"><spring:message
        code="fitxers.orfes" /> </span>
</a>

<%--
<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item" href="<c:url value="/superadmin/option2"/>">
   <span
    style="${(fn:contains(url, 'option2'))? "font-weight:bold;" : ""}">Menú
        ADMIN Option 2</span>
</a>

--%>




