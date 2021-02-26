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

        
        
    }

    componentDidMount() {

        if (this.props.history) {
            console.log("EXISTEIX  HISTORY !!!!!");
            this.props.history.listen(this.canviatRoute);
        } else {
            console.log("NO PUC LLEGIR HISTORY !!!!!");
        }

    }



    canviatRoute(location, action) {

        console.log("CANVIAT ROUTE  A: " + action + " LP: " + location.pathname + "   LS: " + location.state);

        var novaruta = location.pathname;

        // NOMES FUNCIONA PER UN NIVELL DE SECCIO, MÉS S?HA DE PROGRAMAR
        var match = novaruta.match('^\/seccio\/([0-9]+)\/plugin(react|html)\/([0-9]+)');

        console.log("MATCH === " + match);

        if (match) {
            //  Es plugin d'una Secció ????
            var seccioID = match[1];
            console.log("MATCH[SECCIO] === " + seccioID);
            var baseURL = sessionStorage.getItem('contextPath');
            var url6 = baseURL + `/webui/seccio/` + seccioID;
            console.log("URL INFO SECCIO ==> " + url6);
            axios.get(url6).then(res => {
                var seccio = res.data;
                console.log("BREADCRUMB ==> ACTUALITZANT STATE ==> " + seccio.nom + " [" + seccio.seccioID + "]");
                this.setState({ items: [{ id: '/seccio/' + seccio.seccioID, label: seccio.nom }, { id: novaruta, label: "plugin" + match[2] }] });
            });
        } else {
            if (location.pathname == '/') {
                this.setState({ items: [] });
            } else {
                // Altre cosa ...
                console.log("BreadCRUMB ALTRE COSA : " + location.pathname);
                this.setState({ items: [{ id: location.pathname, label: location.pathname }] });
            }
        }

        var f = document.getElementById("imc--fons");
		f.click();
    }


    render() {
        let itemDOMS = [];

        var items = this.state.items;

        let renderHTML = '';

        if (typeof items !== "undefined" && items.length != 0) {

            //itemDOMS.push(<li key='inici'><NavLink to={'/'}>{i18n.t('mollaInici')}</NavLink></li>);
            const TOTAL_ITEMS = items.length;

            itemDOMS.push(<li key='inici'><Link to={'/'}>{i18n.t('mollaInici')}</Link></li>);

            console.log("RENDER Breadcrumb items: " + TOTAL_ITEMS);

            items.forEach(({ id, label }, index) => {
                if (index < TOTAL_ITEMS - 1) {
                    itemDOMS.push(<li key={index}><span className="imc-separador"> &gt;</span><Link to={id}>{label}</Link></li>);
                } /*else {
                    if (i18n.t(label) === 'plugin') {
                        itemDOMS.push(<li id="plugin" key={index}>{pluginNom}</li>);
                    } else {
                        itemDOMS.push(<li key={index}><span className="imc-separador"> &gt; </span>{i18n.t(label)}</li>);
                    }
                }
                */

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
