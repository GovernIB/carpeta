import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import BarraMenu from './BarraMenu';
import MenuDesllisant from './MenuDesllisant';
//import MollaPa from './MollaPa';
import Breadcrumb from './Breadcrumb';
import ContingutInici from './ContingutInici';
import Peu from './Peu';
import Accessibilitat from './Accessibilitat';
import DadesPersonals from './DadesPersonals';
import TramitsPendents from './TramitsPendents';
import Registres from './Registres';
import DetallRegistre from './DetallRegistre';
import Notificacions from './Notificacions';
import PageNotFound from './PageNotFound';
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
                    <Route path="/dadesPersonals"><Breadcrumb items={breadcrumbPaths.DadesPersonals}/></Route>
                    <Route path="/tramitsPendents"><Breadcrumb items={breadcrumbPaths.TramitsPendents}/></Route>
                    <Route path="/registres"><Breadcrumb items={breadcrumbPaths.Registres}/></Route>
                    <Route path="/detallRegistre"><Breadcrumb items={breadcrumbPaths.DetallRegistre}/></Route>
                    <Route path="/notificacions"><Breadcrumb items={breadcrumbPaths.Notificacions}/></Route>
                    <Route path="/"><Breadcrumb items={breadcrumbPaths.Inici}/></Route>
                  </Switch>
              </BrowserRouter>

              <BrowserRouter>
                  <Switch>
                    <Route path="/inici" component={ContingutInici} />
                    <Route path="/accessibilitat" component={Accessibilitat} />
                    <Route path="/dadesPersonals" component={DadesPersonals} />
                    <Route path="/tramitsPendents" component={TramitsPendents} />
                    <Route path="/registres" component={Registres} />
                    <Route path="/detallRegistre" component={DetallRegistre} />
                    <Route path="/notificacions" component={Notificacions} />
                    <Route path="/" component={ContingutInici} />
                    <Route component={PageNotFound}/>
                  </Switch>
              </BrowserRouter>

          </div>

          <Peu />

  			</div>
    );
  }
}

export default ContingutDret;
