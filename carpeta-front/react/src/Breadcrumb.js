import React, { Component, Suspense } from 'react';
import i18n from 'i18next';
import { withTranslation } from 'react-i18next';
import axios from "axios";
import { HashRouter, Switch, Route, Link, useHistory, NavLink } from "react-router-dom";
import { withRouter } from "react-router";

/**
 * @author anadal Migracio A Routes
 */
class Breadcrumb extends Component {

    constructor(props) {
        super(props);

        this.state = {
            items: []
        };

        this.canviatRoute = this.canviatRoute.bind(this);

        if (this.props.currentlocation) {
            console.log(" BREADCRUMB Constructor currentlocation => " + this.props.currentlocation);
        }

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        console.log(" CANVIAT IDIOMA EN BREADCRUMB A ]" + lng+ "[");
        this.canviatRoute(this.props.currentlocation, "PUSH");
    }



    componentDidMount() {
        this.props.history.listen(this.canviatRoute);  
    }



    canviatRoute(location, action) {

        console.log("CANVIAT ROUTE  A: " + action + " LP: " + location.pathname + "   LS: " + location.nomPagina);

        var novaruta = location.pathname;

        // NOMES FUNCIONA PER UN NIVELL DE SECCIO, MÉS S?HA DE PROGRAMAR
        var match = novaruta.match('^\/seccio\/([0-9]+)\/plugin(react|html)\/([0-9]+)');

        console.log("MATCH === " + match);

        if (match) {
            //  Es plugin d'una Secció ????
            var seccioID = match[1];
            console.log("MATCH[SECCIO] === " + seccioID);
            var baseURL = sessionStorage.getItem('contextPath');
            var url6 = baseURL + `/webui/seccioplugin/` + seccioID + `/` + match[3];
            console.log("URL INFO SECCIO - PLUGIN ==> " + url6);
            axios.get(url6).then(res => {

                var seccioNom;
                var pluginNom;

                var seccio = res.data[0];
                seccioNom = seccio.nom;                
                
                var pluginInfo = res.data[1];
                pluginNom =  pluginInfo.nom;
               
                console.log("BREADCRUMB ==> ACTUALITZANT STATE SECCIO-PLUGIN ==> " + seccioNom + " / " + pluginNom + "]");
                 

               this.setState({ items: [{ id: '/seccio/' + seccioID, label: seccioNom }, { id: novaruta, label: pluginNom }] });
            });



        } else {
            if (location.pathname === '/') {
                this.setState({ items: [] });
            } else {
                

                var pluginMatch = novaruta.match('^\/plugin(react|html)\/([0-9]+)');

                if (pluginMatch) {

                    // Es un plugin

                    console.log("BreadCRUMB ES UN PLUGIN : " + pluginMatch[1] + " -  " + pluginMatch[2]);

                    var baseURL2 = sessionStorage.getItem('contextPath');


                    var url7 = baseURL2 + `/webui/plugin/` + pluginMatch[2];
                    axios.get(url7).then(res => {
                        var pluginInfo = res.data;
                        this.setState({items: [{id: location.pathname, label: pluginInfo.nom}]})
                    });

                    /*
                    var url7 = baseURL2 + `/pluginfront/veureplugins/`;
                    axios.get(url7).then(res => {
                        res.data.filter(s => s.pluginID === pluginMatch[2]).map((s, i) => (
                            this.setState({items: [{id: location.pathname, label: s.nom}]})
                        ));
                    });
                    */
                } else {


                    var seccioMatch = novaruta.match('^\/seccio\/([0-9]+)');


                    if (seccioMatch) {
                        var baseURL = sessionStorage.getItem('contextPath');
                        var url6 = baseURL + `/webui/seccio/` + seccioMatch[1];
                        console.log("URL INFO SECCIO ==> " + url6);
                        axios.get(url6).then(res => {
                            var seccio = res.data;
                            console.log("BREADCRUMB ==> ACTUALITZANT STATE SECCIO ==> " + seccio.nom + " [" + seccio.seccioID + "]");
                            this.setState({ items: [{ id: '/seccio/' + seccio.seccioID, label: seccio.nom }] });
                        });

                    } else {

                        const {t} = this.props;
                        // Altre cosa ...
                        console.log("BreadCRUMB ALTRE COSA : " + location.pathname);
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

        if (typeof items !== "undefined" && items.length !== 0) {

            const TOTAL_ITEMS = items.length;

            itemDOMS.push(<li key='inici'><Link to={'/'}>{i18n.t('mollaInici')}</Link></li>);

            console.log("RENDER Breadcrumb items: " + TOTAL_ITEMS);

            items.forEach(({ id, label }, index) => {
                if (index < TOTAL_ITEMS - 1) {
                    itemDOMS.push(<li key={index}><span className="imc-separador"> &gt;</span><Link to={id}>{label}</Link></li>);
                } else {
                    /*if (label === 'plugin') {
                        itemDOMS.push(<li id="plugin" key={index}>{pluginNom}</li>);
                    } else */ 
                    {
                        itemDOMS.push(<li key={index}><span className="imc-separador"> &gt; </span>{label}</li>);
                    }
                }


            });

            renderHTML = <ul className="mollaPa" id="imc-molla-pa">
                            {itemDOMS}
                        </ul>;


        }

        console.log("RENDER Breadcrumb itemsDOMS: " + itemDOMS.length);

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
