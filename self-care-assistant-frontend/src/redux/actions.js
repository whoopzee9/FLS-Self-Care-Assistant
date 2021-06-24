import {
    EMOTION_DIARY_ADD_RECORD_DATE,
    EMOTION_DIARY_ADD_RECORD_SAVE_ACTIVE,
    EMOTION_DIARY_ADD_RECORD_SELECT,
    EMOTION_DIARY_ADD_RECORD_SLIDER,
    EMOTION_DIARY_HISTORY_ACTIVE_DELETE_WINDOW,
    EMOTION_DIARY_HISTORY_DELETE,
    EMOTION_DIARY_HISTORY_FILTER_ADD_EMOTIONS,
    EMOTION_DIARY_HISTORY_FILTER_DATE,
    EMOTION_DIARY_HISTORY_FILTER_INTENSITY,
    EMOTION_DIARY_HISTORY_FILTER_REMOVE_EMOTIONS,
    EMOTION_DIARY_HISTORY_FILTER_WINDOW,
    EMOTION_DIARY_HISTORY_SAVE,
    API_URL,
    AUTH_ROUTE, IS_AUTHENTICATED,
    SING_IN,
    SING_IN_LOGIN, SING_IN_METHOD,
    SING_IN_PASSWORD, SING_UP, SING_UP_LOGIN, SING_UP_METHOD, SING_UP_NAME, SING_UP_PASSWORD, SING_UP_PRIVACY
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

export function emotionDiaryHistorySaveAction(payload) {
    return {
        type: EMOTION_DIARY_HISTORY_SAVE,
        payload
    }
}

export function emotionDiaryHistoryActiveDeleteWindowAction(payload) {
    return {
        type: EMOTION_DIARY_HISTORY_ACTIVE_DELETE_WINDOW,
        payload
    }
}

export function emotionDiaryHistoryDeleteAction(payload) {
    return {
        type: EMOTION_DIARY_HISTORY_DELETE,
        payload,
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

