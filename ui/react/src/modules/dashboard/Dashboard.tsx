import {TopBar} from "../top-bar/TopBar";
import {DashboardCard} from "./DashboardCard";
import {Outlet, useNavigate} from "react-router-dom";
import userContext from "../../context/user-context";

export function Dashboard() {
    const navigate = useNavigate();

    if (!userContext.isLoggedIn) {
        navigate('login');
    }

    return (
        <>
            <TopBar/>
            <DashboardCard>
                <Outlet/>
            </DashboardCard>
        </>
    );
}