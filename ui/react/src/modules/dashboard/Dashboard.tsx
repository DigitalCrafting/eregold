import {TopBar} from "../top-bar/TopBar";
import {DashboardCard} from "./DashboardCard";
import {Outlet, useNavigate} from "react-router-dom";
import userContext from "../../context/user-context";
import EregoldUserContext from "../../context/eregold-user-context";
import useAccounts from "../../hooks/useAccounts";

export function Dashboard() {
    const navigate = useNavigate();

    if (!userContext.isLoggedIn) {
        navigate('login');
    }

    const {accounts, error, isLoading, setAccounts, setError} = useAccounts();

    return (
        <EregoldUserContext.Provider value={{accounts}}>
            <TopBar/>
            <DashboardCard>
                <Outlet/>
            </DashboardCard>
        </EregoldUserContext.Provider>
    );
}