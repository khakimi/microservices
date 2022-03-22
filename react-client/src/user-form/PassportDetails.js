import * as React from 'react';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import * as PropTypes from "prop-types";
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import {DatePicker} from "@mui/lab";
import {useState} from "react";

import AdapterDateFns from "@mui/lab/AdapterDateFns";
import DesktopDatePicker from "@mui/lab/DesktopDatePicker";
import {InputLabel, MenuItem, Select, Stack} from "@mui/material";


LocalizationProvider.propTypes = {
    dateAdapter: PropTypes.any,
    children: PropTypes.node
};
export default function PassportDetails() {

    const [maritalStatus, setMaritalStatus] = React.useState([
        "Single",
        "Married",
        "Divorced",
        "Widowed"
    ]);
    const [selectedMaritalStatus, setSelectedMaritalStatus] = useState(null);

    function handleMaritalStatusChange(event) {
        setSelectedMaritalStatus(event.target.value);
    }

    const [birthDate, setBirthDate] = React.useState(null);
    const [issueDate, setIssueDate] = React.useState(null);
    const handleBirthDateChange = (newValue) => {
        setBirthDate(newValue);
    };
    const handleIssueDateChange = (newValue) => {
        setIssueDate(newValue);
    };
    return (
            <React.Fragment>
                <Typography variant="h6" gutterBottom>
                    Passport details
                </Typography>
                <Grid container spacing={3}>
                    <Grid item xs={12} >
                        <TextField
                            id="passportNumber"
                            label="Passport series and number"
                            fullWidth
                        />
                    </Grid>
                    <Grid item xs={12} >

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
                                            inputProps={{ ...params.inputProps, placeholder: "dd/mm/aaaa" }}
                                        />
                                    )}
                                />
                            </Stack>
                        </LocalizationProvider>


                    </Grid>
                    <Grid item xs={12} >
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
                                            inputProps={{ ...params.inputProps, placeholder: "dd/mm/yyyy" }}
                                        />
                                    )}
                                />
                            </Stack>
                        </LocalizationProvider>
                    </Grid>
                    <Grid item xs={12} >
                        <InputLabel variant={'standard'}>Marital status</InputLabel>
                        <Select
                            fullWidth
                            value={selectedMaritalStatus}
                            onChange={handleMaritalStatusChange}

                        >
                            {maritalStatus.map((value, index) => {
                                return <MenuItem value={value}>{value}</MenuItem>;
                            })}
                        </Select>


                    </Grid>
                </Grid>
            </React.Fragment>
    );
}