import { combineReducers } from 'redux'
import userReducer from './userReducer'

export const GET_TOKEN = 'LOAD'
export const GET_TOKEN_SUCCESS = 'LOAD_SUCCESS'
export const GET_TOKEN_FAIL = 'LOAD_FAIL'

// const USER_INITIAL_STATE = {
//     token: ""
// }

// const userReducer = (state = USER_INITIAL_STATE, action) => {
//     switch (action.type) {
//         case GET_TOKEN:
//             return { ...state, loading: true }
//         case GET_TOKEN_SUCCESS:
//             return { ...state, loading: false, token: action.payload.data }
//         case GET_TOKEN_FAIL:
//             return { ...state, loading: false, error: 'Error whilte requesting token'}
//         default:
//             return state
//     }
// }

export default combineReducers ({
    user: userReducer
})

export function token() {
    return {
        type: GET_TOKEN,
        payload: {
            request: {
                url: '/authentication/token/new'
            }
        }
    }
}

