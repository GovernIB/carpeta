import {Component} from 'react';
import {withTranslation} from 'react-i18next';

class DadesEntitat extends Component {

    constructor(){
        super();
        this.state = {
            textinformatiuentitat: []

        }
    }

    componentWillMount() {
        fetch(window.location.href + "webui/textinformatiuentitat")
            .then((response) => {
                return response.json()
            })
            .then((textinformatiuentitat) => {
                this.setState({ textinformatiuentitat: textinformatiuentitat })
            });
    }

    render() {

        const {t} = this.props;

        let laterallink;

        if(!this.state.textinformatiuentitat.length){
            laterallink = "";
        } else{
            laterallink = this.state.textinformatiuentitat;
        }


        return (
                {laterallink}
        );
    }
}

export default withTranslation()(EnllasosLateral);