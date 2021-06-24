import './App.css';
import {Auth} from './components/Auth/Auth'
import {Redirect, Route} from 'react-router'

function App() {

    return (
        <div className="App">
            <Route exact path='/' component={Auth}/>
        </div>
    );
}

export default App;
