import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import LogoLateral from './LogoLateral';
import EnllasosLateral from './EnllasosLateral';

class MenuLateral extends Component {

    render() {

        return (
            <div className="imc-logo">
                <LogoLateral />
                <EnllasosLateral />
            </div>
        );
    }
}

export default withTranslation()(MenuLateral);
