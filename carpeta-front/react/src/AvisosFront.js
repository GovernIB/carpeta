import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";

class AvisosFront extends Component {

    constructor(){
        super();
        this.state = {
            avisosPublic: [],
            avisosPrivat: []
        }
    }

    componentDidMount() {

        var baseURL = sessionStorage.getItem('contextPath');

        var url =  baseURL + "/webui/avisosfrontpublic";

        axios.get(url).then(res => {
            this.setState({ avisosPublic: res.data })
        });
        var url2 = baseURL + "/webui/avisosfrontprivat";
        axios.get(url2).then(res => {
            this.setState({ avisosPrivat: res.data })
        })
    }

    componentWillReceiveProps(lng) {

        var baseURL = sessionStorage.getItem('contextPath');
        var url = baseURL +  "/webui/avisosfrontpublic";
        axios.get(url).then(res => {
            this.setState({ avisosPublic: res.data })
        });
        var url2 = baseURL + "/webui/avisosfrontprivat";
        axios.get(url2).then(res => {
            this.setState({ avisosPrivat: res.data })
        })
    }

    render() {

        const {t} = this.props;
        var autenticat = this.props.autenticat;
        let avisFront;

        if(autenticat === '0') {
            if (!this.state.avisosPublic.length) {
                avisFront = "";
            } else {
                avisFront = this.state.avisosPublic.map((s, i) => (
                    <div className={`alert avis${s.gravetat} alert-dismissible fade show`} role="alert">
                        {s.label}
                        <button type="button" className="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                ))
            }
        } else if(autenticat === '1') {
            if (!this.state.avisosPrivat.length) {
                avisFront = "";
            } else {
                avisFront = this.state.avisosPrivat.map((s, i) => (
                    <div className={`alert avis${s.gravetat} alert-dismissible fade show`} role="alert">
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
