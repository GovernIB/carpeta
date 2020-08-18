import React from 'react';
import { withTranslation } from 'react-i18next';


function MenuLateral ({ t }) {

    return (
        <div className="imc-logo">
          <a href="http://www.caib.es/govern/index.do?lang=ca" className="imc--goib" title={ t('menuLateralGovern') }>
            <span>{ t('menuLateralGovern') }</span>
          </a>
          <ul>
            <li>
              <a href="http://www.caib.es/pidip2front/jsp/ca/noticies" className="imc-logo-ico imc--informat" title={ t('menuLateralNoticies') }>
                <span>{ t('menuLateralNoticies') }</span>
              </a>
            </li>
            <li>
              <a href="http://www.caib.es/govern/administracio.do?lang=ca" className="imc-logo-ico imc--administracio" title={ t('menuLateralAdministracio') }>
                <span>{ t('menuLateralAdministracio') }</span>
              </a>
            </li>
            <li>
              <a href="http://www.illesbalears.travel/?lang=ca" className="imc-logo-ico imc--illes" title={ t('menuLateralIlles') }>
                <span>{ t('menuLateralIlles') }</span>
              </a>
            </li>
          </ul>
        </div>
    );
}

export default withTranslation()(MenuLateral);
