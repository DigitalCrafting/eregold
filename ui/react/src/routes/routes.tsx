import {createBrowserRouter, Navigate} from "react-router-dom";
import {LoginPage} from "../modules/login/LoginPage";
import {RegistrationPage} from "../modules/registration/RegistrationPage";
import {Dashboard} from "../modules/dashboard/Dashboard";
import {AccountsList} from "../modules/accounts/AccountsList";
import {AccountCreate} from "../modules/accounts/AccountCreate";
import {AccountDetails} from "../modules/accounts/AccountDetails";
import GuardedRoute from "./GuardedRoute";
import {OwnDeposit} from "../modules/deposit/OwnDeposit";
import {OwnTransfer} from "../modules/transfer/OwnTransfer";

const router = createBrowserRouter([
    {path: '', element: <Navigate to="/ui"/>},
    {path: 'login', element: <LoginPage/>},
    {path: 'register', element: <RegistrationPage/>},
    {
        path: 'ui',
        element: <Dashboard/>,
        children: [
            {path: '', element: <Navigate to="/ui/accounts"/>},
            {path: 'accounts', element: <GuardedRoute element={<AccountsList/>} />},
            {path: 'account/new', element: <GuardedRoute element={<AccountCreate/>} />},
            {path: 'account/:accountNumber', element: <GuardedRoute element={<AccountDetails/>} />},
            {path: 'deposit/:accountNumber?', element: <GuardedRoute element={<OwnDeposit/>} />},
            {path: 'transfer/:accountNumber?', element: <GuardedRoute element={<OwnTransfer/>} />},
        ]
    }
]);

export default router;

