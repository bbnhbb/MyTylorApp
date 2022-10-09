import {
    createBrowserRouter,
} from "react-router-dom";
import Home from "../Pages/Home";
import LoginRegister from "../Pages/LoginRegister";


export const router = createBrowserRouter([
    {
        path: "/",
        element: <LoginRegister />,
    },
    {
        path: "/home",
        element: <Home />,
    }
]);