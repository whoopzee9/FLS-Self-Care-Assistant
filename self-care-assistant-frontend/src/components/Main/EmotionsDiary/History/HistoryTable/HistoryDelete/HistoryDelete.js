import React, {useEffect, useRef} from 'react'
import s from './HistoryDelete.module.css'
import {useDispatch, useSelector} from 'react-redux'
import {
    deleteEmotionsData,
    emotionDiaryHistoryActiveDeleteWindowAction
} from '../../../../../../redux/actions'


export const HistoryDelete = (props) => {

    const node1 = useRef()
    const dispatch = useDispatch()
    const token = useSelector(state => state.singIn.token)
    useEffect(() => {
        document.addEventListener('mousedown', handleClick)
        return () => {
            document.removeEventListener('mousedown', handleClick)
        }
    }, [])


    const handleClick = e => {
        let obj = {
            addRecordSaveActive: true,
            addRecordDate: props.item.createDate
        }
        if (node1.current.contains(e.target) === false) {
            dispatch(emotionDiaryHistoryActiveDeleteWindowAction(obj))
        }
    }
    const SetNotActive = (item) => {
        dispatch(emotionDiaryHistoryActiveDeleteWindowAction(item))
    }

    const DeleteItem = () => {
        dispatch(deleteEmotionsData(token, props.item.id))
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
                    {props.item.createDate}
                </p>
                <p className={s.item}>
                    <span className={s.item_title}>emotion:</span>
                    {props.item.emotionName.name}
                </p>
                <p className={s.item}>
                    <span className={s.item_title}>intensity:</span>
                    {props.item.intensity}
                </p>
            </div>
            <div className={s.btn_wrapper}>
                <button className={s.btn} onClick={() => SetNotActive(props.item)}>Cancel</button>
                <button className={s.btn} onClick={DeleteItem}>Remove</button>
            </div>
        </div>
    )
}