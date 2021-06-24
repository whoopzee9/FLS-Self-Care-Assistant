import React from 'react'
import s from './HistoryTable.module.css'
import {useDispatch, useSelector} from 'react-redux'
import {
    emotionDiaryAddRecordSaveActiveAction,
    emotionDiaryHistoryActiveDeleteWindowAction,
    emotionDiaryHistoryDeleteAction
} from '../../../../../redux/actions'
import {AddRecordSave} from '../../AddRecord/AddRecordSave/AddRecordSave'
import {HistoryDelete} from './HistoryDelete/HistoryDelete'

export const HistoryTable = () => {

    const list = useSelector(state => state.diary.history.historyRecords)

    const dispatch = useDispatch()

    const SetActive = (item) => {
        dispatch(emotionDiaryHistoryActiveDeleteWindowAction(item))
    }

    return (
        <div className={s.main}>
            <table className={s.table}>
                <tr className={s.table_row}>
                    <th className={s.table_title}>Date</th>
                    <th className={s.table_title}>Emotion</th>
                    <th className={s.table_title}>Intensity</th>
                    <th className={s.table_title_empty}></th>
                </tr>
                {list.map(item => {
                    return (
                        <tr className={s.table_row}>
                            <td className={s.table_item}>{item.addRecordDate}</td>
                            <td className={s.table_item}>{item.addRecordSelect}</td>
                            <td className={s.table_item}>{item.addRecordSlider}</td>
                            <td className={s.table_item}>
                                <div className={s.btn_wrapper}>
                                    <button className={s.delete_btn} onClick={()=>SetActive(item)}></button>
                                    {item.addRecordSaveActive ?
                                        <HistoryDelete item={item} active={item.addRecordSaveActive}/> : <div></div>}
                                </div>
                            </td>
                        </tr>
                    )
                })}
            </table>
        </div>
    )
}