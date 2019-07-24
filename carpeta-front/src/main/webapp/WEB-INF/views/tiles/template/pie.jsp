<%@include file="/WEB-INF/views/includes.jsp"%>
<footer id="footer" class="containerPie">
    <div class="row mr-auto">

        <div class="col-3 governPie">
            <strong><fmt:message key="pie.govern"/></strong>
        </div>

        <div class="col-6 opcionesPie">
            <ul>

                <li><a href="mapaWeb"><fmt:message key="pie.mapa"/></a></li>

                <li><a href="avisLegal" target="_blank"><fmt:message key="pie.legal"/></a></li>

                <li><a href="<c:url value="/accesibilidad"/>"><span class="oi oi-people" title="" alt="" aria-hidden="true"></span> <fmt:message key="menu.accesibilidad"/></a></li>
            </ul>
        </div>

        <div class="col-3 redesPie">

            <ul>

                <li>
                    <a class="">
                        <span class="">Youtube</span>
                    </a>

                    <a class="">
                        <span class="">Instagram</span>
                    </a>
                </li>


                <li>
                    <a class="">
                        <span class="">Twitter</span>
                    </a>

                    <a class="">
                        <span class="">Facebook</span>
                    </a>
                </li>

            </ul>

        </div>

    </div>
</footer>