import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';

class LogoLateral extends Component {

    constructor(){
        super();
        this.state = {
            infologolateral: [],
            error: null
        }
    }

    componentDidMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        fetch(baseURL + "/webui/infologolateral")

            .then((response) => {
                return response.json()
            })
            .then((infologolateral) => {
                this.setState({ infologolateral: infologolateral })
            })
            .catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                this.setState({
                    infologolateral: [],
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

            if (!this.state.infologolateral.length) {
                content = "";
            } else {
                content = this.state.infologolateral.map((s, i) => (
                    <a href={s.url} className="imc--goib" title={s.label} key={i} target="_blank" tabIndex="600" aria-label={s.label} aria-describedby={t('accedirEnllas') + s.label}>
                        <img src={s.urllogo} style={{maxWidth: "70%"}} title="" alt={s.label} className="logo-govern"/>
                        <span>{s.label}</span>
                    </a>
                ))
            }
        }

        return (
            <div>
                {content}
            </div>
        );
    }
}

export default withTranslation()(LogoLateral);