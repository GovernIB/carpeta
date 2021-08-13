import * as constants from './Constants.js';
import React, {Component} from 'react';
//import {UseColorSchemeHook} from './UseColorShemeHook.js';
import {Text, View, StyleSheet} from 'react-native';

/**
 * @author anadal(u80067)
 * @format
 * @flow strict-local
 */
class Section extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    var isDarkMode = this.props.isDarkMode;
    return (
      /* <UseColorSchemeHook>
         {isDarkMode => ( */
      <View style={styles.sectionContainer}>
        <Text
          style={[
            styles.sectionTitle,
            {
              color: isDarkMode ? constants.Color_WHITE : constants.Color_BLACK,
            },
          ]}>
          {this.props.title}
        </Text>
        <Text
          style={[
            styles.sectionDescription,
            {
              color: isDarkMode ? constants.Color_WHITE : constants.Color_BLACK,
            },
          ]}>
          {this.props.children}
        </Text>
      </View>
      /* )}
      </UseColorSchemeHook> */
    );
  }
}

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
    flex: 1,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default Section;
