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
                <NavLink to={'/main/emotiondiary/addrecord'} activeClassName={s.selected}>Add Record</NavLink>
                <NavLink to={'/main/emotiondiary/history'} activeClassName={s.selected}>History</NavLink>
            </div>
            <div className={s.content}>
                <Route exact path={'/main/emotiondiary'}><Redirect to={'/main/emotiondiary/addrecord'}/></Route>
                <Route path={'/main/emotiondiary/addrecord'} component={AddRecord}/>
                <Route path={'/main/emotiondiary/history'} component={History}/>
            </div>
        </div>
    )
}