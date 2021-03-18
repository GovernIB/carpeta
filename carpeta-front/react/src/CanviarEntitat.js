import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";
import {Link} from "react-router-dom";
import * as Constants from "./Constants";

class CanviarEntitat extends Component {

    constructor(){
        super();
        this.state = {
            loading: true,
            entitats: []
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        this.componentDidMount();
    }


    componentDidMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/entitats";

        axios.get(url).then(res => {
            this.setState({ entitats: res.data, loading:false });
        });
    }



    render() {
        const {t} = this.props;

        const entities = this.state.entitats;
        var baseURL = sessionStorage.getItem('contextPath');
        var entitats;

        entitats = entities.map((s, i) => (
            <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                <a className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`} href={baseURL + "/e/" + s.codi} >
                    <span className="card-title titol pl-1 h3">
                        <img src={s.urlIcona} title={s.nom} alt={s.nom} className="imc-icona" />
                    </span>
                    <span className="titolPlugin titol h3 titolCentrat">{s.nom}</span>
                </a>
            </div>
        ));




        return (
            <div className="row mr-0 ml-0">

                <div className="infoNoMenu">

                    <h2><p className="titol h2">{t('canviarEntitatTitol')}</p></h2>

                    <div className="col-md-12 border-0 pl-0 pr-0">


                        <p className="lh15">{t('canviarEntitatDescripcio')}</p>

                        <div className="card-body imc--llista--capses">

                            <div className="row mb-0">
                                {entitats}
                            </div>

                        </div>

                    </div>

                </div>
            </div>
        );

    }
}

export default withTranslation()(CanviarEntitat);