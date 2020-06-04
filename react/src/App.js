import React, {Component} from 'react';

// Importar components
import MenuLateral from './components/MenuLateral';
import LateralDret from './components/LateralDret';

//import { cookie } from 'react-cookie'

class App extends Component {

	render() {

  //  /* global Modernizr */
    //console.log(Modernizr);

//const { userId } = false

//if (!userId) {
//cookie.save('userId', userId, { path: '/' })
		return (
      <div>

  			<MenuLateral />
        <LateralDret />
      </div>
		);

}

	//}
}

export default App;
