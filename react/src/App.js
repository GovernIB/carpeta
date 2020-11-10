import React, {Component} from 'react';
import i18n from './i18n';
import { withTranslation } from 'react-i18next';
import ReactGA from 'react-ga';
import createHistory from 'history/createBrowserHistory';

const history = createHistory()
ReactGA.initialize('UA-00000000-1'); // Aqui poses l'identificador de compte de Google Analytics
history.listen((location, action) => {
    ReactGA.pageview(location.pathname + location.search);
    console.log(location.pathname)
});

class App extends Component {

    render() {

        const changeLanguage = (lng) => {
            i18n.changeLanguage(lng);
        }

        return (
            <div>

                <h1>{t('benvingut')} </h1> <br/>
                <button onClick={() => changeLanguage('es')}>es</button>
                <button onClick={() => changeLanguage('en')}>en</button>
                <button onClick={() => changeLanguage('ca')}>ca</button>

            </div>
        )
    }
}

export default withTranslation()(App);