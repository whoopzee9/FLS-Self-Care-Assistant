import React from 'react'
import s from '../Auth.module.css'

export const AuthSingOut = () => {
    return (
        <div className={s.route_inner}>
            <input type="text" placeholder={'name'} className={s.input}/>
            <input type="text" placeholder={'email'} className={s.input}/>
            <input type="text" placeholder={'password'} className={s.input}/>
            <input type="text" placeholder={'confirm password'} className={s.input}/>
            <div className={s.checkbox_wrapper}>
                <input type="checkbox"/>
                <label>I accept privacy policy and bla bla bla</label>
            </div>
            <button className={s.auth_btn}>SING IN</button>
        </div>
    )
}