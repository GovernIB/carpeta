import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";

class DadesEntitat extends Component {

    constructor() {
        super();
        this.state = {
            infoEntitat: ''
        }
    }

    componentDidMount() {
        var urlBase = window.location.href;
        var url = urlBase + "webui/textinformatiuentitat";
        axios.get(url).then(res => {            
            this.setState({ infoEntitat: escape.data });
        });
    }


    render() {
        return (
            <div id="substituir" className="imc-peu-govern">{this.state.infoEntitat}</div>
        );
    }
}

export default withTranslation()(DadesEntitat);