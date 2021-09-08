/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 07-09-2021 11:05:56
 * @modify date 07-09-2021 11:05:56
 * @desc [description]
 */

//import {AsyncStorage} from 'react-native';
import AsyncStorage from '@react-native-community/async-storage';
import Storage from 'react-native-storage';
var storage = new Storage({
  // Inicializar
  size: 10,
  storageBackend: AsyncStorage,
  defaultExpires: null,
  enableCache: true,
});

class Persistencia {
  constructor(props) {
    //super(props);
    this.load = this.load.bind(this);
    this.valors = null;
  }

  getValors() {
    return this.valors;
  }

  /**
   *
   * @param {Punter a una funciona que es cridarà quan acabi la càrrega de dades} callOnFinish
   */
  load(callOnFinish) {
    console.log('load::SI DEFINIT STORAGE !!!!!! ');

    storage
      .load({
        key: 'dadescarpeta',
      })
      .then(ret => {
        // regresa cuando hay un valor en ret
        console.log(
          'load:: Llegit de persistencia codiEntitat  ' +
            ret.codientitat +
            ' i url ' +
            ret.urlcarpeta,
        );
        this.valors = [ret.urlcarpeta, ret.codientitat];
        callOnFinish(ret.urlcarpeta, ret.codientitat);
      })
      // FIXME:
      // eslint-disable-next-line handle-callback-err
      .catch(err => {
        console.log('load::Posant valors per defecte !!!!');
        this.valors = ['', ''];
        callOnFinish('', '');
      });
  }

  save(param_urlcarpeta, param_codientitat) {
    storage.save({
      key: 'dadescarpeta', // Note: Do not use underscore("_") in key!
      data: {
        urlcarpeta: param_urlcarpeta,
        codientitat: param_codientitat,
      },
      // if expires not specified, the defaultExpires will be applied instead.
      // if set to null, then it will never expire.
      expires: null,
    });
  }
}

export default Persistencia;
