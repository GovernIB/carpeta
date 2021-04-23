import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import {Link} from "react-router-dom";
import axios from "axios";
import i18n from 'i18next';

class EnllasosPeuCentral extends Component {

    constructor(){
        super();
        this.state = {
            enllasosPeuCentral: []
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        console.log(" CANVIAT IDIOMA EN EnllassosPeuCentral A ]" + lng+ "[");
        this.componentDidMount();
    }


    componentDidMount() {  

        var baseURL = sessionStorage.getItem('contextPath');
        var url =  baseURL + "/webui/centralfooterlinks";
        axios.get(url).then(res => {
            this.setState({ enllasosPeuCentral: res.data })
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

        let enllasosPeuCentral;
        
        if(!this.state.enllasosPeuCentral.length){
            enllasosPeuCentral = "";
        } else{
            enllasosPeuCentral = this.state.enllasosPeuCentral.map((s, i) => (
                <li key={i}>
                    <a href={s.url} target="_blank">
                        <span>{s.label}</span>
                        <img src={s.urllogo} title="" alt="" />
                    </a>
                </li>
            ))
        }

        var entitatActiva = sessionStorage.getItem('entitat');
        console.log("ENTITAT ENLLASOS: " + entitatActiva);

        var avisLegal;

        if(entitatActiva === 'caib'){
            avisLegal = <li><Link to={{pathname: `/avislegal`, nomPagina: 'peuAvis' }}> { t('peuAvis') } <img src="/carpetafront/src/assets/images/legal.png" className="ml-0" title={t('avisLegalTitol')} alt={t('avisLegalTitol')} /></Link></li>;
        } else{
            avisLegal = "";
        }

        return (
            <ul className="enllasosPeu">
                <li><Link to={{pathname: `/mapaweb`, nomPagina: 'peuMapa' }}> { t('peuMapa') }</Link></li>
                {avisLegal}
                {enllasosPeuCentral}
            </ul>
        );
    }
}

export default withTranslation()(EnllasosPeuCentral);