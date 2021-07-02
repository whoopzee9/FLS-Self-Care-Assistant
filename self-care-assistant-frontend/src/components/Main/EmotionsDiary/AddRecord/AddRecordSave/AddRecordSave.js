import React, {useEffect, useRef} from 'react'
import s from './AddRecordSave.module.css'
import {useDispatch, useSelector} from 'react-redux'
import {emotionDiaryAddRecordSaveActiveAction} from '../../../../../redux/actions'
import {NavLink} from 'react-router-dom'


export const AddRecordSave = () => {

    const node = useRef()
    const dispatch = useDispatch()
    const data_redux = useSelector(state => state.diary.addRecords)

    useEffect(() => {
        document.addEventListener('mousedown', handleClick)
        return () => {
            document.removeEventListener('mousedown', handleClick)
        }
    }, [])


    const handleClick = e => {
        if (node.current.contains(e.target) === false) {
            dispatch(emotionDiaryAddRecordSaveActiveAction(false))
        }
    }
    const SetNotActive = () => {
        dispatch(emotionDiaryAddRecordSaveActiveAction(false))
    }

    return (
        <div className={s.main} ref={node}>
            <button className={s.close_btn} onClick={SetNotActive}></button>
            <div className={s.inner}>
                <p className={s.copyright}>New record has been saved!</p>
                <p className={s.item}>
                    <span className={s.item_title}>date:</span>
                    {data_redux.addRecordDate.toLocaleString()}
                </p>
                <p className={s.item}>
                    <span className={s.item_title}>emotion:</span>
                    {data_redux.addRecordSelect.name}
                </p>
                <p className={s.item}>
                    <span className={s.item_title}>intensity:</span>
                    {data_redux.addRecordSlider}
                </p>
            </div>
            <NavLink to={'/main/emotiondiary/history'} className={s.history_btn} onClick={SetNotActive}>Go to history</NavLink>
        </div>
    )
}