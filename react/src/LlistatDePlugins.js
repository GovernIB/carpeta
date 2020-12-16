import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";

class LlistatDePlugins extends Component {

    constructor(){
        super();
        this.state = {
            plugins: []
        }
    }

    componentWillMount() {
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        })
    }

    componentWillReceiveProps(lng) {
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        })
    }

    infoHtml(missatge, pluginID) {
        alert(missatge);
        var contingut = newPluginHtml('contingut', '1', pluginID);
        window.location.href(contingut);
    }

    infoReact(missatge, pluginID) {
        alert(missatge);
        var contingut = newPluginReact('contingut', '1', pluginID);
        window.location.href(contingut);
    }

    error(missatge) {
        alert(missatge);
    }

    loadDialog(maxInactiveInterval) {
        var sessionAlive = maxInactiveInterval;
        var notifyBefore = 30;
        setTimeout(function() {
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
                        $('#expirat').dialog({
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
                                        var url = window.location.href + 'sortir';
                                        window.location.replace(url);
                                    }
                                }
                            ]
                        });
                    }
                });

            });
        }, (sessionAlive - notifyBefore) * 1000);
    };


    render() {

        const { t } = this.props;

        var urlBase = window.location.href;

        const plugins = this.state.plugins;

        var plugHtml;
        var plugHtmlInfo;
        var plugHtmlWarning;
        var plugHtmlError;
        var plugReact;
        var plugReactInfo;
        var plugReactWarning;
        var plugReactError;

        plugHtml = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 0).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5">
                <button alt={s.nom} className={`card col-md-10 align-items-lg-center`}
                        onClick={event => window.location.href = "javascript:newPluginHtml('contingut', '1', '" + s.pluginID + "');"}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        className="imc-icona"></img>{s.nom}</span>
                    <span className="card-text mb-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugHtmlInfo = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 1).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5">
                <button alt={s.nom} className={`card col-md-10 align-items-lg-center alert${s.gravetat}`}
                        onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""} className="imc-icona"></img>{s.nom}</span>
                    <span className="card-text mb-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugHtmlWarning = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 2).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5">
                <button alt={s.nom} className={`card col-md-10 align-items-lg-center alert${s.gravetat}`}
                        onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        className="imc-icona"></img>{s.nom}</span>
                    <span className="card-text mb-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugHtmlError = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 3).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5">
                <button alt={s.nom} className={`card col-md-10 align-items-lg-center alert${s.gravetat}`}
                        onClick={(event) => this.error(s.missatge)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        className="imc-icona"></img>{s.nom}</span>
                    <span className="card-text mb-3">{s.descripcio}</span>
                </button>
            </div>
        ));

        plugReact = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 0).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5">
                <button alt={s.nom} className={`card col-md-10 align-items-lg-center alert${s.gravetat}`}
                        onClick={event => window.location.href = "javascript:newPluginReact('contingut', '1', '" + s.pluginID + "');"}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        className="imc-icona"></img>{s.nom}</span>
                    <span className="card-text mb-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugReactInfo = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 1).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5">
                <button alt={s.nom} className={`card col-md-10 align-items-lg-center alert${s.gravetat}`}
                        onClick={(event) => this.infoReact(s.missatge,s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        className="imc-icona"></img>{s.nom}</span>
                    <span className="card-text mb-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugReactWarning = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 2).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5">
                <button alt={s.nom} className={`card col-md-10 align-items-lg-center alert${s.gravetat}`}
                        onClick={(event) => this.infoReact(s.missatge,s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        className="imc-icona"></img>{s.nom}</span>
                    <span className="card-text mb-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugReactError = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 3).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5">
                <button alt={s.nom} className={`card col-md-10 align-items-lg-center alert${s.gravetat}`}
                        onClick={(event) => this.error(s.missatge)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        className="imc-icona"></img>{s.nom}</span>
                    <span className="card-text mb-3">{s.descripcio}</span>
                </button>
            </div>
        ));


        this.loadDialog(sessionStorage.getItem('maxInactiveInterval'));


        return (

            <div className="row mr-0 ml-0">

                <div id="dialog" title={t('sessioAvisTitol')}className="noVisible">
                    <p className="pt-4">{t('sessioAvisDescripcio')}</p>
                </div>

                <div id="expirat" title={t('sessioExpiradaTitol')} className="dialogExpirat noVisible">
                    <p className="pt-4">{t('sessioExpiradaDescripcio')}</p>
                </div>

                <div className="infoNoMenu">
                    <p className="titol h2">{t('paginaIniciTitolPrivat')}</p>

                    <div className="col-md-12 border-0 pl-0 pr-0">

                        <p className="lh15">{t('paginaIniciIntroduccioPrivat')}</p>

                        <div className="card-body imc--llista--capses">

                            <div className="row mb-0">

                                {plugHtml}
                                {plugHtmlInfo}
                                {plugHtmlWarning}
                                {plugHtmlError}
                                {plugReact}
                                {plugReactInfo}
                                {plugReactWarning}
                                {plugReactError}

                            </div>

                        </div>

                    </div>

                </div>
            </div>

        );
    }

}

export default withTranslation()(LlistatDePlugins);