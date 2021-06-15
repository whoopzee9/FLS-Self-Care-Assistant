import React from 'react'
import s from './Auth.module.css'
import {AuthSingIn} from './AuthSingIn/AuthSingIn'
import {useDispatch, useSelector} from 'react-redux'
import {authRouteAction} from '../../redux/types'
import {AuthSingOut} from './AuthSingOut/AuthSingOut'

export const Auth = () => {

    const dispatch = useDispatch()

    const routeAuth = useSelector(state => state.auth.auth_route)

    const RouteAuthFunction = (e) => {
        e.preventDefault()
        dispatch(authRouteAction(e.target.value))
    }

    return (
        <div className={s.main}>
            <div className={s.btn_wrapper}>
                <button className={routeAuth === 'singIn' ? s.route_btn + ' ' + s.active_route_btn : s.route_btn}
                        value='singIn'
                        onClick={RouteAuthFunction}>
                    Sing In
                </button>
                <button className={routeAuth !== 'singIn' ? s.route_btn + ' ' + s.active_route_btn : s.route_btn}
                        value='singOut'
                        onClick={RouteAuthFunction}>
                    Sing Out
                </button>
            </div>
            {routeAuth === 'singIn' ? <AuthSingIn/> : <AuthSingOut/>}
        </div>
    )
}