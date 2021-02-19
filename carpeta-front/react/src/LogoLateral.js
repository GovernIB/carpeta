import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class LogoLateral extends Component {

    constructor(){
        super();
        this.state = {
            infologolateral: []
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
    }

    render() {

        const {t} = this.props;

        let infologolateral;

        if(!this.state.infologolateral.length){
            infologolateral = "";
        } else{
            infologolateral = this.state.infologolateral.map((s, i) => (
                <a href={s.url} className="imc--goib" title={s.label} key={i}>
                    <img src={s.urllogo} style={{maxWidth: "70%"}} title="" alt="" className="logo-govern" />
                    <span>{s.label}</span>
                </a>
            ))
        }

        return (
            <div>
                {infologolateral}
            </div>
        );
    }
}

export default withTranslation()(LogoLateral);