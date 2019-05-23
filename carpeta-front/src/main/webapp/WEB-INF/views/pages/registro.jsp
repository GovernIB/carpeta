<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="imc--explicacio-detallada imc--e-d registreDetall">
    <p><strong><fmt:message key="registro.detalle.nom"/></strong></p>
    <ul class="dadesRegistre">
        <li>
            <div class="capsaEsquerra">
                <ul class="dadesRegistre">
                    <li><fmt:message key="registro.detalle.fecha"/> <strong><fmt:message key="registro.detalle.fecha.valor"/></strong>.</li>
                    <li><fmt:message key="registro.detalle.oficina"/> <strong><fmt:message key="registro.detalle.oficina.valor"/></strong>.</li>
                    <li><fmt:message key="registro.detalle.organismo"/> <strong><fmt:message key="registro.detalle.organismo.valor"/></strong>.</li>
                    <li><fmt:message key="registro.detalle.extracto"/> <strong><fmt:message key="registro.detalle.extracto.valor"/></strong>.</li>
                    <li><fmt:message key="registro.detalle.idioma"/> <strong><fmt:message key="idioma.catala"/></strong>.</li>
                    <li><fmt:message key="registro.detalle.tipo"/> <strong><fmt:message key="registro.detalle.tipo.valor"/></strong>.</li>
                </ul>
            </div>

            <div class="capsaDreta">
                <ul class="dadesRegistre">
                    <li><strong><fmt:message key="registro.detalle.anexos"/></strong></li>
                    <li>- <fmt:message key="registro.detalle.anexos.1"/></li>
                    <li>- <fmt:message key="registro.detalle.anexos.2"/></li>
                    <li>- <fmt:message key="registro.detalle.anexos.3"/></li>
                </ul>
            </div>
            <div class="capsaDreta">
                <ul class="dadesRegistre">
                    <li><strong><fmt:message key="registro.detalle.interesados"/></strong>.</li>
                    <li>- <fmt:message key="registro.detalle.interesados.1"/> (<strong><fmt:message key="registro.detalle.interesados"/></strong>: <fmt:message key="registro.detalle.representante.1"/>)</li>
                    <li>- <fmt:message key="registro.detalle.interesados.2"/></li>
                </ul>
            </div>

            <div class="capsaEsquerra">
                <div class="imc--annexats">
                    <ul class="dadesRegistre">
                        <li><strong><fmt:message key="registro.detalle.justificante"/></strong></li>
                        <li class="nomJustificant">
                            <strong><fmt:message key="registro.detalle.justificante.valor"/></strong>
                            <button type="button" class="imc-bt imc--ico imc--descarrega" title="<fmt:message key="registro.detalle.descargar"/>"><span><fmt:message key="registro.detalle.descargar"/></span></button>
                        </li>
                    </ul>
                </div>
            </div>

        </li>
    </ul>
</div>