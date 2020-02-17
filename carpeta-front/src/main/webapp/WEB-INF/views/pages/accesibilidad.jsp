<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="card mb-12 border-0">
    <span class="h5 card-title border-bottom verde"><fmt:message key="accesibilidad.declaracion"/></span>
    <nav title="<fmt:message key="accesibilidad.declaracion"/>">
        <p><fmt:message key="accesibilidad.declaracion.contenido.1"/></p>
        <p><fmt:message key="accesibilidad.declaracion.contenido.2"/></p>
    </nav>

    <span class="h5 card-title border-bottom verde"><fmt:message key="accesibilidad.compliment"/></span>
    <nav title="<fmt:message key="accesibilidad.compliment"/>">
        <p><fmt:message key="accesibilidad.compliment.contenido.1"/></p>
    </nav>

    <span class="h5 card-title border-bottom verde"><fmt:message key="accesibilidad.noAccesible"/></span>
    <nav title="<fmt:message key="accesibilidad.noAccesible"/>">
        <p><fmt:message key="accesibilidad.noAccesible.contenido.1"/></p>
        <p><fmt:message key="accesibilidad.noAccesible.contenido.2"/></p>
    </nav>

    <span class="h5 card-title border-bottom verde"><fmt:message key="accesibilidad.preparacion"/></span>
    <nav title="<fmt:message key="accesibilidad.preparacion"/>">
        <p><fmt:message key="accesibilidad.preparacion.contenido.1"/></p>
        <p><fmt:message key="accesibilidad.preparacion.contenido.2"/></p>
    </nav>

    <span class="h5 card-title border-bottom verde"><fmt:message key="accesibilidad.observaciones"/></span>
    <nav title="<fmt:message key="accesibilidad.observaciones"/>">
        <p><fmt:message key="accesibilidad.observaciones.contenido.1"/></p>
        <p><fmt:message key="accesibilidad.observaciones.contenido.2"/></p>
    </nav>

    <span class="h5 card-title border-bottom verde"><fmt:message key="accesibilidad.procedimiento"/></span>
    <nav title="<fmt:message key="accesibilidad.procedimiento"/>">
        <p><fmt:message key="accesibilidad.procedimiento.contenido.1"/></p>
    </nav>

    <span class="h5 card-title border-bottom verde"><fmt:message key="accesibilidad.opcional"/></span>
    <nav title="<fmt:message key="accesibilidad.opcional"/>">
        <p><fmt:message key="accesibilidad.opcional.contenido.1"/></p>
        <p><fmt:message key="accesibilidad.opcional.contenido.2"/></p>
        <p><fmt:message key="accesibilidad.opcional.contenido.3"/></p>
        <p><a class="mr-auto" href="https://www.w3.org/WAI/WCAG1AA-Conformance"><img src="${pageContext.request.contextPath}/static/img/wcag2AA.png" class="" alt="<fmt:message key="accesibilidad.img.wai"/>"/></a></p>
        <p><fmt:message key="accesibilidad.opcional.contenido.4"/></p>
    </nav>
</div>