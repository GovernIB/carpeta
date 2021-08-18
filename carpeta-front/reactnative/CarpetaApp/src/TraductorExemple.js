/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 09:16:02
 * @modify date 17-08-2021 09:16:02
 * @desc Exemple de traducció i de "Listener" de canvi d'idioma
 */

import React, {Component} from 'react';
import {Text} from 'react-native';
import i18n from './i18n';
import {withTranslation} from 'react-i18next';

/**
 *
 */

class TraductorExemple extends Component {
  constructor(props) {
    super(props);
    // I18N
    this.canviatIdioma = this.canviatIdioma.bind(this);
    i18n.on('languageChanged', this.canviatIdioma);
  }

  // I18N
  canviatIdioma(lng) {
    console.log(' CANVIAT IDIOMA EN HOME A ]' + lng + '[');
  }

  render() {
    const {t} = this.props;
    return (
      <Text>
        Missatge Traduït (Actual: {i18n.language}) =>{' '}
        {'\n' /* FIXME:  El missatge surt de la pantalla en ANDROID per la dreta*/}{' '}
        {t('paginaIniciTitol')}
      </Text>
    );
  }
}

export default withTranslation()(TraductorExemple);
