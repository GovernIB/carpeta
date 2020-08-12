
<%@page import="es.caib.carpeta.back.controller.superadmin.PluginFrontSuperAdminController"%>
<script type="text/javascript">

<%--
"<fmt:message key="motiuesborrar"/>:", "");

if (reason != null) {      
  document.getElementById("motiuRebuig").value=reason;
  openModalSubmit('' +

--%>


function testPlugin(pluginID) {

      var url = '<c:url value="${contexte}/testplugin/"/>' + pluginID;

      var administrationID = prompt("XYZ ZZZ ZZZ Insereix DNI per Plugin " + pluginID, "43");

      url = url + "?administrationID=" + encodeURIComponent(administrationID) + "&urlBase=" + encodeURIComponent(window.location.href);
      goTo(url);
  }
  
  
  </script>