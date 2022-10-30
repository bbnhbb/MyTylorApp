import {
    createBrowserRouter,
} from "react-router-dom";
import Dress from "../Pages/Dress";
import Home from "../Pages/Home";
import LoginRegister from "../Pages/LoginRegister";


export const router = createBrowserRouter([
    {
        path: "/MyTylorApp/",
        element: <LoginRegister />,
    },
    {
        path: "/MyTylorApp/home",
        element: <Home />,
    },
    {
        path: "/MyTylorApp/dress",
        element: <Dress />,
    }
]);