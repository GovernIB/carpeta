import React, { Component, Suspense } from 'react';
import i18n from 'i18next';
import { withTranslation } from 'react-i18next';
import axios from "axios";
import { HashRouter, Switch, Route, Link,useHistory } from "react-router-dom";


/**
 * @author anadal Migracio A Routes
 */
class Breadcrumb extends Component{

    constructor(){
        super();
        this.state = {
            //plugins: []
        }
    }


    componentWillMount() {
      /*
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        })
        */
    }

    componentWillReceiveProps(lng) {
      /*
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        })
        */
    }

  render(){
	  
	//var autenticat = sessionStorage.getItem('autenticat');
    
    let itemDOMS = [];
/*
   const plugins = this.state.plugins;
    var pluginID = sessionStorage.getItem('pluginActiu');
    var pluginNom = "";
    if(pluginID !== null) {
        pluginNom = plugins.filter(s => s.pluginID === pluginID).map(s => (
            <p>{s.nom}</p>
        ));
        
    } */

    itemDOMS.push(<li key='inici'><Link to={ '/' }>{ i18n.t('mollaInici') }</Link></li>);

    let items = this.props.items;
    

    //const TOTAL_ITEMS = items.length;
    if (items) {
      items.forEach(({id, label}, index) => {

        /*
        if(index < TOTAL_ITEMS - 1) {
          itemDOMS.push(<li key={index}><a href={ id }>{ i18n.t(label) }</a><span className="imc-separador"> &gt; </span></li>);
        } else  {
          
          if(i18n.t(label) === 'plugin'){
            itemDOMS.push(<li id="plugin" key={index}>{pluginNom}</li>);
          }else { */
            itemDOMS.push(<li key={index}><span className="imc-separador"> &gt; </span> 
              <Link to={ id }>{i18n.t(label)}</Link></li>);
        /* }
        } */

      });
    }

    return (
      <div>
        {itemDOMS.length > 0 &&
        <ul className="mollaPa" id="imc-molla-pa">
          {itemDOMS}
        </ul>
        }
      </div>
    )
  }
}

export default withTranslation()(Breadcrumb);
/*
const Molla = withTranslation()(LegacyComponentClass)

export default function Breadcrumb({items , autenticat}) {
  return (
    <Suspense fallback="loading">
      <Molla items={items}/>
    </Suspense>
  );
}
*/
