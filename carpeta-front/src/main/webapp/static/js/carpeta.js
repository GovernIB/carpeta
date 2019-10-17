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

/** Acceder a una url **/
function goToWindow(url) {
	window.open(url);
}

/**  DATEPICKER **/
$(function() {
	$('#fechaInicio').datetimepicker({
		locale: 'ca',
		format: 'D/MM/YYYY',
		icons: {
			time: 'oi oi-clock',
			date: 'oi oi-calendar',
			up: 'oi oi-chevron-top',
			down: 'oi oi-chevron-bottom'
		}
	});
});

$(function() {
	$('#fechaFin').datetimepicker({
		locale: 'ca',
		format: 'D/MM/YYYY',
		icons: {
			time: 'oi oi-clock',
			date: 'oi oi-calendar',
			up: 'oi oi-chevron-top',
			down: 'oi oi-chevron-bottom'
		}
	});
});