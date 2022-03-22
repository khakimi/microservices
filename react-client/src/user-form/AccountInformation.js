import * as React from 'react';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import NumericInput from 'material-ui-numeric-input';
import {Input, InputLabel, MenuItem, Select} from "@mui/material";
import {useState} from "react";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";

const validEmail = (value) => {
    if (!validator.isEmail(value)) {
        return (
            <Box className="alert alert-danger" role="alert">
                This is not a valid email.
            </Box>
        );
    }
};





export default function AccountInformation() {
    const [email, setEmail] = useState("");
    const onChangeEmail = (e) => {
        const email = e.target.value;
        setEmail(email);
    };

    const [incomeVerificationMethod, setIncomeVerificationMethod] = React.useState([
        "Bank reference",
        "Reference from the employer"
    ]);
    const [selectedIncomeVerificationMethod, setSelectedIncomeVerificationMethod] = useState(incomeVerificationMethod[0]);

    function handleIncomeVerificationMethodChange(event) {
        setSelectedIncomeVerificationMethod(event.target.value);
    }

    const handleMonthlyIncomeChange = (newValue) => {
        setMonthlyIncome(newValue);
    };
    const handleMonthlyLoanCosts = (newValue) => {
        setMonthlyIncome(newValue);
    };

    const [monthlyIncome, setMonthlyIncome] = React.useState(0.0)
    const [monthlyLoanCosts, setMonthlyLoanCosts] = React.useState(0.0)

    return (
        <React.Fragment>
            <Typography variant="h6" gutterBottom>
                Account information
            </Typography>
            <Grid container spacing={3}>
                <Grid item xs={12} >
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
                <Grid item xs={12} >
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
                <Grid item xs={12} >
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
                        validations={validEmail}
                        error
                        helperText={'Invalid e-mail'}

                    />
                </Grid>


            </Grid>
        </React.Fragment>
    );
}