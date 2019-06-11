/** Recalcula los menús según ancho de ventana **/
$( window ).resize(function() {
  var ventana_ancho = $(window).width();
  if(ventana_ancho>=810){
	$("#navigation").addClass("noVisible");
	$("#opcionesLargo").removeClass("noVisible");
	$("#opcionesMenu").removeClass("noVisible");
  }
  if(ventana_ancho<810 & ventana_ancho>=640){
	$("#navigation").addClass("noVisible");
	$("#opcionesLargo").removeClass("noVisible");
	$("#opcionesMenu").removeClass("noVisible");
	$("#menuUsuario").removeClass("col-9");
	$("#menuUsuario").addClass("col-8");
  }
  if(ventana_ancho<641){
	$("#navigation").removeClass("noVisible");
	$("#opcionesLargo").addClass("noVisible");
	$("#opcionesMenu").addClass("noVisible");

  }
});

/** Muestra los menús según ancho de ventana **/
$(document).ready(function($){
  var ventana_ancho = $(window).width();
  if(ventana_ancho<810 & ventana_ancho>=640){
	$("#opcionesLargo").removeClass("col-5");
	$("#opcionesLargo").addClass("col-3");
	$("#idCa").text("ca");
	$("#idEs").text("es");
  }
  if(ventana_ancho<641){
	$("#navigation").removeClass("noVisible");
	$("#opcionesLargo").addClass("noVisible");
	$("#opcionesMenu").addClass("noVisible");
	$("#menuUsuario").removeClass("col-8");
	$("#menuUsuario").addClass("col-10");
  }
});

/** Abre el cuadro de ayuda **/
function abrirAyuda(){
	$("#soporte").addClass("on");
    $("#cajaSoporte").addClass("on");
}

/** Cierra el cuadro de ayuda **/
function cerrarAyuda(){
	$("#soporte").removeClass("on");
    $("#cajaSoporte").removeClass("on");
}

/** Per sortir de la Carpeta **/
function sortirCarpeta(){
	document.getElementById('logout').submit();
}