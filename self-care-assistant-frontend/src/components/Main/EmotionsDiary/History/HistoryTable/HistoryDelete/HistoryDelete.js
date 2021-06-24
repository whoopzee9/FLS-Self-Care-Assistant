import React, {useEffect, useRef} from 'react'
import s from './HistoryDelete.module.css'
import {useDispatch} from 'react-redux'
import {
    emotionDiaryHistoryActiveDeleteWindowAction,
    emotionDiaryHistoryDeleteAction
} from '../../../../../../redux/actions'


export const HistoryDelete = (props) => {

    const node1 = useRef()
    const dispatch = useDispatch()

    useEffect(() => {
        document.addEventListener('mousedown', handleClick)
        return () => {
            document.removeEventListener('mousedown', handleClick)
        }
    }, [])


    const handleClick = e => {
        let obj = {
            addRecordSaveActive: true,
            addRecordDate: props.item.addRecordDate
        }
        if (node1.current.contains(e.target) === false) {
            dispatch(emotionDiaryHistoryActiveDeleteWindowAction(obj))
        }
    }
    const SetNotActive = (item) => {
        dispatch(emotionDiaryHistoryActiveDeleteWindowAction(item))
    }

    const DeleteItem = (value) => {
        dispatch(emotionDiaryHistoryDeleteAction(value))
    }

    return (
        <div className={s.main} ref={node1}>
            <button className={s.close_btn} onClick={() => SetNotActive(props.item)}></button>
            <div className={s.inner}>
                <p className={s.copyright}>
                    Are you sure that you want
                    to remove the record?
                </p>
                <p className={s.item}>
                    <span className={s.item_title}>date:</span>
                    {props.item.addRecordDate}
                </p>
                <p className={s.item}>
                    <span className={s.item_title}>emotion:</span>
                    {props.item.addRecordSelect}
                </p>
                <p className={s.item}>
                    <span className={s.item_title}>intensity:</span>
                    {props.item.addRecordSlider}
                </p>
            </div>
            <div className={s.btn_wrapper}>
                <button className={s.btn} onClick={() => SetNotActive(props.item)}>Cancel</button>
                <button className={s.btn} onClick={() => DeleteItem(props.item)}>Remove</button>
            </div>
        </div>
    )
}