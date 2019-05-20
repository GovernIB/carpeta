



// CSRF

// var tokenCSRF = $("meta[name='_csrf']").attr("content");
// var headerCSRF = $("meta[name='_csrf_header']").attr("content");
var tokenCSRF = "";
var headerCSRF = "";

if (headerCSRF == "") {
	 headerCSRF = "X-CSRF-TOKEN";
}


// configuració de URLs


var	APP_ = "http://caibter.indra.es/sistramitfront/asistente/"
	,APP_SERVIDOR = "http://caibter.indra.es/sistramitfront/asistente/";

var APP_LITERALS = "http://caibter.indra.es/sistramitfront/asistente/js/literales.js";


/* tramit */

var	APP_TRAMIT_INFO = APP_SERVIDOR + "informacionTramite.json"
	,APP_TRAMIT_PAS = APP_SERVIDOR + "irAPaso.json"
	,APP_TRAMIT_SUPORT = APP_SERVIDOR + "formularioSoporte.json"
	,APP_TRAMIT_CLAU_DESCARREGA = APP_SERVIDOR + "descargarClave.html"
	,APP_TRAMIT_DESCONECTA = APP_SERVIDOR + "logout.html"
	,APP_TRAMIT_CANCELA = APP_SERVIDOR + "cancelarTramite.json";


/* pas emplenar */

var APP_FORM_URL = APP_SERVIDOR + "rf/abrirFormulario.json"
	,APP_FORM_PDF = APP_SERVIDOR + "rf/descargarFormulario.html"
	,APP_FORM_XML = APP_SERVIDOR + "rf/descargarXmlFormulario.html";


/* pas annexar */

var APP_ANNEXE_ANNEXA = APP_SERVIDOR + "ad/anexarDocumento.json"
	,APP_ANNEXE_ESBORRA = APP_SERVIDOR + "ad/borrarDocumento.json"
	,APP_ANNEXE_DESCARREGA = APP_SERVIDOR + "ad/descargarDocumento.html"
	,APP_PLANTILLA_DESCARREGA = APP_SERVIDOR + "ad/descargarPlantilla.html";


/* pas taxa */

var APP_TAXA_URL = APP_SERVIDOR + "pt/iniciarPago.json"
	,APP_TAXA_VALIDAR = APP_SERVIDOR + "pt/verificarPagoIniciado.json"
	,APP_TAXA_DESCARTAR = APP_SERVIDOR + "pt/cancelarPagoIniciado.json"
	,APP_TAXA_JUSTIFICANT = APP_SERVIDOR + "pt/descargarJustificante.html"
	,APP_TAXA_PRESENCIAL = APP_SERVIDOR + "pt/cartaPago.json";


/* pas registrar */

var APP_SIGNA = APP_SERVIDOR + "rt/firmarDocumento.json"
	,APP_SIGNATURA_URL = APP_SERVIDOR + "rt/firmarDocumento.json"
	,APP_TRAMIT_REGISTRA = APP_SERVIDOR + "rt//registrarTramite.json"
	,APP_REGISTRE_DESCARREGA = APP_SERVIDOR + "rt/descargarDocumento.html"
	,APP_TRAMIT_REINTENTA = APP_SERVIDOR + "rt/reintentar.json";

/* pas guardar */

var APP_TRAMIT_JUSTIFICANT = APP_SERVIDOR + "gj/descargarJustificante.html",
	APP_GUARDA_DESCARREGA = APP_SERVIDOR + "gj/descargarDocumento.html",
	APP_TRAMIT_SURT = "http://www.google.es"; /* TODO: HAY QUE PASARLO AL JSON DEL PASO */