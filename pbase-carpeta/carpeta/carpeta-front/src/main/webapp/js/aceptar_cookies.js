ac_pag_cookies = "http://www.caib.es/govern/external/cookies.do";

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
				cookie_ok_el = $("<a>").attr({ href: "javascript:;" }).addClass("imc-ac-bt imc-ac-bt-ok").text( txtCookieOk ).on("click", dacord),
				cookie_el = $("<div>").addClass("imc-cookie").append( cookie_p_el ).append( cookie_mes_el ).append( cookie_ok_el );

			imc_contenidor
				.before( cookie_el );

		}
	}
});