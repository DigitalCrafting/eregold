import {createBrowserRouter, Navigate} from "react-router-dom";
import {LoginPage} from "../modules/login/LoginPage";
import {RegistrationPage} from "../modules/registration/RegistrationPage";
import {Dashboard} from "../modules/dashboard/Dashboard";
import {AccountsList} from "../modules/accounts/AccountsList";
import {AccountCreate} from "../modules/accounts/AccountCreate";
import {AccountDetails} from "../modules/accounts/AccountDetails";

const router = createBrowserRouter([
    {path: '', element: <Navigate to="/ui"/>},
    {path: 'login', element: <LoginPage/>},
    {path: 'register', element: <RegistrationPage/>},
    {
        path: 'ui',
        element: <Dashboard/>,
        children: [
            {path: '', element: <Navigate to="/ui/accounts"/>},
            {path: 'accounts', element: <AccountsList/>},
            {path: 'account/new', element: <AccountCreate/>},
            {path: 'account/:id', element: <AccountDetails/>},
        ]
    }
]);

export default router;

