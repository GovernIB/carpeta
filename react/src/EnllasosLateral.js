import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class EnllasosLateral extends Component {

    constructor(){
        super();
        this.state = {
            infologolateral: [],
            laterallinks: []
        }
    }

    componentWillMount() {
        fetch(window.location.href + "webui/laterallinks")
            .then((response) => {
                return response.json()
            })
            .then((laterallinks) => {
                this.setState({ laterallinks: laterallinks })
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
                        <img src={s.urllogo} title={s.label} alt={s.label} />
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
