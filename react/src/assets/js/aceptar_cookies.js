import $ from 'jquery';
//var Modernizr = require('modernizr');
//import {Modernizr} from './modernizr.js';

export default function acceptarCookies(){
	console.log('entram1');

	var APP_SERVIDOR = '/sites/ft/tema2017/';
	var	txtCookie =  'Atenci\xF3. Utilitzem cookies per a millorar l\'experi\xE8ncia d\'usuari';
	var	txtCookieMes = 'Vull m\xE9s informaci\xF3';
	var	txtCookieOk = 'D\'acord';

	var ac_pag_cookies = "http://www.caib.es/govern/external/cookies.do";

	console.log(txtCookie);

	if(typeof txtCookie  == 'undefined' || txtCookie == null || txtCookie == ""){
		//valores por defecto
		txtCookie = "Atenció. Utilitzem cookies per a millorar l'experiència d'usuari";
		txtCookieMes = "Vull més informació";
		txtCookieOk = "D'acord";
	}

	var ac_cookie_id = "goib_cookie";
	var imc_contenidor = $("#contenedor");

	// imc storage
	var ac_IMC_STORAGE = ( typeof(Storage) !== "undefined" && localStorage!== null ) ? true : false;



}
