import {
    API_URL,
    AUTH_ROUTE, IS_AUTHENTICATED,
    SING_IN,
    SING_IN_LOGIN, SING_IN_METHOD,
    SING_IN_PASSWORD, SING_UP, SING_UP_LOGIN, SING_UP_METHOD, SING_UP_NAME, SING_UP_PASSWORD, SING_UP_PRIVACY
} from './types'
import {useSelector} from 'react-redux'

export function authRouteAction(payload) {
    return {
        type: AUTH_ROUTE,
        payload
    }
}

export function singInPasswordAction(payload) {
    return {
        type: SING_IN_PASSWORD,
        payload
    }
}


export function singInLoginAction(payload) {
    return {
        type: SING_IN_LOGIN,
        payload
    }
}

export function singInAction(payload) {
    return {
        type: SING_IN,
        payload
    }
}

export function IsAuthenticatedAction(payload) {
    return {
        type: IS_AUTHENTICATED,
        payload
    }
}

export function singInPostData(data,token) {
    return dispatch => {
        fetch(API_URL + SING_IN_METHOD, {
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            method: 'POST',
        })
            .then((response) => response.json())
            .then(d => {
                if (!d.message) {
                    dispatch(singInAction(d.token))
                    dispatch(singInPasswordAction(''))
                    dispatch(IsAuthenticatedAction(true))
                } else {
                    alert('Неверные данные')
                    dispatch(IsAuthenticatedAction(false))
                }
            }).catch((e) => {
            alert('Проблемки!')
        })
    }
}


export function singUpPasswordAction(payload) {
    return {
        type: SING_UP_PASSWORD,
        payload
    }
}

export function singUpLoginAction(payload) {
    return {
        type: SING_UP_LOGIN,
        payload
    }
}

export function singUpNameAction(payload) {
    return {
        type: SING_UP_NAME,
        payload
    }
}

export function singUpPrivacyAction(payload) {
    return {
        type: SING_UP_PRIVACY,
        payload
    }
}

export function singUpAction(payload) {
    return {
        type: SING_UP,
        payload
    }
}


export function singUpPostData(data) {


    return (dispatch) => {
        fetch(API_URL + SING_UP_METHOD, {
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json',
            },
            method: 'POST',
        })
            .then((response) => {
                response.json()
            }).then(d => {
            alert('Вы успешно зарегистрировались')
            dispatch(singUpPasswordAction(''))
            dispatch(authRouteAction('singIn'))
        }).catch(e => alert('Проблемки!!'))
    }
}
