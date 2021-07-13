import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';

class EnllasosXarxes extends Component {

    constructor(){
        super();
        this.state = {
            enllasosXarxes: [],
            error: null
        }
    }


    componentDidMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        fetch(baseURL + '/webui/socialnetworks')

            .then((response) => {
                return response.json()
            })
            .then((enllasosXarxes) => {
                this.setState({ enllasosXarxes: enllasosXarxes })
            })
            .catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                this.setState({
                    enllasosXarxes: "",
                    error: JSON.stringify(error)
                });
            });
    }

    render() {

        const {t} = this.props;

        var seguir;
        let content;

        if (this.state.error) {
            content = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {

            if (!this.state.enllasosXarxes.length) {
                seguir = "";
                content = "";
            } else {
                seguir = t('peuSeguir');
                content = this.state.enllasosXarxes.map((s, i) => (
                    <li key={i}>
                        <a href={s.url} className="imc-bt-xarxa border-0" title={s.label} target="_blank" tabIndex={651+i} aria-label={s.label} aria-describedby={t('accedirEnllas') + s.label}>
                            <img src={s.urllogo} title="" alt={s.label}/>
                            <span>{s.label}</span>
                        </a>
                    </li>
                ))
            }
        }


        return (
            <div>
                <p>{seguir}</p>
                <ul>
                    {content}
                </ul>
            </div>
        );
    }
}

export default withTranslation()(EnllasosXarxes);
