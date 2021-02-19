import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";

class EnllasosLateral extends Component {

    constructor(){
        super();
        this.state = {
            laterallinks: []
        }
    }

    componentWillMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/laterallinks";
        axios.get(url).then(res => {
            this.setState({ laterallinks: res.data })
        });
    }

    componentWillReceiveProps(lng) {
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/laterallinks";
        axios.get(url).then(res => {
            this.setState({ laterallinks: res.data })
        });
    }

    render() {

        const {t} = this.props;

        var auth = sessionStorage.getItem('autenticat');

        let laterallink;

        if(!this.state.laterallinks.length || auth == '0'){
            laterallink = "";
        } else{
            laterallink = this.state.laterallinks.map((s, i) => (
                <li>
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
