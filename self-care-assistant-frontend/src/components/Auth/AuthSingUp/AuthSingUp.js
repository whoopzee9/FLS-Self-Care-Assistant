import React, {useState} from 'react'
import s from '../Auth.module.css'
import {useDispatch, useSelector} from 'react-redux'
import {
    singUpLoginAction, singUpNameAction, singUpPasswordAction, singUpPostData, singUpPrivacyAction
} from '../../../redux/actions'

export const AuthSingUp = () => {

    const dispatch = useDispatch()

    const singUp = useSelector(state => state.singUp)

    const [confirmPassword, setConfirmPassword] = useState('')

    const setLogin = (e) => {
        dispatch(singUpLoginAction(e.target.value))
    }

    const setPassword = (e) => {
        dispatch(singUpPasswordAction(e.target.value))
    }

    const PasswordConfirm = (e) => {
        setConfirmPassword(e.target.value)
    }

    const setName = (e) => {
        dispatch(singUpNameAction(e.target.value))
    }

    const setPrivacy = (e) => {
        dispatch(singUpPrivacyAction(e.target.checked))
    }

    const SingUpAuth = () => {
        if (singUp.password === confirmPassword && singUp.privacy === true) {
            dispatch(singUpPostData({
                email: singUp.login,
                password: singUp.password,
                name: singUp.name,
                roles: singUp.roles
            }))
        } else {
            if(singUp.password !== confirmPassword) {
                setConfirmPassword('')
                dispatch(singUpPasswordAction(''))
                alert('У вас не совпадают пароли')
            }
            if(!singUp.privacy) {
                alert('Поставьте галочку!!!!')
            }
        }
    }

    return (
        <div className={s.route_inner}>
            <input type="text" placeholder={'name'} className={s.input} onChange={setName}/>
            <input type="text" placeholder={'email'} className={s.input} onChange={setLogin}/>
            <input type="text" placeholder={'password'} className={s.input} value={singUp.password}
                   onChange={setPassword}/>
            <input type="text" placeholder={'confirm password'} className={s.input} value={confirmPassword}
                   onChange={PasswordConfirm}/>
            <div className={s.checkbox_wrapper}>
                <input type="checkbox" onChange={setPrivacy}/>
                <label>I accept privacy policy and bla bla bla</label>
            </div>
            <button className={s.auth_btn} onClick={SingUpAuth}>SING UP</button>
        </div>
    )
}