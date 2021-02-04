import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";

class EnllasosLateral extends Component {

    constructor(){
        super();
        this.state = {
            infologolateral: [],
            laterallinks: []
        }
    }

    componentWillMount() {
        var url = window.location.href + "webui/laterallinks";
        axios.get(url).then(res => {
            this.setState({ laterallinks: res.data })
        });
    }

    componentWillReceiveProps(lng) {
        var url = window.location.href + "webui/laterallinks";
        axios.get(url).then(res => {
            this.setState({ laterallinks: res.data })
        });
    }

    render() {

        const {t} = this.props;

        let laterallink;

        if(!this.state.laterallinks.length){
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
