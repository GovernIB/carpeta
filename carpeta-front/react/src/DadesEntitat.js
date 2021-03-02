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
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/textinformatiuentitat";
        axios.get(url).then(res => {            
            this.setState({ infoEntitat: res.data });
        });
    }


    render() {
        return (
            <div className="imc-peu-govern" dangerouslySetInnerHTML={{__html: this.state.infoEntitat}} />
        );
    }
}

export default withTranslation()(DadesEntitat);