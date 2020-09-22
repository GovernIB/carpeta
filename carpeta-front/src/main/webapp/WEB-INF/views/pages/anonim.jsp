<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="card mb-12 border-0 p-2">
    <div class="card-title border-bottom verde h5 paddingBottomEstandard"><fmt:message key="anonim.title"/></div>

    <p class="card-text lh15"><fmt:message key="anonim.descripcio"/></p>
    <c:if test="${error != null}">
        <p class="" style="color: red;"><fmt:message key="anonim.error"></fmt:message></p>
    </c:if>
    <form name="infoTramiteAnonimoForm" method="post" action="/carpeta/anonim">
        <input type="text" name="claveAcceso" size="35" value="">
        <input type="submit" value="Iniciar">
    </form>
    <div class="pt-3 pb-3">
        <a href="<c:url value="/inicio"/>"><span class="oi oi-arrow-left verde p-1" title="" aria-hidden="true"> </span><fmt:message key="anonim.atras"></fmt:message></a>
    </div>
</div>