import {combineReducers} from 'redux'
import {authReducer} from './authReducer'
import {singInReducer} from './singInReducer'
import {singUpReducer} from './singUpReducer'
import {diaryReducer} from './diaryReducer'

export const rootReducer = combineReducers({
    auth: authReducer,
    singIn: singInReducer,
    singUp: singUpReducer,
    diary: diaryReducer,
})