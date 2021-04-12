import {applyMiddleware, combineReducers, createStore} from "redux";
import {countReducer} from "./countReducer";
import {listReducer} from "./listReducer";
import {composeWithDevTools} from "redux-devtools-extension";
import thunk from "redux-thunk";

const rootReducer = combineReducers({
    count: countReducer,
    list: listReducer
})

export const store = createStore(rootReducer, composeWithDevTools(applyMiddleware(thunk)))