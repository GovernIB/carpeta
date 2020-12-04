import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from "i18next";

class Suport extends Component {

    constructor(){
        super();
        this.state = {
            dadesSuport: []
        }
    }

    componentWillMount() {
        var url = window.location.href + `webui/suport`;
        axios.get(url).then(res => {
            const dadesSuport = res.data;
            this.setState({ dadesSuport });
        })
    }

    componentWillReceiveProps(lng) {
        var url = window.location.href + `webui/suport`;
        axios.get(url).then(res => {
            const dadesSuport = res.data;
            this.setState({ dadesSuport });
        })
    }



    render() {

        var autenticat = this.props.autenticat;
        const {t} = this.props;

        var tancar;
        if(autenticat === '0'){
            tancar = <a href="javascript:newInici('contingut', '0');" className="card-link">{t('suportTanca')}</a>;
        } else if(autenticat === '1'){
            tancar = <a href="javascript:newInici('contingut', '1');" className="card-link">{t('suportTanca')}</a>;
        }

        var dades = this.state.dadesSuport.toString();


            var dadesSuport = dades.substring(1, dades.length - 2);
            // var separadors = [', ','='];
            // var arrayDeCadenas = dadesSuport.split(new RegExp (separadors.join('|'),'g'));
            var arrayDeCadenas = dadesSuport.split(', ');

            const listWebs = arrayDeCadenas.filter(dada => dada.substr(dada.indexOf('=')+1).startsWith('http')).map((dada) =>
                <li className="pb-2"><a href={dada.substr(dada.indexOf('=')+1)}>{dada.substr(0,dada.indexOf('='))}</a></li>
            );

            const listTelefons = arrayDeCadenas.filter(dada => !isNaN(parseInt(dada.substr(dada.indexOf('=')+1)))).map((dada) =>
                <li className="pb-2">{dada.substr(0,dada.indexOf('='))}: {dada.substr(dada.indexOf('=')+1)}</li>
            );

            const listMails = arrayDeCadenas.filter(dada => dada.substr(dada.indexOf('=')+1).includes('@')).map((dada) =>
                <li className="pb-2"><a href={"mailto:'"+dada.substr(dada.indexOf('=')+1)+"'"}>{dada.substr(0,dada.indexOf('='))}</a></li>
            );


        return (

            <div className="card cardSuport">
                <div className="card-header pl-5 pr-5"><p className="card-title titol h2">{t('suportTitol')}</p></div>
                <div className="card-body pl-5 pr-5">
                    <ul className="pl-3">
                        {listWebs}
                        {listTelefons}
                        {listMails}
                    </ul>
                </div>
                <div className="card-footer pl-5 pr-5">
                    {tancar}
                </div>
            </div>

        );

    }
}
export default withTranslation()(Suport);