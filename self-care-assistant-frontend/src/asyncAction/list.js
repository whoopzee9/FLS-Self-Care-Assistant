import {addMuchTextAction} from "../store/listReducer";

export const fetchList = () => {
    return function (dispatch) {
        fetch('https://jsonplaceholder.typicode.com/users')
            .then(response => response.json())
            .then(json => dispatch(addMuchTextAction(json)))
            .then(json => console.log(json))
    }
}