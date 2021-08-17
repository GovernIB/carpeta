import React, {Component} from 'react';

// I18N
import {withTranslation} from 'react-i18next';

//import {NativeRouter} from 'react-router-native';
//import {HashRouter} from 'react-router-dom';
import {Router} from './components/Routing';

// NativeRouter
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
