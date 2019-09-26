//Enlaces entoda la fila de la tabla
jQuery(document).ready(function($) {
	$(".clickable-row").click(function() {
		var $th = $(this);
		window.open($th.attr('data-href'), $th.attr('data-target'));
	});
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

/** Salir de la Carpeta **/
function sortirCarpeta(){
	document.getElementById('logout').submit();
}

/** Acceder a una url **/
function goTo(url) {
	document.location.href=url;
}

// /** Breadcrumb **/
// function breadcrumbs(valoresMiga) {
// 	alert(Object.values(valoresMiga));
// 	var numValores = Object.values(valoresMiga[0]);
// 	alert(numValores * 2 + 1);
// 	var contenidoMiga = "";
// 	for (var i = 1; i < (numValores * 2 + 1); i+=2){
// 		alert(i);
// 		contenidoMiga = contenidoMiga + "<li class=\"breadcrumb-item active\"><a href=\""+valoresMiga[i]+"\">" + valoresMiga[i+1] + "</a></li>";
// 		alert(contenidoMiga);
// 	}
//
// 	$("#migaPan").html(contenidoMiga);
// }