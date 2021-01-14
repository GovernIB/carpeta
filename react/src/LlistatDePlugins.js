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

        var url2 = window.location.href + "webui/nomEntitat";
        axios.get(url2).then(res => {
            this.setState({ nomEntitat: res.data })
        });
    }

    componentWillReceiveProps(lng) {
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        })

        var url2 = window.location.href + "webui/nomEntitat";
        axios.get(url2).then(res => {
            this.setState({ nomEntitat: res.data })
        });
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


    render() {

        const { t } = this.props;

        let entitatNom = this.state.nomEntitat;

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
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`}
                        onClick={event => window.location.href = "javascript:newPluginHtml('contingut', '1', '" + s.pluginID + "');"}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugHtmlInfo = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 1).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                        onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""} alt="" title="" className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugHtmlWarning = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 2).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                        onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugHtmlError = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 3).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                        onClick={(event) => this.error(s.missatge)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3">{s.descripcio}</span>
                </button>
            </div>
        ));

        plugReact = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 0).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                        onClick={event => window.location.href = "javascript:newPluginReact('contingut', '1', '" + s.pluginID + "');"}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugReactInfo = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 1).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                        onClick={(event) => this.infoReact(s.missatge,s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugReactWarning = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 2).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                        onClick={(event) => this.infoReact(s.missatge,s.pluginID)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3">{s.descripcio}</span>
                </button>
            </div>
        ));
        plugReactError = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 3).map(s => (
            <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <button alt={s.nom} className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert${s.gravetat}`}
                        onClick={(event) => this.error(s.missatge)}>
                    <span className="card-title titol pl-1 h3"><img
                        src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                        alt="" title=""
                        className="imc-icona" /></span>
                    <span className="titolPlugin  titol h3">{s.nom}</span>
                    <span className="card-text mb-3 mt-3">{s.descripcio}</span>
                </button>
            </div>
        ));


        return (

            <div className="row mr-0 ml-0">

                <div className="infoNoMenu">
                    <h2><p className="titol h2">{t('paginaIniciTitolPrivat')} {entitatNom}</p></h2>

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