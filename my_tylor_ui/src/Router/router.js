import {
    createBrowserRouter,
} from "react-router-dom";
import Dress from "../Pages/Dress";
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
    },
    {
        path: "/dress",
        element: <Dress />,
    }
]);