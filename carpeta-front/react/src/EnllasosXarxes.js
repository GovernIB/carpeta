import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class EnllasosXarxes extends Component {

    constructor(){
        super();
        this.state = {
            enllasosXarxes: []
        }
    }

    componentWillMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        fetch(baseURL + '/webui/socialnetworks')
            .then((response) => {
                return response.json()
            })
            .then((enllasosXarxes) => {
                this.setState({ enllasosXarxes: enllasosXarxes })
            })
    }

    render() {

        const {t} = this.props;

        var seguir;
        let enllasos;
        if (!this.state.enllasosXarxes.length) {
            seguir = "";
            enllasos = "";
        } else {
            seguir = t('peuSeguir');
            enllasos = this.state.enllasosXarxes.map((s, i) => (
                <li>
                    <a href={s.url} className="imc-bt-xarxa border-0" title={s.label}>
                        <img src={s.urllogo} title="" alt=""/>
                        <span>{s.label}</span>
                    </a>
                </li>
            ))
        }


        return (
            <div>
                <p>{seguir}</p>
                <ul>
                    {enllasos}
                </ul>
            </div>
        );
    }
}

export default withTranslation()(EnllasosXarxes);
