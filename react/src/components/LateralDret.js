import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import BarraMenu from './BarraMenu';
import MenuDesllisant from './MenuDesllisant';
//import MollaPa from './MollaPa';
import Breadcrumb from './Breadcrumb';
import ContingutInici from './ContingutInici';
import Peu from './Peu';
import Accessibilitat from './Accessibilitat';
import * as breadcrumbPaths from '../utils/breadcrumbPaths';


class ContingutDret extends Component{

  render(){
    return (

      <div className="contenedor" id="contenedor">

  				<BarraMenu />

          <MenuDesllisant />

          <div className="imc-continguts" id="continguts">

              <BrowserRouter>
                  <Switch>
                    <Route path="/inici"><Breadcrumb items={breadcrumbPaths.Inici}/></Route>
                    <Route path="/accessibilitat"><Breadcrumb items={breadcrumbPaths.Accessibilitat}/></Route>
                    <Route path="/"><Breadcrumb items={breadcrumbPaths.Inici}/></Route>
                  </Switch>
              </BrowserRouter>

              <BrowserRouter>
                  <Switch>
                    <Route path="/inici" component={ContingutInici} />
                    <Route path="/accessibilitat" component={Accessibilitat} />
                    <Route path="/" component={ContingutInici} />
                  </Switch>
              </BrowserRouter>

          </div>

          <Peu />

  			</div>
    );
  }
}

export default ContingutDret;
