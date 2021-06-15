import React from 'react'
import s from '../Auth.module.css'

export const AuthSingIn = () => {
    return (
        <div className={s.route_inner}>
            <input type="text" placeholder={'login'} className={s.input}/>
            <div className={s.password}>
                <input type="text" placeholder={'password'} className={s.input}/>
                <button className={s.btn_password}>forgot your password?</button>
            </div>
            <button className={s.auth_btn}>SING IN</button>
            <div className={s.string}>
                <div className={s.line}/>
                or
                <div className={s.line}/>
            </div>
            <div className={s.social}>
                <button className={s.social_btn}>google</button>
                <button className={s.social_btn}>yandex</button>
            </div>
        </div>
    )
}