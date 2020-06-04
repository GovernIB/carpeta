import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


class LegacyComponentClass extends Component{

  render(){

   const { t } = this.props;

    return (
        <div className="imc-logo">
          <a href="http://www.caib.es/govern/index.do?lang=ca" className="imc--goib" title={ t('menuLateral.govern') }>
            <span>{ t('menuLateral.govern') }</span>
          </a>
          <ul>
            <li>
              <a href="http://www.caib.es/pidip2front/jsp/ca/noticies" className="imc-logo-ico imc--informat" title={ t('menuLateral.noticies') }>
                <span>{ t('menuLateral.noticies') }</span>
              </a>
            </li>
            <li>
              <a href="http://www.caib.es/govern/administracio.do?lang=ca" className="imc-logo-ico imc--administracio" title={ t('menuLateral.administracio') }>
                <span>{ t('menuLateral.administracio') }</span>
              </a>
            </li>
            <li>
              <a href="http://www.illesbalears.travel/?lang=ca" className="imc-logo-ico imc--illes" title={ t('menuLateral.illes') }>
                <span>{ t('menuLateral.illes') }</span>
              </a>
            </li>
          </ul>
        </div>
    );
  }
}
const MenuL = withTranslation()(LegacyComponentClass)

export default function MenuLateral() {
  return (
    <Suspense fallback="loading">
      <MenuL />
    </Suspense>
  );
}
