import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import ReactPhoneInput from 'react-phone-input-material-ui';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import OtpInput from 'react-otp-input';
import {useState} from "react";


const theme = createTheme();

export default function SignIn() {
    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        console.log({
            phoneNumber: data.get('phoneNumber')
        });
    };
    const [value, setValue] = useState()

    const [signInButtonState, setSignInButtonState] = useState(true);

    const handleSignInButtonState = ()=> setSignInButtonState(false);

    const [verifyButtonState , setVerifyButtonState ] = useState(true);

    //const handleVerifyButtonState = (code)=> ;

    const [code, setCode] = useState("");

    const handleChange = (code) => {
        setVerifyButtonState(!(code.length == 6))
        setCode(code)
    };
    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="s">
                <CssBaseline />
                <Box
                    sx={{
                        width: "s",
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5" marginBottom={4}>
                        Sign in
                    </Typography>
                    <Box style={{display: signInButtonState?"block" : "none",}} component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>

                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="phoneNumber"
                            label="Phone Number"
                            name="phoneNumber"
                            autoComplete="phoneNumber"
                            autoFocus
                        />
                        <Button
                            onClick={handleSignInButtonState}
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Send OTP
                        </Button>


                    </Box>
                    <Box style={{display: signInButtonState?"none" : "block",}}>
                        <Typography variant={"overline"}>
                            Enter the OTP you received
                        </Typography>
                        <OtpInput

                            value={code}
                            onChange={handleChange}
                            numInputs={6}
                            separator={<span style={{ width: "8px" }}></span>}
                            isInputNum={true}
                            inputStyle={{
                                border: "1px solid",
                                borderRadius: "8px",
                                width: "52px",
                                height: "52px",
                                fontSize: "12px",
                                color: "#000",
                                fontWeight: "400",
                                caretColor: "blue"
                            }}
                        />
                        <Button
                            disabled={verifyButtonState}
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Verify & Proceed
                        </Button>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}