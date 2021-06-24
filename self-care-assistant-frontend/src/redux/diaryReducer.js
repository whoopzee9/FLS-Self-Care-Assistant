import {
    EMOTION_DIARY_ADD_RECORD_DATE,
    EMOTION_DIARY_ADD_RECORD_SAVE_ACTIVE,
    EMOTION_DIARY_ADD_RECORD_SELECT,
    EMOTION_DIARY_ADD_RECORD_SLIDER,
    EMOTION_DIARY_HISTORY_ACTIVE_DELETE,
    EMOTION_DIARY_HISTORY_ACTIVE_DELETE_WINDOW,
    EMOTION_DIARY_HISTORY_DELETE,
    EMOTION_DIARY_HISTORY_FILTER_ADD_EMOTIONS,
    EMOTION_DIARY_HISTORY_FILTER_DATE,
    EMOTION_DIARY_HISTORY_FILTER_INTENSITY,
    EMOTION_DIARY_HISTORY_FILTER_REMOVE_EMOTIONS,
    EMOTION_DIARY_HISTORY_FILTER_WINDOW,
    EMOTION_DIARY_HISTORY_SAVE
} from './types'

const initialState = {
    emotions: ['sad', 'happy', 'lol', 'kek'],
    addRecords: {
        addRecordSelect: undefined,
        addRecordSlider: undefined,
        addRecordSaveActive: false,
        addRecordDate: new Date().toLocaleString(),
    },
    history: {
        historyRecords: [],
        historyFilterWindow: false,
        historyFilter: {
            emotions: [],
            intensity: {
                from: undefined,
                to: undefined
            },
            date: {
                value: undefined,
                from: undefined,
                to: undefined
            },
        }
    }
}


export const diaryReducer = (state = initialState, action) => {
    switch (action.type) {
        case EMOTION_DIARY_ADD_RECORD_SELECT :
            return {...state, addRecords: {...state.addRecords, addRecordSelect: action.payload}}
        case EMOTION_DIARY_ADD_RECORD_SLIDER :
            return {...state, addRecords: {...state.addRecords, addRecordSlider: action.payload}}
        case EMOTION_DIARY_ADD_RECORD_SAVE_ACTIVE :
            return {...state, addRecords: {...state.addRecords, addRecordSaveActive: action.payload}}
        case EMOTION_DIARY_ADD_RECORD_DATE :
            return {...state, addRecords: {...state.addRecords, addRecordDate: action.payload}}
        case EMOTION_DIARY_HISTORY_SAVE :
            return {
                ...state, history: {
                    ...state.history, historyRecords: [...state.history.historyRecords, action.payload]
                }
            }
        case EMOTION_DIARY_HISTORY_DELETE :
            return {
                ...state, history: {
                    ...state.history,
                    historyRecords: state.history.historyRecords.filter(item => item.addRecordDate !== action.payload.addRecordDate)
                }
            }
        case EMOTION_DIARY_HISTORY_ACTIVE_DELETE_WINDOW :
            return {
                ...state, history: {
                    ...state.history,
                    historyRecords: state.history.historyRecords.map((item) => item.addRecordDate === action.payload.addRecordDate ?
                        {...item, addRecordSaveActive: !action.payload.addRecordSaveActive} : item
                    )
                }
            }
        case EMOTION_DIARY_HISTORY_FILTER_WINDOW :
            return {
                ...state, history: {
                    ...state.history,
                    historyFilterWindow: action.payload
                }
            }
        case EMOTION_DIARY_HISTORY_FILTER_ADD_EMOTIONS:
            return {
                ...state, history: {
                    ...state.history,
                    historyFilter: {
                        ...state.history.historyFilter,
                        emotions: [...state.history.historyFilter.emotions, action.payload]
                    }
                }
            }
        case EMOTION_DIARY_HISTORY_FILTER_REMOVE_EMOTIONS:
            return {
                ...state, history: {
                    ...state.history,
                    historyFilter: {
                        ...state.history.historyFilter,
                        emotions: state.history.historyFilter.emotions.filter(item => item !== action.payload)
                    }
                }
            }
        case EMOTION_DIARY_HISTORY_FILTER_INTENSITY:
            return {
                ...state, history: {
                    ...state.history,
                    historyFilter: {
                        ...state.history.historyFilter,
                        intensity: {
                            ...state.history.historyFilter.intensity,
                            from: action.payload.from,
                            to: action.payload.to
                        }
                    }
                }
            }
        case EMOTION_DIARY_HISTORY_FILTER_DATE:
            return {
                ...state, history: {
                    ...state.history,
                    historyFilter: {
                        ...state.history.historyFilter,
                        date: {
                            ...state.history.historyFilter.date,
                            value: action.payload.value,
                            from: action.payload.from,
                            to: action.payload.to
                        }
                    }
                }
            }
        default :
            return state
    }
}