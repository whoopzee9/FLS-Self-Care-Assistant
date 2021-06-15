import {AUTH_ROUTE} from './actions'

export function authRouteAction(payload) {
    return {
        type: AUTH_ROUTE,
        payload
    }
}