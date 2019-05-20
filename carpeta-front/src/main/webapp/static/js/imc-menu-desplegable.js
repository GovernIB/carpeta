/* menu horitzontal */

var imc_bt_desplegable;


// onReady

$(function(){
    
    imc_finestra = $(window);
    imc_bt_desplegable = $(".imc-bt-desplegable");

    imc_bt_desplegable
        .appMenuDesplegable();

    var resizeAppMenuDesplegable;
    $(window)
        .on('resize', function(e) {
            clearTimeout(resizeAppMenuDesplegable);
            resizeAppMenuDesplegable = setTimeout(function() {

                imc_bt_desplegable
                    .appMenuDesplegable();

            }, 150);
        });

});


// appMenuDesplegable

$.fn.appMenuDesplegable = function(opcions) {
    var settings = $.extend({
        contenidor: false
    }, opcions);
    this.each(function(){
        var elm = $(this),
            bt_el = elm.find("button:first"),
            submenu_el = elm.find("ul:first"),
            inicia = function() {

                bt_el
                    .off(".appMenuDesplegable")
                    .on("click.appMenuDesplegable", submenu);

                elm
                    .find(".imc-bt-menu-tanca")
                        .remove();

                if (submenu_el.css("position") === "fixed") {
                    mobil();
                }

            },
            submenu = function() {
                
                if (elm.hasClass("imc--obert")) {

                    tanca();

                } else {

                    mostra();

                    setTimeout(
                        function() {

                            imc_finestra
                                .on("click.appMenuDesplegable", tancaFinestra);

                        }, 100);

                }

            },
            mostra = function() {

                elm
                    .addClass("imc--obert");

            },
            tancaFinestra = function(e) {

                var obj = $(e.target),
                    dinsDeLlista = (obj.closest("ul").length) ? true : false,
                    disnDeBotoDesplegable = (obj.closest("ul").parent().hasClass("imc-bt-desplegable")) ? true : false;

                if (!dinsDeLlista || !disnDeBotoDesplegable) {
                    tanca();
                }

            },
            tanca = function() {

                elm
                    .addClass("imc--tancant")
                    .removeClass("imc--obert");

                imc_finestra
                    .off(".appMenuDesplegable");

                setTimeout(
                    function() {

                        elm
                            .removeClass("imc--tancant");

                    }, 200);

            },
            mobil = function() {

                var bt_menu_tanca_txt = $("<span>").text( txtTanca ),
                    bt_menu_tanca = $("<button>").attr({ id: "imc-bt-menu-tanca", type: "button"}).addClass("imc-bt-menu-tanca").html( bt_menu_tanca_txt ).on("click", tancaMobil).appendTo( elm );

            },
            tancaMobil = function() {

                elm
                    .removeClass("imc--obert");

                imc_finestra
                    .off(".appMenuDesplegable");

            };

        // inicia

        inicia();
        
    });
    return this;
}
