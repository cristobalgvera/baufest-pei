import React from 'react';
import './App.css';
import Menu from './components/Menu';
import { BrowserRouter} from 'react-router-dom';
import Jugador from './containers/Jugador/Jugador';

function App() {

  return (
    <BrowserRouter>
      <div className="App">
        <Menu />
        <Jugador />
      </div>
    </BrowserRouter>
  );
}

export default App;