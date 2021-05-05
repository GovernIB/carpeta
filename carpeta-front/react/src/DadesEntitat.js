import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";

class DadesEntitat extends Component {

    constructor() {
        super();
        this.state = {
            infoEntitat: '',
            error: null
        }
    }

    componentDidMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/textinformatiuentitat";
        axios.get(url)
            .then(res => {
                this.setState({ infoEntitat: res.data , error: null});
            })
            .catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                this.setState({
                    infoEntitat: '',
                    error: JSON.stringify(error)
                });
            });
    }


    render() {

        let content;

        if (this.state.error) {
            content = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {
            content = {__html: this.state.infoEntitat}
        }

        return (
            <div className="imc-peu-govern" dangerouslySetInnerHTML={content} />
        );
    }
}

export default withTranslation()(DadesEntitat);