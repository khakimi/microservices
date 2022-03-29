import React from "react";
import {Route, Routes} from "react-router-dom";
import UserComponent from "./components/UserComponent";
import LoginComponent from "./components/LoginComponent";
import {Navigate} from "react-router";
import authHeader from "./services/auth-header";


function isLoggedIn() {
    return authHeader().Authorization === false;
}

export default function App() {
    return (
        <div>
            <Routes>
                <Route path="/login" element={<LoginComponent/>}/>
                <Route path="/user" element={<UserComponent/>}/>
                <Route path="*"
                       element={<Navigate to={isLoggedIn() ? "/login" : "/user"}/>}/>
            </Routes>
        </div>
    );
}