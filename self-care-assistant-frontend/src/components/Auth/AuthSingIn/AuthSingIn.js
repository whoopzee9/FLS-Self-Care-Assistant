import React from 'react'
import s from '../Auth.module.css'
import {useDispatch, useSelector} from 'react-redux'
import {singInLoginAction, singInPasswordAction, singInPostData} from '../../../redux/actions'

export const AuthSingIn = () => {

    const dispatch = useDispatch()

    const singIn = useSelector(state => state.singIn)

    const setLogin = (e) => {
        dispatch(singInLoginAction(e.target.value))
    }

    const setPassword = (e) => {
        dispatch(singInPasswordAction(e.target.value))
    }

    const SingInAuth = (e) => {
        e.preventDefault()
        dispatch(singInPostData({
            email: singIn.login,
            password: singIn.password,
        }))
    }

    return (
        <div className={s.route_inner}>
            <form onSubmit={SingInAuth}>
                <input type="email" placeholder={'login'} className={s.input} onChange={setLogin}/>
                <div className={s.password}>
                    <input type="password" placeholder={'password'} className={s.input} onChange={setPassword}/>
                    <button className={s.btn_password} type='button'>forgot your password?</button>
                </div>
                <input type='submit' className={s.auth_btn} onClick={SingInAuth} value={'SING IN'}/>
            </form>
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