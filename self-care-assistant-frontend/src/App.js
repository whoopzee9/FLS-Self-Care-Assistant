import './App.css';
import {useDispatch, useSelector} from "react-redux";
import {minusCountAction, plusCountAction} from "./store/countReducer";
import {addTextAction, deleteTextAction} from "./store/listReducer";
import {fetchList} from "./asyncAction/list";
import Test from "./components/test";

function App() {

    const dispatch = useDispatch();
    const count = useSelector(state => state.count.count);
    const list = useSelector(state => state.list.list);
    console.log(count);
    console.log(list);

    const Increment = () => {
        dispatch(plusCountAction(1))
    }

    const Decrement = () => {
        dispatch(minusCountAction(1))
    }

    const addText = (text) => {
        const item = {
            text,
            id: Date.now()
        }
        dispatch(addTextAction(item))
    }

    const deleteText = (item) => {
        dispatch(deleteTextAction(item.id))
    }

    return (
        <div className="App">
            <Test/>
            <div className="count">
                <p>{count}</p>
                <button onClick={() => Decrement()}>-</button>
                <button onClick={() => Increment()}>+</button>
            </div>
            <div className="list">
                <div className='wrapper'>
                <button onClick={() => addText(prompt())}>Добавить текст</button>
                <button onClick={() => dispatch(fetchList())}>Данные с api</button>
                </div>
                <div className='wrapper'>
                <div className='todos'>
                    {list.map(item =>
                        item.name ?
                            <div></div>
                            :
                            <div>{item.text}
                                <button onClick={() => deleteText(item)}>del</button>
                            </div>
                    )}
                </div>
                <div className='api'>
                    {list.map(item =>
                        item.name ?
                            <div>{item.name}
                                <button onClick={() => deleteText(item)}>del</button>
                            </div>
                            :
                            <div></div>
                    )}
                </div>
                </div>
            </div>
        </div>
    );
}

export default App;
