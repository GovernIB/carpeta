import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from "i18next";
import {Link} from "react-router-dom";
import * as Constants from './Constants';

/**
 * 
 * @author anadal Migració a ROUTER
 */


class MenuRapid extends Component {

    constructor(){
        super();
        this.state = {
            items: [], // plugins, menupseudoplugin, seccions
            error: null,
            added: false
        }

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        // console.log(" CANVIAT IDIOMA EN MenuRapid A ]" + lng+ "[");
        this.componentDidMount();
    }


    // afegirListener() {
    //
    //     document.addEventListener('click', function() {
    //
    //         let items = document.querySelectorAll('ul.dropdown-menu');
    //         items.forEach(  function(elemento){
    //             if (elemento.classList.contains('show')){
    //                 elemento.classList.remove('show')
    //             }
    //         });
    //     }, false);
    //
    //     this.setState({...this.state, added: true });
    // }

    componentDidMount() {

        // 0 == Nivell Arell
        var baseURL = sessionStorage.getItem('contextPath');
        var autenticat = sessionStorage.getItem('autenticat');
		var url = (autenticat === '1') ? baseURL + `/webui/fullinfosortedauth/0` : baseURL + `/webui/fullinfosorted/0`;
		axios.get(url)
            .then(res => {
                var fulldata = res.data;
                this.setState({ items: fulldata.items });
            })
            .catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                    var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", '');
                    errorPantalla = errorPantalla.replace("</body></html>", '');
                    this.setState({
                        error: errorPantalla
                    });
                } else{
                    this.setState({
                        error: JSON.stringify(error)
                    });
                }

            });

            // if (autenticat === '1' && !this.state.added)
            //     this.afegirListener();
    }

    componentWillUnmount(){

        if(this.state.added){
            document.removeEventListener('click', function() {
                console.log("REMOVE EVENT LISTENER");

                let items = document.querySelectorAll('ul.dropdown-menu');
                items.forEach(  function(elemento){ 
                    if (elemento.classList.contains('show')){
                        elemento.classList.remove('show') 
                    }   
                });
            }, false);
            this.setState({added:false});
        }
    }

    render() {

        // Per amagar dropdowns al fer clic defora
        $(document).click(function(event){
            $(".dropdown-menu").not($(".dropdown-menu").has(event.target)).removeClass("show");
        });

        const { t } = this.props;
        var autenticat = sessionStorage.getItem('autenticat');
        var urlBase = sessionStorage.getItem('contextPath');

        let allItems = [];
        let content;
        let k = 0;
        let l = 0;
        let iActiu = 0;

        if (autenticat === '1') {

            if (this.state.error) {
                content = <div className="alert alert-danger errorConnexio" role="alert">{t(this.state.error)}</div>;
            } else {

                this.state.items.forEach((s, i) => {

                    let plugins = [];
                    k = k + 1;
                    l = 0;

                    switch (s.tipus) {

                        case 0: // Plugin react
                            allItems.push(
                                <li key={"react"+i} className="nav-item pr-4 lletraRapid">
                                    <Link className="navCarpeta"
                                          to={{pathname: Constants.PLUGINREACT_PATH + s.context, nomPagina: "plugin"}} tabIndex={301+k} aria-labelledby={"menuRapidBoto"+i} onFocus={function(){$("#submenu"+iActiu).removeClass('show');iActiu=i;}} >
                                        <img src={urlBase + s.urllogo} alt={s.nom} title={t('iconaDe') + " " + s.nom} className="imc-icona"/>
                                        {/*<img src={s.urllogo} alt="" title="" className="imc-icona"/>*/}
                                        <span className="menuRapidView" id={"menuRapidBoto"+i}>{s.nom}</span>
                                    </Link>
                                </li>
                            );
                            break;

                        case 1: // Plugin html
                            allItems.push(
                                <li key={"html"+i} className="nav-item pr-4 lletraRapid">
                                    <Link className="navCarpeta"
                                          to={{pathname: Constants.PLUGINHTML_PATH + s.context, nomPagina: "plugin"}} tabIndex={301+k} aria-labelledby={"menuRapidBoto"+i} onFocus={function(){$("#submenu"+iActiu).removeClass('show');iActiu=i;}} >
                                        <img src={urlBase + s.urllogo} alt={s.nom} title={t('iconaDe') + " " + s.nom} className="imc-icona"/>
                                        {/*<img src={s.urllogo} alt="" title="" className="imc-icona"/>*/}
                                        <span className="menuRapidView" id={"menuRapidBoto"+i}>{s.nom}</span>
                                    </Link>
                                </li>
                            );
                            break;

                        case 3: // Seccio

                            // plugins
                            s.plugins.forEach((p, j) => {
                                if(p.reactComponent === true) {
                                    plugins.push(
                                        <li key={"seccioReact"+j}>
                                            <Link className="navCarpeta dropdown-item linkVerd"
                                                  to={{pathname: Constants.SECCIO_PATH + s.context + Constants.PLUGINREACT_PATH + p.context, nomPagina: "plugin"
                                                  }} tabIndex={301 + k + j + 1} aria-labelledby={"menuRapidBoto"+i} onClick={function(){$("#submenu"+iActiu).removeClass('show')}}>
                                                <img src={urlBase + "/pluginfront/pluginicon/" + p.pluginID + "/" + i18n.language + ""} alt={p.nom} title={t('iconaDe') + " " + p.nom} className="imc-icona"/>
                                                <span className="menuRapidView">{p.nom}</span>
                                            </Link>
                                        </li>
                                    );
                                }else{
                                    plugins.push(
                                        <li key={"seccio"+j}>
                                            <Link className="navCarpeta dropdown-item linkVerd"
                                                  to={{pathname: Constants.SECCIO_PATH + s.context + Constants.PLUGINHTML_PATH + p.context, nomPagina: "plugin"
                                                  }} tabIndex={301 + k + j + 1} aria-labelledby={"menuRapidBoto"+i} onClick={function(){$("#submenu"+iActiu).removeClass('show')}}>
                                                <img src={urlBase + "/pluginfront/pluginicon/" + p.pluginID + "/" + i18n.language + ""} alt={p.nom} title={t('iconaDe') + " " + p.nom} className="imc-icona"/>
                                                <span className="menuRapidView">{p.nom}</span>
                                            </Link>
                                        </li>
                                    );
                                }
                                k = k + 1;
                                l = j + 1;

                            })

                            // seccio global
                            allItems.push(
                                <li className="btn-group navCarpeta pr-4" key={"global"+i}>
                                    <button type="button" className="btn btn-default dropdown-toggle p-0 lletraRapid disBlok text-verd"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id={"menuRapidBoto"+i} tabIndex={301 + k - l}
                                                onFocus={function(){$("#submenu"+iActiu).removeClass('show');iActiu=i;$("#submenu"+i).addClass('show');}}
                                                onClick={function(){$("#submenu"+iActiu).removeClass('show');iActiu=i;$("#submenu"+i).addClass('show');}}
                                                >
                                        <img src={s.urllogo} title={t('iconaDe') + " " + s.nom} alt={s.nom} className="imc-icona"/>
                                        <span className="menuRapidView lletraRapid" style={{ whiteSpace: "normal"}}>{s.nom}</span>
                                    </button>
                                    <ul className="dropdown-menu maxContent" aria-labelledby={"menuRapidBoto" + i} id={"submenu"+i}>
                                        {plugins}
                                    </ul>
                                </li>
                            );

                            break;

                        case 4: // PseudoPlugin
                            allItems.push(
                                <li key={"pseudo"+i} className="nav-item pr-4 lletraRapid">
                                    <a className="navCarpeta" href={s.url} target="_blank" title={s.nom} tabIndex={301+k} aria-labelledby={"menuRapidBoto"+i} onFocus={function(){$("#submenu"+iActiu).removeClass('show');iActiu=i;}} >
                                        <img src={s.urllogo} alt={s.nom} title={t('iconaDe') + " " + s.nom} className="imc-icona"/>
                                        <span className="menuRapidView" id={"menuRapidBoto"+i}>{s.nom}</span>
                                    </a>
                                </li>
                            );
                            break;
                    }
                });

                content = <div>
                    <nav className="navbar navbar-expand-sm bg-white p-0 fixo borderMenu">
                        <ul className="navbar-nav mRapidGlobal" style={{padding: '0.5rem!important'}}
                            id="llistaMenuRapid">
                            {allItems}
                        </ul>
                    </nav>
                </div>;
            }
        }

        return (
            <div id="menuRapid">
                {content}
            </div>
        );
    }
}

export default withTranslation()(MenuRapid);