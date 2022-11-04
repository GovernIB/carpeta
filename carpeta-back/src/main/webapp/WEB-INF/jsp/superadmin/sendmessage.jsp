<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div class="lead" style="margin-bottom: 10px">
    <label style="font-size: 1.25rem; font-weight: bold;">
       <spring:message code="sendmessage.title" />
    </label>
</div>
<form id="ciutadaForm"
    action="<c:url value="${contexte}/sendmessage/${ciutadaid}"/>"
    method="post" enctype="multipart/form-data">
    <input id="nou" name="nou" type="hidden" value="false" />
    <div class="module_content">
        <div class="tab_container">

            <table id="ciutada_tableid"
                class="tdformlabel table-sm table table-bordered table-striped marTop10 table-genapp">
                <tbody>

                    <tr id="titol_rowid">
                        <td id="titol_columnlabelid"><label>
                                <spring:message code="sendmessage.titol" />
                                (*)
                        </label></td>
                        <td id="titol_columnvalueid"><input
                            id="titol" name="titol"
                            class="w-100 form-control  " type="text"
                            value="${titol}" maxlength="100" /></td>
                    </tr>

                    <tr id="ciutada_mobileId_rowid">
                        <td id="ciutada_mobileId_columnlabelid"><label>
                                <spring:message
                                    code="sendmessage.missatge" /> (*)
                        </label></td>
                        <td id="ciutada_mobileId_columnvalueid">
                            <table style="width: 100%">
                                <tr>
                                    <td><textarea id="missatge"
                                            name="missatge"
                                            class="form-control col-md-9-optional"
                                            style="overflow: auto; display: inline; resize: both;"
                                            wrap="soft" rows="4">${missatge}</textarea>
                                    </td>

                                </tr>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>

        </div>

    </div>



    <div class="navbar-form" style="text-align: right">
        <input type="submit" class="btn btn-primary"
            value=" <spring:message code="sendmessage.enviar" />">
        <input type="button" class="btn btn-secondary"
            onclick="goTo('<c:url value="${contexte}/list"/>')"
            value="<spring:message code="genapp.cancel" />">
    </div>
</form>


