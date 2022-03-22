import ReactDOM from 'react-dom';
import CssBaseline from '@mui/material/CssBaseline';
import { ThemeProvider } from '@mui/material/styles';
import theme from './theme';
import SignIn from "./SignIn";
import React from "react";
import { BrowserRouter } from "react-router-dom";
import UserDataPage from "./user-form/UserDataPage";
ReactDOM.render(

    <BrowserRouter>
        <ThemeProvider theme={theme}>
            <UserDataPage/>
        </ThemeProvider>

    </BrowserRouter>,
    document.getElementById("root")
);
//serviceWorker.unregister();
