import { createContext, useReducer } from "react"

const initialState = {
    userData: null,
    jwtToken: null
}

export const Context = createContext(initialState);

export const Store = ({ children }) => {
    const [state, dispatch] = useReducer((state, action) => {
        switch(action.type) {
            case "ADD_USER" : 
                return {...state, userData: action.payload};
            case "ADD_JWT" : 
                return {...state, jwtToken: action.payload}
            default : 
                return {...state}
        }
    }, initialState);

    return (
        <Context.Provider value={[state, dispatch]}> 
            {children}
        </Context.Provider>
    )
}
