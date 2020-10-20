import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class MollaPa extends Component {

    render() {

        const {t} = this.props;

        return (
            <ul className="mollaPa" id="imc-molla-pa">
                <li>
                    <a href="inici.html">{t('mollaInici')}</a>
                </li>
            </ul>
        );
    }
}

export default withTranslation()(MollaPa);
