import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from 'i18next';

class EnllasosLateral extends Component {

    constructor(){
        super();
        this.state = {
            loading: true,
            laterallinks: [],
            error: null
        }
        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        // console.log(" CANVIAT IDIOMA EN EnllassosLateral A ]" + lng+ "[");
        this.componentDidMount();
    }

    componentDidMount() {

        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL + "/webui/laterallinks";

        axios.get(url)
            .then(res => {
                this.setState({ laterallinks: res.data, loading:false });
            })
            .catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                this.setState({
                    loading:false,
                    laterallinks: "",
                    error: JSON.stringify(error)
                });
            });
    }

    render() {

        const {t} = this.props;

        var auth = sessionStorage.getItem('autenticat');

        let content;

        if (this.state.error) {
            content = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {

            if (!this.state.laterallinks.length || this.state.loading) {
                content = "";
            } else {
                content = this.state.laterallinks.map((s, i) => (
                    <li key={i} id={"enllasLat"+i}>
                        <a href={s.url} className="" title={s.label} target="_blank" tabIndex={11+i} aria-labelledby={"enllasLat"+i}>
                            <img src={s.urllogo} title={s.label} alt={s.label} className="imatgeMobil"/>
                        </a>
                    </li>
                ))
            }
        }


        return (
            <div>
                <ul>
                    {content}
                </ul>
            </div>
        );
    }
}

export default withTranslation()(EnllasosLateral);
