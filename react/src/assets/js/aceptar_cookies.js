import $ from 'jquery';
//var Modernizr = require('modernizr');
//import {Modernizr} from './modernizr.js';
//import { Modernizr } from 'https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js';

export default function acceptarCookies(){
	console.log('entram1');

	var APP_SERVIDOR = '/sites/ft/tema2017/';
	var	txtCookie =  'Atenci\xF3. Utilitzem cookies per a millorar l\'experi\xE8ncia d\'usuari';
	var	txtCookieMes = 'Vull m\xE9s informaci\xF3';
	var	txtCookieOk = 'D\'acord';

	var ac_pag_cookies = "http://www.caib.es/govern/external/cookies.do";

	console.log(txtCookie);

	if(typeof txtCookie  === 'undefined' || txtCookie === null || txtCookie === ""){
		//valores por defecto
		txtCookie = "Atenció. Utilitzem cookies per a millorar l'experiència d'usuari";
		txtCookieMes = "Vull més informació";
		txtCookieOk = "D'acord";
	}

	var ac_cookie_id = "goib_cookie";
	var imc_contenidor = $("#contenedor");

  console.log('contenidor');
	console.log(imc_contenidor);

	// imc storage
	var ac_IMC_STORAGE = ( typeof(Storage) !== "undefined" && localStorage!== null ) ? true : false;

	/* global Modernizr */
		/*console.log(Modernizr);

		Modernizr.load({
			test: Modernizr.localstorage,
			nope: APP_SERVIDOR+"files/jquery.cookie.js",
			complete: function() {

				// cookies
				var imc_app_cookie = (ac_IMC_STORAGE) ? localStorage.getItem(ac_cookie_id) : $.cookie(ac_cookie_id),
					dacord = function() {

						$(".imc-cookie")
							.addClass("imc-cookie-dacord");

						if (ac_IMC_STORAGE) {
							localStorage.setItem(ac_cookie_id, "ok");
						} else {
							$.cookie(ac_cookie_id, "ok", { expires: 365 });
						}
					};

				if (!imc_app_cookie) {

					var cookie_p_el = $("<p>").html( txtCookie ),
						cookie_mes_el = $("<a>").attr({ href: ac_pag_cookies }).addClass("imc-ac-bt-mes").text( txtCookieMes ),
						cookie_ok_el = $("<a>").attr({ href: '', }).addClass("imc-ac-bt imc-ac-bt-ok").text( txtCookieOk ).on("click", dacord),
						cookie_el = $("<div>").addClass("imc-cookie").append( cookie_p_el ).append( cookie_mes_el ).append( cookie_ok_el );

					imc_contenidor.before( cookie_el );

				}
			}
		});*/

}
