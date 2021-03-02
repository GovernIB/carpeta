import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";
import i18n from 'i18next';

class EnllasosLateral extends Component {

    constructor(){
        super();
        this.state = {
            loading: true,
            laterallinks: []
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        console.log(" CANVIAT IDIOMA EN EnllassosLateral A ]" + lng+ "[");
        this.componentDidMount();
    }

    componentDidMount() {

        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/laterallinks";

        axios.get(url).then(res => {
            this.setState({ laterallinks: res.data, loading:false });
        });
    }

    render() {

        const {t} = this.props;

        var auth = sessionStorage.getItem('autenticat');

        let laterallink;

        if(!this.state.laterallinks.length || this.state.loading){ 
            laterallink = "";
        } else{
            laterallink = this.state.laterallinks.map((s, i) => (
                <li key={i}>
                    <a href={s.url} className="" title={s.label}>
                        <img src={s.urllogo} title={s.label} alt={s.label} className="imatgeMobil" />
                    </a>
                </li>
            ))
        }


        return (
            <div>
                <ul>
                    {laterallink}
                </ul>
            </div>
        );
    }
}

export default withTranslation()(EnllasosLateral);
