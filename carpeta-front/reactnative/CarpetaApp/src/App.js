/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 15:15:25
 * @modify date 17-08-2021 15:15:25
 * @desc [description]
 */
import React, {Component} from 'react';

// I18N
import {withTranslation} from 'react-i18next';
import {Router} from './components/Routing';

import Index2 from './Index2';

class App extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <Router>
        <Index2 />
      </Router>
    );
  }
}

export default withTranslation()(App);
