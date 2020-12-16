import React, { Component, Suspense } from 'react';
import i18n from 'i18next';
import { withTranslation } from 'react-i18next';


class LegacyComponentClass extends Component{

  render(){
	  
	var autenticat = sessionStorage.getItem('autenticat');
    let items = this.props.items;

    const TOTAL_ITEMS = items.length;
    let itemDOMS = [];

    items.forEach(({id, label}, index) => {

      if(index < TOTAL_ITEMS - 1) {
        itemDOMS.push(<li key={index}><a href={ id }>{ i18n.t(label) }</a><span className="imc-separador"> &gt; </span></li>);
      } else{
        itemDOMS.push(<li key={index}>{ i18n.t(label) }</li>);
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
