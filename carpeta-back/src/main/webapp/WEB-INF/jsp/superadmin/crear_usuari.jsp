<%@ page language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>

    <!-- Contingut de la pagina -->
    <div class="lead" style="margin-bottom: 10px">
        <h3><fmt:message key="usuari.alta.title" /></h3>
        <h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"> <fmt:message key="usuari.alta.subtitle" /></h5>
    </div>
    <form id="usuariForm" action="<c:url value="${contexte}/checkusername"/>"  method="post" enctype="multipart/form-data">
        <input id="nou" name="nou" type="hidden" value="true" />
        <div class="module_content">
            <div class="tab_container">

                <table
                    class="tdformlabel table-condensed table table-bordered table-striped marTop10  ">
                    <tbody>

                        <tr id="usuari_username_rowid">
                            <td><label> <fmt:message key="usuari.username" /> &nbsp;(*)
                            </label></td>
                            <td><input id="username"
                                name="username"
                                class="col-md-6 form-control "
                                type="text" value="" maxlength="255" /></td>
                        </tr>
                    </tbody>
                </table>

            </div>

        </div>



        <div class="navbar-form float-right">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="genapp.save"/>">
            <input type="button" class="btn" onclick="goTo('<c:url value="${contexte}/list"/>')" value="<fmt:message key="genapp.cancel"/>" >
        </div>
    </form>
    <!-- FINAL DIV CONTINGUT -->
