import {Component} from 'react';
import {withTranslation} from 'react-i18next';
import React from "./main";

class DadesEntitat extends Component {

    constructor(){
        super();

    }



    render() {

        var urlBase = window.location.href;
        var url = urlBase + "/textinformatiuentitat";

        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.onload = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    document.getElementById("substituir").innerHTML = this.responseText;
                } else {
                    window.location.href = window.location.href;
                }
            }
        };
        xhr.send(data);

        return (
            <div id="substituir"></div>
        );
    }
}

export default withTranslation()(DadesEntitat);