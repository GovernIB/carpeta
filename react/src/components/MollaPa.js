import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


class LegacyComponentClass extends Component{

  render(){

    const { t } = this.props;

    return (
      <ul className="mollaPa" id="imc-molla-pa">
        <li>
          <a href="inici.html">{ t('molla.inici') }</a>
        </li>
      </ul>
    );
  }
}
const Molla = withTranslation()(LegacyComponentClass)

export default function MollaPa() {
  return (
    <Suspense fallback="loading">
      <Molla />
    </Suspense>
  );
}
