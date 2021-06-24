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
    EMOTION_DIARY_HISTORY_SAVE
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
