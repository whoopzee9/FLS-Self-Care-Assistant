import React from 'react'
import s from './HistoryFilterItem.module.css'

export const HistoryFilterItem = () => {
    return (
        <div className={s.main}>
            <p className={s.parameter}>
                <span className={s.parameter_name}>Emotion:</span>
                happy
            </p>
            <button className={s.delete_btn}></button>
        </div>
    )
}