import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";


class ExpirarSessio extends Component {


    loadDialog(maxInactiveInterval) {
        var sessionAlive = sessionStorage.getItem('maxInactiveInterval');
        if(sessionAlive === null){
            sessionAlive = 30 * 60;
        }
        var notifyBefore = 30;
        var newTimer = setTimeout(function() {
            $('#dialog').removeClass("noVisible");
            $('#expirat').removeClass("noVisible");
            $(function() {
                const entitatActual = sessionStorage.getItem('entitat');
                $('#dialog').dialog({
                    autoOpen: true,
                    maxWidth:400,
                    maxHeight: 200,
                    width: 400,
                    height: 200,
                    modal: true,
                    open: function(event, ui) {
                        setTimeout(function(){
                            $('#dialog').dialog('close');
                        }, notifyBefore * 1000);
                    },
                    buttons: [
                        {
                            text: 'Sí',
                            class:'botoDialegVerd',
                            click: function() {
                                $.get("/about", function (data, status) {
                                });
                                location.reload();
                            }
                        },
                        {
                            text: 'No',
                            class:'botoDialegVermell',
                            click: function() {
                                $('#dialog').dialog("close");
                            }
                        }
                    ],

                    close: function(event, ui) {
                        var baseURL = sessionStorage.getItem('contextPath');
                        var url = baseURL + '/sortir';

                        axios.get(url)
                            .then(res => {
                                console.log("expirar sessió: " + res.statusText);
                            });

                        $('#expirat').delay(5000).dialog({
                            maxWidth:400,
                            maxHeight: 200,
                            width: 400,
                            height: 200,
                            modal: true,
                            dialogClass: "no-close",
                            buttons: [
                                {
                                    text: "OK",
                                    class:'botoDialegVerd',
                                    click: function() {
                                        $( this ).dialog( "close" );
                                        var baseURL = sessionStorage.getItem('contextPath');
                                        var url = baseURL + '/sortir';
                                        window.location.replace(url);
                                    }
                                }
                            ]
                        });
                    }
                });

            });
        }, (sessionAlive - notifyBefore) * 1000);
        sessionStorage.setItem('idTimeOut', newTimer);
    };


    render() {

        const { t } = this.props;

        this.loadDialog(sessionStorage.getItem('maxInactiveInterval'));


        return (

            <div className="row mr-0 ml-0">

                <div id="dialog" title={t('sessioAvisTitol')} className="noVisible expiratMobil">
                    <p className="pt-4">{t('sessioAvisDescripcio')}</p>
                </div>

                <div id="expirat" title={t('sessioExpiradaTitol')} className="dialogExpirat noVisible expiratMobil">
                    <p className="pt-4">{t('sessioExpiradaDescripcio')}</p>
                </div>

            </div>

        );
    }

}

export default withTranslation()(ExpirarSessio);