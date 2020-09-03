<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="card mb-12 border-0 p-2">
    <div class="card-title border-bottom verde h5 paddingBottomEstandard"><fmt:message key="anonim.title"/></div>

    <p class="card-text lh15"><fmt:message key="anonim.descripcio"/></p>

    <form name="infoTramiteAnonimoForm" method="post" action="${entornoUrl}/sistrafront/zonaperfront/protected/infoTramiteAnonimo.do">
        <input type="text" name="idPersistencia" size="35" value="">
        <input type="submit" value="Iniciar">
    </form>
</div>