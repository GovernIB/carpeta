import React, {Component} from 'react';
import i18n from 'i18next';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import {withRouter} from "react-router";


class TitolPipella extends Component {

    constructor(props) {
        super(props);

        this.state = {
            nomEntitat: null,
            error: null
        };

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        this.componentDidMount();
    }

    componentDidMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + `/webui/infoEntitat`;

        axios.get(url)
            .then(res => {
                var infoEntitat = res.data;
                this.setState({ nomEntitat: infoEntitat.nom });
            })
            .catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                this.setState({
                    nomEntitat: null,
                    codiHtml: null,
                    error: JSON.stringify(error)
                });
            });
    }

    render() {

        const {t} = this.props;
        // Canvia títol de la pestanya del navegador
        let entitatNom = this.state.nomEntitat;
        document.title = t('menuTitol') + " - " + entitatNom;
        sessionStorage.setItem('entitatNom', entitatNom);

        return (
            <div id="titol" />
        )
    }
}


export default withTranslation()(withRouter(TitolPipella));

