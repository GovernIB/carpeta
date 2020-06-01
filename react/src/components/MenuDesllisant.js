import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';


class LegacyComponentClass extends Component{

  render(){

    const { t } = this.props;

    var boto_ca;
    if (i18n.language === 'ca') {
      boto_ca = <strong>{ t('menu.idioma_ca') }</strong>;
    } else {
      boto_ca = <button onClick={() => i18n.changeLanguage('ca')} className="boton-menu">{ t('menu.idioma_ca') }</button>;
    }

    var boto_es;
    if (i18n.language === 'es') {
      boto_es = <strong>{ t('menu.idioma_es') }</strong>;
    } else {
      boto_es = <button onClick={() => i18n.changeLanguage('es')} className="boton-menu">{ t('menu.idioma_es') }</button>;
    }


    return (
      <div className="imc-marc" id="imc-marc" tabIndex="-1" aria-hidden="true">
        <div className="imc--fons"></div>

        <div className="imc-marc-menu" id="imc-marc-menu" aria-hidden="true">
          <div className="imc-cercador" id="imc-cercador">

          </div>
          <ul>
            <li>
              <a href="http://www.caib.es/govern/cercadorAv.do?lang=ca" className="imc-marc-ico imc--avanzada" title={ t('menu.recerca') }>
                <span>{t('menu.recerca') }</span>
              </a>
            </li>
            <li className="imc-marc-ico imc--idioma">
              {boto_ca} \ {boto_es}
            </li>
            <li>
              <a href="accessibilitat" className="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat" title={ t('menu.accessibilitat') }>
                <span>{ t('menu.accessibilitat') }</span>
              </a>
            </li>
          </ul>
        </div>
      </div>
    );
  }
}
const Menu = withTranslation()(LegacyComponentClass)

export default function MenuDesllisant() {
  return (
    <Suspense fallback="loading">
      <Menu />
    </Suspense>
  );
}
