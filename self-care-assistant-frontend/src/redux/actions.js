import {
    API_URL,
    AUTH_ROUTE,
    SING_IN,
    SING_IN_LOGIN, SING_IN_METHOD,
    SING_IN_PASSWORD, SING_UP, SING_UP_LOGIN, SING_UP_METHOD, SING_UP_NAME, SING_UP_PASSWORD, SING_UP_PRIVACY
} from './types'

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

export function singInPostData(data) {
    return dispatch => {
        fetch(API_URL + SING_IN_METHOD, {
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
        })
            .then((response) => {
                response.json()
            })
            .then(d => {
                try {
                    dispatch(singInAction(d.token))
                    dispatch(singInPasswordAction(null))
                } catch (e) {
                    alert('Неверные данные')
                }
            }).catch((error) => {
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
                'Content-Type': 'application/json'
            },
            method: 'POST',
        })
            .then((response) => {
                response.json()


            }).then(d => {
            alert('Вы успешно зарегистрировались')
            dispatch(singUpPasswordAction(null))
            dispatch(authRouteAction('singIn'))
        }).catch(e => alert('Проблемки!!'))
    }
}
