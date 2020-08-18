import React from 'react';
import { withTranslation } from 'react-i18next';


function MollaPa ({ t }) {

    return (
      <ul className="mollaPa" id="imc-molla-pa">
        <li>
          <a href="inici.html">{ t('mollaInici') }</a>
        </li>
      </ul>
    );
}

export default withTranslation()(MollaPa);
