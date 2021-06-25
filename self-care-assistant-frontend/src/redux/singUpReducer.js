import {SING_UP, SING_UP_LOGIN, SING_UP_NAME, SING_UP_PASSWORD, SING_UP_PRIVACY} from './types'


const initialState = {
    login: '',
    password: '',
    name: '',
    privacy: false,
    roles: []
}
export const singUpReducer = (state = initialState, action) => {
    switch (action.type) {
        case SING_UP_LOGIN:
            return {...state, login: action.payload}
        case SING_UP_NAME:
            return {...state, name: action.payload}
        case SING_UP_PASSWORD:
            return {...state, password: action.payload}
        case SING_UP_PRIVACY:
            return {...state, privacy: action.payload}
        case SING_UP:
            return {...state}
        default:
            return state
    }
}