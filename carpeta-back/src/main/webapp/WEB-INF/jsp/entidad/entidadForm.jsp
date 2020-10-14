<%--  XYZ ZZZ  Eliminar ????? --%>

<%@ page language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<center><h1><spring:message code="entidad.entidad"/> </h1></center>


<form:form modelAttribute="entidadForm" method="post" cssClass="form-horizontal" enctype="multipart/form-data">
    <div class="row">
        <div class="col-xs-12">

            <div class="panel panel-warning">

                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-pencil-square-o"></i>
                        <strong>
                            <c:if test="${not empty entidadForm.entidad.id}"><spring:message code="entidad.editar"/></c:if>
                            <c:if test="${empty entidadForm.entidad.id}"><spring:message code="entidad.nuevo"/></c:if>
                        </strong>
                    </h3>
                </div>

                <!-- Formulario -->

                <div class="panel-body">



                    <!-- CAMPOS TRADUCIDOS DE LA ENTIDAD -->
                    <ul <%--class="nav nav-tabs"--%> id="myTab">
                        <c:forEach items="${idiomas}" var="idioma" varStatus="index">
                            <li><a href="#${idioma.nombreCorto}" data-toggle="tab"><spring:message code="idioma.${idioma.nombreCorto}"/></a></li>
                        </c:forEach>
                    </ul>
                    <div id='content' <%--class="tab-content"--%>>
                        <c:forEach items="${idiomas}" var="idioma" varStatus="index">

                            <div <%--class="tab-pane"--%> id="${idioma.nombreCorto}">
                                <div class="form-group col-xs-12">
                                    <div class="form-group col-xs-6 senseMargeLat">
                                        <div class="col-xs-4 pull-lef etiqueta_carpeta control-label textEsq">
                                            <form:label path="entidad.traducciones['${idioma.nombreCorto}'].nombre"><span class="text-danger">*</span>
                                                <spring:message code="carpeta.nombre"/> ${idioma.nombreCorto}</form:label>
                                        </div>
                                        <div class="col-xs-8">
                                            <form:input path="entidad.traducciones['${idioma.nombreCorto}'].nombre" cssClass="form-control"/>
                                            <form:errors path="entidad.traducciones['${idioma.nombreCorto}'].nombre" cssClass="help-block" element="span"/>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div <%--class="tab-pane"--%> id="${idioma.nombreCorto}">
                                <div class="form-group col-xs-12">
                                    <div class="form-group col-xs-6 senseMargeLat">
                                        <div class="col-xs-4 pull-lef etiqueta_carpeta control-label textEsq">
                                            <form:label path="entidad.traducciones['${idioma.nombreCorto}'].descripcion"><span class="text-danger">*</span>
                                                <spring:message code="carpeta.descripcion"/> ${idioma.nombreCorto}</form:label>
                                        </div>
                                        <div class="col-xs-8">
                                            <form:input path="entidad.traducciones['${idioma.nombreCorto}'].descripcion" cssClass="form-control"/>
                                            <form:errors path="entidad.traducciones['${idioma.nombreCorto}'].descripcion" cssClass="help-block" element="span"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>


                    <div class="col-xs-12">
                        <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                            <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                                <form:label path="entidad.codigoDir3"><span class="text-danger">*</span> <spring:message code="entidad.codigoDir3"/></form:label>
                            </div>
                            <div class="col-xs-8">
                                <form:input path="entidad.codigoDir3" cssClass="form-control"/> <form:errors path="entidad.codigoDir3" cssClass="help-block" element="span"/>

                            </div>
                        </div>
                    </div>


                    <div class="col-xs-12">
                        <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                            <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                                <form:label path="entidad.colorMenu"><span class="text-danger">*</span> <spring:message code="entidad.colorMenu"/></form:label>
                            </div>
                            <div class="col-xs-8">
                                <form:input path="entidad.colorMenu" cssClass="form-control"/> <form:errors path="entidad.colorMenu" cssClass="help-block" element="span"/>

                            </div>
                        </div>
                    </div>


                    <div class="col-xs-12">
                        <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                            <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                                <form:label path="entidad.textoPie"><span class="text-danger">*</span> <spring:message code="entidad.textoPie"/></form:label>
                            </div>
                            <div class="col-xs-8">
                                <form:input path="entidad.textoPie" cssClass="form-control"/> <form:errors path="entidad.textoPie" cssClass="help-block" element="span"/>

                            </div>
                        </div>
                    </div>

                    <!--  logo menu -->
                    <div class="col-xs-12">
                        <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                            <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                                <form:label path="logoMenu"><spring:message code="entidad.logoMenu"/></form:label>
                            </div>
                            <div class="col-xs-8">
                                <div class="input-group">
                                                        <span class="input-group-btn">
                                                            <span class="btn btn-success btn-sm btn-file">
                                                                Explorar&hellip; <input id="logoMenu" name="logoMenu" type="file" multiple>
                                                            </span>
                                                        </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <spring:message code="entidad.logoMenu.maxHeight"/><br><spring:message code="entidad.logoMenu.maxWidth"/>
                                <form:errors path="logoMenu" cssClass="help-block" element="span"/>
                            </div>
                        </div>
                        <c:if test="${not empty entidadForm.entidad.logoMenu}">
                            <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                                <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                                    <form:label path="logoMenu"><spring:message code="entidad.logoMenu.existente"/></form:label>
                                    <form:hidden path="entidad.logoMenu.id"/>
                                </div>
                                <div class="col-xs-8 arxiu_actual">
                                    <a href="<c:url value="/archivo/${entidadForm.entidad.logoMenu.id}"/>" target="_blank">${entidadForm.entidad.logoMenu.nombre}</a>  <br>
                                    <form:checkbox path="borrarLogoMenu"></form:checkbox><spring:message code="carpeta.eliminar"/>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <!-- Fi logo menu -->

                    <!--  logo pie -->
                    <div class="col-xs-12">
                        <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                            <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                                <form:label path="logoPie"><spring:message code="entidad.logoPie"/></form:label>
                            </div>
                            <div class="col-xs-8">
                                <div class="input-group">
                                                        <span class="input-group-btn">
                                                            <span class="btn btn-success btn-sm btn-file">
                                                                Explorar&hellip; <input id="logoPie" name="logoPie" type="file" multiple>
                                                            </span>
                                                        </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <form:errors path="logoPie" cssClass="help-block" element="span"/>
                            </div>
                        </div>
                        <c:if test="${not empty entidadForm.entidad.logoPie}">
                            <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                                <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                                    <form:label path="logoPie"><spring:message code="entidad.logoPie.existente"/></form:label>
                                    <form:hidden path="entidad.logoPie.id"/>
                                </div>
                                <div class="col-xs-8 arxiu_actual">
                                    <a href="<c:url value="/archivo/${entidadForm.entidad.logoPie.id}"/>" target="_blank">${entidadForm.entidad.logoPie.nombre}</a>  <br>
                                    <form:checkbox path="borrarLogoPie"></form:checkbox><spring:message code="carpeta.eliminar"/>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <!-- Fi logo pie -->


                    <!--  ficheroCss -->
                    <div class="col-xs-12">
                        <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                            <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                                <form:label path="ficheroCss"><spring:message code="entidad.ficheroCss"/></form:label>
                            </div>
                            <div class="col-xs-8">
                                <div class="input-group">
                                                        <span class="input-group-btn">
                                                            <span class="btn btn-success btn-sm btn-file">
                                                                Explorar&hellip; <input id="ficheroCss" name="logoPie" type="file" multiple>
                                                            </span>
                                                        </span>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <form:errors path="ficheroCss" cssClass="help-block" element="span"/>
                            </div>
                        </div>
                        <c:if test="${not empty entidadForm.entidad.ficheroCss}">
                            <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                                <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                                    <form:label path="logoPie"><spring:message code="entidad.ficheroCss.existente"/></form:label>
                                    <form:hidden path="entidad.ficheroCss.id"/>
                                </div>
                                <div class="col-xs-8 arxiu_actual">
                                    <a href="<c:url value="/archivo/${entidadForm.entidad.ficheroCss.id}"/>" target="_blank">${entidadForm.entidad.ficheroCss.nombre}</a>  <br>
                                    <form:checkbox path="borrarFicheroCss"></form:checkbox><spring:message code="carpeta.eliminar"/>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <!-- Fi ficheroCss -->



                    <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                        <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                            <form:label path="entidad.administradores"><spring:message code="entidad.administradores"/></form:label>
                        </div>
                        <div class="col-xs-8">
                            <form:select path="entidad.administradores" items="${administradoresEntidad}" itemValue="id" itemLabel="nombreCompleto" multiple="true" cssClass="chosen-select"/> <form:errors path="entidad.administradores" cssClass="help-block" element="span"/>
                        </div>
                    </div>

                    <div class="col-xs-12">
                        <div class="form-group col-xs-6 espaiLinies senseMargeLat">
                            <div class="col-xs-4 pull-left etiqueta_carpeta control-label textEsq">
                                <form:label path="entidad.contexto"><span class="text-danger">*</span> <spring:message code="entidad.contexto"/></form:label>
                            </div>
                            <div class="col-xs-8">
                                <form:input path="entidad.contexto" cssClass="form-control"/> <form:errors path="entidad.contexto" cssClass="help-block" element="span"/>

                            </div>
                        </div>
                    </div>
                </div>
            </div>




            <!-- Botonera -->

            <input type="submit" value="<spring:message code="carpeta.guardar"/>" onclick="" class="btn btn-warning btn-sm"/>
            <input type="button" value="<spring:message code="carpeta.cancelar"/>" onclick="goTo('<c:url value="/entidad/list"/>')" class="btn btn-sm">


        </div>

    </div>
</form:form>


<br/>
<br/>
<br/>
<br/>
<br/>
<br/>


<center></center>
