import {combineReducers} from 'redux'
import {authReducer} from './authReducer'
import {singInReducer} from './singInReducer'
import {singUpReducer} from './singUpReducer'

export const rootReducer = combineReducers({
    auth: authReducer,
    singIn: singInReducer,
    singUp: singUpReducer,
})