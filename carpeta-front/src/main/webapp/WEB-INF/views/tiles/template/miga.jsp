<%@include file="/WEB-INF/views/includes.jsp"%>

<nav aria-label="<fmt:message key="carpeta.miga"/>" class="miga">
    <div class="migaDiv">
        <ol class="breadcrumb" id="migaPan">

            <c:forEach items="${breadcrumb}" var="bread" varStatus="index">
                <li class="breadcrumb-item active">
                    <c:if test="${!index.last}">
                        <a href="<c:url value="/${bread}"/>"><fmt:message key="menu.${bread}"/></a>
                    </c:if>
                    <c:if test="${index.last}">
                        <fmt:message key="menu.${bread}"/>
                    </c:if>
                </li>
            </c:forEach>

        </ol>
    </div>
</nav>