/**
 * @author anadal (u80067)
 * @email governdigital.carpeta@fundaciobit.org
 * @create date 17-08-2021 12:46:24
 * @modify date 17-08-2021 12:46:24
 * @desc [description]
 */

import PropTypes from 'prop-types';
import React, {Component} from 'react';
import {Text, TouchableOpacity} from 'react-native';
import {withRouter} from 'react-router-dom';

class WebLink extends Component {
  constructor(props) {
    super(props);
    this.clickOnMenu.bind(this);
  }

  clickOnMenu(path) {
    this.props.history.push(path);
  }

  render() {
    return (
      <TouchableOpacity onPress={() => this.clickOnMenu(this.props.to)} style={this.props.style}>
        <Text>{this.props.children}</Text>
      </TouchableOpacity>
    );
  }
}

WebLink.propTypes = {
  to: PropTypes.string.isRequired,
  style: PropTypes.any.isRequired,
};

const WebLinkRouter = withRouter(WebLink);

export {HashRouter as Router, Switch, Route, withRouter} from 'react-router-dom';
export {WebLinkRouter as Link};
