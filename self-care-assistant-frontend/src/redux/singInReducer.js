import {IS_AUTHENTICATED, SING_IN, SING_IN_LOGIN, SING_IN_PASSWORD} from './types'

const initialState = {
    login: '',
    password: '',
    token:'',
    is_authenticated: false

}
export const singInReducer = (state = initialState, action) => {
    switch (action.type) {
        case SING_IN_PASSWORD:
            return {...state, password: action.payload}
        case SING_IN_LOGIN:
            return {...state, login: action.payload}
        case SING_IN:
            return {...state, token: action.payload}
        case IS_AUTHENTICATED:
            return {...state, is_authenticated: action.payload}
        default:
            return state
    }
}