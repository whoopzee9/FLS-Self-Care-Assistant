import React from 'react'
import s from './History.module.css'
import {HistoryTable} from './HistoryTable/HistoryTable'
import {HistorySwitch} from './HistorySwitch/HistorySwitch'
import {HistoryFilter} from './HistoryFilter/HistoryFilter'

export const History = () => {
    return (
        <div className={s.main}>
            <HistorySwitch/>
            <HistoryFilter/>
            <HistoryTable/>
        </div>
    )
}