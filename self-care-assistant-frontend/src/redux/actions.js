import {
    EMOTION_DIARY_ADD_RECORD_DATE,
    EMOTION_DIARY_ADD_RECORD_SAVE_ACTIVE,
    EMOTION_DIARY_ADD_RECORD_SELECT,
    EMOTION_DIARY_ADD_RECORD_SLIDER,
    EMOTION_DIARY_HISTORY_ACTIVE_DELETE_WINDOW,
    EMOTION_DIARY_HISTORY_FILTER_ADD_EMOTIONS,
    EMOTION_DIARY_HISTORY_FILTER_DATE,
    EMOTION_DIARY_HISTORY_FILTER_INTENSITY,
    EMOTION_DIARY_HISTORY_FILTER_REMOVE_EMOTIONS,
    EMOTION_DIARY_HISTORY_FILTER_WINDOW,
    API_URL,
    AUTH_ROUTE,
    IS_AUTHENTICATED,
    SING_IN,
    SING_IN_LOGIN,
    SING_IN_METHOD,
    SING_IN_PASSWORD,
    SING_UP_LOGIN,
    SING_UP_METHOD,
    SING_UP_NAME,
    SING_UP_PASSWORD,
    SING_UP_PRIVACY,
    EMOTION_DIARY_ADD_RECORD_GET_EMOTION,
    GET_EMOTION_NAME_METHOD,
    SAVE_EMOTION_METHOD, EMOTION_DIARY_GET_HISTORY, GET_EMOTION_METHOD, DELETE_EMOTION_METHOD
} from './types'

export function emotionDiaryAddRecordSelectAction(payload) {
    return {
        type: EMOTION_DIARY_ADD_RECORD_SELECT,
        payload
    }
}

export function emotionDiaryAddRecordSliderAction(payload) {
    return {
        type: EMOTION_DIARY_ADD_RECORD_SLIDER,
        payload
    }
}

export function emotionDiaryAddRecordSaveActiveAction(payload) {
    return {
        type: EMOTION_DIARY_ADD_RECORD_SAVE_ACTIVE,
        payload
    }
}

export function emotionDiaryAddRecordDateAction(payload) {
    return {
        type: EMOTION_DIARY_ADD_RECORD_DATE,
        payload
    }
}


export function emotionDiaryHistoryActiveDeleteWindowAction(payload) {
    return {
        type: EMOTION_DIARY_HISTORY_ACTIVE_DELETE_WINDOW,
        payload
    }
}


export function emotionDiaryHistoryFilterWindowAction(payload) {
    return {
        type: EMOTION_DIARY_HISTORY_FILTER_WINDOW,
        payload,
    }
}

export function emotionDiaryHistoryFilterAddEmotionsAction(payload) {
    return {
        type: EMOTION_DIARY_HISTORY_FILTER_ADD_EMOTIONS,
        payload,
    }
}

export function emotionDiaryHistoryFilterRemoveEmotionsAction(payload) {
    return {
        type: EMOTION_DIARY_HISTORY_FILTER_REMOVE_EMOTIONS,
        payload,
    }
}

export function emotionDiaryHistoryFilterIntensityAction(payload) {
    return {
        type: EMOTION_DIARY_HISTORY_FILTER_INTENSITY,
        payload,
    }
}

export function emotionDiaryHistoryFilterDateAction(payload) {
    return {
        type: EMOTION_DIARY_HISTORY_FILTER_DATE,
        payload,
    }
}

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

export function singInPostData(data) {
    return dispatch => {
        fetch(API_URL + SING_IN_METHOD, {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json',

            },
        })
            .then((response) => response.json())
            .then(d => {
                if (!d.message) {
                    dispatch(singInAction(d.token))
                    dispatch(getEmotionName(d.token))
                    dispatch(getEmotionsData(d.token))
                    dispatch(singInPasswordAction(''))
                    dispatch(IsAuthenticatedAction(true))
                } else {
                    alert('Неверные данные')
                    dispatch(IsAuthenticatedAction(false))
                }
            })
            .catch((e) => {
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

export function getEmotionName(token) {
    return (dispatch) => {
        fetch(API_URL + GET_EMOTION_NAME_METHOD, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token,
            },
        })
            .then((response) => response.json())
            .then((d) => {
                dispatch(getEmotions(d))
            }).catch(e => console.log(e))
    }
}

export function getEmotions(payload) {
    return {
        type: EMOTION_DIARY_ADD_RECORD_GET_EMOTION,
        payload
    }
}

export function saveEmotionPostData(token, data) {
    return (dispatch) => {
        fetch(API_URL + SAVE_EMOTION_METHOD, {
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token,
            },
            method: 'POST',
        }).then(() => {
            dispatch(emotionDiaryAddRecordSaveActiveAction(true))
            dispatch(getEmotionsData(token))
        })
            .catch(e => alert('Проблемки!!'))
    }
}

export function getEmotionsData(token) {
    return (dispatch) => {
        fetch(API_URL + GET_EMOTION_METHOD, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token,
            },
        })
            .then((response) => response.json())
            .then((d) => {
                dispatch(getEmotionsHistory(d))
            }).catch(e => console.log(e))
    }
}

export function getEmotionsHistory(payload) {
    return {
        type: EMOTION_DIARY_GET_HISTORY,
        payload
    }
}

export function deleteEmotionsData(token, data) {
    return (dispatch) => {
        fetch(API_URL + DELETE_EMOTION_METHOD + '?id=' + data, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token,
            },
        }).then(() => dispatch(getEmotionsData(token)))
            .catch(e => console.log(e))
    }
}