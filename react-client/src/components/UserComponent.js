import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import {User} from "../models/User";
import {InputLabel, MenuItem, Select, Stack, TextField, Toolbar} from "@mui/material";
import UserService from "../services/user.service";
import Grid from "@mui/material/Grid";
import LocalizationProvider from "@mui/lab/LocalizationProvider";
import AdapterDateFns from "@mui/lab/AdapterDateFns";
import DesktopDatePicker from "@mui/lab/DesktopDatePicker";
import {useState} from "react";
import * as PropTypes from "prop-types";
import NumericInput from "material-ui-numeric-input";
import userService from "../services/user.service";
import {useNavigate} from "react-router-dom";
import authService from "../services/auth.service";


const steps = ['Personal information', 'Passport details', 'Account information'];


const theme = createTheme();


LocalizationProvider.propTypes = {
    dateAdapter: PropTypes.any,
    children: PropTypes.node
};

let user: User

export default function UserComponent() {

    const [hasLoadedData, setHasLoadedData] = React.useState(false)

    const [activeStep, setActiveStep] = React.useState(0)

    const handleNext = () => {
        setActiveStep(activeStep + 1);
    }

    const handleSave = () => {
        user.name = name
        user.lastname = lastname
        user.patronymic = patronymic
        user.passportNumber = passportNumber
        user.birthDate = birthDate
        user.passportIssueDate = issueDate
        user.maritalStatus = selectedMaritalStatus
        user.monthlyExpensesOnAlimonyAndCredits = monthlyLoanCosts
        user.monthlyIncome = monthlyIncome
        user.incomeVerificationMethod = selectedIncomeVerificationMethod
        user.email = email
        userService.saveUser(user).then((r) => {
            console.log(r.data);
            setActiveStep(3);
        }).catch((error)=>{
            console.log(error.data)
        })
    }

    const handleBack = () => {
        setActiveStep(activeStep - 1);
    }

    const [name, setName] = React.useState("")

    const handleName = (newValue) => {
        setName(newValue.target.value)
    }

    const [lastname, setLastname] = React.useState("")

    const handleLastname = (newValue) => {
        setLastname(newValue.target.value)
    }
    const [patronymic, setPatronymic] = React.useState("")

    const handlePatronymic = (newValue) => {
        setPatronymic(newValue.target.value);
    }

    const [passportNumber, setPassportNumber] = React.useState("")

    const handlePassportNumber = (newValue) => {
        setPassportNumber(newValue.target.value)
    }
    const [maritalStatus, setMaritalStatus] = React.useState([
        "Single",
        "Married",
        "Divorced",
        "Widowed"
    ])
    const [selectedMaritalStatus, setSelectedMaritalStatus] = useState('');

    function handleMaritalStatusChange(event) {
        setSelectedMaritalStatus(event.target.value);
    }

    const [birthDate, setBirthDate] = React.useState('');

    const [issueDate, setIssueDate] = React.useState('');

    const handleBirthDateChange = (newValue) => {
        setBirthDate(newValue);
    }

    const handleIssueDateChange = (newValue) => {
        setIssueDate(newValue);
    }

    const validateEmail = (email) => {
        if (email.length === 0)
            return true;
        return String(email)
            .toLowerCase()
            .match(
                /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            );
    };

    const [email, setEmail] = useState('')

    const [emailError, setEmailError] = useState('')

    const onChangeEmail = (event) => {
        setEmail(event.target.value);
        if (validateEmail(email)) {
            setEmailError("");
        } else {
            setEmailError('invalid format');
        }
    };

    const [incomeVerificationMethod, setIncomeVerificationMethod] = React.useState([
        "Bank reference",
        "Reference from the employer"
    ]);

    const [selectedIncomeVerificationMethod, setSelectedIncomeVerificationMethod] = useState('');

    function handleIncomeVerificationMethodChange(event) {
        setSelectedIncomeVerificationMethod(event.target.value);
    }

    function handleMonthlyIncomeChange(newValue) {
        setMonthlyIncome(newValue.target.value);
    }

    const handleMonthlyLoanCosts = (newValue) => {
        setMonthlyLoanCosts(newValue.target.value);
    };

    const [monthlyIncome, setMonthlyIncome] = React.useState(0.0)

    const [monthlyLoanCosts, setMonthlyLoanCosts] = React.useState(0.0)

    const navigate = useNavigate()

    if (!hasLoadedData) {
        UserService.getUser().then((r) => {
            user = r.data;
            setName(user.name)
            setLastname(user.lastname)
            setPatronymic(user.patronymic)
            setPassportNumber(user.passportNumber)
            setBirthDate(user.birthDate)
            setIssueDate(user.passportIssueDate)
            setSelectedMaritalStatus(user.maritalStatus)
            setMonthlyIncome(user.monthlyIncome)
            setMonthlyLoanCosts(user.monthlyExpensesOnAlimonyAndCredits)
            setSelectedIncomeVerificationMethod(user.incomeVerificationMethod)
            setEmail(user.email)
            setHasLoadedData(true)
        }).catch((error)=>{
            console.log(error.data)
            navigate("/login")
        })
    }

    function logOut() {
        authService.logout()
        navigate('/login')
    }

    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <AppBar position="static" color={"primary"}>
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{flexGrow: 1}}>
                        User registry service
                    </Typography>
                    <Button color="inherit" onClick={logOut}>Logout</Button>
                </Toolbar>
            </AppBar>
            <Container component="main" maxWidth="sm" sx={{mb: 4}}>
                <Paper variant="outlined" sx={{my: {xs: 3, md: 6}, p: {xs: 2, md: 3}}}>
                    <Stepper activeStep={activeStep} sx={{pt: 3, pb: 5}}>
                        {steps.map((label) => (
                            <Step key={label}>
                                <StepLabel>{label}</StepLabel>
                            </Step>
                        ))}
                    </Stepper>
                    <React.Fragment>

                        {activeStep === steps.length ? (
                            <React.Fragment>
                                <Typography variant="h5" gutterBottom>
                                    Your data was successfully saved.
                                </Typography>
                                <Button onClick={handleBack} sx={{mt: 3, ml: 1}}>
                                    Back
                                </Button>
                            </React.Fragment>
                        ) : (
                            <React.Fragment>
                                <Box display={activeStep === 0 ? "block" : "none"}>
                                    <Typography variant="h6" gutterBottom>
                                        Personal Information
                                    </Typography>
                                    <Grid container spacing={3}>
                                        <Grid item xs={12}>
                                            <TextField
                                                label="First name"
                                                fullWidth
                                                value={name}
                                                onChange={handleName}
                                            />
                                        </Grid>
                                        <Grid item xs={12}>
                                            <TextField
                                                label="Last name"
                                                fullWidth
                                                value={lastname}
                                                onChange={handleLastname}
                                            />
                                        </Grid>
                                        <Grid item xs={12}>
                                            <TextField
                                                label="Patronymic"
                                                fullWidth
                                                value={patronymic}
                                                onChange={handlePatronymic}
                                            />
                                        </Grid>
                                    </Grid>
                                </Box>
                                <Box display={activeStep === 1 ? "block" : "none"}>
                                    <Typography variant="h6" gutterBottom>
                                        Passport details
                                    </Typography>
                                    <Grid container spacing={3}>
                                        <Grid item xs={12}>
                                            <TextField
                                                id="passportNumber"
                                                label="Passport series and number"
                                                value={passportNumber}
                                                onChange={handlePassportNumber}
                                                fullWidth
                                            />
                                        </Grid>
                                        <Grid item xs={12}>
                                            <LocalizationProvider dateAdapter={AdapterDateFns}>
                                                <Stack spacing={3}>
                                                    <DesktopDatePicker
                                                        label="Date of birth"
                                                        inputFormat="dd/MM/yyyy"
                                                        value={birthDate}
                                                        onChange={handleBirthDateChange}
                                                        renderInput={(params) => (
                                                            <TextField
                                                                {...params}
                                                                inputProps={{
                                                                    ...params.inputProps,
                                                                    placeholder: "dd/mm/aaaa"
                                                                }}
                                                            />
                                                        )}
                                                    />
                                                </Stack>
                                            </LocalizationProvider>
                                        </Grid>
                                        <Grid item xs={12}>
                                            <LocalizationProvider dateAdapter={AdapterDateFns}>
                                                <Stack spacing={3}>
                                                    <DesktopDatePicker
                                                        label="Date of issue"
                                                        inputFormat="dd/MM/yyyy"
                                                        value={issueDate}
                                                        onChange={handleIssueDateChange}
                                                        renderInput={(params) => (
                                                            <TextField
                                                                {...params}
                                                                inputProps={{
                                                                    ...params.inputProps,
                                                                    placeholder: "dd/mm/aaaa"
                                                                }}
                                                            />
                                                        )}
                                                    />
                                                </Stack>
                                            </LocalizationProvider>
                                        </Grid>
                                        <Grid item xs={12}>
                                            <InputLabel variant={'standard'}>Marital status</InputLabel>
                                            <Select
                                                fullWidth
                                                value={selectedMaritalStatus}
                                                defaultValue={null}
                                                onChange={handleMaritalStatusChange}
                                            >
                                                {maritalStatus.map((value, index) => {
                                                    return <MenuItem value={value}>{value}</MenuItem>;
                                                })}
                                            </Select>
                                        </Grid>
                                    </Grid>
                                </Box>
                                <Box display={activeStep === 2 ? "block" : "none"}>
                                    <Typography variant="h6" gutterBottom>
                                        Account information
                                    </Typography>
                                    <Grid container spacing={3}>
                                        <Grid item xs={12}>
                                            <InputLabel variant={'standard'}>Monthly income</InputLabel>
                                            <NumericInput
                                                fullWidth
                                                precision={2}
                                                decimalChar=','
                                                thousandChar='.'
                                                value={monthlyIncome}
                                                onChange={handleMonthlyIncomeChange}
                                            />
                                        </Grid>
                                        <Grid item xs={12}>
                                            <InputLabel variant={'standard'}>Monthly loan and alimony costs</InputLabel>
                                            <NumericInput
                                                fullWidth
                                                precision={2}
                                                decimalChar=','
                                                thousandChar='.'
                                                value={monthlyLoanCosts}
                                                onChange={handleMonthlyLoanCosts}
                                            />
                                        </Grid>
                                        <Grid item xs={12}>
                                            <InputLabel variant={'standard'}>Income verification method</InputLabel>
                                            <Select
                                                fullWidth
                                                value={selectedIncomeVerificationMethod}
                                                defaultValue={null}
                                                onChange={handleIncomeVerificationMethodChange}
                                            >
                                                {incomeVerificationMethod.map((value, index) => {
                                                    return <MenuItem value={value}>{value}</MenuItem>;
                                                })}
                                            </Select>
                                        </Grid>

                                        <Grid item xs={12}>
                                            <TextField
                                                label={'E-mail'}
                                                fullWidth
                                                type="text"
                                                name="email"
                                                value={email}
                                                onChange={onChangeEmail}
                                                helperText={emailError}
                                                error={!validateEmail(email)}

                                            />
                                        </Grid>
                                    </Grid>
                                </Box>
                                <Box sx={{display: 'flex', justifyContent: 'flex-end'}}>
                                    <Button
                                        variant="contained"
                                        color={"secondary"}
                                        sx={{mt: 3, ml: 1}}
                                        onClick={handleSave}
                                        disabled={emailError!==""}
                                    >
                                        Submit
                                    </Button>
                                    {activeStep !== 0 && (
                                        <Button onClick={handleBack} sx={{mt: 3, ml: 1}}>
                                            Back
                                        </Button>
                                    )}
                                    {activeStep !== steps.length - 1 && (
                                        <Button
                                            variant="contained"
                                            onClick={handleNext}
                                            sx={{mt: 3, ml: 1}}
                                        >
                                            Next
                                        </Button>

                                    )}
                                </Box>
                            </React.Fragment>
                        )}
                    </React.Fragment>
                </Paper>
            </Container>
        </ThemeProvider>
    );
}