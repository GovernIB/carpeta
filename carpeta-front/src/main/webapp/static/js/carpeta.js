/** Recalcula els menús segons ample de finestra **/
$( window ).resize(function() {
  var ventana_ancho = $(window).width();
  if(ventana_ancho<641){
	$("#navigation").removeClass("no-visible");
	$("#opcionsLlarg").addClass("no-visible");
	$("#imc-menu").addClass("no-visible");
  }else{
	$("#navigation").addClass("no-visible");
	$("#opcionsLlarg").removeClass("no-visible");
	$("#imc-menu").removeClass("no-visible");
  }
});

/** Calcula el tamany del height del menú 
$(document).ready(function($){
	altMenu = $('#imc-cap').outerHeight();
	alert(altMenu);
	$('#imc-menu').css({'padding-top': altMenu});
});**/

/** Obre els idiomes disponibles del menú **/
$(document).ready(function() {
$("#imc-bt-idioma").hover(function(){
  $("#idioma").css("display", "grid");
  }, function(){
  $("#idioma").css("display", "none");
});

});

/** Mostra els menús segons ample de finestra **/
$(document).ready(function($){
  var ventana_ancho = $(window).width();
  if(ventana_ancho<780){
	$("#id_ca").text("ca");
	$("#id_es").text("es");
  }
  if(ventana_ancho<641){
	$("#navigation").removeClass("no-visible");
	$("#opcionsLlarg").addClass("no-visible");
	$("#imc-menu").addClass("no-visible");
  }
});


/** Obre el quadre d'ajuda **/
function obrirAjuda(){
	$("#imc-suport").addClass("imc--on");
    $("#imc-suport-capsa").addClass("imc--on");
}

/** Tanca el quadre d'ajuda **/
function tancarAjuda(){
	$("#imc-suport").removeClass("imc--on");
    $("#imc-suport-capsa").removeClass("imc--on");
}

/** Per sortir de la Carpeta **/
function sortirCarpeta(){
    document.getElementById('logout').submit();
}


/** Controla el menú per a mòbils **/
function desplegarMenu(){
	var obert = $("#menu-mobil").hasClass("obert");
	if(!obert){
		$("#menu-mobil").addClass("obert");
		$("#menu-mobil").removeClass("invisible");
	}else{
		$("#menu-mobil").removeClass("obert");
		$("#menu-mobil").addClass("invisible");
	}
}
