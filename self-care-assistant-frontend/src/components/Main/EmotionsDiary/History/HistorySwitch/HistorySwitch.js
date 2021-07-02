import React from 'react'
import s from './HistorySwitch.module.css'

export const HistorySwitch = () => {
    return (
        <div className={s.main}>
            <div className={s.switch_wrapper}>
                <span className={s.switch_title}>List</span>
                <div className={s.switch}>
                    <input className={s.switch_input} type="checkbox" id="switch"/>
                    <label className={s.switch_label} htmlFor="switch">Toggle</label>
                </div>
                <span className={s.switch_title}>Chart</span>
            </div>
        </div>
    )
}