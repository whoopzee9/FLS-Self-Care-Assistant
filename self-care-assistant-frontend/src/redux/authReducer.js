import {AUTH_ROUTE} from './types'

const initialState = {
    auth_route: 'singIn'
}
export const authReducer = (state = initialState, action) => {
    switch (action.type) {
        case AUTH_ROUTE:
            return {...state, auth_route: action.payload}
        default:
            return state
    }
}