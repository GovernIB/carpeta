import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import {Link} from "react-router-dom";
import axios from "axios";
import i18n from 'i18next';

class EnllasosPeuCentral extends Component {

    constructor(){
        super();
        this.state = {
            enllasosPeuCentral: [],
            error: null
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        // console.log(" CANVIAT IDIOMA EN EnllassosPeuCentral A ]" + lng+ "[");
        this.componentDidMount();
    }


    componentDidMount() {  

        var baseURL = sessionStorage.getItem('contextPath');
        var url =  baseURL + "/webui/centralfooterlinks";
        axios.get(url)
            .then(res => {
                this.setState({ enllasosPeuCentral: res.data })
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
                    enllasosPeuCentral: "",
                    error: JSON.stringify(error)
                });
            });

    }



    render() {
        var autenticat = sessionStorage.getItem('autenticat');    
        const {t} = this.props;

        // var mapa;
		// if (autenticat === '1'){
		// 	mapa = <li><a href="javascript:newMapaWeb('contingut', '1');">{ t('peuMapa') }</a></li>;
		// }
		// if(autenticat === '0'){
		// 	mapa = <li><a href="javascript:newMapaWeb('contingut', '0');">{ t('peuMapa') }</a></li>;
		// }

        let content;

        if (this.state.error) {
            content = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {

            if (!this.state.enllasosPeuCentral.length) {
                content = "";
            } else {
                content = this.state.enllasosPeuCentral.map((s, i) => (
                    <li key={i} id={"enllasPeu"+i}>
                        <a href={s.url} target="_blank" tabIndex={631+i} aria-labelledby={"enllasPeu"+i}>
                            <span>{s.label}</span>
                            <img src={s.urllogo} title="" alt={s.label}/>
                        </a>
                    </li>
                ))
            }
        }

        var entitatActiva = sessionStorage.getItem('entitat');
        // console.log("ENTITAT ENLLASOS: " + entitatActiva);

        var avisLegal;

        if(entitatActiva === 'caib'){
            avisLegal = <li id="enllasAvisLegal"><Link to={{pathname: `/avislegal`, nomPagina: 'peuAvis' }} tabIndex="622" aria-labelledby="enllasAvisLegal"> { t('peuAvis') } <img src="/carpetafront/src/assets/images/legal.png" className="ml-0" title={t('avisLegalTitol')} alt={t('avisLegalTitol')} /></Link></li>;
        } else{
            avisLegal = "";
        }

        return (
            <ul className="enllasosPeu">
                <li id="enllasMapaWeb"><Link to={{pathname: `/mapaweb`, nomPagina: 'peuMapa' }} tabIndex="621" aria-labelledby="enllasMapaWeb"> { t('peuMapa') }</Link></li>
                {avisLegal}
                {content}
            </ul>
        );
    }
}

export default withTranslation()(EnllasosPeuCentral);