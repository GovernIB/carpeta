<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="card mb-12 border-0">
    <span class="h5 card-title border-bottom verde"><fmt:message key="accesibilidad.compromiso"/></span>
    <nav title="<fmt:message key="accesibilidad.compromiso"/>">
        <p><fmt:message key="accesibilidad.compromiso.contenido.1"/></p>
        <p><fmt:message key="accesibilidad.compromiso.contenido.2"/></p>
        <p><a class="mr-auto" href="https://www.w3.org/WAI/WCAG1AA-Conformance"><img src="${pageContext.request.contextPath}/static/img/wcag1AA.png" class="" alt="<fmt:message key="accesibilidad.img.wai"/>"/></a></p>
        <p><fmt:message key="accesibilidad.compromiso.contenido.3"/></p>
    </nav>

    <span class="h5 card-title border-bottom verde"><fmt:message key="accesibilidad.tecnologia"/></span>
    <nav title="<fmt:message key="accesibilidad.tecnologia"/>">
        <p><fmt:message key="accesibilidad.tecnologia.contenido.1"/></p>
        <p><fmt:message key="accesibilidad.tecnologia.contenido.2"/></p>
        <p>
            <a class="mr-auto" href="http://validator.w3.org/check?uri=referer"><img src="${pageContext.request.contextPath}/static/img/valid-xhtml10.png" class="" alt="<fmt:message key="accesibilidad.img.xhtml"/>"/></a>
            <a class="mr-auto" href="http://jigsaw.w3.org/css-validator/"><img src="${pageContext.request.contextPath}/static/img/vcss.gif" class="" alt="<fmt:message key="accesibilidad.img.css"/>"/></a>
        </p>
    </nav>

    <span class="h5 card-title border-bottom verde"><fmt:message key="accesibilidad.herramientas"/></span>
    <nav title="<fmt:message key="accesibilidad.herramientas"/>">
        <p><fmt:message key="accesibilidad.herramientas.contenido.1"/></p>
        <p><fmt:message key="accesibilidad.herramientas.contenido.2"/></p>
    </nav>
</div>