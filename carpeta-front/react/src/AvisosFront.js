import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";

/** 
 * @autor anadal  Avisos del Front carrega avisos públics i privats #373 
 */
class AvisosFront extends Component {

    constructor() {
        super();
        this.state = {
            avisos: []
        }
    }

    componentDidMount() {

        var baseURL = sessionStorage.getItem('contextPath');
        var auth = sessionStorage.getItem('autenticat');

        if (auth === '0') {
            var url = baseURL + "/webui/avisosfrontpublic";
            axios.get(url).then(res => {
                this.setState({ avisos: res.data })
            });
        } else {
            var url2 = baseURL + "/webui/avisosfrontprivat";
            axios.get(url2).then(res => {
                this.setState({ avisos: res.data })
            })
        }
    }

    componentWillReceiveProps(lng) {

        var baseURL = sessionStorage.getItem('contextPath');
        var auth = sessionStorage.getItem('autenticat');

        if (auth === '0') {
            var url = baseURL + "/webui/avisosfrontpublic";
            axios.get(url).then(res => {
                this.setState({ avisos: res.data });
            });
        } else {
            var url2 = baseURL + "/webui/avisosfrontprivat";
            axios.get(url2).then(res => {
                this.setState({ avisos: res.data });
            });
        }
    }

    render() {

        const { t } = this.props;
        var autenticat = sessionStorage.getItem('autenticat');

        let avisFront;

        if (autenticat === '0') {
            if (!this.state.avisos.length) {
                avisFront = "";
            } else {
                avisFront = this.state.avisos.map((s, i) => (                    
                    <div key={i} className={`alert avis${s.gravetat} alert-dismissible fade show`} role="alert">                       
                        {s.label}
                        <button type="button" className="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                ))
            }
        } else {
            if (!this.state.avisos.length) {
                avisFront = "";
            } else {
                avisFront = this.state.avisos.map((s, i) => (
                    <div key={i} className={`alert avis${s.gravetat} alert-dismissible fade show`} role="alert">
                        {s.label}
                        <button type="button" className="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                ))
            }
        }

        return (
            <div>
                {avisFront}
            </div>
        );
    }
}

export default withTranslation()(AvisosFront);
