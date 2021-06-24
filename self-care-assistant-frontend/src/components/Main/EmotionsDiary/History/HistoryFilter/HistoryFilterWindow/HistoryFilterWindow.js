import React from 'react'
import s from './HistoryFilterWindow.module.css'
import {useDispatch, useSelector} from 'react-redux'
import {
    emotionDiaryHistoryFilterAddEmotionsAction,
    emotionDiaryHistoryFilterDateAction,
    emotionDiaryHistoryFilterIntensityAction,
    emotionDiaryHistoryFilterRemoveEmotionsAction
} from '../../../../../../redux/actions'

export const HistoryFilterWindow = (props) => {

    const emotions = useSelector(state => state.diary.emotions)
    const date = useSelector(state => state.diary.date)
    const dispatch = useDispatch()

    const getEmotion = (e) => {
        if (e.target.checked === true) {
            dispatch(emotionDiaryHistoryFilterAddEmotionsAction(e.target.value))
        } else {
            dispatch(emotionDiaryHistoryFilterRemoveEmotionsAction(e.target.value))
        }
    }
    const getIntensity = (e) => {
        e.preventDefault()
        let obj = {
            from: e.target[0].value,
            to: e.target[1].value
        }
        dispatch(emotionDiaryHistoryFilterIntensityAction(obj))
    }

    const getDate = (e) => {
        e.preventDefault()
        let obj = {
            value: e.target[0].value,
            from: e.target[1].value,
            to: e.target[2].value
        }
        dispatch(emotionDiaryHistoryFilterDateAction(obj))
    }
    return (
        <div className={s.filter}>
            <button className={s.close_btn} onClick={() => props.setActive(false)}></button>
            <div className={s.emotions_wrapper}>
                <p className={s.parameter_title}>Emotion</p>
                <div className={s.emotions_inner}>
                    <div className={s.emotions}>
                        {emotions.map(item => {
                            return (
                                <div>
                                    <input type="checkbox" onClick={e => getEmotion(e)}
                                           value={item}
                                           className={s.checkbox}/>
                                    <label className={s.emotion_title}>{item}</label>
                                </div>
                            )
                        })}
                    </div>
                    <form className={s.intensity} onSubmit={e => getIntensity(e)}>
                                <span className={s.intensity_title}>
                                    Intensity from
                                    <input type="text" className={s.input} name={'from'}/>
                                    to
                                    <input type="text" className={s.input} name={'to'}/>
                                </span>
                        <button type={'submit'}>submit</button>
                    </form>
                </div>
            </div>
            <div className={s.date_wrapper}>
                <p className={s.parameter_title}>Date:</p>
                <div className={s.date_inner}>
                    <form className={s.date} onSubmit={e => getDate(e)}>
                        <div className={s.date_item}>
                            <input type="radio" value={'all'} className={s.radio} name={'date'}/>
                            <label className={s.emotion_title}>All</label>
                        </div>
                        <div className={s.date_item}>
                            <input type="radio" value={'last_week'} className={s.radio} name={'date'}/>
                            <label className={s.emotion_title}>Last week</label>
                        </div>
                        <div className={s.date_item}>
                            <input type="radio" value={'last_month'} className={s.radio} name={'date'}/>
                            <label className={s.emotion_title}>Last month</label>
                        </div>
                        <div className={s.date_item}>
                            <input type="radio" value={'custom'} className={s.radio} name={'date'}/>
                            <label className={s.emotion_title}>Custom</label>
                            <div className={s.date_inputs}>
                                <div className={s.date_input_wrapper}>
                                    <label className={s.date_input_label}>from</label>
                                    <input type="text" className={s.date_input} name={'from'}/>
                                </div>
                                <div className={s.date_input_wrapper}>
                                    <label className={s.date_input_label}>to</label>
                                    <input type="text" className={s.date_input} name={'to'}/>
                                </div>
                            </div>
                        </div>
                        <button type={'submit'}>submit</button>
                    </form>
                </div>
            </div>
            <div className={s.btn_wrapper}>
                <button className={s.btn}>Clear all</button>
                <button className={s.btn}>Apply</button>
            </div>
        </div>
    )
}