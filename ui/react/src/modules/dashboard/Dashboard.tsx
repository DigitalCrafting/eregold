import {TopBar} from "../top-bar/TopBar";
import {DashboardCard} from "./DashboardCard";
import {Outlet} from "react-router-dom";

export function Dashboard() {
    return (
        <>
            <TopBar/>
            <DashboardCard>
                <Outlet/>
            </DashboardCard>
        </>
    );
}