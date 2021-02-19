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

    componentWillMount() {

        var urlBase = window.location.href;
        var url = urlBase + "webui/textinformatiuentitat";
        axios.get(url).then(res => {            
            this.setState({ infoEntitat: escape.data });
        });

        /*
        var data = new FormData();
        var urlBase = window.location.href;
        var url = urlBase + "webui/textinformatiuentitat";

        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                    this.setState({ infoEntitat:this.responseText });
                    //document.getElementById("substituir").innerHTML = this.responseText;
                } else {
                    //window.location.href = window.location.href;
                    this.setState({ infoEntitat: 'XYZ ZZZ ERROR DESCARREGANT DADES DE ENTITAT' });
                }
            
        };
        xhr.send(data);
        */
    }


    render() {

        

        return (
            <div id="substituir" className="imc-peu-govern">{this.state.infoEntitat}</div>
        );
    }
}

export default withTranslation()(DadesEntitat);