import React from 'react'
import s from './HistoryFilter.module.css'
import {HistoryFilterItem} from './HistoryFilterItem/HistoryFilterItem'
import {HistoryFilterWindow} from './HistoryFilterWindow/HistoryFilterWindow'
import {useDispatch, useSelector} from 'react-redux'
import {emotionDiaryHistoryFilterWindowAction} from '../../../../../redux/actions'

export const HistoryFilter = () => {

    const activeWindow = useSelector(state => state.diary.history.historyFilterWindow)
    const dispatch = useDispatch()

    const SetActive = (value) => {
        dispatch(emotionDiaryHistoryFilterWindowAction(value))
    }

    return (
        <div className={s.main}>
            <HistoryFilterItem/>
            <div className={s.filter_wrapper}>
                <button className={s.filter_btn} onClick={() => SetActive(true)}></button>
                {activeWindow ? <HistoryFilterWindow key={new Date()} active={activeWindow} setActive={SetActive}/> : <div></div> }
            </div>
        </div>
    )
}