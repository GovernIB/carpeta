<%@include file="/WEB-INF/views/includes.jsp"%>
<nav aria-label="breadcrumb" class="miga">
    <div class="migaDiv">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active"><a href="<c:url value="/"/>"><fmt:message key="menu.inicio"/></a></li>
            <li class="breadcrumb-item"><a href="<c:url value="/registros"/>"><fmt:message key="menu.registros"/></a></li>
            <li class="breadcrumb-item active" aria-current="page"><fmt:message key="registro.registro"/> ${asiento.numeroRegistroFormateado}</li>
        </ol>
    </div>
</nav>