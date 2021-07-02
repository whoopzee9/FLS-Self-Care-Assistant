import './App.css'
import {Auth} from './components/Auth/Auth'
import {Redirect, Route, Router, Switch} from 'react-router'
import {useSelector} from 'react-redux'
import {Content} from './components/Content/Content'
import {Main} from './components/Main/Main'

function App() {

    const isAuthenticated = useSelector(state => state.singIn.is_authenticated)

    function PrivateRoute({ children, ...rest }) {
        return (
            <Route {...rest} render={({ location }) => {
                return isAuthenticated
                    ? children
                    : <Redirect to={{
                        pathname: '/login',
                    }} />
            }} />
        )
    }

    return (
        <div className="App">
            <Route path="/login" component={Auth}/>
            <PrivateRoute path='/emotiondiary' component={Main}/>
            <Route path="/"><Redirect to={'/login'}/></Route>
        </div>
    )
}

export default App