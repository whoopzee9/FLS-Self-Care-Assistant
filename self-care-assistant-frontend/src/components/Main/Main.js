import React from 'react'
import s from './Main.module.css'
import {NavLink} from 'react-router-dom'
import {EmotionsDiary} from './EmotionsDiary/EmotionsDiary'
import {Route} from 'react-router'

export const Main = () => {
    return (
        <div className={s.main}>
            <div className={s.nav}>
                <NavLink to={'/main/username'} activeClassName={s.selected}><p className={s.userName}>User Name</p></NavLink>
                <NavLink to={'/main/emotiondiary'} activeClassName={s.selected}>Emotion Diary</NavLink>
                <NavLink to={'/main/assistant'} activeClassName={s.selected}>Assistant</NavLink>
                <NavLink to={'/main/practiceshistory'} activeClassName={s.selected}>Practices history</NavLink>
            </div>
            <div className={s.content}>
                <Route path={'/main/emotiondiary'} component={EmotionsDiary}/>
            </div>
        </div>
    )
}