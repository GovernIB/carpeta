<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="mb-4 quitarMovil" style="background: white; padding: 1%">
    <ul class="container nav nav-pills nav-justified">
<%--        <li class="nav-item active"><a class="nav-link" href="<c:url value="/inicio"/>"><span class="oi oi-home verde p-1" title="" aria-hidden="true"></span>&nbsp; <fmt:message key="menu.inicio"/></a></li>--%>
        <li class="nav-item"><a class="nav-link" href="<c:url value="/tramite/list"/>"><span class="oi oi-document" title="" aria-hidden="true"></span>&nbsp; <fmt:message key="menu.tramite/list"/> </a>
        <li class="nav-item"><a class="nav-link" href="<c:url value="/registro/list"/>"><span class="oi oi-book" title="" aria-hidden="true"></span>&nbsp; <fmt:message key="menu.registro/list"/>  </a>
        <li class="nav-item"><a class="nav-link" href="<c:url value="/notificacion/list"/>"><span class="oi oi-document" title="" aria-hidden="true"></span>&nbsp; <fmt:message key="menu.notificaciones"/> </a>
        <li class="nav-item"><a class="nav-link" href="<c:url value="/datosPersonales"/>"><span class="oi oi-folder" title="" aria-hidden="true"></span>&nbsp; <fmt:message key="menu.datosPersonales"/> </a>
    </ul>
</div>
<script>
    $('a').click(function () {
        $(this).html("<span class='oi oi-loop-circular rotating'></span>");
    });
</script>