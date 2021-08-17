/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 09:24:27
 * @modify date 17-08-2021 09:24:27
 * @desc Exemple de Control de canvi de Idioma
 */
import React, {Component} from 'react';

// I18N
import i18n from './i18n';
import {withTranslation} from 'react-i18next';

import {Button, View, StyleSheet} from 'react-native';

class TraductorControl extends Component {
  constructor(props) {
    super(props);
    // I18N
    this.canviarIdioma = this.canviarIdioma.bind(this);
  }

  canviarIdioma(lng) {
    i18n.changeLanguage(lng);
  }

  render() {
    return (
      <View>
        <View style={styles.fixToText}>
          <Button title="Català" onPress={() => this.canviarIdioma('ca')} />
          <Button title="Castellà" onPress={() => this.canviarIdioma('es')} />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  fixToText: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
});

export default withTranslation()(TraductorControl);
