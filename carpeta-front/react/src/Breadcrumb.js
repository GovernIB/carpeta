import React, { Component, Suspense } from 'react';
import i18n from 'i18next';
import { withTranslation } from 'react-i18next';
import axios from "axios";


class LegacyComponentClass extends Component{

    constructor(){
        super();
        this.state = {
            plugins: []
        }
    }

    componentDidMount() {
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        })
    }

    componentWillReceiveProps(lng) {
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        })
    }

  render(){
	  
	var autenticat = sessionStorage.getItem('autenticat');
    let items = this.props.items;
    const plugins = this.state.plugins;

    const TOTAL_ITEMS = items.length;
    let itemDOMS = [];

    var pluginID = sessionStorage.getItem('pluginActiu');
    var pluginNom = "";
    if(pluginID !== null) {
        pluginNom = plugins.filter(s => s.pluginID === pluginID).map(s => (
            <p>{s.nom}</p>
        ));
    }

    items.forEach(({id, label}, index) => {

      if(index < TOTAL_ITEMS - 1) {
        itemDOMS.push(<li key={index}><a href={ id }>{ i18n.t(label) }</a><span className="imc-separador"> &gt; </span></li>);
      } else{
        if(i18n.t(label) === 'plugin'){
          itemDOMS.push(<li id="plugin" key={index}>{pluginNom}</li>);
        }else {
          itemDOMS.push(<li key={index}>{i18n.t(label)}</li>);
        }
      }

    });

    return (
      <div>
        {itemDOMS.length > 1 &&
        <ul className="mollaPa" id="imc-molla-pa">
          {itemDOMS}
        </ul>
        }
      </div>
    )
  }
}

const Molla = withTranslation()(LegacyComponentClass)

export default function Breadcrumb({items , autenticat}) {
  return (
    <Suspense fallback="loading">
      <Molla items={items}/>
    </Suspense>
  );
}
