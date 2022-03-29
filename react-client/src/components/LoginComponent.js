import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import OtpInput from 'react-otp-input';
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import authService from "../services/auth.service";

const theme = createTheme();

export default function LoginComponent() {

    const [phoneNumber, setPhoneNumber] = useState("");
    const [otp, setOtp] = useState("");
    const navigate = useNavigate();
    const [otpSend, setOtpSend] = useState(true);

    const submitForm = () => {
        authService.verifyOtp(phoneNumber, otp).then(r => {
            console.log(r)
            navigate('/user')
        });

    };

    const sendOtp = () => {
        authService.sendOtp(phoneNumber).then(r => {
            console.log(r)
            setOtpSend(false)
        }).catch(function (error) {
            console.log(error.toJSON())
        });

    }

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="s">
                <CssBaseline/>
                <Box
                    sx={{
                        width: "s",
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{m: 1, bgcolor: 'secondary.main'}}>
                        <LockOutlinedIcon/>
                    </Avatar>
                    <Typography component="h1" variant="h5" marginBottom={4}>
                        Sign in
                    </Typography>
                    <Box style={{display: otpSend ? "block" : "none",}}
                         sx={{mt: 1}}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            value={phoneNumber}
                            onChange={e => setPhoneNumber(e.target.value)}
                            label="Phone number"
                        />
                        <Button
                            onClick={() => {
                                sendOtp()
                            }}
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}
                        >
                            Send OTP
                        </Button>
                    </Box>
                    <Box style={{display: otpSend ? "none" : "block"}}>
                        <Typography variant={"overline"}>
                            Enter the OTP you received
                        </Typography>
                        <OtpInput
                            value={otp}
                            onChange={e => setOtp(e)}
                            numInputs={6}
                            separator={<span style={{width: "8px"}}></span>}
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
                            //disabled={otp.length !== 6}
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}
                            onClick={sendOtp}
                        >
                            Resend OTP
                        </Button>
                        <Button
                            disabled={otp.length !== 6}
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 1, mb: 2}}
                            onClick={submitForm}
                        >
                            Verify & Proceed
                        </Button>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}