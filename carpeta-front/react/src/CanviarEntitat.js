import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";

class CanviarEntitat extends Component {

    constructor(){
        super();
        this.state = {
            loading: true,
            entitats: [],
            error: null
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

        axios.get(url)
            .then(res => {
                this.setState({ entitats: res.data, loading:false, error: "" });
            })
            .catch(error => {
                console.log(" AXIOS ERROR ERROR ERROR ");
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                this.setState({
                    loading:false,
                    entitats: "",
                    error: JSON.stringify(error)
                });
            });
    }



    render() {

        const {t} = this.props;
        let content;

        if (this.state.error) {
            content = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {

            const entities = this.state.entitats;
            var baseURL = sessionStorage.getItem('contextPath');

            content = entities.map((s, i) => (
                <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5 pl-0">
                    <a className={`card col-md-12 align-items-lg-center capsaPlugin pt-3`}
                       href={baseURL + "/e/" + s.codi}>
                    <span className="card-title titol pl-1 h3">
                        <img src={s.urlIcona} title={s.nom} alt={s.nom} className="imc-icona"/>
                    </span>
                        <span className="titolPlugin titol h3 titolCentrat">{s.nom}</span>
                    </a>
                </div>
            ));

        }


        return (
            <div className="row mr-0 ml-0">

                <div className="infoNoMenu">

                    <h2><p className="titol h2">{t('canviarEntitatTitol')}</p></h2>

                    <div className="col-md-12 border-0 pl-0 pr-0">


                        <p className="lh15">{t('canviarEntitatDescripcio')}</p>

                        <div className="card-body imc--llista--capses">

                            <div className="row mb-0">
                                {content}
                            </div>

                        </div>

                    </div>

                </div>
            </div>
        );

    }
}

export default withTranslation()(CanviarEntitat);