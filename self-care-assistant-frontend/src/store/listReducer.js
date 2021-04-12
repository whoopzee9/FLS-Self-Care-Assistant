const defaultState = {
    list: [],
}

const ADD_TEXT = "ADD_TEXT";
const DELETE_TEXT = "DELETE_TEXT";
const ADD_MUCH_TEXT = 'ADD_MUCH_TEXT';

export const listReducer = (state = defaultState, action) => {
    switch (action.type){

        case ADD_MUCH_TEXT:
            return {...state, list: [...state.list, ...action.payload] }
        case ADD_TEXT:
            return {...state, list: [...state.list, action.payload] }
        case DELETE_TEXT:
            return {...state, list: state.list.filter( item => item.id !==action.payload) }
        default:
            return state
    }
}

export const addTextAction = (payload) => ({type:ADD_TEXT, payload});
export const addMuchTextAction = (payload) => ({type:ADD_MUCH_TEXT, payload});
export const deleteTextAction = (payload) => ({type:DELETE_TEXT, payload});