<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="imc--explicacio-detallada imc--e-d registreDetall">
    <p><strong><fmt:message key="registre.detall.nom"/></strong></p>
    <ul class="dadesRegistre">
        <li>
            <div class="capsaEsquerra">
                <ul class="dadesRegistre">
                    <li><fmt:message key="registre.detall.data"/> <strong><fmt:message key="registre.detall.data.valor"/></strong>.</li>
                    <li><fmt:message key="registre.detall.oficina"/> <strong><fmt:message key="registre.detall.oficina.valor"/></strong>.</li>
                    <li><fmt:message key="registre.detall.organisme"/> <strong><fmt:message key="registre.detall.organisme.valor"/></strong>.</li>
                    <li><fmt:message key="registre.detall.extracte"/> <strong><fmt:message key="registre.detall.extracte.valor"/></strong>.</li>
                    <li><fmt:message key="registre.detall.idioma"/> <strong><fmt:message key="idioma.catala"/></strong>.</li>
                    <li><fmt:message key="registre.detall.tipus"/> <strong><fmt:message key="registre.detall.tipus.valor"/></strong>.</li>
                </ul>
            </div>

            <div class="capsaDreta">
                <ul class="dadesRegistre">
                    <li><strong><fmt:message key="registre.detall.annexos"/></strong></li>
                    <li>- <fmt:message key="registre.detall.annexos.1"/></li>
                    <li>- <fmt:message key="registre.detall.annexos.2"/></li>
                    <li>- <fmt:message key="registre.detall.annexos.3"/></li>
                </ul>
            </div>
            <div class="capsaDreta">
                <ul class="dadesRegistre">
                    <li><strong><fmt:message key="registre.detall.interessats"/></strong>.</li>
                    <li>- <fmt:message key="registre.detall.interessats.1"/> (<strong><fmt:message key="registre.detall.representant"/></strong>: <fmt:message key="registre.detall.representant.1"/>)</li>
                    <li>- <fmt:message key="registre.detall.interessats.2"/></li>
                </ul>
            </div>

            <div class="capsaEsquerra">
                <div class="imc--annexats">
                    <ul class="dadesRegistre">
                        <li><strong><fmt:message key="registre.detall.justificant"/></strong></li>
                        <li class="nomJustificant">
                            <strong><fmt:message key="registre.detall.justificant.valor"/></strong>
                            <button type="button" class="imc-bt imc--ico imc--descarrega" title="<fmt:message key="registre.detall.descarregar"/>"><span><fmt:message key="registre.detall.descarregar"/></span></button>
                        </li>
                    </ul>
                </div>
            </div>

        </li>
    </ul>
</div>