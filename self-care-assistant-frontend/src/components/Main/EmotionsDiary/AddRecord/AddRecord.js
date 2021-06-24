import React from 'react'
import s from './AddRecord.module.css'
import 'rc-slider/assets/index.css'
import {AddRecordSelect} from './AddRecordSelect/AddRecordSelect'
import {AddRecordSlider} from './AddRecordSlider/AddRecordSlider'
import {AddRecordSave} from './AddRecordSave/AddRecordSave'
import {useDispatch, useSelector} from 'react-redux'
import {
    emotionDiaryAddRecordDateAction,
    emotionDiaryAddRecordSaveActiveAction,
    emotionDiaryHistorySaveAction
} from '../../../../redux/actions'


export const AddRecord = () => {

    const active = useSelector(state => state.diary.addRecords.addRecordSaveActive)
    const info = useSelector(state => state.diary.addRecords)
    const dispatch = useDispatch()

    const SetActive = () => {
        if (info.addRecordSelect && info.addRecordSlider) {
            dispatch(emotionDiaryAddRecordSaveActiveAction(true))
            dispatch(emotionDiaryAddRecordDateAction(new Date().toLocaleString()))
            dispatch(emotionDiaryHistorySaveAction(info))
        }
    }
    return (
        <div className={s.main}>
            <div className={s.select}>
                <p className={s.label}>Select emotion</p>
                <AddRecordSelect/>
            </div>
            <div className={s.input}>
                <p className={s.label}>Emotion intensity</p>
                <AddRecordSlider/>
            </div>
            <div className={s.btn_wrapper}>
                <button className={s.btn} onClick={SetActive}>Save</button>
                {active ? <AddRecordSave active={active}/> : <div></div>}
            </div>
        </div>
    )
}