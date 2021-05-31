import React, {Component} from 'react';
import i18n from 'i18next';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import {Link} from "react-router-dom";
import {withRouter} from "react-router";
import * as Constants from './Constants';

/**
 * @author anadal Migracio A Routes
 */
class Breadcrumb extends Component {

    constructor(props) {
        super(props);

        this.state = {
            items: [],
            error: null
        };

        this.canviatRoute = this.canviatRoute.bind(this);

        // if (this.props.currentlocation) {
        //     console.log(" BREADCRUMB Constructor currentlocation => " + this.props.currentlocation);
        // }

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        // console.log(" CANVIAT IDIOMA EN BREADCRUMB A ]" + lng+ "[");
        this.canviatRoute(this.props.currentlocation, "PUSH");
    }



    componentDidMount() {
        this.props.history.listen(this.canviatRoute);  
    }



    canviatRoute(location, action) {

        console.log("CANVIAT ROUTE  A: " + action + " LP: " + location.pathname + "   LS: " + location.nomPagina);

        var novaruta = location.pathname;

        // NOMES FUNCIONA PER UN NIVELL DE SECCIO, MÉS S?HA DE PROGRAMAR
        var match = novaruta.match('^' + Constants.SECCIO_MATCH + '([a-z0-9]+)(' + Constants.PLUGINHTML_MATCH+  '|' + Constants.PLUGINREACT_MATCH + ')([a-z0-9]+)');

        // console.log("MATCH === " + match);

        if (match) {
            //  Es plugin d'una Secció ????
            var seccioContext = match[1];
            // console.log("MATCH[SECCIO] === " + seccioContext);
            var baseURL = sessionStorage.getItem('contextPath');
            var url6 = baseURL + `/webui/seccioplugin/` + seccioContext + `/` + match[3];
            // console.log("URL INFO SECCIO - PLUGIN ==> " + url6);
            axios.get(url6)
                .then(res => {

                    var seccioNom;
                    var pluginNom;

                    var seccio = res.data[0];
                    seccioNom = seccio.nom;

                    var pluginInfo = res.data[1];
                    pluginNom =  pluginInfo.nom;

                    // console.log("BREADCRUMB ==> ACTUALITZANT STATE SECCIO-PLUGIN ==> " + seccioNom + " / " + pluginNom + "]");


                   this.setState({ items: [{ id: Constants.SECCIO_PATH + seccioContext, label: seccioNom }, { id: novaruta, label: pluginNom }] });
                })
                .catch(error => {
                    console.log(JSON.stringify(error));
                    if (error.response) {
                        console.log("error.response.data: " + error.response.data);
                        console.log("error.response.status: " + error.response.status);
                        console.log("error.response.headers: " + error.response.headers);
                    }
                    this.setState({
                        items: "",
                        error: JSON.stringify(error)
                    });
                });



        } else {
            if (location.pathname === '/') {
                this.setState({ items: [] });
            } else {
                

                var pluginMatch = novaruta.match('^(' + Constants.PLUGINHTML_MATCH+  '|' + Constants.PLUGINREACT_MATCH + ')([a-z0-9]+)');

                if (pluginMatch) {

                    // Es un plugin

                    // console.log("BreadCRUMB ES UN PLUGIN : " + pluginMatch[1] + " -  " + pluginMatch[2]);

                    var baseURL2 = sessionStorage.getItem('contextPath');


                    var url7 = baseURL2 + `/webui/plugin/` + pluginMatch[2];
                    axios.get(url7)
                        .then(res => {
                            var pluginInfo = res.data;
                            this.setState({items: [{id: location.pathname, label: pluginInfo.nom}]})
                        })
                        .catch(error => {
                            console.log(JSON.stringify(error));
                            if (error.response) {
                                console.log("error.response.data: " + error.response.data);
                                console.log("error.response.status: " + error.response.status);
                                console.log("error.response.headers: " + error.response.headers);
                            }
                            this.setState({
                                items: "",
                                error: JSON.stringify(error)
                            });
                        });

                } else {


                    var seccioMatch = novaruta.match('^' +  Constants.SECCIO_MATCH + '([a-z0-9]+)');


                    if (seccioMatch) {
                        var baseURL = sessionStorage.getItem('contextPath');
                        var url6 = baseURL + `/webui/seccio/` + seccioMatch[1];
                        // console.log("URL INFO SECCIO ==> " + url6);
                        axios.get(url6)
                            .then(res => {
                                var seccio = res.data;
                                // console.log("BREADCRUMB ==> ACTUALITZANT STATE SECCIO ==> " + seccio.nom + " [" + seccio.context + "]");
                                this.setState({ items: [{ id: '/seccio/' + seccio.context, label: seccio.nom }] });
                            })
                            .catch(error => {
                                console.log(JSON.stringify(error));
                                if (error.response) {
                                    console.log("error.response.data: " + error.response.data);
                                    console.log("error.response.status: " + error.response.status);
                                    console.log("error.response.headers: " + error.response.headers);
                                }
                                this.setState({
                                    items: "",
                                    error: JSON.stringify(error)
                                });
                            });

                    } else {

                        const {t} = this.props;
                        // Altre cosa ...
                        // console.log("BreadCRUMB ALTRE COSA : " + location.pathname);
                        this.setState({items: [{id: location.pathname, label: t(location.nomPagina)}]});
                    }
                }
            }
        }

        var f = document.getElementById("imc--fons");
		f.click();
    }


    render() {
        let itemDOMS = [];

        var items = this.state.items;

        let renderHTML = '';

        if (this.state.error) {
            renderHTML = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {

            if (typeof items !== "undefined" && items.length !== 0) {

                const TOTAL_ITEMS = items.length;

                itemDOMS.push(<li key='inici'><Link to={'/'} tabIndex="90" aria-labelledby={i18n.t('mollaInici')} aria-describedby={i18n.t('accedirEnllas') + i18n.t('mollaInici')}>{i18n.t('mollaInici')}</Link></li>);

                // console.log("RENDER Breadcrumb items: " + TOTAL_ITEMS);

                items.forEach(({id, label}, index) => {
                    // if (index < TOTAL_ITEMS - 1) {
                    itemDOMS.push(<li key={index}><span className="imc-separador"> &gt;</span><Link
                        to={id} tabIndex={91+index} aria-labelledby={label} aria-describedby={i18n.t('accedirEnllas') + label}>{label}</Link></li>);
                    // } else {
                    //     /*if (label === 'plugin') {
                    //         itemDOMS.push(<li id="plugin" key={index}>{pluginNom}</li>);
                    //     } else */
                    //     {
                    //         itemDOMS.push(<li key={index}><span className="imc-separador"> &gt; </span>{label}</li>);
                    //     }
                    // }


                });

                renderHTML = <ul className="mollaPa" id="imc-molla-pa">
                    {itemDOMS}
                </ul>;


            }
        }

        // console.log("RENDER Breadcrumb itemsDOMS: " + itemDOMS.length);

        return (
            <div id="breadcrumb">
                {renderHTML}
            </div>
        )
    }
}


export default withTranslation()(withRouter(Breadcrumb));
/*
const Molla = withTranslation()(LegacyComponentClass)

export default function Breadcrumb({items , autenticat}) {
  return (
    <Suspense fallback="loading">
      <Molla items={items}/>
    </Suspense>
  );
}
*/
