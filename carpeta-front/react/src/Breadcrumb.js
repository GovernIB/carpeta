import React, { Component, Suspense } from 'react';
import i18n from 'i18next';
import { withTranslation } from 'react-i18next';
import axios from "axios";
import { HashRouter, Switch, Route, Link, useHistory } from "react-router-dom";
import { withRouter } from "react-router";

/**
 * @author anadal Migracio A Routes
 */
class Breadcrumb extends Component {

    constructor() {
        super();
        this.state = {
            items: []
        };
        this.canviatRoute = this.canviatRoute.bind(this);
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

        console.log("CANVIAT ROUTE !!!!!!!");
        {
            // location is an object like window.location
            console.log("MMMMMMMMMMMMMMMMMMMM A: " + action   + " LP: " + location.pathname + "   LS: " + location.state);

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
                    this.setState({ items: [{ id: '/seccio/' + seccio.seccioID , label: seccio.nom  },{ id: novaruta, label:"plugin" + match[2]}] });
                });
            } else {
                // Altre cosa ...
                this.setState({ items: [{ id: location.pathname, label: location.pathname }] });
            }
        }
    }


    componentWillMount() {
    }

    componentWillReceiveProps(lng) {

    }

    render() {

        //var autenticat = sessionStorage.getItem('autenticat');

        let itemDOMS = [];


        itemDOMS.push(<li key='inici'><Link to={'/'}>{i18n.t('mollaInici')}</Link></li>);

        let items = this.state.items;


        //
        if (items) {
            const TOTAL_ITEMS = items.length;

            items.forEach(({ id, label }, index) => {

                if (index < TOTAL_ITEMS - 1) {
                    itemDOMS.push(<li key={index}><span className="imc-separador"> &gt; </span>
                        <Link to={id}>{i18n.t(label)}</Link></li>);
                }


            });
        }

        return (
            <div>
                {itemDOMS.length > 0 &&
                    <ul className="mollaPa" id="imc-molla-pa">
                        {itemDOMS}
                    </ul>
                }
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
