/**
 * @format
 */

// FIXME canvia el nom per main.native.js

import {AppRegistry} from 'react-native';
import App from './src/App';
import {name as appName} from './src/app.json';
import './src/i18n';

AppRegistry.registerComponent(appName, () => App);
