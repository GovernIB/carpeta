<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>


<a class="dropdown-item"
    href="<c:url value="/adminentitat/propietatglobal/list"/>"> <span
    style="${(fn:contains(url, '/adminentitat/propietatglobal'))? " font-weight:bold;" : ""}"><spring:message
            code="propietatGlobal.propietatGlobal.plural" /></span>
</a>

<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item"
    href="<c:url value="/adminentitat/usuarientitat/list"/>"> <span
    style="${(fn:contains(url, 'adminentitat/usuarientitat'))? " font-weight:bold;" : ""}"><spring:message
            code="usuariEntitat.usuariEntitat.plural" /></span>
</a>

<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item"
    href="<c:url value="/adminentitat/pluginEntitat/list"/>"> <span
    style="${(fn:contains(url, '/adminentitat/pluginEntitat'))? " font-weight:bold;" : ""}"><spring:message
            code="pluginEntitat.pluginEntitat.plural" /></span>
</a>

<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item"
    href="<c:url value="/adminentitat/enllaz/list"/>"> <span
    style="${(fn:contains(url, '/adminentitat/enllaz'))? " font-weight:bold;" : ""}"><spring:message
            code="enllaz.enllaz.plural" /></span>
</a>

<%--
<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item" href="<c:url value="/user/option1"/>"> <span
    style="${(fn:contains(url, 'option1'))? " font-weight:bold;" : ""}">Menú
        USER Option 1</span>
</a>


<hr style="margin-top: 6px; margin-bottom: 6px;" />

<a class="dropdown-item" href="<c:url value="/user/option2"/>"> <span
    style="${(fn:contains(url, 'option2'))? " font-weight:bold;" : ""}">Menú
        USER Option 2</span>
</a>

--%>
