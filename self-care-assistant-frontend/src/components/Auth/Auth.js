import React from 'react'
import s from './Auth.module.css'
import {AuthSingIn} from './AuthSingIn/AuthSingIn'
import {useDispatch, useSelector} from 'react-redux'
import {authRouteAction} from '../../redux/actions'
import {AuthSingUp} from './AuthSingUp/AuthSingUp'
import {Redirect, useLocation} from 'react-router'

export const Auth = () => {

    const dispatch = useDispatch()

    const isAuthenticated = useSelector(state => state.singIn.is_authenticated)

    const routeAuth = useSelector(state => state.auth.auth_route)

    const { state } = useLocation()

    if (isAuthenticated === true) {
        return <Redirect to={state?.from || '/main/emotiondiary/addrecord'} />
    }

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
                        value='singUp'
                        onClick={RouteAuthFunction}>
                    Sing Out
                </button>
            </div>
            {routeAuth === 'singIn' ? <AuthSingIn/> : <AuthSingUp/>}
        </div>
    )
}