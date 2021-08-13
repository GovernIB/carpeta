import React from 'react';
//import { View } from 'react-native';
// This "dummy" WebView is to render something for unsupported platforms,
// like for example Expo SDK "web" platform. It matches the previous react-native
// implementation which is produced by Expo SDK 37.0.0.1 implementation, with
// similar interface than the native ones have.
var WebView = function () { return "" };
export { WebView };
export default WebView;
