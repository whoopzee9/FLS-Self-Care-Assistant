import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {Provider} from "react-redux";
import {applyMiddleware, createStore} from 'redux'
import {rootReducer} from './redux/rootReducer'
import {composeWithDevTools} from 'redux-devtools-extension'
import thunk from 'redux-thunk'
import {BrowserRouter} from 'react-router-dom'
import {Route} from 'react-router'

const store = createStore(rootReducer, composeWithDevTools(applyMiddleware(thunk)))

ReactDOM.render(
    <Provider store={store}>
        <BrowserRouter>
            <Route path={'/'} component={App}/>
        </BrowserRouter>
    </Provider>,
    document.getElementById('root'))