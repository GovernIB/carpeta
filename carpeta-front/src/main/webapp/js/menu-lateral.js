// Document JavaScript

// elements

var	imc_html = $("html"),
	imc_marc = $("#imc-marc"),
	imc_bt_menu = $("#imc-bt-menu"),
	imc_marc = $("#imc-marc"),
	imc_marc_menu = $("#imc-marc-menu"),
	imc_molla_pa = $("#imc-molla-pa");
	

// appMollaPa

$.fn.appMollaPa = function(opcions){
	var settings = $.extend({
			contenidor: false
		}, opcions);
	this.each(function(){
		var	elm = $(this),
			inicia = function() {	
				
				elm
					.find("button")
						.off(".appMollaPa")
						.on("click.appMollaPa", activa);

			},
			activa = function(e) {

				var bt = $(this),
					contenidor = bt.closest("li"),
					estaMostrat = (contenidor.hasClass("imc-menu-mostrat")) ? true : false;
				
				imc_molla_pa
					.find(".imc-menu-mostrat")
						.removeClass("imc-menu-mostrat");

				if (!estaMostrat) {
					contenidor
						.addClass("imc-menu-mostrat");
				}

			};
		
		// inicia
		inicia();
		
	});
	return this;
}

imc_molla_pa
	.appMollaPa();


// appRellisca

$.fn.appRellisca = function(opcions){
	var settings = $.extend({
			espai: "horitzontal",
			sentit: "dreta",
			origen: $("#imc-bt-menu"),
			tanca: false
		}, opcions);
	this.each(function(){
		var	elm = $(this),
			origen = settings.origen,
			tanca = settings.tanca,
			posX,
			pos_movX,
			pos_novaX,
			pos_marcX,
			menu_pos,
			app_media_posicio,
			inicia = function() {	
				
				elm
					.on("touchstart.appRellisca", activa)
					.on("touchend.appRellisca", acaba);

			},
			activa = function(e) {

				posX = e.touches[0].screenX;
				menu_pos = parseInt(elm.css("transform").split(',')[4]);
				menu_pos_limit = menu_pos/2;
				pos_marcX = menu_pos;

				elm
					.on("touchmove.appRellisca", seguix);

			},
			seguix = function(e) {

				pos_novaX = e.touches[0].screenX - posX;
				pos_marcX = menu_pos + pos_novaX;

				if (pos_marcX <= menu_pos) {
					pos_marcX = menu_pos;
				}
				
				elm
					.css({
							"-moz-transform": "translateX("+pos_marcX+"px)",
							"-webkit-transform": "translateX("+pos_marcX+"px)",
							"-o-transform": "translateX("+pos_marcX+"px)",
							"-ms-transform": "translateX("+pos_marcX+"px)",
							"transform": "translateX("+pos_marcX+"px)"
						});
				
			},
			acaba = function(e) {

				if (menu_pos === pos_marcX) {
					return;
				}

				if ( pos_marcX <= menu_pos_limit ) {

					elm
						.removeAttr("style");

				} else {

					elm
						.removeClass("imc-visible")
						.attr("aria-hidden", "true")
						.removeAttr("style");

					origen
						.removeClass("imc-invisible")
						.attr("aria-hidden", "false");
					
					if (tanca) {

						tanca
							.removeClass("imc-visible")
							.attr("aria-hidden", "true");

					}

				}

				elm
					.off("touchmove.appRellisca");

			};
		
		// inicia
		inicia();
		
	});
	return this;
}


// appMenu

$.fn.appMenu = function(opcions){
	var settings = $.extend({
			contenidor: false
		}, opcions);
	this.each(function(){
		var	elm = $(this),
			marc_fons = imc_marc.find(".imc--fons:first"),
			inicia = function() {	
				
				elm
					.off(".appMenu")
					.on('click.appMenu', mostra);

				imc_marc
					.attr({ "tabindex": "-1", "aria-hidden": "true" })
					.appRellisca();

			},
			mostra = function() {

				imc_html
					.addClass("imc-menu-visible");

				imc_marc
					.attr("aria-hidden", "false");

				marc_fons
					.off(".appMenu")
					.on('click.appMenu', amaga);

			},
			amaga = function() {

				imc_html
					.removeClass("imc-menu-visible");

				imc_marc
					.attr("aria-hidden", "true");

				elm
					.off(".appMenu")
					.on('click.appMenu', mostra);

			};
		
		// inicia
		inicia();
		
	});
	return this;
}

imc_bt_menu
	.appMenu();

// css scroll slim

Modernizr
	.load({
		test: Modernizr.cssscrollbar,
		//nope: [ APP_SERVIDOR+"files/jquery-1.12.4.min.js", APP_SERVIDOR+"files/jquery.slimscroll.min.js" ],
		//both: [APP_SERVIDOR+"files/modernizr.cssscroll.js"],
		complete: function() {

			if (!Modernizr.cssscrollbar) {
				
				$(".imc-scroll-mini .imc-scroll-mini-contingut")
					.slimScroll({
						color: '#cc073c',
						opacity: 1,
						size: '.5em',
						height: "200px",
						alwaysVisible: true
					});

			}

		}
	});
