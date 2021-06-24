import React from 'react'
import s from './EmotionsDiary.module.css'
import {NavLink} from 'react-router-dom'
import {Redirect, Route} from 'react-router'
import {AddRecord} from './AddRecord/AddRecord'
import {History} from './History/History'

export const EmotionsDiary = () => {
    return (
        <div className={s.main}>
            <div className={s.menu}>
                <NavLink to={'/emotiondiary/addrecord'} activeClassName={s.selected}>Add Record</NavLink>
                <NavLink to={'/emotiondiary/history'} activeClassName={s.selected}>History</NavLink>
            </div>
            <div className={s.content}>
                <Route exact path={'/emotiondiary'}><Redirect to={'/emotiondiary/addrecord'}/></Route>
                <Route path={'/emotiondiary/addrecord'} component={AddRecord}/>
                <Route path={'/emotiondiary/history'} component={History}/>
            </div>
        </div>
    )
}