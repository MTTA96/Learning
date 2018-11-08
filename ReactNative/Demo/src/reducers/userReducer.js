import {GET_TOKEN} from './../actions/type'

const initialState = 'g'

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_TOKEN:
            return {...state, loading: true}
        default: 
            return state
    }
}