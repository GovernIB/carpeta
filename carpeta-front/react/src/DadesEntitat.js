import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';

class DadesEntitat extends Component {

    render() {

        var data = new FormData();
        var urlBase = window.location.href;
        var url = urlBase + "webui/textinformatiuentitat";

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
            <div id="substituir" class="imc-peu-govern"></div>
        );
    }
}

export default withTranslation()(DadesEntitat);