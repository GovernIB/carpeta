
import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import BarraMenu from './BarraMenu';
import MenuDesllisant from './MenuDesllisant';
import MenuLateral from './MenuLateral';
import MenuRapid from './MenuRapid';
import Peu from './Peu';
import Breadcrumb from './Breadcrumb';
import AvisosFront from './AvisosFront';
import Inici from './Inici';
import MapaWeb from './MapaWeb';
import PluginHtml from './PluginHtml';
import PluginReact from './PluginReact';
import Accessibilitat from './Accessibilitat';
import NivellAutenticacio from './NivellAutenticacio';

// NO ESBORRAR LA SEGÜENT LINIA  !!!!!!
import i18n from './i18n';

import { HashRouter, Switch, Route, Link, useHistory } from "react-router-dom";



import LlistatDePlugins from './LlistatDePlugins';

/**
 * @author anadal Migracio A Routes i passar de index.jsp a Index.js
 */



//@withRouter
class Index extends Component {



    constructor() {
        super();
        


    }
    componentWillMount() {
        console.log("Entra a INDEX WILL MOUNT");

    }
    componentWillUnmount() {

    }


    render() {


        var auth = sessionStorage.getItem('autenticat');


        console.log(" XYZ ZZZ   \n INDEX :: Auth Val: " + auth + "\n");


        var user = sessionStorage.getItem('usuariNomComplet');

        var infoUsuari;
        if (auth == '1' && user != null) {
            infoUsuari = <div className="imc-titol usuari">
                <nav className="imc--contingut">
                    <h3>
                        <span className="estilUsuari">
                            {/* <c:if test="${user != null}"> */}
                            <span className="oi oi-person pr-2" aria-hidden="true"> </span>{user}
                            {/* <!-- Nivell d'autenticació --> */}
                            <span id="nivellAutenticacio" className="imc--autenticacio">
                                <NivellAutenticacio />
                            </span>
                            {/* </c:if> */}
                        </span>
                    </h3>
                </nav>
            </div>;
        } else {
            infoUsuari = '';
        }

        /*
                const styleContenidor = (auth === '1')? { marginTop: '4.5em !important'} : {};
                style={styleContenidor} 
        */
        return (
            <HashRouter >
                { /* Mrar si aquest div es pot llevar */}

                <div>
                    {/* -- Menú vertical */}
                    {/*<div id = "menuLateral"></div>*/}
                    <MenuLateral autenticat={auth} />

                    {/* Contingut dret */}
                    <div className="contenedor" id="contenedor">

                        {/* Capçalera */}
                        {/*<div id = "barraMenu"></div>*/}
                        <BarraMenu />

                        {infoUsuari}

                        {/* Menú Ràpid */}
                        {/*<div id = "menuRapid"></div>*/}
                        <MenuRapid autenticat={auth} />

                        {/* Menú desplegable */}
                        {/*<div id = "menuDesllisant"></div>*/}
                        <MenuDesllisant autenticat={auth} />

                        {/* Zona Contingut */}
                        <div className="imc-continguts" id="continguts">

                            {/* Molla de pa */}
                            {/*<div id = "mollaPa"></div>*/}
                            <Breadcrumb autenticat={auth} />

                            {/* Avisos Front */}
                            {/*<div id = "avisosFront"></div>*/}
                            <AvisosFront autenticat={auth} />

                            {/* Contingut pàgina */}
                            <div id="carregant" className="loader-container centrat loaderOcult">
                                <div className="loader"></div>
                            </div>

                            {/*  XYZ ZZZ   S'ha de substituir per Routes*/}
                            <div id="contingut" className="pt-2">
                            </div>

                            <div id="contingut2222" className="pt-2">
                                <Switch>
                                    <Route exact path="/" component={Inici} />
                                    <Route path="/accessibilitat" component={Accessibilitat}  />

                                    <Route path="/mapaweb" component={MapaWeb} />

                                    <Route
                                        path="/seccio/:seccioId/pluginhtml/:pluginId"
                                        render={(props) => {
                                            return <PluginHtml {...props} seccioID={props.match.params.seccioId} pluginID={props.match.params.pluginId} autenticat={auth} />
                                        }}
                                    />

                                    <Route
                                        path="/seccio/:seccioId/pluginreact/:pluginId"
                                        render={(props) => {
                                            return <PluginReact {...props} seccioID={props.match.params.seccioId} pluginID={props.match.params.pluginId} autenticat={auth} />
                                        }}
                                    />

                                    <Route
                                        path="/seccio/:seccioId"
                                        render={(props) => {
                                            return <LlistatDePlugins {...props} seccioID={props.match.params.seccioId} autenticat={auth} />
                                        }}
                                    />
                                    <Route
                                        path="/pluginhtml/:pluginId"
                                        render={(props) => {
                                            return <PluginHtml {...props} seccioID={0}  autenticat={auth} />
                                        }}
                                    />

                                    {/*//alert("\nMOSTRAR PLUGIN REACT" + props.match.params.pluginId + "\n");   */}
                                    <Route
                                        path="/pluginreact/:pluginId"
                                        render={(props) => {
                                            return <PluginReact {...props} seccioID={0} pluginID={props.match.params.pluginId} autenticat={auth} />
                                        }}
                                    />

                                  

                                </Switch>
                            </div>

                        </div>
                        {/* Fi Zona Contingut */}

                        {/* Peu */}
                        {/* <div id = "peu" class="capsaPeu"></div> */}
                        <Peu autenticat={auth} />


                    </div>
                </div>
            </HashRouter>
        );
    }
}

export default withTranslation()(Index);

