<%@include file="/WEB-INF/views/includes.jsp"%>
<div id="imc-carrega-ini" class="imc-carrega-inicial imc-amaga" aria-hidden="true">

    <h1><fmt:message key="carga.carpeta"/></h1>

    <!-- gestió errors -->

    <div class="imc--errors" aria-hidden="true">

        <div><p><fmt:message key="carga.error"/></p></div>

        <ul class="imc--botonera">
            <li>
                <button type="button" class="imc-bt imc--ico imc--torna imc--error-torna" data-tipus="torna"><span><fmt:message key="carga.recargar"/></span></button>
            </li>
            <li>
                <p><a href="http://www.caib.es/"><span><fmt:message key="carga.goib"/></span></a></p>
            </li>
        </ul>

    </div>

</div>