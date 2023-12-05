import React, {Component} from 'react';
import { Route, Navigate } from "react-router-dom";
import userContext from "../context/user-context";

// @ts-ignore
const GuardedRoute = ({ element }) => {
    const isLoggedIn: boolean = userContext.isLoggedIn;
    return isLoggedIn ? element : <Navigate to='/login' />
}


export default GuardedRoute;