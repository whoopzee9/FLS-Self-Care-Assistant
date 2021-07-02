import React from 'react'
import s from './HistoryTable.module.css'
import {useDispatch, useSelector} from 'react-redux'
import {emotionDiaryHistoryActiveDeleteWindowAction} from '../../../../../redux/actions'
import {HistoryDelete} from './HistoryDelete/HistoryDelete'

export const HistoryTable = () => {

    const list = useSelector(state => state.diary.history.historyRecords)

    const dispatch = useDispatch()

    const SetActive = (item) => {
        dispatch(emotionDiaryHistoryActiveDeleteWindowAction(item))
    }

    console.log(list)
    return (
        <div className={s.main}>
            <table className={s.table}>
                <tbody>
                <tr className={s.table_row}>
                    <th className={s.table_title}>Date</th>
                    <th className={s.table_title}>Emotion</th>
                    <th className={s.table_title}>Intensity</th>
                    <th className={s.table_title_empty}></th>
                </tr>
                {list.map(item => {
                    return (
                        <tr className={s.table_row} key={item.id}>
                            <td className={s.table_item}>{item.createDate}</td>
                            <td className={s.table_item}>{item.emotionName.name}</td>
                            <td className={s.table_item}>{item.intensity}</td>
                            <td className={s.table_item}>
                                <div className={s.btn_wrapper}>
                                    <button className={s.delete_btn} onClick={() => SetActive(item)}></button>
                                    {item.addRecordSaveActive ?
                                        <HistoryDelete item={item} active={item.addRecordSaveActive}/> : <div></div>}
                                </div>
                            </td>
                        </tr>
                    )
                })}
                </tbody>
            </table>
        </div>
    )
}