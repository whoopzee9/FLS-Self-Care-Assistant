import React from 'react'
import s from './AddRecord.module.css'
import 'rc-slider/assets/index.css'
import {AddRecordSelect} from './AddRecordSelect/AddRecordSelect'
import {AddRecordSlider} from './AddRecordSlider/AddRecordSlider'
import {AddRecordSave} from './AddRecordSave/AddRecordSave'
import {useDispatch, useSelector} from 'react-redux'
import {emotionDiaryAddRecordDateAction,saveEmotionPostData} from '../../../../redux/actions'


export const AddRecord = () => {

    const active = useSelector(state => state.diary.addRecords.addRecordSaveActive)
    const info = useSelector(state => state.diary.addRecords)
    const dispatch = useDispatch()

    const token = useSelector(state => state.singIn.token)

    const emotions = useSelector(state => state.diary.emotions)

    let selectOption = []

    const getEmotionsNamesFunction = () => {
        emotions.map(item => {
            selectOption.push({value: item.id, label: item.name})
        })
    }

    window.onload = getEmotionsNamesFunction()

    const saveEmotion = () => {
        if (info.addRecordSelect && info.addRecordSlider) {
            dispatch(emotionDiaryAddRecordDateAction(new Date()))
            dispatch(saveEmotionPostData(token, {
                createDate:  new Date().toISOString().replace(/T/, ' ').replace(/\..+/, ''),
                intensity: info.addRecordSlider,
                emotionName: info.addRecordSelect
            }))
        }
    }

    return (
        <div className={s.main}>
            <div className={s.select}>
                <p className={s.label}>Select emotion</p>
                <AddRecordSelect selectOption={selectOption}/>
            </div>
            <div className={s.input}>
                <p className={s.label}>Emotion intensity</p>
                <AddRecordSlider/>
            </div>
            <div className={s.btn_wrapper}>
                <button className={s.btn} onClick={saveEmotion}>Save</button>
                {active ? <AddRecordSave active={active}/> : <div></div>}
            </div>
        </div>
    )
}