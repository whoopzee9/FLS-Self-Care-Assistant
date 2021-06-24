import {SING_IN, SING_IN_LOGIN, SING_IN_PASSWORD} from './types'

const initialState = {
    login: null,
    password: null,
    id:null

}
export const singInReducer = (state = initialState, action) => {
    switch (action.type) {
        case SING_IN_PASSWORD:
            return {...state, password: action.payload}
        case SING_IN_LOGIN:
            return {...state, login: action.payload}
        case SING_IN:
            return {...state, id: action.payload}
        default:
            return state
    }
}