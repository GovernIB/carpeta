import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import axios from "axios";
import DocumentTitle from "react-document-title";

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
        var amplePantalla = screen.width;
        if(amplePantalla < 576) {
            document.getElementById("headerBarra").style.backgroundColor = sessionStorage.getItem("colorBarra");
        }
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/entitats";

        axios.get(url)
            .then(res => {
                this.setState({ entitats: res.data, loading:false, error: "" });
            })
            .catch(error => {
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
                <div key={i} className="col-lg-4 col-md-4 col-sm-4 col-xs-12 pl-0">
                    <a className={`card col-md-12 align-items-lg-center capsaPlugin pt-3 alert`}
                       href={baseURL + "/e/" + s.codi} tabIndex={501+i} aria-labelledby={"pluginNom"+i}>
                        <span className="card-title titol pl-1 h3">
                            <img src={s.urlIcona} title={s.nom} alt={s.nom} className="imc-icona"/>
                        </span>
                        <h3 className="apartat titolPlugin titol h3" id={"pluginNom"+i}>{s.nom}</h3>
                    </a>
                </div>
            ));

        }


        return (
            <div className="row mr-0 ml-0">

                <DocumentTitle title={i18n.t('breadcrumbCanviarEntitat') + " - " + t('menuTitol')} />

                <div className="infoNoMenu">

                    <h2 className="titol h2">{t('canviarEntitatTitol')}</h2>

                    <div className="col-md-12 border-0 pl-0 pr-0">


                        <p className="lh15 subtitol">{t('canviarEntitatDescripcio')}</p>

                        <div className="card-body imc--llista--capses">

                            <div className="row mb-0">
                                {content}
                            </div>

                        </div>

                    </div>

                    <div className="col-md-12 border-0 float-left pt-0 pr-0 pb-0 pl-1" id="botoTornarCanviar" style={{ marginTop: '20px' }}>
                        <button type="button" data-toggle="modal" onClick={() => {
                            window.location.href = sessionStorage.getItem("pagTornar")
                        }} className="botoSuport botoTornauApp" tabIndex="550" aria-labelledby="botoTornarCanviar">{t('tornar')}</button>
                    </div>

                </div>
            </div>
        );

    }
}

export default withTranslation()(CanviarEntitat);