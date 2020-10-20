import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class MenuLateral extends Component {

    constructor(){
        super();
        this.state = { infologolateral: [] }
    }

    componentWillMount() {
        fetch(window.location.href + "webui/infologolateral")
            .then((response) => {
                return response.json()
            })
            .then((infologolateral) => {
                this.setState({ infologolateral: infologolateral })
            })
    }

    render() {

        const {t} = this.props;

        let infologolateral;

        if(!this.state.infologolateral.length){
            infologolateral = "";
        } else{
            infologolateral = this.state.infologolateral.map((s, i) => (
                <a href={s.url} className="imc--goib" title={s.label}>
                    <img src={s.urllogo} title={s.label} alt={s.label} />
                    <span>{s.label}</span>
                </a>
            ))
        }


        return (
            <div className="imc-logo">
                {infologolateral}
                <ul>
                    <li>
                        <a href="http://www.caib.es/pidip2front/jsp/ca/noticies" className="imc-logo-ico imc--informat"
                           title={t('menuLateralNoticies')}>
                            <span>{t('menuLateralNoticies')}</span>
                        </a>
                    </li>
                    <li>
                        <a href="http://www.caib.es/govern/administracio.do?lang=ca"
                           className="imc-logo-ico imc--administracio" title={t('menuLateralAdministracio')}>
                            <span>{t('menuLateralAdministracio')}</span>
                        </a>
                    </li>
                    <li>
                        <a href="http://www.illesbalears.travel/?lang=ca" className="imc-logo-ico imc--illes"
                           title={t('menuLateralIlles')}>
                            <span>{t('menuLateralIlles')}</span>
                        </a>
                    </li>
                </ul>
            </div>
        );
    }
}

export default withTranslation()(MenuLateral);
