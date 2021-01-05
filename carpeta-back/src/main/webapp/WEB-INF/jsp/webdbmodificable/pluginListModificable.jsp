
<%@page import="es.caib.carpeta.back.controller.superadmin.PluginFrontSuperAdminController"%>
<script type="text/javascript">


function testPlugin(pluginID) {

      var url = '<c:url value="${contexte}/testplugin/"/>' + pluginID;

      var administrationID = prompt("Insereix DNI per Plugin " + pluginID, "");

      url = url + "?administrationID=" + encodeURIComponent(administrationID) + "&urlBase=" + encodeURIComponent(window.location.href);
      goTo(url);
  }
  
  
  </script>